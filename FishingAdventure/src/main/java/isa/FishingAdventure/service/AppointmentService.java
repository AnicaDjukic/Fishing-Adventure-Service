package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.repository.AppointmentRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private VacationHomeService vacationHomeService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private FishingAdventureService adventureService;

    public Appointment save(Appointment newAppointment) {
        return appointmentRepository.save(newAppointment);
    }

    public void deleteById(Integer offerId, Integer serviceProfileId) {
        serviceProfileService.deleteOffer(offerId, serviceProfileId);
        appointmentRepository.deleteById(offerId);
    }

    public void addAppointmentToServiceProfile(Integer id, Appointment newAppointment) {
        ServiceProfile profile = serviceProfileService.getById(id);
        profile.getAppointments().add(newAppointment);
        serviceProfileService.save(profile);
    }

    public Integer createAppointment(Appointment newAppointment, Duration duration, Integer serviceProfileId)
            throws MessagingException {
        newAppointment.setDateCreated(new Date());
        newAppointment.setDuration(duration);
        Appointment savedAppointment = save(newAppointment);
        addAppointmentToServiceProfile(serviceProfileId, newAppointment);
        ServiceProfile serviceProfile = serviceProfileService.getById(serviceProfileId);
        sendEmailsToClients(newAppointment, serviceProfile,
                clientService.getClientSubscribedToServiceProfile(serviceProfileId));
        return savedAppointment.getAppointmentId();
    }

    private void sendEmailsToClients(Appointment newAppointment, ServiceProfile serviceProfile, List<User> clients)
            throws MessagingException {
        for (User client : clients) {
            String text = emailService.createNewOfferEmail(newAppointment, serviceProfile);
            emailService.sendEmail(client.getEmail(), "New offer for " + serviceProfile.getName(), text);
        }
    }

    public List<AppointmentDto> getOffersByAdvertiser(String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User advertiser = userService.findByEmail(email);

        List<Appointment> appointments;
        switch (advertiser.getUserType().getName()) {
            case "ROLE_VACATION_HOME_OWNER":
                appointments = getValidAppointments(vacationHomeService.getOffersByAdvertiser(advertiser.getEmail()));
                break;
            case "ROLE_BOAT_OWNER":
                appointments = getValidAppointments(boatService.getOffersByAdvertiser(advertiser.getEmail()));
                break;
            default:
                appointments = getValidAppointments(adventureService.getOffersByAdvertiser(advertiser.getEmail()));
                break;
        }

        return createAppointmentDtos(appointments);
    }

    public List<Appointment> getValidAppointments(List<Appointment> appointments) {
        List<Appointment> validAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (!appointment.isReserved() && Date.from(
                    appointment.getDateCreated().toInstant().plusMillis(appointment.getDuration().toMillis() / 1000))
                    .after(new Date())) {
                validAppointments.add(appointment);
            }
        }
        return validAppointments;
    }

    public List<AppointmentDto> getOffersByServiceId(Integer id) {
        ServiceProfile serviceProfile = serviceProfileService.getById(id);
        return new ArrayList<>(
                createAppointmentDtos(getValidAppointments(new ArrayList<>(serviceProfile.getAppointments()))));
    }

    private List<AppointmentDto> createAppointmentDtos(List<Appointment> appointments) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                ServiceProfile profile = serviceProfileService.getByAppointmentsIsContaining(appointment);
                appointmentDtos.add(new AppointmentDto(appointment, profile));
            }
        }
        return appointmentDtos;
    }

    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id).orElse(new Appointment());
    }

    public List<Appointment> getAppointmentsByServiceId(Integer id) {
        ServiceProfile serviceProfile = serviceProfileService.getById(id);
        return new ArrayList<>(new ArrayList<>(serviceProfile.getAppointments()));
    }
}
