package isa.FishingAdventure.service;

import isa.FishingAdventure.repository.FishingEquipmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FishingEquipmentService {
	
	@Autowired
	private FishingEquipmentRepository equipmentRepository;
}
