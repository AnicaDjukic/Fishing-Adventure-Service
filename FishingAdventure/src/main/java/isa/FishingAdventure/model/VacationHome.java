package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class VacationHome extends ServiceProfile {

    @Column(name = "availabilityStart", nullable = false)
    private Date availabilityStart;

    @Column(name = "availabilityEnd", nullable = false)
    private Date availabilityEnd;

    @ManyToOne(targetEntity = VacationHomeOwner.class,cascade = CascadeType.ALL)
    public VacationHomeOwner vocationHomeOwner;

    @OneToMany(mappedBy = "vacationHome", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Room> rooms;

    public VacationHome(int serviceId, String name, String description, double cancellationRule, double rating, Location location, Set<Appointment> appointments, Set<Rule> rules, Set<AdditionalService> additionalServices) {
        super(serviceId, name, description, cancellationRule, rating, location, appointments, rules, additionalServices);
    }


    public VacationHome() {
    }

    public Date getAvailabilityStart() {
        return this.availabilityStart;
    }

    public void setAvailabilityStart(Date availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public Date getAvailabilityEnd() {
        return this.availabilityEnd;
    }

    public void setAvailabilityEnd(Date availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public VacationHomeOwner getVocationHomeOwner() {
        return this.vocationHomeOwner;
    }

    public void setVocationHomeOwner(VacationHomeOwner vocationHomeOwner) {
        this.vocationHomeOwner = vocationHomeOwner;
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }


}