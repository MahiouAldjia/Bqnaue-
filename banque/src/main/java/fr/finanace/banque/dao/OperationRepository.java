package fr.finanace.banque.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.finanace.banque.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{
	/**
	 * @Query pour ecrir les requette avec le 
	 * language HQL de hibernate
	 * ie (je une liste des operations tries par date)
	 */
	@Query("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
	/**
	 * On utilse @Param pour dire que codCpte sa represente le parametre x
	 * 
	 */
	public Page<Operation> listOperation(@Param("x")String codeCpte, Pageable pageable);
}
