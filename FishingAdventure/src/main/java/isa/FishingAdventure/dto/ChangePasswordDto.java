package isa.FishingAdventure.dto;

public class ChangePasswordDto {
	
	private String email;
	
	private String newPassword;
	
	private String passwordAgain;

	public ChangePasswordDto() {
		super();
	}

	public ChangePasswordDto(String email, String newPassword, String passwordAgain) {
		super();
		this.email = email;
		this.newPassword = newPassword;
		this.passwordAgain = passwordAgain;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
