package isa.FishingAdventure.model;

import isa.FishingAdventure.dto.UserDto;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends User {

    @Column(name = "penalties", nullable = false)
    private Integer penalties;

    @ManyToMany(targetEntity = ServiceProfile.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<ServiceProfile> subscriptions;

    public Client(String email, String name, String surname, String password, String phoneNumber, UserType userType,
            Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
        this.penalties = 0;
        this.subscriptions = new HashSet<>();
    }

    public Client() {
    }

    public Client(UserDto userDto) {
        super(userDto.getEmail(), userDto.getName(), userDto.getSurname(), userDto.getPassword(),
                userDto.getPhoneNumber(), userDto.getUserType(), userDto.getAddress(), null);
        this.penalties = 0;
        this.subscriptions = new HashSet<>();
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