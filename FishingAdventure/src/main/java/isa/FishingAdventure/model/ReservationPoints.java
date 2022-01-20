package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class ReservationPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "clientPointsPercentage", nullable = false)
    private double clientPointsPercentage;

    @Column(name = "advertiserPointsPercentage", nullable = false)
    private double advertiserPointsPercentage;

    public ReservationPoints() {
    }

    public ReservationPoints(Integer id, double clientPointsPercentage, double advertiserPointsPercentage) {
        this.id = id;
        this.clientPointsPercentage = clientPointsPercentage;
        this.advertiserPointsPercentage = advertiserPointsPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getClientPointsPercentage() {
        return clientPointsPercentage;
    }

    public void setClientPointsPercentage(double clientPointsPercentage) {
        this.clientPointsPercentage = clientPointsPercentage;
    }

    public double getAdvertiserPointsPercentage() {
        return advertiserPointsPercentage;
    }

    public void setAdvertiserPointsPercentage(double advertiserPointsPercentage) {
        this.advertiserPointsPercentage = advertiserPointsPercentage;
    }
}
