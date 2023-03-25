package borakdmytro.trspo_lab2.controller;

import borakdmytro.trspo_lab2.model.Crop;
import borakdmytro.trspo_lab2.service.CropService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crops")
public class CropController {
    // get /id - get
    // get / - get all
    // post / - add
    // put /id - edit
    // delete /id - delete
    private final CropService cropService;

    @Autowired
    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Crop get(@PathVariable int id) {
        return cropService.getCropById(id).orElseThrow();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Crop> getAll() {
        return cropService.getAllCrops();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody Crop crop) {
        cropService.saveCrop(crop);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody Crop crop) {
        Crop newCrop = cropService.getCropById(id).orElseThrow();
        newCrop.setName(crop.getName());
        cropService.updateCrop(newCrop);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        cropService.deleteCropById(id);
    }
}
