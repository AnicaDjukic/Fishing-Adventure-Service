package isa.FishingAdventure.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.FishingAdventure.model.Admin;
import isa.FishingAdventure.repository.AdminRepository;

@Service
public class AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
}
