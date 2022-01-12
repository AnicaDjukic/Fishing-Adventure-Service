package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class FishingEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public FishingEquipment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FishingEquipment() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}