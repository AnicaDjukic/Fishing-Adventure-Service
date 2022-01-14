package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.DeleteRequestDto;
import isa.FishingAdventure.dto.DeleteRequestForAdminDto;
import isa.FishingAdventure.model.DeleteRequest;
import isa.FishingAdventure.service.DeleteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "deleteRequest")
public class DeleteRequestController {

    @Autowired
    private DeleteRequestService deleteRequestService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createDeleteRequest(@RequestHeader("Authorization") String token, @RequestBody DeleteRequestDto requestDto) {
        deleteRequestService.createDeleteRequest(token, requestDto.getContent());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/isRequested")
    public ResponseEntity<Boolean> checkIfRequestExist(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(deleteRequestService.checkIfRequestExist(token), HttpStatus.OK);
    }

    @GetMapping(value="getDeleteRequests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DeleteRequestForAdminDto>> getAllRegistrationRequests(@RequestHeader("Authorization") String token) {
        List<DeleteRequestForAdminDto> requestDtos = new ArrayList<>();
        for (DeleteRequest request : deleteRequestService.getDeleteRequests(token)) {
            DeleteRequestForAdminDto requestDto = new DeleteRequestForAdminDto(request);
            requestDtos.add(requestDto);
        }

        return new ResponseEntity<>(requestDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/approveDeletion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<String> approveDeletionRequest(@RequestParam("id") Integer id, @RequestParam("response") String response) {
        deleteRequestService.approveRequest(id, response);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "/rejectDeletion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<String> rejectDeletionRequest(@RequestParam("id") Integer id, @RequestParam("response") String response) {
        deleteRequestService.rejectRequest(id, response);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
