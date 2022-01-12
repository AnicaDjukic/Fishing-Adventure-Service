package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertiserReservationDto {
    private Integer reservationId;

    private Integer appointmentId;

    private String name;

    private String clientName;

    private String clientSurname;

    private String clientEmail;

    private int persons;

    private Date startDate;

    private Date endDate;

    private double price;

    private List<AdditionalService> additionalServices;

    private String status;

    private String imagePath;

    private String place;

    private Boolean isReportFilled;

    public AdvertiserReservationDto(Integer reservationId, Integer appointmentId, String name, String clientName,
            String clientSurname, String clientEmail, int persons, Date startDate, Date endDate,
            double price, List<AdditionalService> additionalServices, String status,
            String imagePath, String place, Boolean isReportFilled) {
        this.reservationId = reservationId;
        this.appointmentId = appointmentId;
        this.name = name;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientEmail = clientEmail;
        this.persons = persons;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.additionalServices = additionalServices;
        this.status = status;
        this.imagePath = imagePath;
        this.place = place;
        this.isReportFilled = isReportFilled;
    }

    public AdvertiserReservationDto(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.appointmentId = reservation.getAppointment().getAppointmentId();
        this.clientName = reservation.getClient().getName();
        this.clientSurname = reservation.getClient().getSurname();
        this.clientEmail = reservation.getClient().getEmail();
        this.persons = reservation.getAppointment().getMaxPersons(); // TODO: replace with field for the number of
                                                                     // people
        this.startDate = reservation.getAppointment().getStartDate();
        this.endDate = reservation.getAppointment().getEndDate();
        this.price = reservation.getAppointment().getPrice();
        this.additionalServices = new ArrayList<>(reservation.getAppointment().getChosenServices());
        this.status = "";
        this.place = reservation.getAppointment().getPlace();
        this.isReportFilled = reservation.getReportFilled();
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getIsReportFilled() {
        return this.isReportFilled;
    }

    public void setIsReportFilled(Boolean isReportFilled) {
        this.isReportFilled = isReportFilled;
    }

}
