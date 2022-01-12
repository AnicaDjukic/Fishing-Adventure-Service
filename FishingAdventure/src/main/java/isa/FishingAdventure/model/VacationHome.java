package isa.FishingAdventure.model;

import javax.persistence.*;

import java.util.Set;

@Entity
public class VacationHome extends ServiceProfile {

	@ManyToOne(targetEntity = VacationHomeOwner.class, cascade = CascadeType.MERGE)
	public VacationHomeOwner vacationHomeOwner;

	@OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Room> rooms;

	public VacationHome(int id, String name, String description, double cancellationRule, double rating,
			Location location, Set<Appointment> appointments, Set<Rule> rules,
			Set<AdditionalService> additionalServices, int persons) {
		super(id, name, description, cancellationRule, rating, location, appointments, rules, additionalServices,
				persons);
	}

	public VacationHome() {
	}

	public VacationHomeOwner getVacationHomeOwner() {
		return vacationHomeOwner;
	}

	public void setVacationHomeOwner(VacationHomeOwner vocationHomeOwner) {
		this.vacationHomeOwner = vocationHomeOwner;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
}