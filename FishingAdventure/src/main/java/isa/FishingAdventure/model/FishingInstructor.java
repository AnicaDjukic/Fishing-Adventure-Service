package isa.FishingAdventure.model;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FishingInstructor extends User {

    public FishingInstructor(String email, String name, String surname, String password, String phoneNumber, UserType userType, Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
    }

    public FishingInstructor() {
    }
}