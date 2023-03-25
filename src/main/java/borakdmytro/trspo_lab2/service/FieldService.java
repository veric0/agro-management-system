package borakdmytro.trspo_lab2.service;

import borakdmytro.trspo_lab2.model.Field;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    Optional<Field> getFieldById(int fieldId);
    List<Field> getAllFields();
    List<Field> getAllFarmersFields(int farmerId);
    List<Field> getAllFieldsWithThatCrop(int cropId);
    List<Field> getAllFarmersFieldsWithThatCrop(int farmerId, int cropId);
    void saveField(Field field);
    void updateField(Field field);
    void deleteFieldById(int fieldId);
}
