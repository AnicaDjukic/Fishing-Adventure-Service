package isa.FishingAdventure.controller;

import isa.FishingAdventure.service.InstructorAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class InstructorAvailabilityController {

    @Autowired
    private InstructorAvailabilityService instructorAvailabilityService;
}
