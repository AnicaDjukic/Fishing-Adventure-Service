package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.Location;

public class FishingAdventureDto {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private double rating;

	private Location location;
	
	public String imagePath;
	
	public FishingInstructor fishingInstructor;

	public FishingAdventureDto(int id, String name, String description, double rating, Location location,
							   String imagePath, FishingInstructor fishingInstructor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.location = location;
		this.imagePath = imagePath;
		this.fishingInstructor = fishingInstructor;
	}

	public FishingAdventureDto(FishingAdventure fishingAdventure) {
		this.id = fishingAdventure.getId();
		this.name = fishingAdventure.getName();
		this.description = fishingAdventure.getDescription();
		this.rating = fishingAdventure.getRating();
		this.location = fishingAdventure.getLocation();
		for(Image img : fishingAdventure.getImages()) {
			if (img.isCoverImage()) {
				this.imagePath = img.getPath();
				break;
			}
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

	public FishingInstructor getFishingInstructor() {
		return fishingInstructor;
	}

	public void setFishingInstructor(FishingInstructor fishingInstructor) {
		this.fishingInstructor = fishingInstructor;
	}
}
