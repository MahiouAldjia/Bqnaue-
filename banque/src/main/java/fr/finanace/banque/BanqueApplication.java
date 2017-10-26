package fr.finanace.banque;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.finanace.banque.dao.ClientRepository;
import fr.finanace.banque.dao.CompteRepository;
import fr.finanace.banque.dao.OperationRepository;
import fr.finanace.banque.entities.Client;
import fr.finanace.banque.entities.Compte;
import fr.finanace.banque.entities.CompteCourant;
import fr.finanace.banque.entities.CompteEpargne;
import fr.finanace.banque.entities.Retrait;
import fr.finanace.banque.entities.Versement;
import fr.finanace.banque.metier.IBanqueMetier;

@SpringBootApplication
public class BanqueApplication implements CommandLineRunner{
/**
 * 2 solution on impliment cette class de CommandLineRunner
 * et ce dernier il va nous obliger de crrer une methode run
 * (ie que spring des il termine de demarrer il va appeler la methode run
 * et alors les test il vont places dasns la methode run)
 */
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		//1 solution avec aapctx et les beans
		/**
		 * ApplicationContext ctx=SpringApplication.run(BanqueApplication.class, args);
		//getBean donner moi un bean ie il demande a spring
		//de crrer un objet de type clientRepository.class
		ClientRepository clientRepository=ctx.getBean(ClientRepository.class);
		clientRepository.save(new Client("MAHIOU", "aldjiamahiou@outlook.fr"));
		 */
		
		SpringApplication.run(BanqueApplication.class, args);
		
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		Client c1=clientRepository.save(new Client("MAHIOU", "aldjiamahiou@outlook.fr"));
		Client c2=clientRepository.save(new Client("RACHID", "rachidmahiou@outlook.fr"));
		
		Compte cp1= compteRepository.save( 
				new CompteCourant("c1", new Date(), 6000, c1, 3000));
		Compte cp2= compteRepository.save( 
				new CompteEpargne("c2", new Date(), 9000, c2, 3.5));
		
		operationRepository.save(new Versement(new Date(), 9000, cp1));
		operationRepository.save(new Versement(new Date(), 6000, cp1));
		operationRepository.save(new Versement(new Date(), 1400, cp1));
		operationRepository.save(new Retrait(new Date(), 9000, cp1));
		
		operationRepository.save(new Versement(new Date(), 7000, cp2));
		operationRepository.save(new Versement(new Date(), 11000, cp2));
		operationRepository.save(new Versement(new Date(), 1400, cp2));
		operationRepository.save(new Retrait(new Date(), 2300, cp2));
		
		
		
		banqueMetier.verser("c1", 111111);
	}
}
