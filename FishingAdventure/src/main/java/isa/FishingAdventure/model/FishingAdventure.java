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

    @ManyToMany(targetEntity = FishingEquipment.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<FishingEquipment> fishingEquipment;

    public FishingAdventure() {
    }

    public FishingAdventure(String name, String description, double cancellationRule, double rating, Location location, Set<Rule> rules, Set<AdditionalService> additionalServices, int persons, FishingInstructor fishingInstructor, Duration adventureDuration, Set<FishingEquipment> fishingEquipment) {
        super(name, description, cancellationRule, rating, location, rules, additionalServices, persons);
        this.fishingInstructor = fishingInstructor;
        this.adventureDuration = adventureDuration;
        this.fishingEquipment = fishingEquipment;
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

    public Set<FishingEquipment> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(Set<FishingEquipment> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}