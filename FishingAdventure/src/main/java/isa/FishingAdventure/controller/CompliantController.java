package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewCompliantDto;
import isa.FishingAdventure.model.Complaint;
import isa.FishingAdventure.service.CompliantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "compliant")
public class CompliantController {

    @Autowired
    private CompliantService compliantService;

    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<NewCompliantDto> saveNewComplaint(@RequestBody NewCompliantDto dto) {
        Complaint newComplaint = new Complaint(dto.getContent(), false, dto.getServiceId());
        compliantService.addNewCompliant(newComplaint, dto.getReservationId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "exists/{reservationId}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer reservationId) {
        return new ResponseEntity<>(compliantService.exists(reservationId), HttpStatus.OK);
    }
}
