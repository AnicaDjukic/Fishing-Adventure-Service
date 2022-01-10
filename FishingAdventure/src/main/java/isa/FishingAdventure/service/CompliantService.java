package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Complaint;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.repository.CompliantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompliantService {

    @Autowired
    private CompliantRepository repository;

    @Autowired
    private ReservationService reservationService;

    public void addNewCompliant(Complaint newComplaint, Integer reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        newComplaint.setReservation(reservation);
        repository.save(newComplaint);
    }

    public boolean exists(Integer reservationId) {
        for(Complaint complaint : repository.findAll()){
            if(complaint.getReservation().getReservationId().equals(reservationId))
                return true;
        }

        return false;
    }
}
