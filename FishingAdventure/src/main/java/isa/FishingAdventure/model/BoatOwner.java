package isa.FishingAdventure.model;

import javax.persistence.Entity;

import java.sql.Timestamp;

@Entity
public class BoatOwner extends User {

    public BoatOwner(Integer userId, String email, String password, String name, String surname, String phoneNumber,
            UserType userType, double points, UserCategory category, boolean activated, Address address,
            Timestamp lastPasswordResetDate) {
        super(userId, email, password, name, surname, phoneNumber, userType, points, category, activated, address,
                lastPasswordResetDate);
    }

    public BoatOwner() {
    }
}