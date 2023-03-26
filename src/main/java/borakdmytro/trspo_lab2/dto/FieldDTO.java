package borakdmytro.trspo_lab2.dto;

import lombok.Data;

@Data
public class FieldDTO {
    private int fieldId;
    private double fieldArea;
    private String fieldName;
    private int ownerId;
    private int cropId;
}
