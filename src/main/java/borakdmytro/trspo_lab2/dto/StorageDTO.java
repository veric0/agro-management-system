package borakdmytro.trspo_lab2.dto;

import lombok.Data;

@Data
public class StorageDTO {
    private int storageId;
    private String storageName;
    private int ownerId;
    private double maxVolume;
    private double freeVolume;
}
