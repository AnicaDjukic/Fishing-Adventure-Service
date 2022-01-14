package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.UserType;
import isa.FishingAdventure.repository.UserTypeRepository;

@Service
public class UserTypeService {
	@Autowired
	private UserTypeRepository userTypeRepository;

	public UserType findById(Integer id) {
		UserType auth = userTypeRepository.getById(id);
		return auth;
	}

	public List<UserType> findByName(String name) {
		List<UserType> roles = userTypeRepository.findByName(name);
		return roles;
	}

}
