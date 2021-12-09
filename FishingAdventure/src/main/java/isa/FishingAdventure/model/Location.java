package isa.FishingAdventure.model;

import javax.persistence.*;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    public Address address;

    public Location(Integer locationId, double longitude, double latitude, Address address) {
        this.locationId = locationId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public Location() {
    }


	public Integer getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}