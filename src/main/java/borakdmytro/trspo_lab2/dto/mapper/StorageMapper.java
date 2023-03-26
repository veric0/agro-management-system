package borakdmytro.trspo_lab2.dto.mapper;

import borakdmytro.trspo_lab2.dto.StorageDTO;
import borakdmytro.trspo_lab2.model.Storage;

public class StorageMapper {
    public static StorageDTO toDTO(Storage storage) {
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setStorageId(storage.getId());
        storageDTO.setOwnerId(storage.getOwner().getId());
        storageDTO.setMaxVolume(storage.getMaxVolume());
        storageDTO.setFreeVolume(storage.getFreeSpace());
        return storageDTO;
    }

    public static Storage toStorage(StorageDTO storageDTO) {
        Storage storage = new Storage();
        storage.setMaxVolume(storageDTO.getMaxVolume());
        return storage;
    }
}
