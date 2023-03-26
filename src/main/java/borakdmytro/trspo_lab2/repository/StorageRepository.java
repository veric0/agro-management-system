package borakdmytro.trspo_lab2.repository;

import borakdmytro.trspo_lab2.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    List<Storage> findAllByOwnerId(int farmerId);
}
