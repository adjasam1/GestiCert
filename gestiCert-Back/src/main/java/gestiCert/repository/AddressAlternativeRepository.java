package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.AddressAlternative;

/**
 * AddressAlternativeRepository indique que la classe amelioree AddressAlternative est un referentiel qui etend JpaRepository
 * 
 * @see AddressAlternative
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface AddressAlternativeRepository extends JpaRepository<AddressAlternative, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant l'url ou une partie de l'url de l'adresse alternative comme parametre
	 * 
	 * @param linkAddressAlternative
	 * @return une liste d'adresses alternatives
	 */
	
	@Query("FROM AddressAlternative addressAlternative WHERE addressAlternative.linkAddressAlternative LIKE %?1%")
	public List<AddressAlternative> findByLinkAddressAlternative(String linkAddressAlternative);

}
