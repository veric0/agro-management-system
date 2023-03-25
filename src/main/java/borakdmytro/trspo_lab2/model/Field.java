package borakdmytro.trspo_lab2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "field")
@Data
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "farmer_id")
    private Farmer owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "crop_id")
    private Crop crop;

    @Column(name = "crop_area")
    private double area;

    @NotNull
    @Column(name = "crop_name", unique = true)
    private String name;
}
