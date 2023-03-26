package borakdmytro.trspo_lab2.dto.mapper;

import borakdmytro.trspo_lab2.dto.CropDTO;
import borakdmytro.trspo_lab2.model.Crop;

public class CropMapper {
    public static CropDTO toDTO(Crop crop) {
        CropDTO cropDTO = new CropDTO();
        cropDTO.setCropId(crop.getId());
        cropDTO.setCropName(crop.getName());
        return cropDTO;
    }

    public static Crop toCrop(CropDTO cropDTO) {
        Crop crop = new Crop();
        crop.setName(cropDTO.getCropName());
        return crop;
    }
}