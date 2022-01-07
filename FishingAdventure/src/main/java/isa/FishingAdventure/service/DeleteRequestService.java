package isa.FishingAdventure.service;

import isa.FishingAdventure.model.DeleteRequest;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.repository.DeleteRequestRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeleteRequestService {

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    public void createDeleteRequest(String token, String content) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User user = userService.findByEmail(email);
        DeleteRequest newRequest = new DeleteRequest(email, content, new Date(), user.getUserType().getName());
        deleteRequestRepository.save(newRequest);
    }

    public boolean checkIfRequestExist(String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        return deleteRequestRepository.findByEmail(email) != null;
    }
}
