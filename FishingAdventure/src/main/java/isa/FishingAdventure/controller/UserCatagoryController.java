package isa.FishingAdventure.controller;

import isa.FishingAdventure.service.UserCatagoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "userCategory")
public class UserCatagoryController {
	
	@Autowired
	private UserCatagoryService catagoryService;
}
