package borakdmytro.trspo_lab2.repository;

import borakdmytro.trspo_lab2.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {
}
