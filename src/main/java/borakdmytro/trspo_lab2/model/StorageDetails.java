package borakdmytro.trspo_lab2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "storage_details")
@IdClass(StorageDetailsId.class)
@Data
public class StorageDetails {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", referencedColumnName = "storage_id")
    private Storage storage;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "crop_id")
    private Crop crop;

    @Column(name = "volume")
    private double volume;
}
