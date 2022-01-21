package isa.FishingAdventure.service;

import java.util.Date;
import java.util.List;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.BoatOwnerRepository;

@Service
public class BoatOwnerService {

	@Autowired
	private BoatOwnerRepository boatOwnerRepository;

	@Autowired
	private BoatService boatService;

	@Autowired
	private UserTypeService userTypeService;

	public BoatOwner findByEmail(String email) {
		return boatOwnerRepository.findByEmail(email);
	}

	public void saveNewBoatOwner(BoatOwner boatOwner) {
		List<UserType> roles = userTypeService.findByName("ROLE_BOAT_OWNER");
		boatOwner.setUserType(roles.get(0));
		boatOwnerRepository.save(boatOwner);
	}

	public boolean isBoatOwnerAvailableForDateRange(Integer boatId, Date start, Date end) {
		return checkAppointmentsForOwnerBoats(boatId, start, end, boatService.getById(boatId).getBoatOwner());
	}

	private boolean checkAppointmentsForOwnerBoats(Integer boatId, Date start, Date end, BoatOwner boatOwner) {
		boolean available = true;
		for (Boat b : boatService.findAllNonDeleted()) {
			if (b.getBoatOwner().getUserId().equals(boatOwner.getUserId()) && !b.getId().equals(boatId)) {
				available = checkAppointmentsByBoat(start, end, b);
				if (!available)
					break;
			}
		}
		return available;
	}

	private boolean checkAppointmentsByBoat(Date start, Date end, Boat boat) {
		boolean available = true;
		for (Appointment ap : boat.getAppointments()) {
			if (((start.after(ap.getStartDate()) && start.before(ap.getEndDate()))
					|| (end.after(ap.getStartDate()) && end.before(ap.getEndDate()))
					|| (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) && ap.getOwnerPresence()
							.equals(true)) {
				available = false;
				break;
			}
		}
		return available;
	}
}
