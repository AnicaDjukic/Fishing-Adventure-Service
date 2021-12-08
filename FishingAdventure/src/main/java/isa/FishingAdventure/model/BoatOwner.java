package isa.FishingAdventure.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BoatOwner extends User {

    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Boat> boats = new HashSet<Boat>();

    public BoatOwner(Integer userId, String email, String password, String name, String surname, String phoneNumber,
                     UserType userType, double points, UserCategory category, boolean activated, Address address, Timestamp lastPasswordResetDate) {
        super(userId, email, password, name, surname, phoneNumber, userType, points, category, activated, address, lastPasswordResetDate);
    }


    public BoatOwner() {
    }
}