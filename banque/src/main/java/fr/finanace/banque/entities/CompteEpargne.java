package fr.finanace.banque.entities;

import java.util.Date;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
	private double taux;

	
	public CompteEpargne() {
		super();
	}

	public CompteEpargne(String codeCompte, Date dataCreation, double solde, Client client, double taux) {
		super(codeCompte, dataCreation, solde, client);
		this.taux = taux;
	}



	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	

}
