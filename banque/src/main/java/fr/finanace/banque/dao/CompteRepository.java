package fr.finanace.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.finanace.banque.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}
