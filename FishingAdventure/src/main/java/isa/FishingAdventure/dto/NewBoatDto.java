package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.*;

import java.util.Set;

public class NewBoatDto {

    private Integer id;

    private String name;

    private String description;

    private double cancellationRule;

    private double rating;

    private Location location;

    private Set<Rule> rules;

    private Set<AdditionalService> additionalServices;

    private Set<Image> images;

    private Set<FishingEquipment> fishingEquipments;

    private Set<NavigationEquipment> navigationEquipments;

    private BoatOwner boatOwner;

    private String imagePath;

    private int persons;

    private double pricePerDay;

    private String type;

    private int length;

    private int motorNumber;

    private double motorPower;

    private double maxSpeed;

    public NewBoatDto() {
        super();
    }

    public NewBoatDto(Boat boat) {
        this.type = boat.getType();
        this.length = boat.getLength();
        this.motorPower = boat.getMotorPower();
        this.motorNumber = boat.getMotorNumber();
        this.maxSpeed = boat.getMaxSpeed();
        this.id = boat.getId();
        this.name = boat.getName();
        this.cancellationRule = boat.getCancellationRule();
        this.location = boat.getLocation();
        this.rules = boat.getRules();
        this.additionalServices = boat.getAdditionalServices();
        this.images = boat.getImages();
        this.description = boat.getDescription();
        this.rating = boat.getRating();
        for (Image img : boat.getImages()) {
            if (img.isCoverImage()) {
                this.imagePath = img.getPath();
                break;
            }
        }
        this.persons = boat.getPersons();
        this.boatOwner = boat.getBoatOwner();
        this.pricePerDay = boat.getPricePerDay();
        this.fishingEquipments = boat.getFishingEquipment();
        this.navigationEquipments = boat.getNavigationEquipment();
    }

    public NewBoatDto(Integer id, String name, String description, double cancellationRule, double rating, Location location, Set<Rule> rules, Set<AdditionalService> additionalServices, Set<Image> images, Set<FishingEquipment> fishingEquipments, Set<NavigationEquipment> navigationEquipments, BoatOwner boatOwner, String imagePath, int persons, double pricePerDay, String type, int length, int motorNumber, double motorPower, double maxSpeed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cancellationRule = cancellationRule;
        this.rating = rating;
        this.location = location;
        this.rules = rules;
        this.additionalServices = additionalServices;
        this.images = images;
        this.fishingEquipments = fishingEquipments;
        this.navigationEquipments = navigationEquipments;
        this.boatOwner = boatOwner;
        this.imagePath = imagePath;
        this.persons = persons;
        this.pricePerDay = pricePerDay;
        this.type = type;
        this.length = length;
        this.motorNumber = motorNumber;
        this.motorPower = motorPower;
        this.maxSpeed = maxSpeed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCancellationRule() {
        return cancellationRule;
    }

    public void setCancellationRule(double cancellationRule) {
        this.cancellationRule = cancellationRule;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public BoatOwner getVacationHomeOwner() {
        return boatOwner;
    }

    public void setVacationHomeOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }


    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Set<FishingEquipment> getFishingEquipments() {
        return fishingEquipments;
    }

    public void setFishingEquipments(Set<FishingEquipment> fishingEquipments) {
        this.fishingEquipments = fishingEquipments;
    }

    public Set<NavigationEquipment> getNavigationEquipments() {
        return navigationEquipments;
    }

    public void setNavigationEquipments(Set<NavigationEquipment> navigationEquipments) {
        this.navigationEquipments = navigationEquipments;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(int motorNumber) {
        this.motorNumber = motorNumber;
    }

    public double getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(double motorPower) {
        this.motorPower = motorPower;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
