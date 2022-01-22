package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.ReservationInfoDto;
import isa.FishingAdventure.dto.ReservationIssueDto;
import isa.FishingAdventure.model.Complaint;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.repository.CompliantRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    protected final Log loggerLog = LogFactory.getLog(getClass());

    public void addNewCompliant(Complaint newComplaint, Integer reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        newComplaint.setReservation(reservation);
        compliantRepository.save(newComplaint);
    }

    public boolean exists(Integer reservationId) {
        for (Complaint complaint : compliantRepository.findAll()) {
            if (complaint.getReservation().getReservationId().equals(reservationId))
                return true;
        }
        return false;
    }

    public Complaint findById(int id) {
        return compliantRepository.getById(id);
    }

    public List<Complaint> findAll() { return compliantRepository.findAll(); }

    public List<ReservationIssueDto> getComplaintsForAdmin() {
        List<ReservationIssueDto> complaintDtos = new ArrayList<>();
        for (Complaint complaint : getUnansweredComplaints()) {
            complaintDtos.add(createReservationIssueDto(complaint));
        }
        return complaintDtos;
    }

    public List<Complaint> getUnansweredComplaints() {
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
        return createReservationIssueDto(complaint, reservationInfoDto);
    }

    private ReservationIssueDto createReservationIssueDto(Complaint complaint, ReservationInfoDto reservationInfoDto) {
        ReservationIssueDto reportDto = new ReservationIssueDto(complaint.getId(), complaint.getContent(),
                reservationInfoDto.getClientEmail(), reservationInfoDto.getAdvertiserEmail());
        reportDto.setServiceName(serviceProfileService.getById(reservationInfoDto.getServiceId()).getName());
        reportDto.setAdvertiserFullName(userService.getFullNameByEmail(reservationInfoDto.getAdvertiserEmail()));
        reportDto.setClientFullName(userService.getFullNameByEmail(reservationInfoDto.getClientEmail()));
        return reportDto;
    }

    public void sendComplaintResponse(String email, String message) {
        try {
            String emailText = emailService.createGenericEmail("Reservation complaint", message);
            emailService.sendEmail(email, "Reservation complaint", emailText);
        } catch (Exception e) {
            loggerLog.debug("Email could not be sent.");
        }
    }

    public void markComplaintAsRepliedTo(Complaint complaint) {
        complaint.setReplySent(true);
        compliantRepository.save(complaint);
    }
}
