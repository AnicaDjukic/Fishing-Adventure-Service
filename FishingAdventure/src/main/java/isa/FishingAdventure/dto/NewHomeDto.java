package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.*;

import java.util.Set;


public class NewHomeDto {

    private Integer id;

    private String name;

    private String description;

    private double cancellationRule;

    private double rating;

    private Location location;

    private Set<Rule> rules;

    private Set<AdditionalService> additionalServices;

    private Set<Image> images;

    private Set<Room> rooms;

    private VacationHomeOwner vacationHomeOwner;

    private String imagePath;

    private int persons;

    private double pricePerDay;

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public NewHomeDto(Integer id, String name, String description, double cancellationRule, double rating, Location location, Set<Rule> rules, Set<AdditionalService> additionalServices, Set<Image> images, Set<Room> rooms, VacationHomeOwner vacationHomeOwner, String imagePath, int persons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cancellationRule = cancellationRule;
        this.rating = rating;
        this.location = location;
        this.rules = rules;
        this.additionalServices = additionalServices;
        this.images = images;
        this.rooms = rooms;
        this.vacationHomeOwner = vacationHomeOwner;
        this.imagePath = imagePath;
        this.persons = persons;
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

    public VacationHomeOwner getVacationHomeOwner() {
        return vacationHomeOwner;
    }

    public void setVacationHomeOwner(VacationHomeOwner vocationHomeOwner) {
        this.vacationHomeOwner = vocationHomeOwner;
    }

    public NewHomeDto() {
        super();
    }

    public NewHomeDto(VacationHome home) {
        this.id = home.getId();
        this.name = home.getName();
        this.cancellationRule = home.getCancellationRule();
        this.location = home.getLocation();
        this.rules = home.getRules();
        this.additionalServices = home.getAdditionalServices();
        this.images = home.getImages();
        this.rooms = home.getRooms();
        this.description = home.getDescription();
        this.rating = home.getRating();
        for (Image img : home.getImages()) {
            if (img.isCoverImage()) {
                this.imagePath = img.getPath();
                break;
            }
        }
        this.persons = home.getPersons();
        this.vacationHomeOwner = home.getVacationHomeOwner();
        this.pricePerDay = home.getPricePerDay();
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
}
