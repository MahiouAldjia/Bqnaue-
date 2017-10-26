package fr.finanace.banque.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.finanace.banque.dao.CompteRepository;
import fr.finanace.banque.dao.OperationRepository;
import fr.finanace.banque.entities.Compte;
import fr.finanace.banque.entities.CompteCourant;
import fr.finanace.banque.entities.Operation;
import fr.finanace.banque.entities.Retrait;
import fr.finanace.banque.entities.Versement;

/**
 * pour que spring il peut instancier cette
 * class au demarage on utilise @Service(il est
 *utiser par des objet dee la class metier)
 */
@Service
/**
 * @Transactional despring (en cas ou on demande au spring 
 * de gerer les transaction au niveau de la couche metier
 *
 */
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{
	/**
	 * pour faire l'injection de depandance on va utiliser
	 * @Autowired qui va demander a spring a injecter une implimentation
	 * de cette interface
	 */
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp =compteRepository.findOne(codeCpte);
		/**
		 * faire un teste en cas ou le cpte inexistante et
		 * utiliser RuntimeException()c'est une exception qui n'est pas surveille
		 */
		if(cp==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp =consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		//MAJ le soulde
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp =consulterCompte(codeCpte);
		
		/**savoir est que on a le droit de retrait
		 * et tester est ce que le solde est sufisant
		 * et encore le type de compte pour le decouvert
		 */
		double facilitesCaisse=0;
		if(cp instanceof CompteCourant)
			facilitesCaisse=((CompteCourant)cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse<montant)
			throw new RuntimeException("Solde insuffisant");
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		//MAJ le soulde
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
		return operationRepository.listOperation(codeCpte,new PageRequest(page, 3));
	}

}
