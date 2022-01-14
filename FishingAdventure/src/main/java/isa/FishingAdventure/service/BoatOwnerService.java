package isa.FishingAdventure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.BoatOwnerRepository;

@Service
public class BoatOwnerService {

	@Autowired
	private BoatOwnerRepository boatOwnerRepository;

	@Autowired
	private  BoatService boatService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserTypeService userTypeService;

	public BoatOwner findByEmail(String email) {
		return (BoatOwner)boatOwnerRepository.findByEmail(email);
	}
	
	public BoatOwner save(UserDto userDto) {
		BoatOwner u = new BoatOwner();
		u.setEmail(userDto.getEmail());

		u.setPassword(passwordEncoder.encode(userDto.getPassword()));
		u.setName(userDto.getName());
		u.setSurname(userDto.getSurname());
		u.setPhoneNumber(userDto.getPhoneNumber());
		u.setEmail(userDto.getEmail());
		u.setAddress(userDto.getAddress());
		u.setBiography(userDto.getBiography());
		u.setDeleted(false);
		u.setActivated(false);

		List<UserType> roles = userTypeService.findByName("ROLE_BOAT_OWNER");
		u.setUserType(roles.get(0));
		u.setPoints(0.0);
		
		return this.boatOwnerRepository.save(u);
	}

	public boolean isBoatOwnerAvailableForDateRange(Integer boatId, Date start, Date end) {
		Boat boat = boatService.getById(boatId);
		BoatOwner boatOwner = boat.getBoatOwner();
		List<Boat> ownerBoats = new ArrayList<Boat>();
		for(Boat b : boatService.findAllNonDeleted()){
			if(b.getId().equals(boatId))
				continue;

			if(b.getBoatOwner().getUserId().equals(boatOwner.getUserId())){
				ownerBoats.add(b);
			}

		}
		boolean available = true;
		for(Boat b : ownerBoats){
			for(Appointment ap : b.getAppointments()){
				if ((start.after(ap.getStartDate()) && start.before(ap.getEndDate())) || (end.after(ap.getStartDate()) && end.before(ap.getEndDate())) || (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) {
					if (ap.getOwnerPresence().equals(true)) {
						available = false;
						break;
					}
				}
			}
		}

		return available;
	}
}
