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

	public FishingInstructor save(UserDto userDto) {
		FishingInstructor u = new FishingInstructor();
		u.setEmail(userDto.getEmail());

		u.setPassword(passwordEncoder.encode(userDto.getPassword()));
		u.setName(userDto.getName());
		u.setSurname(userDto.getSurname());
		u.setPhoneNumber(userDto.getPhoneNumber());
		u.setEmail(userDto.getEmail());
		u.setAddress(userDto.getAddress());

		
		List<UserType> roles = userTypeService.findByName("ROLE_FISHING_INSTRUCTOR");
		u.setUserType(roles.get(0));
		u.setPoints(0.0);
		u.setBiography("");
		
		return this.fishingInstructorRepository.save(u);
	}

	public List<isa.FishingAdventure.model.User> findAll() {
		return fishingInstructorRepository.findAll();
	}
}
