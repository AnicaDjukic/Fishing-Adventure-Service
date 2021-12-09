package isa.FishingAdventure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.VacationHomeRepository;

@Service
public class VacationHomeService{
	
	@Autowired
	private VacationHomeRepository homeRepository;
	
	public List<ServiceProfile> findAll() {
		return homeRepository.findAll();
	}
}
