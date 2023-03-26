package borakdmytro.trspo_lab2.controller;

import borakdmytro.trspo_lab2.dto.StorageDTO;
import borakdmytro.trspo_lab2.dto.StorageDetailsDTO;
import borakdmytro.trspo_lab2.dto.mapper.StorageDetailsMapper;
import borakdmytro.trspo_lab2.dto.mapper.StorageMapper;
import borakdmytro.trspo_lab2.model.Storage;
import borakdmytro.trspo_lab2.model.StorageDetails;
import borakdmytro.trspo_lab2.service.StorageService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storages")
public class StorageController {
    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StorageDTO get(@PathVariable int id) {
        Storage storage = storageService.getStorageById(id).orElseThrow();
        return StorageMapper.toDTO(storage);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<StorageDTO> getAll() {
        return storageService.getAllStorages()
                .stream()
                .map(StorageMapper::toDTO)
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@NotNull @RequestBody StorageDTO storageDTO) {
        Storage newStorage = StorageMapper.toStorage(storageDTO);
        storageService.saveStorage(newStorage);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @NotNull @RequestBody StorageDTO storageDTO) {
        Storage storage = storageService.getStorageById(id).orElseThrow();
        storage.setMaxVolume(storageDTO.getMaxVolume());
        storageService.updateStorage(storage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        storageService.deleteStorageById(id);
    }

    @GetMapping("/farmers/{farmerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<StorageDTO> getAllFarmersStorages(@PathVariable int farmerId) {
        return storageService.getAllFarmersStorages(farmerId)
                .stream()
                .map(StorageMapper::toDTO)
                .toList();
    }


    @GetMapping("/{storageId}/details")
    @ResponseStatus(HttpStatus.OK)
    public List<StorageDetailsDTO> getAllStorageDetails(@PathVariable int storageId) {
        return storageService.getAllStorageDetails(storageId)
                .stream()
                .map(StorageDetailsMapper::toDTO)
                .toList();
    }

    @GetMapping("/{storageId}/crops/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public StorageDetailsDTO getStorageCropDetails(@PathVariable int storageId, @PathVariable int cropId) {
        StorageDetails storageDetails = storageService.getStorageCropDetails(storageId, cropId);
        return StorageDetailsMapper.toDTO(storageDetails);
    }

    @PutMapping("/{storageId}/crops/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public void addToStorage(@PathVariable int storageId, @PathVariable int cropId, @NotNull @RequestBody StorageDetailsDTO dto) {
        StorageDetails storageDetails = storageService.getStorageCropDetails(storageId, cropId);
        storageService.addToStorage(storageDetails, dto.getVolume());
    }

    @DeleteMapping("/{storageId}/crops/{cropId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFromStorage(@PathVariable int storageId, @PathVariable int cropId, @NotNull @RequestBody StorageDetailsDTO dto) {
        StorageDetails storageDetails = storageService.getStorageCropDetails(storageId, cropId);
        storageService.removeFromStorage(storageDetails, dto.getVolume());
    }
}
