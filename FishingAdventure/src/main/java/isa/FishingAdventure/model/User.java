package isa.FishingAdventure.model;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class User implements UserDetails {
	@Id
	@SequenceGenerator(name = "mySeqGenV1", sequenceName = "mySeqV1", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
	private Integer userId;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;

	@Column(name = "biography", nullable = false, length = 1024)
	private String biography;

	@OneToOne(targetEntity = UserType.class, cascade = CascadeType.MERGE)
	private UserType userType;

	@Column(name = "points", nullable = false)
	private double points;

	@ManyToOne(targetEntity = UserCategory.class)
	private UserCategory category;

	@Column(name = "activated", nullable = false)
	private boolean activated;

	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	private Address address;

	@Column(name = "last_password_reset_date")
	private Timestamp lastPasswordResetDate;

	protected User() {
	}

	protected User(String email, String name, String surname, String password, String phoneNumber, UserType userType,
			Address address, Timestamp lastPasswordResetDate) {
		this.email = email;
		this.isDeleted = false;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.biography = "";
		this.userType = userType;
		this.points = 0.0;
		this.activated = false;
		this.address = address;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserCategory getCategory() {
		return category;
	}

	public void setCategory(UserCategory category) {
		this.category = category;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public double getPoints() {
		return this.points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<UserType> userTypes = new ArrayList<>();
		userTypes.add(userType);
		return userTypes;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return email;
	}

}