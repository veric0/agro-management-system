package borakdmytro.trspo_lab2.service;

import borakdmytro.trspo_lab2.model.Farmer;

import java.util.List;
import java.util.Optional;

public interface FarmerService {
    Optional<Farmer> getFarmerById(int farmerId);
    List<Farmer> getAllFarmers();
    void saveFarmer(Farmer farmer);
    void updateFarmer(Farmer farmer);
    void deleteFarmerById(int farmerId);
}
