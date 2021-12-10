package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.Image;

public class FishingAdventureDto {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private double rating;
	
	public String street;
	
	public String city;
	
	public String country;
	
	public String imagePath;
	
	public FishingInstructor fishingInstructor;

	public FishingAdventureDto(int id, String name, String description, double rating, String street,
			String city, String country, String imagePath, FishingInstructor fishingInstructor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.street = street;
		this.city = city;
		this.country = country;
		this.imagePath = imagePath;
		this.fishingInstructor = fishingInstructor;
	}

	public FishingAdventureDto(FishingAdventure fishingAdventure) {
		this.id = fishingAdventure.getServiceId();
		this.name = fishingAdventure.getName();
		this.description = fishingAdventure.getDescription();
		this.rating = fishingAdventure.getRating();
		this.street = fishingAdventure.getLocation().getAddress().getStreet();
		this.city = fishingAdventure.getLocation().getAddress().getCity();
		this.country = fishingAdventure.getLocation().getAddress().getCountry();
		for(Image img : fishingAdventure.getImages()) {
			this.imagePath = img.getPath();
			System.out.println(img.getPath());
			break;
		}
		this.fishingInstructor = fishingAdventure.getFishingInstructor();
	}

	public FishingAdventureDto() {
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

	public FishingInstructor getFishingInstructor() {
		return fishingInstructor;
	}

	public void setFishingInstructor(FishingInstructor fishingInstructor) {
		this.fishingInstructor = fishingInstructor;
	}
}
