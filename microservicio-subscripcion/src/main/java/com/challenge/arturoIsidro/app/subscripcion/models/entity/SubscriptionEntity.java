package com.challenge.arturoIsidro.app.subscripcion.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="subscripcion")
public class SubscriptionEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idSubscripcion;
	@Column(name="email",nullable =false)
	private String email;
	@Column(name="nombre",nullable =true)
	private String firstname;
	@Column(name="genero",nullable = true)
	private String gender;
	@Column(name="fecha_cumple",nullable = true)
	private String birth;
	@Column(name="noticiasActivated",nullable = true)
	private String newsLetterActivated;
	
	public String getNewsLetterActivated() {
		return newsLetterActivated;
	}

	public void setNewsLetterActivated(String newsLetterActivated) {
		this.newsLetterActivated = newsLetterActivated;
	}

	@Column(name= "create_at")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		this.createAt= new Date();
	}
	
	public Long getIdSubscripcion() {
		return idSubscripcion;
	}
	public void setIdSubscripcion(Long idSubscripcion) {
		this.idSubscripcion = idSubscripcion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "SubscriptionEntity [idSubscripcion=" + idSubscripcion + ", email=" + email + ", firstname=" + firstname
				+ ", gender=" + gender + ", birth=" + birth + "]";
	}
	
	
	

}
