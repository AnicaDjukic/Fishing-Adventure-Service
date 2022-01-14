package isa.FishingAdventure.model;

import javax.persistence.Entity;

import java.sql.Timestamp;

@Entity
public class VacationHomeOwner extends User {

    public VacationHomeOwner(String email, String name, String surname, String password, String phoneNumber, UserType userType, Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
    }

    public VacationHomeOwner() {
    }

}