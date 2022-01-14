package isa.FishingAdventure.model;

import java.sql.Timestamp;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    public Admin(String email, String name, String surname, String password, String phoneNumber, UserType userType, Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
    }

    public Admin() {
    }
}