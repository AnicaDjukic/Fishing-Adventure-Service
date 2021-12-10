package isa.FishingAdventure.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class ServiceProfile {
    @Id
    @SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
    private Integer serviceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cancellationRule", nullable = false)
    private double cancellationRule;

    @Column(name = "rating", nullable = false)
    private double rating;

    @OneToOne(targetEntity = Location.class,cascade = CascadeType.ALL)
    public Location location;

    @OneToMany(targetEntity = Appointment.class,cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Appointment> appointments;

    @ManyToMany(targetEntity = Rule.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rule> rules;

    @OneToMany(targetEntity = AdditionalService.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdditionalService> additionalServices;
    
    @OneToMany(targetEntity = Image.class,cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<Image> images;

    public ServiceProfile(Integer serviceId, String name, String description, double cancellationRule, double rating, Location location, Set<Appointment> appointments, Set<Rule> rules, Set<AdditionalService> additionalServices) {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.cancellationRule = cancellationRule;
        this.rating = rating;
        this.location = location;
        this.appointments = appointments;
        this.rules = rules;
        this.additionalServices = additionalServices;
    }

    public ServiceProfile() {
    }

    public Integer getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCancellationRule() {
        return this.cancellationRule;
    }

    public void setCancellationRule(double cancellationRule) {
        this.cancellationRule = cancellationRule;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Appointment> getAppointments() {
        return this.appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
}