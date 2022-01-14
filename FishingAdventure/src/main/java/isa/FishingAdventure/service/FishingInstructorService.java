package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.FishingInstructorRepository;

@Service
public class FishingInstructorService{
	
	@Autowired
	private FishingInstructorRepository fishingInstructorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserTypeService userTypeService;

	public FishingInstructor findByEmail(String email) {
		return fishingInstructorRepository.findByEmail(email);
	}

	public void saveNewInstructor(FishingInstructor fishingInstructor) {
		fishingInstructor.setPassword(passwordEncoder.encode(fishingInstructor.getPassword()));
		List<UserType> roles = userTypeService.findByName("ROLE_FISHING_INSTRUCTOR");
		fishingInstructor.setUserType(roles.get(0));
		fishingInstructorRepository.save(fishingInstructor);
	}

	public List<isa.FishingAdventure.model.User> findAll() {
		return fishingInstructorRepository.findAll();
	}
}
