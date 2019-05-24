package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Certificate;
import gestiCert.model.Server;
import gestiCert.repository.ServerRepository;

/**
 * ServerServiceImpl contient les methodes CRUD de l'application pour l'entite Server
 * 
 * @see Server
 * @see ServerRepository
 * 
 * @author Utilisateur
 * @version 0.0
 */

@Service
public class ServerServiceImpl implements ServerService
{
	
	/**
	 * injection des dependances de ServerRepository
	 * 
	 * @see ServerRepository
	 */

	private ServerRepository serverRepo;

	/**
	 * constructeur
	 * 
	 * @param serverRepo
	 */
	
	public ServerServiceImpl(ServerRepository serverRepo)
	{
		super();
		this.serverRepo = serverRepo;
	}
	
	/**
	 * methode qui cherche tous les serveurs
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllServers()
	{
		
		List<Server> listServers = null;
		
		try
		{
			listServers = serverRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listServers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listServers);
	}

	/**
	 * methode qui cherche un serveur par son identifiant
	 * 
	 * @param idServer
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getServerById(Integer idServer)
	{

		Optional<Server> listServers = null;
		
		try
		{
			listServers = serverRepo.findById(idServer);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listServers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listServers);
	}
	
	/**
	 * methode qui cherche un environnement par son son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getServerByName(String word)
	{
		List<Server> listServers = null;
		
		try
		{
			listServers = serverRepo.findByNameServer(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listServers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listServers);
	}
	
//	@Override
//	public List<Server> getServerByCertificate(Certificate certificate)
//	{
//		return serverRepo.findByCertificate(certificate);
//	}
	
	/**
	 * methode qui ajoute un serveur
	 * 
	 * @param server
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createServer(Server server)
	{
		Server newServer = null;
		
		String nameServer = server.getNameServer();
		if ((nameServer == null) || nameServer.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du serveur doit être renseigné");
		}
		
		try
		{
			newServer = serverRepo.saveAndFlush(server);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newServer);
	}
	
	/**
	 * methode qui modifie un serveur
	 * 
	 * @param server
	 * @param idServer
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateServer(Server server, Integer idServer)
	{
		Server modifyServer = null;
		getServerById(idServer);
		
		String nameServer = server.getNameServer();
		if ((nameServer == null) || (nameServer.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du serveur doit être renseigné");
		}
		
		try
		{
			modifyServer = serverRepo.saveAndFlush(server);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyServer);
	}
	
	/**
	 * methode qui supprime un serveur par son identifiant
	 * 
	 * @param idServer
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteServer(Integer idServer)
	{
		 
		try
		{
			serverRepo.deleteById(idServer);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
