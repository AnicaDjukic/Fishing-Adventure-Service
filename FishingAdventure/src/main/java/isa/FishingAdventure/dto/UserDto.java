package isa.FishingAdventure.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import isa.FishingAdventure.model.Address;
import isa.FishingAdventure.model.UserType;

public class UserDto {

	private Integer id;
	
	private String email;

	private String password;

	private String name;

	private String surname;

	private String phoneNumber;

	private UserType userType;
	
	private Address address; 

	public UserDto() {}

	public UserDto(Integer id, String email, String password, String name, String surname, String phoneNumber,
			UserType userType) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
	}

	public UserDto(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
