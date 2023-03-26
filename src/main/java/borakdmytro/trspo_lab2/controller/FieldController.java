package borakdmytro.trspo_lab2.controller;

import borakdmytro.trspo_lab2.dto.FieldDTO;
import borakdmytro.trspo_lab2.dto.mapper.FieldMapper;
import borakdmytro.trspo_lab2.model.Field;
import borakdmytro.trspo_lab2.service.CropService;
import borakdmytro.trspo_lab2.service.FarmerService;
import borakdmytro.trspo_lab2.service.FieldService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fields")
public class FieldController {
    private final FieldService fieldService;
    private final FarmerService farmerService;
    private final CropService cropService;

    @Autowired
    public FieldController(FieldService fieldService, FarmerService farmerService, CropService cropService) {
        this.fieldService = fieldService;
        this.farmerService = farmerService;
        this.cropService = cropService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FieldDTO get(@PathVariable int id) {
        Field field = fieldService.getFieldById(id).orElseThrow();
        return FieldMapper.toDTO(field);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FieldDTO> getAll() {
        return fieldService.getAllFields()
                .stream()
                .map(FieldMapper::toDTO)
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody FieldDTO fieldDTO) {
        Field newField = FieldMapper.toField(fieldDTO);
        fieldService.saveField(newField);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody FieldDTO fieldDTO) {
        Field field = fieldService.getFieldById(id).orElseThrow();
        field.setArea(fieldDTO.getFieldArea());
        field.setName(fieldDTO.getFieldName());
        field.setOwner(farmerService.getFarmerById(fieldDTO.getOwnerId()).orElseThrow());
        field.setCrop(cropService.getCropById(fieldDTO.getCropId()).orElseThrow());
        fieldService.updateField(field);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        fieldService.deleteFieldById(id);
    }

    @GetMapping("/farmers/{farmerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FieldDTO> getAllFarmersFields(@PathVariable int farmerId) {
        return fieldService.getAllFarmersFields(farmerId)
                .stream()
                .map(FieldMapper::toDTO)
                .toList();
    }

    @GetMapping("/crops/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FieldDTO> getAllFieldsWithThatCrop(@PathVariable int cropId) {
        return fieldService.getAllFieldsWithThatCrop(cropId)
                .stream()
                .map(FieldMapper::toDTO)
                .toList();
    }

    @GetMapping("/farmers/{farmerId}/crops/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FieldDTO> getAllFarmersFieldsWithThatCrop(@PathVariable int farmerId, @PathVariable int cropId) {
        return fieldService.getAllFarmersFieldsWithThatCrop(farmerId, cropId)
                .stream()
                .map(FieldMapper::toDTO)
                .toList();
    }
}
