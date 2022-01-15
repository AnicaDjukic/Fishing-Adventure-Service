package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.ReservationIssueDto;
import isa.FishingAdventure.dto.ReservationInfoDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.ReservationReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationReportService {

    @Autowired
    private ReservationReportRepository reportRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private EmailService emailService;

    public ReservationReport save(Integer reservationId, String content) {
        Reservation reservation = reservationService.getById(reservationId);
        reservation.setReportFilled(true);
        return reportRepository.save(new ReservationReport(content, reservation));
    }

    public ReservationReport findById(Integer id) {
        return reportRepository.getById(id);
    }

    public void managePenalties(String option, ReservationReport report) {
        switch (option) {
            case "sanctionRequest":
                report.setWaitingReview(true);
                reportRepository.save(report);
                break;
            case "didntShowUp":
                clientService.addPenaltyToClient(report.getReservation().getClient());
                break;
            default:
                break;
        }
    }

    public void reviewReport(ReservationReport report, String advertiserEmail, boolean isSanctioned) {
        if (isSanctioned) {
            clientService.addPenaltyToClient(report.getReservation().getClient());
        }
        report.setWaitingReview(false);
        reportRepository.save(report);
        sendEmailAboutReport(advertiserEmail, report, isSanctioned);
        if (isSanctioned)
            sendEmailAboutReport(report.getReservation().getClient().getEmail(), report, true);
    }

    public List<ReservationIssueDto> getAdvertiserReportsForAdmin() {
        List<ReservationIssueDto> reportDtos = new ArrayList<>();
        for (ReservationReport report : getAllReportsAwaitingReview()) {
            reportDtos.add(createReservationIssueDto(report));
        }
        return reportDtos;
    }

    private List<ReservationReport> getAllReportsAwaitingReview() {
        List<ReservationReport> reports = new ArrayList<>();
        for (ReservationReport report : reportRepository.findAll()) {
            if (report.getWaitingReview()) {
                reports.add(report);
            }
        }
        return reports;
    }

    private ReservationIssueDto createReservationIssueDto(ReservationReport report) {
        ReservationInfoDto reservationInfoDto = reservationService.getReservationInfo(report.getReservation());
        ReservationIssueDto reportDto = new ReservationIssueDto(report.getId(), report.getReport(),
                reservationInfoDto.getClientEmail(), reservationInfoDto.getAdvertiserEmail());
        reportDto.setServiceName(serviceProfileService.getById(reservationInfoDto.getServiceId()).getName());
        reportDto.setAdvertiserFullName(userService.getFullNameByEmail(reservationInfoDto.getAdvertiserEmail()));
        reportDto.setClientFullName(userService.getFullNameByEmail(reservationInfoDto.getClientEmail()));
        return reportDto;
    }

    private void sendEmailAboutReport(String email, ReservationReport report, boolean isSanctioned) {
        String emailText;
        if (report.getReservation().getClient().getEmail().equals(email)) {
            emailText = emailService.createGenericEmail("Reservation report",
                    "You have received one penalty because of a complained filed against you. If you receive 3" +
                            "penalties you will not be able to make any new reservation until the end of the month.");
        } else {
            User user = report.getReservation().getClient();
            if (isSanctioned) {
                emailText = emailService.createGenericEmail("Reservation report",
                        "Your complaint against " + user.getName() + " " + user.getSurname() + " has been " +
                                "approved. They have been sanctioned and will be receiving a penalty.");
            } else {
                emailText = emailService.createGenericEmail("Reservation report",
                        "Your complaint against " + user.getName() + " " + user.getSurname() + " has not been " +
                                "approved. They will not be sanctioned at this time.");

            }
        }
        try {
            emailService.sendEmail(email, "Reservation report", emailText);
        } catch (Exception e) {
            System.out.println("Email could not be sent.");
        }

    }

}
