package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.Root;

/**
 * RacineRepository indique que la classe amelioree Racine est un referentiel qui etend JpaRepository
 * 
 * @see Root
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface RootRepository extends JpaRepository<Root, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom ou une partie du nom de la racine comme parametre
	 * 
	 * @param nameRoot
	 * @return une liste de racines
	 */
	
	@Query("FROM Root root WHERE root.nameRoot LIKE %?1%")
	public List<Root> findByNameRoot(String nameRoot);

}
