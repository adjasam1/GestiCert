package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.TypeDemand;

/**
 * TypeDemandeRepository indique que la classe amelioree TypeDemande est un referentiel qui etend JpaRepository
 * 
 * @see TypeDemand
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface TypeDemandRepository extends JpaRepository<TypeDemand, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le type ou une partie du type de demande comme parametre
	 * 
	 * @param typeTypeDemand
	 * @return une liste de types de demande
	 */
	
	@Query("FROM TypeDemand typeTypeDemand WHERE typeTypeDemand.typeTypeDemand LIKE %?1%")
	public List<TypeDemand> findByTypeDemand(String typeTypeDemand);

}
