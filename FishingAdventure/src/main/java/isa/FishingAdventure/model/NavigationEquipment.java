package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class NavigationEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public NavigationEquipment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public NavigationEquipment() {
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