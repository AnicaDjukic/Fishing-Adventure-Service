package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.ReservationInfoDto;
import isa.FishingAdventure.dto.ReservationIssueDto;
import isa.FishingAdventure.model.Complaint;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.ReservationReport;
import isa.FishingAdventure.repository.CompliantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private CompliantRepository compliantRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public void addNewCompliant(Complaint newComplaint, Integer reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        newComplaint.setReservation(reservation);
        compliantRepository.save(newComplaint);
    }

    public boolean exists(Integer reservationId) {
        for(Complaint complaint : compliantRepository.findAll()){
            if(complaint.getReservation().getReservationId().equals(reservationId))
                return true;
        }

        return false;
    }

    public Complaint findById(int id) {
        return compliantRepository.getById(id);
    }

    public List<ReservationIssueDto> getComplaintsForAdmin() {
        List<ReservationIssueDto> complaintDtos = new ArrayList<>();
        for (Complaint complaint : getUnansweredComplaints()) {
            complaintDtos.add(createReservationIssueDto(complaint));
        }

        return complaintDtos;
    }

    private List<Complaint> getUnansweredComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        for (Complaint complaint : compliantRepository.findAll()) {
            if (!complaint.isReplySent()) {
                complaints.add(complaint);
            }
        }
        return complaints;
    }

    private ReservationIssueDto createReservationIssueDto(Complaint complaint) {
        ReservationInfoDto reservationInfoDto = reservationService.getReservationInfo(complaint.getReservation());
        ReservationIssueDto reportDto = new ReservationIssueDto();
        reportDto.setId(complaint.getId());
        reportDto.setContent(complaint.getContent());
        reportDto.setClientEmail(reservationInfoDto.getClientEmail());
        reportDto.setAdvertiserEmail(reservationInfoDto.getAdvertiserEmail());
        reportDto.setServiceName(serviceProfileService.getById(reservationInfoDto.getServiceId()).getName());
        reportDto.setAdvertiserFullName(userService.getFullNameByEmail(reservationInfoDto.getAdvertiserEmail()));
        reportDto.setClientFullName(userService.getFullNameByEmail(reservationInfoDto.getClientEmail()));

        return reportDto;
    }

    public void sendComplaintResponse(String email, String message) {
        try {
            emailService.sendEmail(email, "Reservation complaint", message);
        } catch (Exception e) {
            System.out.println("Email could not be sent.");
        }
    }

    public void markComplaintAsRepliedTo(Complaint complaint) {
        complaint.setReplySent(true);
        compliantRepository.save(complaint);
    }
}
