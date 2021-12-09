package isa.FishingAdventure.model;

import javax.persistence.*;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class User {
    @Id
    @SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
    private Integer userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "userType", nullable = false)
    private UserType userType;

    @Column(name = "points", nullable = false)
    private double points;

    @ManyToOne(targetEntity = UserCategory.class,cascade = CascadeType.ALL)
    private UserCategory category;

    @Column(name = "activated", nullable = false)
    private boolean activated;

    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    public Address address;

    public User(Integer userId, String email, String password, String name, String surname, String phoneNumber, UserType userType, double points, UserCategory category, boolean activated, Address address) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.points = points;
        this.category = category;
        this.activated = activated;
        this.address = address;
    }


    public User() {
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public double getPoints() {
        return this.points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public UserCategory getCategory() {
        return this.category;
    }

    public void setCategory(UserCategory category) {
        this.category = category;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}