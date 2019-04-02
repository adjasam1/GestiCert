package gestiCert.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestiCert.model.Demand;

/**
 * DemandeRepository indique que la classe amelioree Demande est un referentiel qui etend JpaRepository
 * 
 * @see Demand
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer>
{

}
