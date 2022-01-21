package isa.FishingAdventure.controller;

import isa.FishingAdventure.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import isa.FishingAdventure.service.AdminService;

@RestController
@RequestMapping(value = "admin")
@CrossOrigin
public class AdminController{
	
	@Autowired
	private AdminService adminService;

	@GetMapping(value = "/isHeadAdmin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> isHeadAdmin(@RequestHeader("Authorization") String token) {
		Admin admin = adminService.findByToken(token.split(" ")[1]);
		boolean isHead = adminService.isAdminHeadAdmin(admin);
		return new ResponseEntity<>(isHead, HttpStatus.OK);
	}
}
