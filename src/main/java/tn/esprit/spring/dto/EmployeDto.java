package tn.esprit.spring.dto;

import tn.esprit.spring.entities.Role;

public class EmployeDto {
	private String prenom;
	private String nom;
	private String email;
	private boolean isActif;
	private Role role;
	private int idContrat;
	public int getIdContrat() {
		return idContrat;
	}
	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActif() {
		return isActif;
	}
	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
