package tn.esprit.spring.dto;

import java.util.Date;

public class ContratDto {
	private int reference;

	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	private Date dateDebut;
	private String typeContrat;
	private float salaire;
	private int idEmploye;
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getTypeContrat() {
		return typeContrat;
	}
	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}
	public int getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

}
