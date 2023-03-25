package borakdmytro.trspo_lab2.service;

import borakdmytro.trspo_lab2.model.Crop;

import java.util.List;
import java.util.Optional;

public interface CropService {
    Optional<Crop> getCropById(int cropId);
    List<Crop> getAllCrops();
    void saveCrop(Crop crop);
    void updateCrop(Crop crop);
    void deleteCropById(int cropId);
}
