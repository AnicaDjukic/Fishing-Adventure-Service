package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name = "place", nullable = false)
    private String place;

    @OneToOne(targetEntity = Appointment.class,cascade = CascadeType.ALL)
    public Appointment appointment;

    @ManyToOne(targetEntity = Client.class,cascade = CascadeType.ALL)
    public Client client;

    @ManyToMany(targetEntity = Review.class,cascade = CascadeType.ALL)
    public Review review;

    public Reservation(Integer reservationId, String place, Appointment appointment, Client client) {
        this.reservationId = reservationId;
        this.place = place;
        this.appointment = appointment;
        this.client = client;
    }

    public Reservation() {
    }

    public Integer getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}