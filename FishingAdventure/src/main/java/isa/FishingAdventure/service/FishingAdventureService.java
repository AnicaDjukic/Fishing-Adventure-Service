package isa.FishingAdventure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.AvailabilityDateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.repository.FishingAdventureRepository;

@Service
public class FishingAdventureService{
	
	@Autowired
	private FishingAdventureRepository adventureRepository;

	@Autowired
	private AvailabilityDateRangeService dateRangeService;

	@Autowired
	private ReservationService reservationService;

	public List<FishingAdventure> findAll() {
		return adventureRepository.findAll();
	}

	public List<FishingAdventure> findByFishingInstructor(FishingInstructor instructor) {
		return adventureRepository.findByFishingInstructor(instructor);
	}

	public FishingAdventure findById(int id) {
		return adventureRepository.findById(id);
	}

	public boolean isInstructorAvailable(FishingInstructor instructor, Date start, Date end) {
		List<FishingAdventure> adventures = findByFishingInstructor(instructor);

		boolean available = true;
		AvailabilityDateRange availabilityPeriod = dateRangeService.findByServiceProfile(adventures.get(0)).get(0);
		if (start.after(availabilityPeriod.getStartDate()) && end.before(availabilityPeriod.getEndDate())) {
			for (FishingAdventure adventure : adventures) {
				if (areAdventuresOverlaping(start, end, new ArrayList<>(adventure.getAppointments()))) {
					available = false;
					break;
				}
			}
		}
		else {
			available = false;
		}
		return available;
	}

	private boolean areAdventuresOverlaping(Date start, Date end, List<Appointment> appointments) {
		boolean isOverlaping = false;
		for (Appointment appointment : appointments) {
			if (!reservationService.isReservationCanceled(appointment.getAppointmentId())) {
				if ((start.after(appointment.getStartDate()) && start.before(appointment.getEndDate())) ||
						(end.after(appointment.getStartDate()) && end.before(appointment.getEndDate())) ||
						start.equals(appointment.getStartDate()) || start.equals(appointment.getEndDate()) ||
						end.equals(appointment.getStartDate()) || end.equals(appointment.getEndDate())) {
					isOverlaping = true;
					break;
				}
			}
		}
		return isOverlaping;
	}

}
