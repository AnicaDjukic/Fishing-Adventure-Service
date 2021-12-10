package isa.FishingAdventure.service;

import java.util.List;

import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.model.VacationHomeOwner;
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

	public List<VacationHome> findByVacationHomeOwner(VacationHomeOwner owner){return homeRepository.findByVocationHomeOwner(owner);}

	public VacationHome save(VacationHome home) {
		return homeRepository.save(home);
	}
	
	public void delete(Integer id) {
		homeRepository.deleteById(id);
	}
}
