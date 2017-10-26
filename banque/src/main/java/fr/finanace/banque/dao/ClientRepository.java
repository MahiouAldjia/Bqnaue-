package fr.finanace.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.finanace.banque.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
