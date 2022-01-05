package isa.FishingAdventure.model;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Client extends User {

    @Column(name = "penalties", nullable = false)
    private Integer penalties;

    @ManyToMany(targetEntity = ServiceProfile.class,cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
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
}