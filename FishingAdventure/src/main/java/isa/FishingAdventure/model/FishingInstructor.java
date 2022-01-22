package isa.FishingAdventure.model;

import isa.FishingAdventure.dto.UserDto;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
public class FishingInstructor extends User {

    @OneToMany(targetEntity = InstructorAvailability.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<InstructorAvailability> instructorAvailabilities;

    public FishingInstructor(String email, String name, String surname, String password, String phoneNumber,
            UserType userType, Address address, Timestamp lastPasswordResetDate) {
        super(email, name, surname, password, phoneNumber, userType, address, lastPasswordResetDate);
    }

    public FishingInstructor(UserDto userDto) {
        super(userDto.getEmail(), userDto.getName(), userDto.getSurname(), userDto.getPassword(),
                userDto.getPhoneNumber(), userDto.getUserType(), userDto.getAddress(), null);
        this.setBiography(userDto.getBiography());
    }

    public FishingInstructor() {
    }

    public Set<InstructorAvailability> getInstructorAvailabilities() {
        return instructorAvailabilities;
    }

    public void setInstructorAvailabilities(Set<InstructorAvailability> instructorAvailabilities) {
        this.instructorAvailabilities = instructorAvailabilities;
    }
}