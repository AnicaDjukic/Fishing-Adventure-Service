package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.ReservationRepository;

import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository repository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private ServiceProfileService serviceProfileService;

	@Autowired
	private EmailService emailService;

	public void save(Reservation reservation) {
		repository.save(reservation);
	}

	public boolean createReservation(String token, Appointment newAppointment, Integer serviceProfileId) {
		try {
			String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
			Client client = clientService.findByEmail(email);
			appointmentService.save(newAppointment);
			ServiceProfile serviceProfile = serviceProfileService.getById(serviceProfileId);
			serviceProfile.getAppointments().add(newAppointment);
			serviceProfileService.save(serviceProfile);
			Reservation newReservation = new Reservation();
			newReservation.setAppointment(newAppointment);
			newReservation.setPlace("");
			newReservation.setClient(client);
			save(newReservation);
			String emailText = createEmail(client, newAppointment, serviceProfile);
			emailService.sendEmail(email, "Reservation conformation", emailText);

		}catch (Exception e){
			return true;
		}

		return true;
	}

	private String createEmail(Client client, Appointment newAppointment, ServiceProfile serviceProfile) {
		StringBuilder content = new StringBuilder();
		content.append(client.getName()).append(" ").append(client.getSurname()).append(" thank you for your reservation!\n");
		content.append("Reservation for: ").append(serviceProfile.getName()).append("\n");
		content.append("Location: ").append(serviceProfile.getLocation().getAddress().getStreet());
		content.append(", ").append(serviceProfile.getLocation().getAddress().getCity());
		content.append(", ").append(serviceProfile.getLocation().getAddress().getCountry()).append("\n");
		content.append("Start date: ").append(newAppointment.getStartDate()).append("\n");
		content.append("End date: ").append(newAppointment.getEndDate()).append("\n");
		content.append("Number of persons: ").append(newAppointment.getMaxPersons()).append("\n");
		content.append("Chosen additional services:\n");
		for(AdditionalService as : newAppointment.getChosenServices()){
			content.append("\t").append(as.getName()).append(" ").append(as.getPrice()).append("$\n");
		}
		content.append("Total price: ").append(newAppointment.getPrice()).append("$\n");
		content.append("You can cancel your reservation up to 3 days before it is scheduled\n");
		content.append("Sincerely,\n").append("Angler");

		return content.toString();
	}
}
