package isa.FishingAdventure.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import isa.FishingAdventure.model.*;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import isa.FishingAdventure.dto.JwtAuthenticationRequest;
import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.dto.UserTokenState;
import isa.FishingAdventure.exception.ResourceConflictException;
import isa.FishingAdventure.security.util.TokenUtils;

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
	private VacationHomeOwnerService homeOwnerService;
	
	@Autowired
	private FishingInstructorService instructorService;

	@Autowired
	private BoatOwnerService boatOwnerService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getEmail(), authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		if(!user.isActivated()) {
			return ResponseEntity.ok(null);
		}
		String jwt = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), false);
		String refreshToken = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), true);

		List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new UserTokenState(jwt, refreshToken, roles));
	}

	@GetMapping("/refreshToken")
	public ResponseEntity<UserTokenState> refreshToken(@RequestHeader("Authorization") String token) {
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		User user = userService.findByEmail(email);

		String jwt = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), false);
		String refreshToken = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), true);

		List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new UserTokenState(jwt, refreshToken, roles));

	}

	@PostMapping("/signup")
	public ResponseEntity<Client> addUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {

		Client userClient;
		if (userService.isEmailRegistered(userDto.getEmail())){
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}else{
			userClient = this.clientService.save(userDto);
			sendRegistrationEmail(userDto);
		}

		return new ResponseEntity<>(userClient, HttpStatus.CREATED);
	}

	private void sendRegistrationEmail(UserDto userDto){
		try {
			emailService.sendEmail(userDto.getEmail(), "Registration confirmation", "Hello " + userDto.getName() + ",\n\nto confirm your account, please click here : " + "http://localhost:8080/auth/confirm-account?token=" + generateRegistrationToken(userDto.getEmail()));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	private String generateRegistrationToken(String email) {
		ConfirmationToken confirmationToken = new ConfirmationToken();
		confirmationToken.setEmail(email);
		confirmationToken.setToken(tokenUtils.generateToken(email, "ROLE_CLIENT", false));
		confirmationTokenService.save(confirmationToken);

		return confirmationToken.getToken();
	}

	@PostMapping("/signup/homeOwner")
	public ResponseEntity<VacationHomeOwner> addHomeOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
		VacationHomeOwner vacationHomeOwner;
		if (userService.isEmailRegistered(userDto.getEmail())){
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}else{
			vacationHomeOwner = this.homeOwnerService.save(userDto);
		}

		return new ResponseEntity<>(vacationHomeOwner, HttpStatus.CREATED);
	}
	
	@PostMapping("/signup/boatOwner")
	public ResponseEntity<BoatOwner> addBoatOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
		BoatOwner boatOwner;
		if (userService.isEmailRegistered(userDto.getEmail())){
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}else{
			boatOwner = this.boatOwnerService.save(userDto);
		}

		return new ResponseEntity<>(boatOwner, HttpStatus.CREATED);
	}
	
	@PostMapping("/signup/fishingInstructor")
	public ResponseEntity<FishingInstructor> addFishingInstructor(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
		FishingInstructor fishingInstructor;
		if (userService.isEmailRegistered(userDto.getEmail())){
			throw new ResourceConflictException(userDto.getId(), "Email already exists");
		}else{
			fishingInstructor = this.instructorService.save(userDto);
		}

		//TODO: send registration to admin for approval

		return new ResponseEntity<>(fishingInstructor, HttpStatus.CREATED);
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
		        .body("Success");
	}

}