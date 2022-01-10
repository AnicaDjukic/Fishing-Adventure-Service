package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "isReplySent", nullable = false)
    private boolean isReplySent;

    @OneToOne(targetEntity = Reservation.class, cascade = CascadeType.MERGE)
    private Reservation reservation;

    @Column(name = "serviceId", nullable = false)
    private Integer serviceId;

    public Complaint(Integer id, String content, boolean isReplySent, Integer serviceId) {
        this.id = id;
        this.content = content;
        this.isReplySent = isReplySent;
        this.serviceId = serviceId;
    }

    public Complaint(String content, boolean isReplySent, Integer serviceId) {
        this.content = content;
        this.isReplySent = isReplySent;
        this.serviceId = serviceId;
    }

    public Complaint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReplySent() {
        return isReplySent;
    }

    public void setReplySent(boolean replySent) {
        isReplySent = replySent;
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
}
