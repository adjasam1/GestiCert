package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestiCert.model.AppUser;
import gestiCert.model.Application;

/**
 * ApplicationRepository indique que la classe amelioree Application est un referentiel qui etend JpaRepository
 * 
 * @see Application
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le code CCX de l'application comme parametre
	 * 
	 * @param codeCCX
	 * @return une liste d'applications
	 */
	
	public List<Application> findByCodeCCX(String codeCCX);
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom de l'application comme parametre
	 * 
	 * @param nameApplication
	 * @return une liste d'applications
	 */
	
	public List<Application> findByNameApplication(String nameApplication);
	
//	public List<Application> findByIdRHUser(Integer idRHUser);
	
//	public List<Application> findByUser(AppUser user);

}
