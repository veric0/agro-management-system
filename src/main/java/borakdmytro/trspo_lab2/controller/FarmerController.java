package borakdmytro.trspo_lab2.controller;

import borakdmytro.trspo_lab2.dto.FarmerDTO;
import borakdmytro.trspo_lab2.dto.mapper.FarmerMapper;
import borakdmytro.trspo_lab2.model.Farmer;
import borakdmytro.trspo_lab2.service.FarmerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmers")
public class FarmerController {
    private final FarmerService farmerService;

    @Autowired
    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FarmerDTO get(@PathVariable int id) {
        Farmer farmer = farmerService.getFarmerById(id).orElseThrow();
        return FarmerMapper.toDTO(farmer);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FarmerDTO> getAll() {
        return farmerService.getAllFarmers()
                .stream()
                .map(FarmerMapper::toDTO)
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody FarmerDTO farmerDTO) {
        Farmer newFarmer = FarmerMapper.toFarmer(farmerDTO);
        farmerService.saveFarmer(newFarmer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody FarmerDTO farmerDTO) {
        Farmer farmer = farmerService.getFarmerById(id).orElseThrow();
        farmer.setName(farmerDTO.getFarmerName());
        farmerService.updateFarmer(farmer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        farmerService.deleteFarmerById(id);
    }
}
