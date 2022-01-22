package isa.FishingAdventure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import isa.FishingAdventure.dto.InstructorAvailabilityDto;
import isa.FishingAdventure.model.InstructorAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.FishingInstructorRepository;
import isa.FishingAdventure.security.util.TokenUtils;

@Service
public class FishingInstructorService {

	@Autowired
	private FishingInstructorRepository fishingInstructorRepository;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private InstructorAvailabilityService instructorAvailabilityService;

	public FishingInstructor findByEmail(String email) {
		return fishingInstructorRepository.findByEmail(email);
	}

	public void saveNewInstructor(FishingInstructor fishingInstructor) {
		List<UserType> roles = userTypeService.findByName("ROLE_FISHING_INSTRUCTOR");
		fishingInstructor.setUserType(roles.get(0));
		fishingInstructorRepository.save(fishingInstructor);
	}

	public FishingInstructor findByToken(String token) {
		String email = tokenUtils.getEmailFromToken(token);
		return findByEmail(email);
	}

	public List<FishingInstructor> findAll() {
		return fishingInstructorRepository.getAll();
	}

	public List<InstructorAvailabilityDto> getAllAvailabilitiesForInstructor(String email) {
		FishingInstructor instructor = findByEmail(email);
		List<InstructorAvailabilityDto> availabilityDtos = new ArrayList<>();
		for (InstructorAvailability availability : instructor.getInstructorAvailabilities())
			availabilityDtos.add(new InstructorAvailabilityDto(availability));

		return availabilityDtos;
	}

	public void addAvailabilityPeriodToInstructor(String email, InstructorAvailability availabilityPeriod) {
		FishingInstructor instructor = findByEmail(email);
		List<InstructorAvailability> availabilities = new ArrayList<>(instructor.getInstructorAvailabilities());
		InstructorAvailability newAvailability = instructorAvailabilityService.save(availabilityPeriod);
		availabilities.add(newAvailability);

		instructor.setInstructorAvailabilities(new HashSet<>(checkForOverlappingAvailabilities(availabilities, availabilityPeriod)));
		fishingInstructorRepository.save(instructor);
	}

	public void updateAvailabilityPeriodForInstructor(String email, InstructorAvailability newAvailabilityPeriod, int id) {
		FishingInstructor instructor = findByEmail(email);
		for(InstructorAvailability availability : instructor.getInstructorAvailabilities()) {
			if (availability.getId().equals(id)) {
				availability.setStartDate(newAvailabilityPeriod.getStartDate());
				availability.setEndDate(newAvailabilityPeriod.getEndDate());
				instructor.setInstructorAvailabilities(new HashSet<>(checkForOverlappingAvailabilities(new ArrayList<>(instructor.getInstructorAvailabilities()), availability)));
				fishingInstructorRepository.save(instructor);
				break;
			}
		}
	}

	public void deleteAvailabilityPeriodForInstructor(String email, int id) {
		FishingInstructor instructor = findByEmail(email);
		instructor.getInstructorAvailabilities().removeIf(availability -> availability.getId().equals(id));
		fishingInstructorRepository.save(instructor);
	}

	public List<InstructorAvailability> checkForOverlappingAvailabilities(List<InstructorAvailability> availabilityPeriods, InstructorAvailability newAvailability) {
		List<InstructorAvailability> newAvailabilities = new ArrayList<>();
		for (InstructorAvailability availability : availabilityPeriods) {
			if (!availability.getId().equals(newAvailability.getId()) && (isBetweenAvailabilityDate(newAvailability.getStartDate(), availability)
					|| isBetweenAvailabilityDate(newAvailability.getEndDate(), availability))) {
				Date startDate = availability.getStartDate();
				Date endDate = availability.getEndDate();
				availabilityPeriods.remove(availability);
				availability = calculateNewAvailability(startDate, endDate, newAvailability);
			} else if (newAvailability.getStartDate().before(availability.getStartDate()) && newAvailability.getEndDate().after(availability.getEndDate())) {
				availability.setStartDate(newAvailability.getStartDate());
				availability.setEndDate(newAvailability.getEndDate());
			}
			newAvailabilities.add(availability);
		}
		return newAvailabilities;
	}

	private boolean isBetweenAvailabilityDate(Date newDate, InstructorAvailability availability) {
		return (newDate.before(availability.getEndDate()) && newDate.after(availability.getStartDate()))
				|| newDate.equals(availability.getEndDate()) || newDate.equals(availability.getStartDate());
	}

	private InstructorAvailability calculateNewAvailability(Date startDate, Date endDate, InstructorAvailability newAvailability) {
		if (startDate.before(newAvailability.getStartDate()))
			newAvailability.setStartDate(startDate);

		if (endDate.after(newAvailability.getEndDate()))
			newAvailability.setEndDate(endDate);

		return newAvailability;
	}
}
