package borakdmytro.trspo_lab2.dto.mapper;

import borakdmytro.trspo_lab2.dto.FarmerDTO;
import borakdmytro.trspo_lab2.model.Farmer;

public class FarmerMapper {
    public static FarmerDTO toDTO(Farmer farmer) {
        FarmerDTO farmerDTO = new FarmerDTO();
        farmerDTO.setFarmerId(farmer.getId());
        farmerDTO.setFarmerName(farmer.getName());
        return farmerDTO;
    }

    public static Farmer toFarmer(FarmerDTO farmerDTO) {
        Farmer farmer = new Farmer();
        farmer.setName(farmerDTO.getFarmerName());
        return farmer;
    }
}
