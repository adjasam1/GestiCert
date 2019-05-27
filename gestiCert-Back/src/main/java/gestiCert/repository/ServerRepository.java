package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.Server;

/**
 * ServeurRepository indique que la classe amelioree Serveur est un referentiel qui etend JpaRepository
 * 
 * @see Server
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface ServerRepository extends JpaRepository<Server, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom ou une partie du nom du serveur comme parametre
	 * 
	 * @param nameServer
	 * @return une liste de serveurs
	 */
	
	@Query("FROM Server server WHERE server.nameServer LIKE %?1%")
	public List<Server> findByNameServer(String nameServer);
	
//	public List<Server> findByCertificate(Certificate certificate);

}
