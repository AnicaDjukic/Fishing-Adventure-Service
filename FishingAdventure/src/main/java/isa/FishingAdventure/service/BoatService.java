package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.BoatRepository;

@Service
public class BoatService {
	
	@Autowired
	private BoatRepository boatRepository;

	public List<ServiceProfile> findAll() {
		return boatRepository.findAll();
	}
}
