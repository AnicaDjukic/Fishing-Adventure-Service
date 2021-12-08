package isa.FishingAdventure.model;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends User {

    @Column(name = "penalties", nullable = false)
    private Integer penalties;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @ManyToMany
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "serviceId", referencedColumnName = "serviceId"))
    public Set<ServiceProfile> subscriptions;

    public Client(Integer userId, String email, String password, String name, String surname, String phoneNumber,
                  UserType userType, double points, UserCategory category, boolean activated, Address address, Timestamp lastPasswordResetDate) {
        super(userId, email, password, name, surname, phoneNumber, userType, points, category, activated, address, lastPasswordResetDate);
    }

    public Client() {
    }

    public Integer getPenalties() {
        return this.penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public Set<ServiceProfile> getSubscriptions() {
        return this.subscriptions;
    }

    public void setSubscriptions(Set<ServiceProfile> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}