package borakdmytro.trspo_lab2.service.impl;

import borakdmytro.trspo_lab2.model.Farmer;
import borakdmytro.trspo_lab2.repository.FarmerRepository;
import borakdmytro.trspo_lab2.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceImpl implements FarmerService {
    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public Optional<Farmer> getFarmerById(int farmerId) {
        return farmerRepository.findById(farmerId);
    }

    @Override
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    @Override
    public void saveFarmer(Farmer farmer) {
        Farmer newFarmer = new Farmer();
        newFarmer.setName(farmer.getName());
        farmerRepository.save(newFarmer);
    }

    @Override
    public void updateFarmer(Farmer farmer) {
        farmerRepository.save(farmer);
    }

    @Override
    public void deleteFarmerById(int farmerId) {
        farmerRepository.deleteById(farmerId);
    }
}
