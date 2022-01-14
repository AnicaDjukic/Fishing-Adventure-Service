package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.JwtAuthenticationRequest;
import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.dto.UserTokenState;
import isa.FishingAdventure.exception.ResourceConflictException;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

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
            @RequestBody JwtAuthenticationRequest authenticationRequest) {
        UserTokenState userTokenState = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(userTokenState);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<UserTokenState> refreshToken(@RequestHeader("Authorization") String token) {
        UserTokenState authentication= authenticationService.refreshToken(token);
        return ResponseEntity.ok(authentication);

    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> addClient(@RequestBody UserDto userDto) throws Exception {
        authenticationService.signUpClient(new Client(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PostMapping("/signup/homeOwner")
    public ResponseEntity<VacationHomeOwner> addHomeOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
        VacationHomeOwner vacationHomeOwner;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        } else {
            vacationHomeOwner = this.homeOwnerService.save(userDto);
        }

        return new ResponseEntity<>(vacationHomeOwner, HttpStatus.CREATED);
    }

    @PostMapping("/signup/boatOwner")
    public ResponseEntity<BoatOwner> addBoatOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
        BoatOwner boatOwner;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        } else {
            boatOwner = this.boatOwnerService.save(userDto);
        }

        return new ResponseEntity<>(boatOwner, HttpStatus.CREATED);
    }

    @PostMapping("/signup/fishingInstructor")
    public ResponseEntity<FishingInstructor> addFishingInstructor(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
        FishingInstructor fishingInstructor;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        } else {
            fishingInstructor = this.instructorService.save(userDto);
        }

        //TODO: send registration to admin for approval

        return new ResponseEntity<>(fishingInstructor, HttpStatus.CREATED);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmAccount(@RequestParam(name = "token") String token) {
        authenticationService.confirmAccount(token);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}