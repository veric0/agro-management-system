package borakdmytro.trspo_lab2.repository;

import borakdmytro.trspo_lab2.model.StorageDetails;
import borakdmytro.trspo_lab2.model.StorageDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageDetailsRepository extends JpaRepository<StorageDetails, StorageDetailsId> {
    List<StorageDetails> findAllByStorageId(int storageId);
    Optional<StorageDetails> findByStorageIdAndCropId(int storageId, int cropId);
    void deleteByStorageIdAndCropId(int storageId, int cropId);
}
