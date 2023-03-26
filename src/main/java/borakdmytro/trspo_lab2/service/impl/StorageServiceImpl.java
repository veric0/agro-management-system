package borakdmytro.trspo_lab2.service.impl;

import borakdmytro.trspo_lab2.model.Storage;
import borakdmytro.trspo_lab2.model.StorageDetails;
import borakdmytro.trspo_lab2.repository.CropRepository;
import borakdmytro.trspo_lab2.repository.FarmerRepository;
import borakdmytro.trspo_lab2.repository.StorageDetailsRepository;
import borakdmytro.trspo_lab2.repository.StorageRepository;
import borakdmytro.trspo_lab2.service.StorageService;
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
    public void saveStorage(Storage storage) {
        Storage newStorage = new Storage();
        newStorage.setName(storage.getName());
        newStorage.setOwner(farmerRepository.findById(storage.getOwner().getId()).orElseThrow());
        newStorage.setMaxVolume(storage.getMaxVolume());
    }

    @Override
    public void updateStorage(Storage storage) {
        Storage newStorage = storageRepository.findById(storage.getId()).orElseThrow();
        newStorage.setName(storage.getName());
        newStorage.setOwner(farmerRepository.findById(storage.getOwner().getId()).orElseThrow());
        newStorage.setMaxVolume(storage.getMaxVolume());
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
    public void addToStorage(StorageDetails storageDetails, double addVolume) {
//        Storage storage = storageDetails.getStorage();
        Storage storage = storageRepository.findById(storageDetails.getStorage().getId()).orElseThrow();
        if (storage.getFreeSpace() > 0 && storage.getFreeSpace() + addVolume <= storage.getMaxVolume()) {
            if (addVolume <= storage.getFreeSpace()) {
                storageDetails.setVolume(storageDetails.getVolume() + addVolume);
            } else {
                storageDetails.setVolume(storageDetails.getVolume() + storage.getFreeSpace());
            }
            storageDetailsRepository.save(storageDetails);
        }
    }

    @Override
    public void removeFromStorage(StorageDetails details, double removeVolume) {
//        Storage storage = details.getStorage();
        Storage storage = storageRepository.findById(details.getStorage().getId()).orElseThrow();
        if (removeVolume <= storage.getMaxVolume() - storage.getFreeSpace()) {
            if (removeVolume < details.getVolume()) {
                details.setVolume(details.getVolume() - removeVolume);
                storageDetailsRepository.save(details);
            } else {
//                details.setVolume(0);
                storageDetailsRepository.deleteByStorageIdAndCropId(details.getStorage().getId(), details.getCrop().getId());
            }
        }
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
