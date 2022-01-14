package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.Admin;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.AdminRepository;

@Service
public class AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserTypeService userTypeService;

	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	public Admin save(UserDto userDto) {
		Admin u = new Admin();
		u.setEmail(userDto.getEmail());

		u.setPassword(passwordEncoder.encode(userDto.getPassword()));
		u.setName(userDto.getName());
		u.setSurname(userDto.getSurname());
		u.setPhoneNumber(userDto.getPhoneNumber());
		u.setEmail(userDto.getEmail());
		u.setAddress(userDto.getAddress());

		
		List<UserType> roles = userTypeService.findByName("ROLE_ADMIN");
		u.setUserType(roles.get(0));
		u.setPoints(0.0);
		
		return this.adminRepository.save(u);
	}
}
