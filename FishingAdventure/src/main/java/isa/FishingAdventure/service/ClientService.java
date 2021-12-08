package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.ClientRepository;

@Service
public class ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserTypeService userTypeService;

	public Client findByEmail(String email) {
		return (Client) clientRepository.findByEmail(email);
	}

	public Client save(UserDto userRequest) {
		Client u = new Client();
		u.setEmail(userRequest.getEmail());
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		u.setName(userRequest.getName());
		u.setSurname(userRequest.getSurname());
		u.setPhoneNumber(userRequest.getPhoneNumber());
		//u.setEnabled(true);
		u.setEmail(userRequest.getEmail());

		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		List<UserType> roles = userTypeService.findByName("CLIENT");
		u.setUserType(roles.get(0));
		System.out.println(u.getUserType().getName() + u.getUserType().getId());
		u.setPoints(0.0);
		u.setPenalties(0);
		
		
		
		return this.clientRepository.save(u);
	}
}
