package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AdvertiserReservationDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.ReservationRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FishingInstructorService instructorService;

    @Autowired
    private VacationHomeOwnerService homeOwnerService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    @Autowired
    private FishingAdventureService adventureService;

    @Autowired
    private VacationHomeService vacationHomeService;

    @Autowired
    private BoatService boatService;

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

    public List<Reservation> findAll() throws AccessDeniedException {
        return repository.findAll();
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
            newReservation.setClient(client);
            newReservation.setCanceled(false);
            save(newReservation);
            String emailText = createEmail(client, newAppointment, serviceProfile);
            emailService.sendEmail(email, "Reservation conformation", emailText);

        } catch (Exception e) {
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
        for (AdditionalService as : newAppointment.getChosenServices()) {
            content.append("\t").append(as.getName()).append(" ").append(as.getPrice()).append("$\n");
        }
        content.append("Total price: ").append(newAppointment.getPrice()).append("$\n");
        content.append("You can cancel your reservation up to 3 days before it is scheduled\n");
        content.append("Sincerely,\n").append("Angler");

        return content.toString();
    }

    public List<AdvertiserReservationDto> findAllReservationsByAdvertiser(String email, String role) {
        List<AdvertiserReservationDto> reservationDtos = new ArrayList<>();
        if (role.equals("ROLE_FISHING_INSTRUCTOR"))
            reservationDtos = findAllReservationsForInstructor(email);
        else if (role.equals("ROLE_VACATION_HOME_OWNER"))
            reservationDtos = null; //TODO: implement findAllReservationsForHomeOwner(email);
        else
            reservationDtos = null; //TODO: implement findAllReservationsForBoatOwner(email);

        return reservationDtos;
    }

    private List<AdvertiserReservationDto> findAllReservationsForInstructor(String email) {
        FishingInstructor instructor = instructorService.findByEmail(email);
        List<AdvertiserReservationDto> reservationDtos = new ArrayList<>();
        for (FishingAdventure adventure : adventureService.findByFishingInstructor(instructor)) {
            List<AdvertiserReservationDto> resDtos = getReservationsFromAppointments(adventure.getAppointments());
            for (AdvertiserReservationDto reservationDto : resDtos) {
                reservationDto.setName(adventure.getName());
                for(Image img : adventure.getImages()) {
                    if (img.isCoverImage()) {
                        reservationDto.setImagePath(img.getPath());
                        break;
                    }
                }
                reservationDto.setStatus(checkReservationStatus(reservationDto.getStartDate(), reservationDto.getEndDate()));
            }
            reservationDtos.addAll(resDtos);
        }
        return reservationDtos;
    }

    private List<AdvertiserReservationDto> getReservationsFromAppointments(Set<Appointment> appointments) {
        List<AdvertiserReservationDto> reservationDtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            for (Reservation reservation : findAll()) {
                if (appointment.getAppointmentId().equals(reservation.getAppointment().getAppointmentId())) {
                    AdvertiserReservationDto reservationDto = new AdvertiserReservationDto(reservation);
                    reservationDtos.add(reservationDto);
                }
            }
        }

        return reservationDtos;
    }

    private String checkReservationStatus(Date startDate, Date endDate) {
        String reservationStatus = "";
        Date today = new Date(System.currentTimeMillis());
        if (startDate.after(today))
            reservationStatus = "Upcoming";
        else if (endDate.before(today))
            reservationStatus = "Finished";
        else
            reservationStatus = "Current";

        return reservationStatus;
    }
}
