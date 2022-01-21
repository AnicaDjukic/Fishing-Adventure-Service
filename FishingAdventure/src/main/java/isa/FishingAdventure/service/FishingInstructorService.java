package isa.FishingAdventure.service;

import java.util.List;

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
}
