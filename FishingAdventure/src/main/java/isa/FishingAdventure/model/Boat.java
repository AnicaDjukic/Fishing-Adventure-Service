package isa.FishingAdventure.model;

import isa.FishingAdventure.dto.NewBoatDto;

import javax.persistence.*;
import java.util.HashSet;
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

    public Boat(String name, String description, double cancellationRule, double rating, Location location,
            Set<Rule> rules, Set<AdditionalService> additionalServices, int persons, String type, int length,
            int motorNumber, double motorPower, double maxSpeed, BoatOwner boatOwner,
            Set<NavigationEquipment> navigationEquipment, Set<FishingEquipment> fishingEquipment) {
        super(name, description, cancellationRule, rating, location, rules, additionalServices, persons);
        this.type = type;
        this.length = length;
        this.motorNumber = motorNumber;
        this.motorPower = motorPower;
        this.maxSpeed = maxSpeed;
        this.boatOwner = boatOwner;
        this.navigationEquipment = navigationEquipment;
        this.fishingEquipment = fishingEquipment;
    }

    public Boat() {
    }

    public Boat(NewBoatDto dto) {
        super(dto.getName(), dto.getDescription(), dto.getCancellationRule(), 0.0, dto.getLocation(), dto.getRules(),
                dto.getAdditionalServices(), dto.getPersons());
        this.type = dto.getType();
        this.length = dto.getLength();
        this.motorNumber = dto.getMotorNumber();
        this.motorPower = dto.getMotorPower();
        this.maxSpeed = dto.getMaxSpeed();
        this.boatOwner = dto.getBoatOwner();
        this.navigationEquipment = new HashSet<>(dto.getNavigationEquipments());
        this.fishingEquipment = new HashSet<>(dto.getFishingEquipments());
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