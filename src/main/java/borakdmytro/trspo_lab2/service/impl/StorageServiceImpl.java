package borakdmytro.trspo_lab2.service.impl;

import borakdmytro.trspo_lab2.model.Storage;
import borakdmytro.trspo_lab2.model.StorageDetails;
import borakdmytro.trspo_lab2.repository.CropRepository;
import borakdmytro.trspo_lab2.repository.FarmerRepository;
import borakdmytro.trspo_lab2.repository.StorageDetailsRepository;
import borakdmytro.trspo_lab2.repository.StorageRepository;
import borakdmytro.trspo_lab2.service.StorageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final StorageDetailsRepository storageDetailsRepository;
    private final FarmerRepository farmerRepository;
    private final CropRepository cropRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, StorageDetailsRepository storageDetailsRepository, FarmerRepository farmerRepository, CropRepository cropRepository) {
        this.storageRepository = storageRepository;
        this.storageDetailsRepository = storageDetailsRepository;
        this.farmerRepository = farmerRepository;
        this.cropRepository = cropRepository;
    }

    @Override
    public Optional<Storage> getStorageById(int storageId) {
        Optional<Storage> storage = storageRepository.findById(storageId);
        storage.ifPresent(this::calculateFreeSpace);
        return storage;
    }

    @Override
    public List<Storage> getAllStorages() {
        List<Storage> list = storageRepository.findAll();
        list.forEach(this::calculateFreeSpace);
        return list;
    }

    @Override
    public List<Storage> getAllFarmersStorages(int farmerId) {
        List<Storage> list = storageRepository.findAllByOwnerId(farmerId);
        list.forEach(this::calculateFreeSpace);
        return list;
    }

    @Override
    @Transactional
    public void saveStorage(Storage storage) {
        Storage newStorage = new Storage();
        newStorage.setName(storage.getName());
        newStorage.setOwner(farmerRepository.findById(storage.getOwner().getId()).orElseThrow());
        newStorage.setMaxVolume(storage.getMaxVolume());
        storageRepository.save(storage);
    }

    @Override
    @Transactional
    public void updateStorage(Storage storage) {
        Storage newStorage = storageRepository.findById(storage.getId()).orElseThrow();
        newStorage.setName(storage.getName());
        newStorage.setOwner(farmerRepository.findById(storage.getOwner().getId()).orElseThrow());
        newStorage.setMaxVolume(storage.getMaxVolume());
        storageRepository.save(storage);
    }

    @Override
    public void deleteStorageById(int storageId) {
        storageRepository.deleteById(storageId);
    }


    @Override
    public List<StorageDetails> getAllStorageDetails(int storageId) {
        return storageDetailsRepository.findAllByStorageId(storageId);
    }

    @Override
    public StorageDetails getStorageCropDetails(int storageId, int cropId) {
        return storageDetailsRepository
                .findByStorageIdAndCropId(storageId, cropId)
                .orElseGet(() -> {
                    StorageDetails emptyStorageDetails = new StorageDetails();
                    emptyStorageDetails.setStorage(storageRepository.findById(storageId).orElseThrow());
                    emptyStorageDetails.setCrop(cropRepository.findById(cropId).orElseThrow());
                    emptyStorageDetails.setVolume(0);
                    return emptyStorageDetails;
                });
    }

    @Override
    @Transactional
    public StorageDetails addToStorage(StorageDetails storageDetails, double addVolume) {
//        Storage storage = storageDetails.getStorage();
        Storage storage = storageRepository.findById(storageDetails.getStorage().getId()).orElseThrow();
        calculateFreeSpace(storage);
        if (storage.getFreeSpace() > 0) {
            if (addVolume <= storage.getFreeSpace()) { // якщо є вільне місце то заповнюємо
                storageDetails.setVolume(storageDetails.getVolume() + addVolume);
                addVolume = 0;
            } else { // якщо немає, то заповнюємо усе що можна
                storageDetails.setVolume(storageDetails.getVolume() + storage.getFreeSpace());
                addVolume -= storage.getFreeSpace();
            }
            storageDetailsRepository.save(storageDetails);
        }
        storageDetails.setVolume(addVolume);
        return storageDetails; // повертає скільки залишилось зайвого зерна
    }

    @Override
    @Transactional
    public StorageDetails removeFromStorage(StorageDetails details, double removeVolume) {
//        Storage storage = details.getStorage();
        Storage storage = storageRepository.findById(details.getStorage().getId()).orElseThrow();
        calculateFreeSpace(storage);
        if (removeVolume < details.getVolume()) { // якщо на складі більше зерна ніж треба видалити
            details.setVolume(details.getVolume() - removeVolume);
            removeVolume = 0;
            storageDetailsRepository.save(details);
        } else { // якщо на складі менше зерна ніж треба видалити
            removeVolume -= details.getVolume();
            storageDetailsRepository.deleteByStorageIdAndCropId(details.getStorage().getId(), details.getCrop().getId());
        }
        details.setVolume(removeVolume);
        return details; // повертає скільки не вистачило зерна в складі щоб видалити
    }


    private void calculateFreeSpace(Storage storage) {
        double freeSpace = storage.getMaxVolume();
        List<StorageDetails> list = storageDetailsRepository.findAllByStorageId(storage.getId());
        for(var detail: list) {
            freeSpace -= detail.getVolume();
        }
        if (freeSpace < 0) {
            freeSpace = 0;
        }
        storage.setFreeSpace(freeSpace);
    }
}
