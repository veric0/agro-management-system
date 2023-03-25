package borakdmytro.trspo_lab2.service.impl;

import borakdmytro.trspo_lab2.model.Crop;
import borakdmytro.trspo_lab2.model.Farmer;
import borakdmytro.trspo_lab2.model.Field;
import borakdmytro.trspo_lab2.repository.CropRepository;
import borakdmytro.trspo_lab2.repository.FarmerRepository;
import borakdmytro.trspo_lab2.repository.FieldRepository;
import borakdmytro.trspo_lab2.service.FieldService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmerRepository farmerRepository;
    private final CropRepository cropRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, FarmerRepository farmerRepository, CropRepository cropRepository) {
        this.fieldRepository = fieldRepository;
        this.farmerRepository = farmerRepository;
        this.cropRepository = cropRepository;
    }

    @Override
    public Optional<Field> getFieldById(int fieldId) {
        return fieldRepository.findById(fieldId);
    }

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public List<Field> getAllFarmersFields(int farmerId) {
        return fieldRepository.findAllByOwnerId(farmerId);
    }

    @Override
    public List<Field> getAllFieldsWithThatCrop(int cropId) {
        return fieldRepository.findAllByCropId(cropId);
    }

    @Override
    public List<Field> getAllFarmersFieldsWithThatCrop(int farmerId, int cropId) {
        return fieldRepository.findAllByOwnerIdAndCropId(farmerId, cropId);
    }

    @Override
    @Transactional
    public void saveField(Field field) {
        Field newField = new Field();
        newField.setName(field.getName());
        newField.setArea(field.getArea());
        Farmer owner = farmerRepository.findById(field.getOwner().getId()).orElseThrow();
        Crop crop = cropRepository.findById(field.getCrop().getId()).orElseThrow();
        newField.setOwner(owner);
        newField.setCrop(crop);
        fieldRepository.save(newField);
    }

    @Override
    @Transactional
    public void updateField(Field field) {
        Field newField = fieldRepository.findById(field.getId()).orElseThrow();
        newField.setName(field.getName());
        newField.setArea(field.getArea());
        Farmer owner = farmerRepository.findById(field.getOwner().getId()).orElseThrow();
        Crop crop = cropRepository.findById(field.getCrop().getId()).orElseThrow();
        newField.setOwner(owner);
        newField.setCrop(crop);
        fieldRepository.save(newField);
    }

    @Override
    public void deleteFieldById(int fieldId) {
        fieldRepository.deleteById(fieldId);
    }
}
