package isa.FishingAdventure.model;

import isa.FishingAdventure.dto.UserDto;

import javax.persistence.Entity;

import java.sql.Timestamp;

@Entity
public class BoatOwner extends User {

    public BoatOwner(String email, String name, String surname, String password, String phoneNumber, UserType userType, Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
    }

    public BoatOwner(UserDto userDto){
        super(userDto.getEmail(), userDto.getName(), userDto.getSurname(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getUserType(), userDto.getAddress(), null);
    }


    public BoatOwner() {
    }
}