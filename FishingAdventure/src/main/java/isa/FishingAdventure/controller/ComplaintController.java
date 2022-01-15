package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.ComplaintResponseDto;
import isa.FishingAdventure.dto.NewCompliantDto;
import isa.FishingAdventure.dto.ReservationIssueDto;
import isa.FishingAdventure.model.Complaint;
import isa.FishingAdventure.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "complaint")
@CrossOrigin
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<NewCompliantDto> saveNewComplaint(@RequestBody NewCompliantDto dto) {
        Complaint newComplaint = new Complaint(dto.getContent(), false, dto.getServiceId());
        complaintService.addNewCompliant(newComplaint, dto.getReservationId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "exists/{reservationId}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer reservationId) {
        return new ResponseEntity<>(complaintService.exists(reservationId), HttpStatus.OK);
    }

    @GetMapping(value="getClientComplaints")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<List<ReservationIssueDto>> getClientComplaints() {
        return new ResponseEntity<>(complaintService.getComplaintsForAdmin(), HttpStatus.OK);
    }

    @PutMapping(value = "/respondToComplaint/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<String> respondToComplaint(@RequestBody ComplaintResponseDto dto) {
        System.out.println(dto.getAdvertiserEmail() + "   " + dto.getAdvertiserMessage() + "    " + dto.getClientEmail() + "     " + dto.getClientMessage() + "     " + dto.getComplaintId());
        complaintService.sendComplaintResponse(dto.getClientEmail(), dto.getClientMessage());
        complaintService.sendComplaintResponse(dto.getAdvertiserEmail(), dto.getAdvertiserEmail());
        complaintService.markComplaintAsRepliedTo(complaintService.findById(dto.getComplaintId()));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
