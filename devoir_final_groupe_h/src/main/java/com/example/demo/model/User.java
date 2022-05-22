package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	
	@GeneratedValue
	@Id
	private Long id;
	
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String nom;
	
	private String prenom;
	

	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id", referencedColumnName ="id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
	private List<Role> roles; 



	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return username;
	}


	
	
}


