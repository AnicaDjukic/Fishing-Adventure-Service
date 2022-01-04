package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService{
	
	@Autowired
	private AppointmentRepository appointmentRepository;
    @Autowired
    private VacationHomeService vacationHomeService;

    public void save(Appointment newAppointment) {
        appointmentRepository.save(newAppointment);
    }

    public void addAppointmentToVacationHome(Integer id, Appointment newAppointment) {
        VacationHome vacationHome = vacationHomeService.findById(id);
        vacationHome.getAppointments().add(newAppointment);
        vacationHomeService.save(vacationHome);
    }
}
