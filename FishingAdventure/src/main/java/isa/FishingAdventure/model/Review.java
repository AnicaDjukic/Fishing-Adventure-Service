package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "isApproved", nullable = false)
    private boolean isApproved;

    @Column(name = "isRejected", nullable = false)
    private boolean isRejected;

    @OneToOne(targetEntity = Reservation.class, cascade = CascadeType.MERGE)
    private Reservation reservation;

    @Column(name = "serviceId", nullable = false)
    private Integer serviceId;

    @Column(name = "datePosted", nullable = false)
    private Date datePosted;

    public Review(Integer id, String content, int rating, boolean isApproved, boolean isRejected, Reservation reservation, Integer serviceId) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.isApproved = isApproved;
        this.isRejected = isRejected;
        this.reservation = reservation;
        this.serviceId = serviceId;
        this.datePosted = new Date();
    }

    public Review(String content, int rating, Integer serviceId, boolean isApproved, boolean isRejected) {
        this.content = content;
        this.rating = rating;
        this.isApproved = isApproved;
        this.isRejected = isRejected;
        this.serviceId = serviceId;
        this.datePosted = new Date();
    }

    public Review() {
        this.datePosted = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
}