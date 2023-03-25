package borakdmytro.trspo_lab2.controller;

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
    public Farmer get(@PathVariable int id) {
        return farmerService.getFarmerById(id).orElseThrow();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Farmer> getAll() {
        return farmerService.getAllFarmers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody Farmer farmer) {
        farmerService.saveFarmer(farmer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody Farmer farmer) {
        Farmer newFarmer = farmerService.getFarmerById(id).orElseThrow();
        newFarmer.setName(farmer.getName());
        farmerService.updateFarmer(newFarmer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        farmerService.deleteFarmerById(id);
    }
}
