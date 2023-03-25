package borakdmytro.trspo_lab2.service.impl;

import borakdmytro.trspo_lab2.model.Crop;
import borakdmytro.trspo_lab2.repository.CropRepository;
import borakdmytro.trspo_lab2.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropServiceImpl implements CropService {
    private final CropRepository cropRepository;

    @Autowired
    public CropServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public Optional<Crop> getCropById(int cropId) {
        return cropRepository.findById(cropId);
    }

    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Override
    public void saveCrop(Crop crop) {
        Crop newCrop = new Crop();
        newCrop.setName(crop.getName());
        cropRepository.save(newCrop);
    }

    @Override
    public void updateCrop(Crop crop) {
        cropRepository.save(crop);
    }

    @Override
    public void deleteCropById(int cropId) {
        cropRepository.deleteById(cropId);
    }
}
