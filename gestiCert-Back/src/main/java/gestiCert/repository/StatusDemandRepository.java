package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.StatusDemand;

/**
 * StatusDemandRepository indique que la classe amelioree StatusDemand est un referentiel qui etend JpaRepository
 * 
 * @see StatusDemand
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface StatusDemandRepository extends JpaRepository<StatusDemand, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le statut ou une partie du statut de la demande comme parametre
	 * 
	 * @param statusDemand
	 * @return une liste de statuts de demande
	 */
	
	@Query("FROM StatusDemand statusDemand WHERE statusDemand.nameStatusDemand LIKE %?1%")
	public List<StatusDemand> findByNameStatusDemand(String nameStatusDemand);

}
