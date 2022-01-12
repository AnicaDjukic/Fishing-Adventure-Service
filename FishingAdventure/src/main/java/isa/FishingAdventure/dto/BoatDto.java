package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.Location;

public class BoatDto {

    private int id;

    private String name;

    private String description;

    private double rating;

    private Location location;

    private String imagePath;

    private BoatOwner boatOwner;

    private double pricePerDay;

    private int persons;

    public BoatDto(int id, String name, String description, double rating, Location location, String imagePath,
            BoatOwner boatOwner, double pricePerDay, int persons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.location = location;
        this.imagePath = imagePath;
        this.boatOwner = boatOwner;
        this.pricePerDay = pricePerDay;
        this.persons = persons;
    }

    public BoatDto(Boat boat) {
        this.id = boat.getId();
        this.name = boat.getName();
        this.description = boat.getDescription();
        this.rating = boat.getRating();
        this.location = boat.getLocation();
        for (Image img : boat.getImages()) {
            if (img.isCoverImage()) {

                this.imagePath = img.getPath();
                break;
            }
        }
        this.boatOwner = boat.getBoatOwner();
        this.pricePerDay = boat.getPricePerDay();
        this.persons = boat.getPersons();
    }

    public BoatDto() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
}
