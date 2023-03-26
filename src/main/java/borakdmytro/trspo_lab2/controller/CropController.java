package borakdmytro.trspo_lab2.controller;

import borakdmytro.trspo_lab2.dto.CropDTO;
import borakdmytro.trspo_lab2.dto.mapper.CropMapper;
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
    public CropDTO get(@PathVariable int id) {
        Crop crop = cropService.getCropById(id).orElseThrow();
        return CropMapper.toDTO(crop);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CropDTO> getAll() {
        return cropService.getAllCrops()
                .stream()
                .map(CropMapper::toDTO)
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody CropDTO cropDTO) {
        Crop newCrop = CropMapper.toCrop(cropDTO);
        cropService.saveCrop(newCrop);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody CropDTO cropDTO) {
        Crop crop = cropService.getCropById(id).orElseThrow();
        crop.setName(cropDTO.getCropName());
        cropService.updateCrop(crop);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        cropService.deleteCropById(id);
    }
}
