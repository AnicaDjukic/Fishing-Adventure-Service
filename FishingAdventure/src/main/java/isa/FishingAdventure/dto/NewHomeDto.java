package isa.FishingAdventure.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.Location;
import isa.FishingAdventure.model.Room;
import isa.FishingAdventure.model.Rule;
import isa.FishingAdventure.model.VacationHomeOwner;


public class NewHomeDto {

    private String name;

    private String description;

    private double cancellationRule;

    private double rating;

    public Location location;

    private Set<Appointment> appointments;

    private Set<Rule> rules;

    private Set<AdditionalService> additionalServices;
    
    public Set<Image> images;
    
    public Set<Room> rooms;

	private Date availabilityStart;

	private Date availabilityEnd;

	public String vocationHomeOwner;



	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public NewHomeDto(String name, String description, double cancellationRule, double rating, Location location,
			Set<Appointment> appointments, Set<Rule> rules, Set<AdditionalService> additionalServices,
			Set<Image> images, Set<Room> rooms, Date availabilityStart, Date availabilityEnd,
			String vocationHomeOwner) {
		super();
		this.name = name;
		this.description = description;
		this.cancellationRule = cancellationRule;
		this.rating = rating;
		this.location = location;
		this.appointments = appointments;
		this.rules = rules;
		this.additionalServices = additionalServices;
		this.images = images;
		this.rooms = rooms;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.vocationHomeOwner = vocationHomeOwner;
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

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
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

	public Date getAvailabilityStart() {
		return availabilityStart;
	}

	public void setAvailabilityStart(Date availabilityStart) {
		this.availabilityStart = availabilityStart;
	}

	public Date getAvailabilityEnd() {
		return availabilityEnd;
	}

	public void setAvailabilityEnd(Date availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}

	public String getVocationHomeOwner() {
		return vocationHomeOwner;
	}

	public void setVocationHomeOwner(String vocationHomeOwner) {
		this.vocationHomeOwner = vocationHomeOwner;
	}

	public NewHomeDto() {
		super();
	}


}
