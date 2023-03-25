package borakdmytro.trspo_lab2.repository;

import borakdmytro.trspo_lab2.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
}
