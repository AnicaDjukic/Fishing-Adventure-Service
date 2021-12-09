package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends User {

    @Column(name = "penalties", nullable = false)
    private Integer penalties;

    @ManyToMany(targetEntity = ServiceProfile.class,cascade = CascadeType.ALL)
    public Set<ServiceProfile> subscriptions;

    public Client(Integer userId, String email, String password, String name, String surname, String phoneNumber,
                  UserType userType, double points, UserCategory category, boolean activated, Address address) {
        super(userId, email, password, name, surname, phoneNumber, userType, points, category, activated, address);
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