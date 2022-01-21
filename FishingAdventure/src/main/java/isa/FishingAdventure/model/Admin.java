package isa.FishingAdventure.model;

import isa.FishingAdventure.dto.UserDto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "isHeadAdmin", nullable = false)
    private Boolean isHeadAdmin;

    public Admin(String email, String name, String surname, String password, String phoneNumber, UserType userType,
            Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
        this.isHeadAdmin = false;
    }

    public Admin() {
    }

    public Admin(UserDto userDto) {
        super(userDto.getEmail(), userDto.getName(), userDto.getSurname(), userDto.getPassword(),
                userDto.getPhoneNumber(), userDto.getUserType(), userDto.getAddress(), null);
        this.setBiography(userDto.getBiography());
        this.isHeadAdmin = false;
    }

    public Boolean getHeadAdmin() {
        return isHeadAdmin;
    }

    public void setHeadAdmin(Boolean headAdmin) {
        isHeadAdmin = headAdmin;
    }
}