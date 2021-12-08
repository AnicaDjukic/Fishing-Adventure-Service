package isa.FishingAdventure.model;

import java.sql.Timestamp;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(Integer userId, String email, String password, String name, String surname, String phoneNumber,
                 UserType userType, double points, UserCategory category, boolean activated, Address address, Timestamp lastPasswordResetDate) {
        super(userId, email, password, name, surname, phoneNumber, userType, points, category, activated, address, lastPasswordResetDate);

    }


    public Admin() {
    }
}