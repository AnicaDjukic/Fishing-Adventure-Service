package isa.FishingAdventure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "confirmation_token", length = 1024)
	private String token;

	public ConfirmationToken() {
	}

	public ConfirmationToken(Integer id, String email, String confirmationToken) {
		super();
		this.id = id;
		this.email = email;
		this.token = confirmationToken;
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

	public String getToken() {
		return token;
	}

	public void setToken(String confirmationToken) {
		this.token = confirmationToken;
	}

}
