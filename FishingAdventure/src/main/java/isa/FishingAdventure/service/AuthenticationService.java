package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.UserTokenState;
import isa.FishingAdventure.exception.ResourceConflictException;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import isa.FishingAdventure.dto.JwtAuthenticationRequest;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

@Service
public class AuthenticationService {

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

    @Autowired
    private UserCategoryService categoryService;

    private static final String EMAIL_EXISTS = "Email already exists";

    public UserTokenState login(JwtAuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        if (!user.isActivated()) {
            return null;
        }
        return getAuthentication(user);
    }

    public List<String> getRoles(User user) {
        return user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public String getRefreshToken(User user) {
        return tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), true);
    }

    public String getToken(User user) {
        return tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), false);
    }

    public UserTokenState getAuthentication(User user) {
        return new UserTokenState(getToken(user), getRefreshToken(user), getRoles(user));
    }

    public UserTokenState refreshToken(String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User user = userService.findByEmail(email);
        return getAuthentication(user);
    }

    public void signUpClient(Client client) throws MessagingException {
        if (userService.isEmailRegistered(client.getEmail()).equals(true)) {
            throw new ResourceConflictException(EMAIL_EXISTS);
        } else {
            clientService.saveNewClient(client);
            sendRegistrationEmail(client);
        }
    }

    public void signUpHomeOwner(VacationHomeOwner vacationHomeOwner) {
        if (userService.isEmailRegistered(vacationHomeOwner.getEmail()).equals(true)) {
            throw new ResourceConflictException(EMAIL_EXISTS);
        } else {
            homeOwnerService.saveNewHomeOwner(vacationHomeOwner);
        }
    }

    public void signUpBoatOwner(BoatOwner boatOwner) {
        if (userService.isEmailRegistered(boatOwner.getEmail()).equals(true)) {
            throw new ResourceConflictException(EMAIL_EXISTS);
        } else {
            boatOwnerService.saveNewBoatOwner(boatOwner);
        }
    }

    public void signUpFishingInstructor(FishingInstructor fishingInstructor) {
        if (userService.isEmailRegistered(fishingInstructor.getEmail()).equals(true)) {
            throw new ResourceConflictException(EMAIL_EXISTS);
        } else {
            instructorService.saveNewInstructor(fishingInstructor);
        }
    }

    public void confirmAccount(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        Client user = clientService.findByEmail(confirmationToken.getEmail());
        user.setActivated(true);
        user.setCategory(categoryService.getUserCategoryByName("REGULAR_CLIENT"));
        clientService.save(user);
        confirmationTokenService.delete(confirmationToken);
    }

    private void sendRegistrationEmail(Client client) throws MailException, MessagingException {
        String token = generateRegistrationToken(client.getEmail());
        String html = emailService.createConfirmRegistrationEmail(token);
        emailService.sendEmail(client.getEmail(), "Confirm registration", html);
    }

    private String generateRegistrationToken(String email) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setEmail(email);
        confirmationToken.setToken(tokenUtils.generateToken(email, "ROLE_CLIENT", false));
        confirmationTokenService.save(confirmationToken);
        return confirmationToken.getToken();
    }
}
