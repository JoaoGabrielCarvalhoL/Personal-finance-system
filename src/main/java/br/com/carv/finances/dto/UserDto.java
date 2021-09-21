package br.com.carv.finances.dto;

public class UserDto {
	
	private String emailUser; 
	private String nameUser; 
	private String passwordUser; 
	
	public UserDto() {
		
	}
	
	public UserDto(String emailUser, String nameUser, String passwordUser) {
		this.emailUser = emailUser; 
		this.nameUser = nameUser; 
		this.passwordUser = passwordUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	@Override
	public String toString() {
		return "UserDto [emailUser=" + emailUser + ", nameUser=" + nameUser + ", passwordUser=" + passwordUser + "]";
	}
	
	
	
}
