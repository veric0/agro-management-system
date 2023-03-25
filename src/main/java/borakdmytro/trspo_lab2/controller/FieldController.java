package borakdmytro.trspo_lab2.controller;

import borakdmytro.trspo_lab2.model.Field;
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

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Field get(@PathVariable int id) {
        return fieldService.getFieldById(id).orElseThrow();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Field> getAll() {
        return fieldService.getAllFields();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody Field field) {
        fieldService.saveField(field);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody Field field) {
        Field newField = fieldService.getFieldById(id).orElseThrow();
        fieldService.updateField(newField);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        fieldService.deleteFieldById(id);
    }

    @GetMapping("/farmers/{farmerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Field> getAllFarmersFields(@PathVariable int farmerId) {
        return fieldService.getAllFarmersFields(farmerId);
    }

    @GetMapping("/crops/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Field> getAllFieldsWithThatCrop(@PathVariable int cropId) {
        return fieldService.getAllFieldsWithThatCrop(cropId);
    }

    @GetMapping("/farmer/{farmerId}/crop/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Field> getAllFarmersFieldsWithThatCrop(@PathVariable int farmerId, @PathVariable int cropId) {
        return fieldService.getAllFarmersFieldsWithThatCrop(farmerId, cropId);
    }
}
