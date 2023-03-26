package borakdmytro.trspo_lab2.service;

import borakdmytro.trspo_lab2.model.Storage;
import borakdmytro.trspo_lab2.model.StorageDetails;

import java.util.List;
import java.util.Optional;

public interface StorageService {
    Optional<Storage> getStorageById(int storageId);
    List<Storage> getAllStorages();
    List<Storage> getAllFarmersStorages(int farmerId);
    void saveStorage(Storage storage);
    void updateStorage(Storage storage);
    void deleteStorageById(int storageId);

    List<StorageDetails> getAllStorageDetails(int storageId);
    StorageDetails getStorageCropDetails(int storageId, int cropId);
    void addToStorage(StorageDetails storageDetails, double addVolume);
    void removeFromStorage(StorageDetails storageDetails, double removeVolume);
}
