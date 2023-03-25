package borakdmytro.trspo_lab2.repository;

import borakdmytro.trspo_lab2.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {
    List<Field> findAllByOwnerId(int farmerId);
    List<Field> findAllByCropId(int cropId);
    List<Field> findAllByOwnerIdAndCropId(int farmerId, int cropId);
}
