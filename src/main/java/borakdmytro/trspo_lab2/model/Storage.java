package borakdmytro.trspo_lab2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "storage")
@Data
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "farmer_id")
    private Farmer owner;

    @Column(name = "max_volume")
    private double maxVolume;

    @Transient
    private double freeSpace;
}
