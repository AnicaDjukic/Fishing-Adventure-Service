package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class ServiceProfile {
    @Id
    @SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Column(name = "cancellationRule", nullable = false)
    private double cancellationRule;

    @Column(name = "rating", nullable = false)
    private double rating;

    @OneToOne(targetEntity = Location.class, cascade = CascadeType.ALL)
    public Location location;

    @OneToMany(targetEntity = Appointment.class, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Appointment> appointments;

    @ManyToMany(targetEntity = Rule.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rule> rules;

    @ManyToMany(targetEntity = AdditionalService.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdditionalService> additionalServices;

    @OneToMany(targetEntity = Image.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Image> images;

    @Column(name = "persons", nullable = false)
    private int persons;

    @Column(name = "price_per_day", nullable = false)
    private double pricePerDay;

    public ServiceProfile(Integer id, String name, String description, double cancellationRule, double rating, Location location, Set<Appointment> appointments, Set<Rule> rules, Set<AdditionalService> additionalServices, int persons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cancellationRule = cancellationRule;
        this.rating = rating;
        this.location = location;
        this.appointments = appointments;
        this.rules = rules;
        this.additionalServices = additionalServices;
        this.isDeleted = false;
        this.persons = persons;
        this.pricePerDay = 0;
    }

    public ServiceProfile() {
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer serviceId) {
        this.id = serviceId;
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

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}