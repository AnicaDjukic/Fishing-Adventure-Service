package isa.FishingAdventure.controller;

import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import isa.FishingAdventure.service.ClientService;

@RestController
@RequestMapping(value = "client")
public class ClientController{
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private TokenUtils tokenUtils;

	@GetMapping(value = "/isSubscribed/{id}")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity getIsSubscribed(@RequestHeader("Authorization") String token, @PathVariable Integer id){
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		Client client = clientService.findByEmail(email);
		boolean subscribed = clientService.isSubscribed(client, id);
		return new ResponseEntity(subscribed, HttpStatus.OK);
	}

	@GetMapping(value = "/subscribe/{id}")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity subscribe(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		Client client = clientService.findByEmail(email);
		boolean subscribeSucess = clientService.subscribe(client, id);
		return new ResponseEntity(subscribeSucess, HttpStatus.OK);
	}

	@GetMapping(value = "/unsubscribe/{id}")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity unsubscribe(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		Client client = clientService.findByEmail(email);
		boolean unsubscribeSucess = clientService.unsubscribe(client, id);
		return new ResponseEntity(unsubscribeSucess, HttpStatus.OK);
	}



}
