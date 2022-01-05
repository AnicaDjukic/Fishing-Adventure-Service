package isa.FishingAdventure.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

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

    @ManyToMany(targetEntity = AdditionalService.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    public Set<AdditionalService> chosenServices;

    public Appointment(Integer appointmentId, double discount, boolean isReserved, Date startDate, Date endDate, Duration duration, int maxPersons, double price, Set<AdditionalService> chosenServices) {
        this.appointmentId = appointmentId;
        this.discount = discount;
        this.isReserved = isReserved;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.maxPersons = maxPersons;
        this.price = price;
        this.chosenServices = chosenServices;
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

    public boolean isIsReserved() {
        return this.isReserved;
    }

    public boolean getIsReserved() {
        return this.isReserved;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
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
}