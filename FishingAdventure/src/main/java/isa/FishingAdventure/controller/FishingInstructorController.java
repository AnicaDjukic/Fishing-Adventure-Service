package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.InstructorAvailabilityDto;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.InstructorAvailability;
import isa.FishingAdventure.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "fishingInstructor")
@CrossOrigin
public class FishingInstructorController{

	@Autowired
    private FishingInstructorService instructorService;

    @GetMapping(value = "/getAllAvailabilitiesForInstructor")
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<List<InstructorAvailabilityDto>> getAllAvailabilitiesForInstructor(@RequestHeader("Authorization") String token) {
        FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
        return new ResponseEntity<>(instructorService.getAllAvailabilitiesForInstructor(instructor.getEmail()), HttpStatus.OK);
    }

    @PutMapping(value = "/addAvailability")
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<String> addAvailability(@RequestHeader("Authorization") String token,
                                                                                             @RequestBody InstructorAvailabilityDto dto) {
        FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
        instructorService.addAvailabilityPeriodToInstructor(instructor.getEmail(), new InstructorAvailability(dto.getStartDate(), dto.getEndDate()));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PutMapping(value = "/updateAvailability")
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateAvailability(@RequestHeader("Authorization") String token,
                                                                                             @RequestBody InstructorAvailabilityDto dto) {
        FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
        instructorService.updateAvailabilityPeriodForInstructor(instructor.getEmail(), new InstructorAvailability(dto.getStartDate(), dto.getEndDate()), dto.getId());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "/deleteAvailability")
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<String> getAllAvailabilitiesForInstructor(@RequestHeader("Authorization") String token,
                                                                        @RequestParam("id") Integer id) {
        FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
        instructorService.deleteAvailabilityPeriodForInstructor(instructor.getEmail(), id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
