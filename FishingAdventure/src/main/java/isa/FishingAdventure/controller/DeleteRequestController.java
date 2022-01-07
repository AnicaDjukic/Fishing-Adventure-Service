package isa.FishingAdventure.controller;

import isa.FishingAdventure.service.DeleteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "deleteRequest")
public class DeleteRequestController {

    @Autowired
    private DeleteRequestService deleteRequestService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createDeleteRequest(@RequestHeader("Authorization") String token, @RequestBody String content) {
        System.out.println(content);
        deleteRequestService.createDeleteRequest(token, content);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/isRequested")
    public ResponseEntity<Boolean> checkIfRequestExist(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(deleteRequestService.checkIfRequestExist(token), HttpStatus.OK);
    }
}
