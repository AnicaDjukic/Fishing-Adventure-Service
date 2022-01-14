package isa.FishingAdventure.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Column(name = "isHeadAdmin", nullable = false)
    private Boolean isHeadAdmin;


	public Admin(Integer userId, String email, String password, String name, String surname, String phoneNumber,
                 UserType userType, double points, UserCategory category, boolean activated, Address address, Timestamp lastPasswordResetDate) {
        super(userId, email, password, name, surname, phoneNumber, userType, points, category, activated, address, lastPasswordResetDate);

    }


    public Admin() {
    }

    public Boolean getHeadAdmin() {
        return isHeadAdmin;
    }

    public void setHeadAdmin(Boolean headAdmin) {
        isHeadAdmin = headAdmin;
    }
}