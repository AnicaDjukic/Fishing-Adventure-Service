package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.model.VacationHomeOwner;
import isa.FishingAdventure.repository.VacationHomeOwnerRepository;

@Service
public class VacationHomeOwnerService{
	
	@Autowired
	private VacationHomeOwnerRepository homeOwnerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserTypeService userTypeService;

	public VacationHomeOwner findByEmail(String email) {
		return (VacationHomeOwner)homeOwnerRepository.findByEmail(email);
	}

	public VacationHomeOwner save(UserDto userDto) {
		VacationHomeOwner u = new VacationHomeOwner();
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
		
		List<UserType> roles = userTypeService.findByName("ROLE_VACATION_HOME_OWNER");
		u.setUserType(roles.get(0));
		u.setPoints(0.0);
		
		return this.homeOwnerRepository.save(u);
	}
}
