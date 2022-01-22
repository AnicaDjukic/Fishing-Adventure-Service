package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AdvertiserReservationDto;
import isa.FishingAdventure.dto.ReservationInfoDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.ReservationRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private AdvertiserEarningsService advertiserEarningsService;

    protected final Log loggerLog = LogFactory.getLog(getClass());

    public void save(Reservation reservation) {
        repository.save(reservation);
    }

    public List<Reservation> findAll() throws AccessDeniedException {
        return repository.findAll();
    }

    public boolean createReservationForClient(String token, Appointment newAppointment, Integer serviceProfileId) {
        String clientEmail = tokenUtils.getEmailFromToken(token);
        return createReservation(clientEmail, newAppointment, serviceProfileId);
    }

    @Transactional
    public boolean createReservation(String clientEmail, Appointment newAppointment, Integer serviceProfileId) {
        boolean status = true;
        try {
            if (serviceProfileService.isServiceAvailableForDateRange(serviceProfileId, newAppointment.getStartDate(),
                    newAppointment.getEndDate()) && !clientService.isClientBanned(clientEmail)) {
                Client client = clientService.findByEmail(clientEmail);
                saveNewAppointment(newAppointment, serviceProfileId);
                Reservation newReservation = new Reservation(false, newAppointment, client, false);
                save(newReservation);
                advertiserEarningsService.calculateEarningsForNewReservation(
                        getAdvertiserByServiceId(serviceProfileId).getEmail(), newReservation);
                String text = emailService.createConfirmReservationEmail(newAppointment, serviceProfileId);
                emailService.sendEmail(clientEmail, "Reservation confirmation", text);
            } else {
                status = false;
            }
        } catch (MessagingException e) {
            loggerLog.debug("Email could not be sent.");
        } catch (OptimisticEntityLockException e) {
            loggerLog.debug("Optimistic lock exception.");
            status = false;
        }
        return status;
    }

    @Transactional
    public void saveNewAppointment(Appointment newAppointment, Integer serviceProfileId) {
        appointmentService.save(newAppointment);
        serviceProfileService.addAppointment(serviceProfileId, newAppointment);
    }

    public Reservation getById(Integer id) {
        return repository.getById(id);
    }

    public ReservationInfoDto getReservationInfo(Reservation reservation) {
        ServiceProfile profile = serviceProfileService.getByAppointmentsIsContaining(reservation.getAppointment());
        User advertiser = getAdvertiserByServiceId(profile.getId());
        return new ReservationInfoDto(reservation.getReservationId(), profile.getId(),
                advertiser.getEmail(), reservation.getClient().getEmail());
    }

    private User getAdvertiserByServiceId(int id) {
        User advertiser;
        if (adventureService.findByIdIfExists(id).isPresent())
            advertiser = adventureService.findById(id).getFishingInstructor();
        else if (vacationHomeService.findByIdIfExists(id).isPresent())
            advertiser = vacationHomeService.findById(id).getVacationHomeOwner();
        else
            advertiser = boatService.findById(id).getBoatOwner();
        return advertiser;
    }

    public List<Reservation> getClientCurrentReservations(String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        Client client = clientService.findByEmail(email);
        List<Reservation> reservations = repository.findByClient(client);
        List<Reservation> currentReservations = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCanceled().equals(true))
                continue;
            if (r.getAppointment().getEndDate().after(new Date())) {
                currentReservations.add(r);
            }
        }
        return currentReservations;
    }

    public List<ServiceProfile> getServiceProfiles(List<Reservation> reservations) {
        List<ServiceProfile> serviceProfiles = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCanceled().equals(true))
                continue;
            for (ServiceProfile s : serviceProfileService.findAll()) {
                if (s.getAppointments().contains(r.getAppointment())) {
                    serviceProfiles.add(s);
                    break;
                }
            }
        }
        return serviceProfiles;
    }

    public List<Reservation> getClientPastReservations(String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        Client client = clientService.findByEmail(email);
        List<Reservation> reservations = repository.findByClient(client);
        List<Reservation> currentReservations = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCanceled().equals(true))
                continue;
            if (r.getAppointment().getEndDate().before(new Date())) {
                currentReservations.add(r);
            }
        }
        return currentReservations;
    }

    public List<AdvertiserReservationDto> findAllReservationsByAdvertiser(String token) {
        String email = tokenUtils.getEmailFromToken(token);
        String role = tokenUtils.getRoleFromToken(token);
        return findAllReservationsForAdvertiser(email, role);
    }

    private List<AdvertiserReservationDto> findAllReservationsForAdvertiser(String email, String role) {
        List<AdvertiserReservationDto> reservationDtos = new ArrayList<>();
        for (ServiceProfile serviceProfile : findServiceProfilesByAdvertiser(email, role)) {
            reservationDtos.addAll(getReservationsFromAppointments(serviceProfile));
        }
        return reservationDtos;
    }

    private List<ServiceProfile> findServiceProfilesByAdvertiser(String email, String role) {
        List<ServiceProfile> serviceProfiles;
        if (role.equals("ROLE_FISHING_INSTRUCTOR")) {
            FishingInstructor instructor = instructorService.findByEmail(email);
            serviceProfiles = adventureService.findFishingAdventuresByFishingInstructor(instructor);
        } else if (role.equals("ROLE_VACATION_HOME_OWNER")) {
            VacationHomeOwner homeOwner = homeOwnerService.findByEmail(email);
            serviceProfiles = vacationHomeService.findVacationHomesByVacationHomeOwner(homeOwner);
        } else {
            BoatOwner boatOwner = boatOwnerService.findByEmail(email);
            serviceProfiles = boatService.findBoatsByBoatOwner(boatOwner);
        }
        return serviceProfiles;
    }

    private List<AdvertiserReservationDto> getReservationsFromAppointments(ServiceProfile serviceProfile) {
        List<AdvertiserReservationDto> reservationDtos = new ArrayList<>();
        for (Appointment appointment : serviceProfile.getAppointments()) {
            for (Reservation reservation : findAll()) {
                if (appointment.getAppointmentId().equals(reservation.getAppointment().getAppointmentId())) {
                    AdvertiserReservationDto reservationDto = new AdvertiserReservationDto(reservation, serviceProfile);
                    reservationDto.setStatus(checkReservationStatus(reservationDto.getIsReportFilled(),
                            reservationDto.getStartDate(), reservationDto.getEndDate()));
                    reservationDtos.add(reservationDto);
                }
            }
        }
        return reservationDtos;
    }

    public boolean isReservationCanceled(int appointmentId) {
        boolean isCanceled = false;
        for (Reservation reservation : findAll()) {
            if (reservation.getAppointment().getAppointmentId().equals(appointmentId)
                    && reservation.getCanceled().equals(true)) {
                isCanceled = true;
                break;
            }
        }
        return isCanceled;
    }

    private String checkReservationStatus(Boolean isReportFilled, Date startDate, Date endDate) {
        String reservationStatus = "";
        Date today = new Date(System.currentTimeMillis());
        if (startDate.after(today))
            reservationStatus = "Upcoming";
        else if (endDate.before(today) && isReportFilled.equals(false))
            reservationStatus = "Finished";
        else if (endDate.before(today) && isReportFilled.equals(true))
            reservationStatus = "Report filled";
        else
            reservationStatus = "Current";

        return reservationStatus;
    }

    @Transactional
    public void reserveSpecialOffer(String token, Integer offerId, Integer serviceProfileId) {
        String email = tokenUtils.getEmailFromToken(token);
        Client client = clientService.findByEmail(email);
        Appointment appointment = appointmentService.findById(offerId);
        appointment.setReserved(true);
        appointment.setDateCreated(new Date());
        appointmentService.save(appointment);
        Reservation newReservation = new Reservation(false, appointment, client, false);
        save(newReservation);
        advertiserEarningsService.calculateEarningsForNewReservation(
                getAdvertiserByServiceId(serviceProfileId).getEmail(), newReservation);
    }

    public Reservation findById(Integer id) {
        return repository.findById(id).orElse(new Reservation());
    }

    public boolean cancelReservation(Integer id) {
        for (Reservation r : findAll()) {
            if (r.getReservationId().equals(id)) {
                r.setCanceled(true);
                r.getAppointment().setCancelled(true);
                save(r);
                return true;
            }
        }
        return false;
    }
    
    public void calculateEarningsForCancelledReservation(int id) {
        Reservation res = getById(id);
        ServiceProfile serviceProfile = findServiceProfilesByReservation(res);
        if (serviceProfile != null)
            advertiserEarningsService.calculateEarningsForCancelledReservation(res,
                    serviceProfile.getCancellationRule());
    }

    private ServiceProfile findServiceProfilesByReservation(Reservation reservation) {
        for (ServiceProfile serviceProfile : serviceProfileService.findAll()) {
            for (Appointment appointment : serviceProfile.getAppointments()) {
                if (appointment.getAppointmentId().equals(reservation.getAppointment().getAppointmentId()))
                    return serviceProfile;
            }
        }
        return null;
    }

    public List<Reservation> getClientReservationsForServiceProfile(String token, Integer serviceId) {
        String email = tokenUtils.getEmailFromToken(token);
        List<Reservation> reservations = new ArrayList<>();
        for (Appointment ap : appointmentService.getAppointmentsByServiceId(serviceId)) {
            for (Reservation r : findAll()) {
                if (r.getAppointment().getAppointmentId().equals(ap.getAppointmentId())
                        && r.getClient().getEmail().equals(email)) {
                    reservations.add(r);
                    break;
                }
            }
        }
        return reservations;
    }

    public boolean overlapsWithDateRange(List<Reservation> reservations, Date start, Date end) {
        for (Reservation r : reservations) {
            if (r.getCanceled().equals(false))
                continue;
            if (start.equals(r.getAppointment().getStartDate()) || end.equals(r.getAppointment().getEndDate()) ||
                    end.equals(r.getAppointment().getStartDate()) || start.equals(r.getAppointment().getEndDate())
                    || (start.after(r.getAppointment().getStartDate()) && start.before(r.getAppointment().getEndDate()))
                    || (end.after(r.getAppointment().getStartDate()) && end.before(r.getAppointment().getEndDate()))
                    || (start.before(r.getAppointment().getStartDate()) && end.after(r.getAppointment().getEndDate())))
                return true;
        }
        return false;
    }

}
