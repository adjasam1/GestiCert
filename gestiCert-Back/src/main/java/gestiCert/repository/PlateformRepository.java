package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.Plateform;

/**
 * PlateformRepository indique que la classe amelioree Plateform est un referentiel qui etend JpaRepository
 * 
 * @see Plateform
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface PlateformRepository extends JpaRepository<Plateform, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom ou une partie du nom de la plateforme comme parametre
	 * 
	 * @param namePlateform
	 * @return une liste de plateformes
	 */
	
	@Query("FROM Plateform plateform WHERE plateform.namePlateform LIKE %?1%")
	public List<Plateform> findByNamePlateform(String namePlateform);

}
