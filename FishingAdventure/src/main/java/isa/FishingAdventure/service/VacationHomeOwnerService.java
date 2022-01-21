package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.model.VacationHomeOwner;
import isa.FishingAdventure.repository.VacationHomeOwnerRepository;

@Service
public class VacationHomeOwnerService {

	@Autowired
	private VacationHomeOwnerRepository homeOwnerRepository;

	@Autowired
	private UserTypeService userTypeService;

	public VacationHomeOwner findByEmail(String email) {
		return homeOwnerRepository.findByEmail(email);
	}

	public void saveNewHomeOwner(VacationHomeOwner vacationHomeOwner) {
		List<UserType> roles = userTypeService.findByName("ROLE_VACATION_HOME_OWNER");
		vacationHomeOwner.setUserType(roles.get(0));
		homeOwnerRepository.save(vacationHomeOwner);
	}
}
