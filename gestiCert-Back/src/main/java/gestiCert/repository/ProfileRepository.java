package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestiCert.model.Profile;

/**
 * ProfilRepository indique que la classe amelioree Profil est un referentiel qui etend JpaRepository
 * 
 * @see Profile
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le type du profil comme parametre
	 * 
	 * @param typeProfile
	 * @return une liste de profiles
	 */
	
	public List<Profile> findByTypeProfile(String typeProfile);

}
