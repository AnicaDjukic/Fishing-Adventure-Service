package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.User;

public class UserInfoDto {
	private Integer id;

	private String email;

	private String password;

	private String name;

	private String surname;

	private String phoneNumber;

	private String street;

	private String city;

	private String country;

	private String biography;

	public UserInfoDto() {
	}

	public UserInfoDto(Integer id, String email, String password, String name, String surname, String phoneNumber,
			String street, String city, String country, String biography) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.city = city;
		this.country = country;
		this.biography = biography;
	}

	public UserInfoDto(User user) {
		this.id = user.getUserId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.phoneNumber = user.getPhoneNumber();
		this.street = user.getAddress().getStreet();
		this.city = user.getAddress().getCity();
		this.country = user.getAddress().getCountry();
		this.biography = user.getBiography();
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
