package fr.finanace.banque.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
/**comme il y a l'hetitage on utilise l'annotation
 * @Inheritance et il y a trois strategie 
 * 1- single table:une table pout toutes  l'heirarche de
 *classe une seulle table dans laquelle on va stocke tous les type
 *de compte (pb : il reste tjrs des conone nul mais dans cette
 *cas on utise hebernate on dit quelle strategie utilisable et on continue 
 *d'utiliser OO car c JPA qui va gerer l'accer aux donnees
 *2-table_PER_CLASS:une table poucr chaque class fille et dans ce cas la
 *il va creer une table pour compte courante et une pour compte epargne
 *3-JOINED: cie il va creer une table pour representer la table et une 
 *table pour chaque classe filles et apres il va les liees parjointure
 *
 */
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
/**
 * comme on autiise SINGLETABLE donc il ya une colonne qui va ajouter dans 
 * la table compte cette colonne type de comte et donc on va utiliser
 * @DiscriminatorColumn avec le nom type de compte
 *et une type et une taille 2 cara(cc, ce)
 */
@DiscriminatorColumn(name="TYPE_CPTE",
discriminatorType=DiscriminatorType.STRING, length=2)
public abstract class Compte implements Serializable{
	@Id
	private String codeCompte;
	private Date dataCreation;
	private double solde;
	@ManyToOne
	/**
	 * @ManyToOne ie il ya une cle etrangere et si on 
	 * mit rien par defuat il va nommmer cette cle client
	 * mais pour priciser le nome de la cle on va utiliser 
	 * @JoinColumn
	 */
	@JoinColumn(name="CODE_CLI")
	private Client client;
	@OneToMany(mappedBy="compte")
	private Collection<Operation> operations;
	
	public Compte() {
		super();
	}

	public Compte(String codeCompte, Date dataCreation, double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dataCreation = dataCreation;
		this.solde = solde;
		this.client = client;
	}







	public String getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	public Date getDataCreation() {
		return dataCreation;
	}

	public void setDataCreation(Date dataCreation) {
		this.dataCreation = dataCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	
	
	

}
