package isa.FishingAdventure.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
public class FishingAdventure extends ServiceProfile {

	@ManyToOne(targetEntity = FishingInstructor.class,cascade = CascadeType.MERGE)
    public FishingInstructor fishingInstructor;

    @Column(name = "adventureDuration", nullable = false)
    public Duration adventureDuration;

    public FishingAdventure() {
    }

    public FishingAdventure(Integer id, String name, String description, double cancellationRule, double rating, Location location,
                            Set<Appointment> appointments, Set<Rule> rules, Set<AdditionalService> additionalServices, int persons) {
        super(id, name, description, cancellationRule, rating, location, appointments, rules, additionalServices, persons);
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public Duration getAdventureDuration() {
        return adventureDuration;
    }

    public void setAdventureDuration(Duration adventureDuration) {
        this.adventureDuration = adventureDuration;
    }
}