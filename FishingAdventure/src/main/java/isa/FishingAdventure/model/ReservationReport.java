package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class ReservationReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Column(name = "report", nullable = false)
    private String report;

    @Column(name = "waitingReview", nullable = false)
    private Boolean waitingReview;

    @OneToOne(targetEntity = Reservation.class, cascade = CascadeType.MERGE)
    private Reservation reservation;

    public ReservationReport(String report, Reservation reservation) {
        this.report = report;
        this.reservation = reservation;
        this.waitingReview = false;
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

    public Boolean getWaitingReview() {
        return waitingReview;
    }

    public void setWaitingReview(Boolean waitingReview) {
        this.waitingReview = waitingReview;
    }
}