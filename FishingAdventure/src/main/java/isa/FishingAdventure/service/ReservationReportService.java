package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.ReservationReport;
import isa.FishingAdventure.repository.ReservationReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationReportService {

    @Autowired
    private ReservationReportRepository reportRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    public ReservationReport save(Integer reservationId, String content) {
        Reservation reservation = reservationService.getById(reservationId);
        reservation.setReportFilled(true);
        return reportRepository.save(new ReservationReport(content, reservation));
    }

    public void managePenalties(String option, ReservationReport report) {
        switch (option) {
            case "sunctionRequest":
                report.setWaitingReview(true);
                reportRepository.save(report);
                break;
            case "didntShowUp":
                Client client = report.getReservation().getClient();
                client.setPenalties(client.getPenalties() + 1);
                clientService.save(client);
                break;
            default:
                break;
        }
    }
}
