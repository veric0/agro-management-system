package borakdmytro.trspo_lab2.dto.mapper;

import borakdmytro.trspo_lab2.dto.FieldDTO;
import borakdmytro.trspo_lab2.model.Field;

public class FieldMapper {
    public static FieldDTO toDTO(Field field) {
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldId(field.getId());
        fieldDTO.setFieldArea(field.getArea());
        fieldDTO.setFieldName(field.getName());
        fieldDTO.setOwnerId(field.getOwner().getId());
        fieldDTO.setCropId(field.getCrop().getId());
        return fieldDTO;
    }

    public static Field toField(FieldDTO fieldDTO) {
        Field field = new Field();
        field.setArea(fieldDTO.getFieldArea());
        field.setName(fieldDTO.getFieldName());
        return field;
    }

}
