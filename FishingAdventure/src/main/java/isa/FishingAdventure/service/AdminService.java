package isa.FishingAdventure.service;


import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.FishingAdventure.model.Admin;
import isa.FishingAdventure.repository.AdminRepository;

@Service
public class AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private TokenUtils tokenUtils;

	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	public boolean isAdminHeadAdmin(Admin admin) {
		return admin.getHeadAdmin().equals(true);
	}

	public Admin findByToken(String token) {
		String email = tokenUtils.getEmailFromToken(token);
		return findByEmail(email);
	}
}
