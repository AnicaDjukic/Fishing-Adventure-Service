package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class ReservationReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "report", nullable = false)
    private String report;

    @OneToOne(targetEntity = Reservation.class,cascade = CascadeType.ALL)
    public Reservation reservation;

    public ReservationReport(String report, Reservation reservation) {
        this.report = report;
        this.reservation = reservation;
    }

    public ReservationReport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}