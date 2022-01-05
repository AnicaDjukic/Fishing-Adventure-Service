package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService{
	
	@Autowired
	private AppointmentRepository appointmentRepository;

    public void save(Appointment newAppointment) {
        appointmentRepository.save(newAppointment);
    }
}
