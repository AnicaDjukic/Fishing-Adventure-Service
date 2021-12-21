package isa.FishingAdventure.dto;

import java.util.List;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {
	
    private String accessToken;
	private String refreshToken;
	private List<String> roles;
    
    
	public UserTokenState() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserTokenState(String accessToken, String refreshToken, List<String> roles) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.roles = roles;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}