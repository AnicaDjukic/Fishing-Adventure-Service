package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.FishingAdventureRepository;

@Service
public class FishingAdventureService{
	
	@Autowired
	private FishingAdventureRepository adventureRepository;

	public List<FishingAdventure> findAll() {
		return adventureRepository.findAll();
	}
}
