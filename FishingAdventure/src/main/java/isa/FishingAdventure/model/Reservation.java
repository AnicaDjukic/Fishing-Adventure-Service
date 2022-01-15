package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @OneToOne(targetEntity = Appointment.class, cascade = CascadeType.MERGE)
    private Appointment appointment;

    @ManyToOne(targetEntity = Client.class, cascade = CascadeType.MERGE)
    private Client client;

    @Column(name = "isCanceled", nullable = false)
    private Boolean isCanceled;

    @Column(name = "isReportFilled", nullable = false)
    private Boolean isReportFilled;

    public Reservation(Integer reservationId, Appointment appointment, Client client) {
        this.reservationId = reservationId;
        this.appointment = appointment;
        this.client = client;
        this.isCanceled = false;
        this.isReportFilled = false;
    }

    public Reservation() {
    }

    public Reservation(boolean isReportFilled, Appointment appointment, Client client, boolean isCanceled) {
        this.isReportFilled = isReportFilled;
        this.appointment = appointment;
        this.client = client;
        this.isCanceled = isCanceled;
    }

    public Integer getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Boolean getReportFilled() {
        return isReportFilled;
    }

    public void setReportFilled(Boolean reportFilled) {
        isReportFilled = reportFilled;
    }
}