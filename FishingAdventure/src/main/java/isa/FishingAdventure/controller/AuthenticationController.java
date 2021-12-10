package isa.FishingAdventure.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isa.FishingAdventure.dto.JwtAuthenticationRequest;
import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.dto.UserTokenState;
import isa.FishingAdventure.exception.ResourceConflictException;
import isa.FishingAdventure.model.Admin;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.ConfirmationToken;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.model.VacationHomeOwner;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.AdminService;
import isa.FishingAdventure.service.BoatOwnerService;
import isa.FishingAdventure.service.ClientService;
import isa.FishingAdventure.service.ConfirmationTokenService;
import isa.FishingAdventure.service.EmailService;
import isa.FishingAdventure.service.FishingInstructorService;
import isa.FishingAdventure.service.VacationHomeOwnerService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private VacationHomeOwnerService homeOwnerService;
	
	@Autowired
	private FishingInstructorService instructorService;
	@Autowired
	private BoatOwnerService boatOwnerService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getEmail(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		if(!user.isActivated()) {
			return ResponseEntity.ok(null);
		}
		String jwt = tokenUtils.generateToken(user.getEmail());
		int expiresIn = tokenUtils.getExpiredIn();
		String username = tokenUtils.getUsernameFromToken(jwt);
		
		List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, (long) expiresIn, username, roles));
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<Client> addUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {

		Client existClient = this.clientService.findByEmail(userDto.getEmail());
		Admin existAdmin = null;
		FishingInstructor existInstructor = null;
		VacationHomeOwner existHomeOwner = null;
		BoatOwner existBoatOwner = null;
				
		if(existClient == null) {
			existAdmin = this.adminService.findByEmail(userDto.getEmail());
			if(existAdmin == null) {
				existInstructor = this.instructorService.findByEmail(userDto.getEmail());
				if(existHomeOwner == null) {
					existHomeOwner = this.homeOwnerService.findByEmail(userDto.getEmail());
					if(existBoatOwner == null) {
						existBoatOwner = this.boatOwnerService.findByEmail(userDto.getEmail());
					}
				}
			}
		}
		
		
		if (existClient != null || existAdmin != null || existInstructor != null || existHomeOwner != null) {
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}

		Client userClient = this.clientService.save(userDto); 
		
		try {
			emailService.sendNotificaitionAsync(userDto);
		}
		catch(Exception e){
			System.out.println(e);
		}

		return new ResponseEntity<>(userClient, HttpStatus.CREATED);
	}
	
	@PostMapping("/signup/homeOwner")
	public ResponseEntity<VacationHomeOwner> addHomeOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {

		Client existClient = this.clientService.findByEmail(userDto.getEmail());
		Admin existAdmin = null;
		FishingInstructor existInstructor = null;
		VacationHomeOwner existHomeOwner = null;
		BoatOwner existBoatOwner = null;
				
		if(existClient == null) {
			existAdmin = this.adminService.findByEmail(userDto.getEmail());
			if(existAdmin == null) {
				existInstructor = this.instructorService.findByEmail(userDto.getEmail());
				if(existHomeOwner == null) {
					existHomeOwner = this.homeOwnerService.findByEmail(userDto.getEmail());
					if(existBoatOwner == null) {
						existBoatOwner = this.boatOwnerService.findByEmail(userDto.getEmail());
					}
				}
			}
		}
		
		
		if (existClient != null || existAdmin != null || existInstructor != null || existHomeOwner != null) {
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}

		VacationHomeOwner userVacationHomeOwner = this.homeOwnerService.save(userDto); 
		
		try {
			emailService.sendNotificaitionAsync(userDto);
		}
		catch(Exception e){
			System.out.println(e);
		}

		return new ResponseEntity<>(userVacationHomeOwner, HttpStatus.CREATED);
	}
	
	@PostMapping("/signup/boatOwner")
	public ResponseEntity<BoatOwner> addBoatOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {

		Client existClient = this.clientService.findByEmail(userDto.getEmail());
		Admin existAdmin = null;
		FishingInstructor existInstructor = null;
		VacationHomeOwner existHomeOwner = null;
		BoatOwner existBoatOwner = null;
				
		if(existClient == null) {
			existAdmin = this.adminService.findByEmail(userDto.getEmail());
			if(existAdmin == null) {
				existInstructor = this.instructorService.findByEmail(userDto.getEmail());
				if(existHomeOwner == null) {
					existHomeOwner = this.homeOwnerService.findByEmail(userDto.getEmail());
					if(existBoatOwner == null) {
						existBoatOwner = this.boatOwnerService.findByEmail(userDto.getEmail());
					}
				}
			}
		}
		
		
		if (existClient != null || existAdmin != null || existInstructor != null || existHomeOwner != null) {
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}

		BoatOwner userBoatOwner = this.boatOwnerService.save(userDto); 
		
		try {
			emailService.sendNotificaitionAsync(userDto);
		}
		catch(Exception e){
			System.out.println(e);
		}

		return new ResponseEntity<>(userBoatOwner, HttpStatus.CREATED);
	}
	
	@PostMapping("/signup/fishingInstructor")
	public ResponseEntity<FishingInstructor> addFishingInstructor(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {

		Client existClient = this.clientService.findByEmail(userDto.getEmail());
		Admin existAdmin = null;
		FishingInstructor existInstructor = null;
		VacationHomeOwner existHomeOwner = null;
		BoatOwner existBoatOwner = null;
				
		if(existClient == null) {
			existAdmin = this.adminService.findByEmail(userDto.getEmail());
			if(existAdmin == null) {
				existInstructor = this.instructorService.findByEmail(userDto.getEmail());
				if(existHomeOwner == null) {
					existHomeOwner = this.homeOwnerService.findByEmail(userDto.getEmail());
					if(existBoatOwner == null) {
						existBoatOwner = this.boatOwnerService.findByEmail(userDto.getEmail());
					}
				}
			}
		}
		
		
		if (existClient != null || existAdmin != null || existInstructor != null || existHomeOwner != null) {
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}

		FishingInstructor userFishingInstructor = this.instructorService.save(userDto); 
		
		try {
			emailService.sendNotificaitionAsync(userDto);
		}
		catch(Exception e){
			System.out.println(e);
		}

		return new ResponseEntity<>(userFishingInstructor, HttpStatus.CREATED);
	}
	
	@GetMapping("/confirm-account")
	public ResponseEntity<String>  confirm(@RequestParam(name = "token") String token) {
		
		ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);

        if(token != null)
        {
            Client user = clientService.findByEmail(confirmationToken.getEmail());
            user.setActivated(true);
            clientService.save(user);
            confirmationTokenService.delete(confirmationToken);
        }
        else
        {
        	return ResponseEntity.badRequest()
    		        .body("Error");
        }
		
		return ResponseEntity.ok()
		        .body("Sucess");
	}

}