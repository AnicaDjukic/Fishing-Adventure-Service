package isa.FishingAdventure.service;

import isa.FishingAdventure.repository.AdditionalServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdditionalServiceService {

	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;
}
