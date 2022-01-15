package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.User;

public class RegistrationRequestDto {
    private Integer id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String phoneNumber;

    private String userType;

    private String country;

    private String city;

    private String street;

    private String description;

    public RegistrationRequestDto() {
    }

    public RegistrationRequestDto(Integer id, String email, String password, String name, String surname,
            String phoneNumber, String userType, String country, String city, String street,
            String description) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.country = country;
        this.city = city;
        this.street = street;
        this.description = description;
    }

    public RegistrationRequestDto(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.userType = user.getUserType().getName();
        this.country = user.getAddress().getCountry();
        this.city = user.getAddress().getCity();
        this.street = user.getAddress().getStreet();
        this.description = user.getBiography();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String biography) {
        this.description = biography;
    }
}
