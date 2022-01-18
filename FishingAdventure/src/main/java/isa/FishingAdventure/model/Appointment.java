package isa.FishingAdventure.model;

import javax.persistence.*;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.dto.NewReservationDto;

import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "isReserved", nullable = false)
    private boolean isReserved;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    @Column(name = "maxPersons", nullable = false)
    private int maxPersons;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "dateCreated", nullable = false)
    private Date dateCreated;

    @ManyToMany(targetEntity = AdditionalService.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    public Set<AdditionalService> chosenServices;

    @Column(name = "owner_presence", nullable = false)
    private Boolean ownerPresence;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled;

    public Appointment(Integer appointmentId, double discount, boolean isReserved, Date startDate, Date endDate,
            Duration duration, int maxPersons, double price, Set<AdditionalService> chosenServices,
            boolean ownerPresence, boolean isCancelled) {
        this.appointmentId = appointmentId;
        this.discount = discount;
        this.isReserved = isReserved;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.maxPersons = maxPersons;
        this.price = price;
        this.chosenServices = chosenServices;
        this.dateCreated = new Date();
        this.ownerPresence = ownerPresence;
        this.isCancelled = isCancelled;
    }

    public Appointment(AppointmentDto dto) {
        this.dateCreated = new Date();
        this.place = "";
        this.discount = dto.getDiscount();
        this.isReserved = false;
        this.maxPersons = dto.getMaxPersons();
        this.price = dto.getPrice();
        this.ownerPresence = dto.getOwnerPresence();
        this.isCancelled = false;

        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        long duration = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
        if(duration != 0) {
            this.duration = Duration.ofDays(duration);
        } else {
            duration = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60);
            this.duration = Duration.ofMinutes(duration);
        }

        this.chosenServices = new HashSet<>();

        this.chosenServices.addAll(dto.getChosenServices());
    }

    public Appointment(NewReservationDto dto) {
        this.place = "";
        this.discount = 0.0;
        this.isReserved = true;
        this.isCancelled = false;
        this.dateCreated = new Date();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        long duration = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
        if(duration != 0) {
            this.duration = Duration.ofDays(duration);
        } else {
            duration = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60);
            this.duration = Duration.ofMinutes(duration);
        }
        this.maxPersons = dto.getPersons();
        if (this.chosenServices == null) {
            this.chosenServices = new HashSet<AdditionalService>();
        }
        this.chosenServices.addAll(dto.getChosenServices());
        this.price = dto.getPrice();
        this.ownerPresence = dto.isOwnersPresence();
    }

    public Appointment() {
    }

    public Integer getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getMaxPersons() {
        return this.maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Set<AdditionalService> getChosenServices() {
        return chosenServices;
    }

    public void setChosenServices(Set<AdditionalService> chosenServices) {
        this.chosenServices = chosenServices;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getOwnerPresence() {
        return ownerPresence;
    }

    public void setOwnerPresence(Boolean ownerPresence) {
        this.ownerPresence = ownerPresence;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }
}