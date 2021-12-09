package isa.FishingAdventure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.model.ConfirmationToken;
import isa.FishingAdventure.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {

	
	@Autowired
	private ConfirmationTokenRepository repository;
	
	public ConfirmationToken save(ConfirmationToken token) {
		return repository.save(token);
	}

	public ConfirmationToken findByConfirmationToken(String token) {
		return repository.findByToken(token);
	}

	public void delete(ConfirmationToken confirmationToken) {
		repository.delete(confirmationToken);
	}
}
