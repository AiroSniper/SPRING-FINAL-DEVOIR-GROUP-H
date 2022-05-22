package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	
	@GeneratedValue
	@Id
	private Long id;
	
	private String description;
	
	private String urgence;
	
	private String envirenment;
	
	private String logiciel;
	
	private Long priorite;
	
	private String status;
	
	
	@ManyToOne
	private User client;
	
	@ManyToOne
	private User developper;
	
	

	@ManyToOne
	private User admin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrgence() {
		return urgence;
	}

	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}

	public String getEnvirenment() {
		return envirenment;
	}

	public void setEnvirenment(String envirenment) {
		this.envirenment = envirenment;
	}

	public String getLogiciel() {
		return logiciel;
	}

	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}

	public Long getPriorite() {
		return priorite;
	}

	public void setPriorite(Long priorite) {
		this.priorite = priorite;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public User getDevelopper() {
		return developper;
	}

	public void setDevelopper(User developper) {
		this.developper = developper;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
	

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", description=" + description + ", urgence=" + urgence + ", envirenment="
				+ envirenment + ", logiciel=" + logiciel + ", priorite=" + priorite + ", status=" + status + ", client="
				+ client + ", developper=" + developper + ", admin=" + admin + "]";
	}
	
	
	
	
}
