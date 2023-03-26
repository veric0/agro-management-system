package borakdmytro.trspo_lab2.dto.mapper;

import borakdmytro.trspo_lab2.dto.StorageDetailsDTO;
import borakdmytro.trspo_lab2.model.StorageDetails;

public class StorageDetailsMapper {
    public static StorageDetailsDTO toDTO(StorageDetails storageDetails) {
        StorageDetailsDTO dto = new StorageDetailsDTO();
        dto.setStorageId(storageDetails.getStorage().getId());
        dto.setCropId(storageDetails.getCrop().getId());
        dto.setVolume(storageDetails.getVolume());
        return dto;
    }

    public static StorageDetails toStorageDetails(StorageDetailsDTO dto) {
        StorageDetails storageDetails = new StorageDetails();
        storageDetails.setVolume(dto.getVolume());
        return storageDetails;
    }
}
