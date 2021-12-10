package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.FishingAdventure.dto.FishingInstructorDto;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.service.FishingInstructorService;

@RestController
@RequestMapping(value = "fishingInstructor")
public class FishingInstructorController{
	
	@Autowired
	private FishingInstructorService fishingInstructorService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<FishingInstructorDto>> getAllFishingInstructors(){
		List<User> fishingInstructors = fishingInstructorService.findAll();
		
		List<FishingInstructorDto> fishingInstructorDtos = new ArrayList<FishingInstructorDto>();
		for (User u : fishingInstructors) {
			FishingInstructor fishingInstructor = (FishingInstructor) u;
			//FishingInstructorDto dto = new FishingInstructorDto(fishingInstructor);
			//fishingInstructorDtos.add(dto);
		}
		
		return new ResponseEntity<>(fishingInstructorDtos, HttpStatus.OK);
	}
}
