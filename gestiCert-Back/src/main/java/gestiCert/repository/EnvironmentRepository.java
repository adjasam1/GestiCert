package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.Environment;

/**
 * EnvironmentRepository indique que la classe amelioree Environment est un referentiel qui etend JpaRepository
 * 
 * @see Environment
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Integer>
{
	
//	/**
//	 * utilise la methode findById du CrudRepository en utilisant le nom de l'environnement comme parametre
//	 * 
//	 * @param nameEnvironment
//	 * @return une liste d'environnements
//	 */
//	
//	public List<Environment> findByNameEnvironment(String nameEnvironment);
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom ou une partie du nom de l'environnement comme parametre
	 * 
	 * @param nameEnvironment
	 * @return une liste d'environnements
	 */
	
	@Query("FROM Environment environment WHERE environment.nameEnvironment LIKE %?1%")
	public List<Environment> findByNameEnvironnement(String nameEnvironment);

}
