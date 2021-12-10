package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.model.Image;

public class BoatDto {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private double rating;
	
	public String street;
	
	public String city;
	
	public String country;
	
	public String imagePath;
	
	public BoatOwner boatOwner;

	public BoatDto(int id, String name, String description, double rating, String street,
			String city, String country, String imagePath, BoatOwner boatOwner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.street = street;
		this.city = city;
		this.country = country;
		this.imagePath = imagePath;
		this.boatOwner = boatOwner;
	}

	public BoatDto(Boat boat) {
		this.id = boat.getServiceId();
		this.name = boat.getName();
		this.description = boat.getDescription();
		this.rating = boat.getRating();
		this.street = boat.getLocation().getAddress().getStreet();
		this.city = boat.getLocation().getAddress().getCity();
		this.country = boat.getLocation().getAddress().getCountry();
		for(Image img : boat.getImages()) {
			this.imagePath = img.getPath();
			System.out.println(img.getPath());
			break;
		}
		//this.imagePath = "";
		this.boatOwner = boat.getBoatOwner();
	}

	public BoatDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	
	
}
