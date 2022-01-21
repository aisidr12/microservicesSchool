package com.challenge.arturoIsidro.app.subscripcion.dto;

public class SubscripcionDto {
	private String idSubscripcion;
	private String email;
	private String name;
	private String gender;
	private String birthDate;
	private String newsletterActivated;

	public String getIdSubscripcion() {
		return idSubscripcion;
	}

	public void setIdSubscripcion(String idSubscripcion) {
		this.idSubscripcion = idSubscripcion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getNewsletterActivated() {
		return newsletterActivated;
	}

	public void setNewsletterActivated(String newsletterActivated) {
		this.newsletterActivated = newsletterActivated;
	}
	

}
