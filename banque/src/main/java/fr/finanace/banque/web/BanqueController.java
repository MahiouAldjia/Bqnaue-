package fr.finanace.banque.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.finanace.banque.metier.IBanqueMetier;

@Controller
public class BanqueController {
	/**
	 * ona besion de la couche metier donc on declare
	 * un varible IBanqueMetier
	 */
	@Autowired
	private IBanqueMetier banqueMetier;
	/**
	 * on va utiliser 
	 * pour acceder a notre vue comptes.html
	 */
	@RequestMapping("/operations")
	public String index() {
				
		return "comptes";
	}

}
