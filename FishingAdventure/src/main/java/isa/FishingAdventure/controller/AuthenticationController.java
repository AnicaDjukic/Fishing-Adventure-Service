package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.JwtAuthenticationRequest;
import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.dto.UserTokenState;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) {
        UserTokenState userTokenState = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(userTokenState);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<UserTokenState> refreshToken(@RequestHeader("Authorization") String token) {
        UserTokenState authentication = authenticationService.refreshToken(token);
        return ResponseEntity.ok(authentication);

    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> addClient(@RequestBody UserDto userDto) throws MessagingException {
        authenticationService.signUpClient(new Client(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/signup/homeOwner")
    public ResponseEntity<UserDto> addHomeOwner(@RequestBody UserDto userDto) throws MailException {
        authenticationService.signUpHomeOwner(new VacationHomeOwner(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/signup/boatOwner")
    public ResponseEntity<UserDto> addBoatOwner(@RequestBody UserDto userDto) throws MailException {
        authenticationService.signUpBoatOwner(new BoatOwner(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/signup/fishingInstructor")
    public ResponseEntity<UserDto> addFishingInstructor(@RequestBody UserDto userDto) throws MailException {
        authenticationService.signUpFishingInstructor(new FishingInstructor(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<UserDto> addAdmin(@RequestBody UserDto userDto) throws MailException {
        authenticationService.signUpAdmin(new Admin(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmAccount(@RequestParam(name = "token") String token) {
        authenticationService.confirmAccount(token);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}