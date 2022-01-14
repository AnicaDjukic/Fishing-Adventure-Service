package isa.FishingAdventure.model;

import javax.persistence.*;

import isa.FishingAdventure.dto.NewHomeDto;

import java.util.HashSet;
import java.util.Set;

@Entity
public class VacationHome extends ServiceProfile {

	@ManyToOne(targetEntity = VacationHomeOwner.class, cascade = CascadeType.MERGE)
	public VacationHomeOwner vacationHomeOwner;

	@OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Room> rooms;

	public VacationHome(String name, String description, double cancellationRule, double rating, Location location,
			Set<Rule> rules, Set<AdditionalService> additionalServices, int persons,
			VacationHomeOwner vacationHomeOwner) {
		super(name, description, cancellationRule, rating, location, rules, additionalServices, persons);
		this.rooms = new HashSet<>();
		this.vacationHomeOwner = vacationHomeOwner;
	}

	public VacationHome() {
	}

	public VacationHome(NewHomeDto dto) {
		super(dto.getName(), dto.getDescription(), dto.getCancellationRule(), 0.0, dto.getLocation(), dto.getRules(),
				dto.getAdditionalServices(), dto.getPersons());
		this.rooms = dto.getRooms();
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