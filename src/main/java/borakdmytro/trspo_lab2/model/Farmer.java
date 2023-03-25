package borakdmytro.trspo_lab2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "farmer")
@Data
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farmer_id")
    private int id;

    @Column(name = "farmer_name", unique = true)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Field> fields;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Storage> storages;

    /*
    todo OneToMany - cascade, fetch
    todo GeneratedValue - strategy
    cascade = CascadeType.ALL
    fetch = FetchType.LAZY
    strategy = GenerationType.IDENTITY
     */
}
