package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Boat extends ServiceProfile {

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "motorNumber", nullable = false)
    private int motorNumber;

    @Column(name = "motorPower", nullable = false)
    private double motorPower;

    @Column(name = "maxSpeed", nullable = false)
    private double maxSpeed;

    @ManyToOne(targetEntity = BoatOwner.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    public BoatOwner boatOwner;

    @ManyToMany(targetEntity = NavigationEquipment.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<NavigationEquipment> navigationEquipment;

    @ManyToMany(targetEntity = FishingEquipment.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<FishingEquipment> fishingEquipment;

    public Boat(Integer id, String name, String description, double cancellationRule, double rating, Location location, Set<Appointment> appointments, Set<Rule> rules, Set<AdditionalService> additionalServices, int persons) {
        super(id, name, description, cancellationRule, rating, location, appointments, rules, additionalServices, persons);

    }


    public Boat() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMotorNumber() {
        return this.motorNumber;
    }

    public void setMotorNumber(int motorNumber) {
        this.motorNumber = motorNumber;
    }

    public double getMotorPower() {
        return this.motorPower;
    }

    public void setMotorPower(double motorPower) {
        this.motorPower = motorPower;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }


    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }


    public Set<NavigationEquipment> getNavigationEquipment() {
        return navigationEquipment;
    }


    public void setNavigationEquipment(Set<NavigationEquipment> navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }


    public Set<FishingEquipment> getFishingEquipment() {
        return fishingEquipment;
    }


    public void setFishingEquipment(Set<FishingEquipment> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}