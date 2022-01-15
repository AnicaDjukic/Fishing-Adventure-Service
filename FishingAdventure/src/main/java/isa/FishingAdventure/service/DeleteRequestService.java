package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Admin;
import isa.FishingAdventure.model.DeleteRequest;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.repository.DeleteRequestRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeleteRequestService {

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    public List<DeleteRequest> findAllUnreviewed() {
        List<DeleteRequest> requests = new ArrayList<>();
        for (DeleteRequest request : deleteRequestRepository.findAll()) {
            if (!request.isReviewed()) {
                requests.add(request);
            }
        }
        return requests;
    }

    public DeleteRequest findById(int id) {
        return deleteRequestRepository.getById(id);
    }

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

    public List<DeleteRequest> getDeleteRequests(String token) {
        List<DeleteRequest> deleteRequests;
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        Admin admin = adminService.findByEmail(email);
        if (admin.getHeadAdmin())
            deleteRequests = findAllUnreviewed();
        else
            deleteRequests = findAllExceptAdmin();
        return deleteRequests;
    }

    private List<DeleteRequest> findAllExceptAdmin() {
        List<DeleteRequest> deleteRequests = new ArrayList<>();
        for (DeleteRequest request : findAllUnreviewed()) {
            if (!request.getUserRole().equals("ROLE_ADMIN")) {
                deleteRequests.add(request);
            }
        }
        return deleteRequests;
    }

    public void approveRequest(int id, String response) {
        DeleteRequest request = findById(id);
        request.setReviewed(true);
        userService.delete(request.getEmail());
        deleteRequestRepository.save(request);
        sendDeletionResponseEmail(response, request, true);
    }

    private void sendDeletionResponseEmail(String response, DeleteRequest request, boolean requestApproved) {
        String emailText;
        if (requestApproved) {
            emailText = emailService.createDeletionResponseEmail("Account deleted", response,
                    "We're sorry to see you go.");
        } else {
            emailText = emailService.createDeletionResponseEmail("Account not deleted", response,
                    "If you have any questions, just reply to this email. We're always happy to help out.");
        }
        try {
            emailService.sendEmail(request.getEmail(), "Request for account deletion", emailText);
        } catch (Exception e) {
            System.out.println("Email could not be sent.");
        }
    }

    public void rejectRequest(int id, String response) {
        DeleteRequest request = findById(id);
        request.setReviewed(true);
        deleteRequestRepository.save(request);
        sendDeletionResponseEmail(response, request, false);
    }
}
