package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Server;

/**
 * 
 * @see Server
 * 
 * @author Utilisateur
 * @version 0.0
 */

public interface ServerService {

	ResponseEntity<?> getAllServers();

	ResponseEntity<?> getServerById(Integer idServer);

	ResponseEntity<?> getServerByName(String word);

	ResponseEntity<?> createServer(Server server);

	ResponseEntity<?> updateServer(Server server, Integer idServer);

	ResponseEntity<String> deleteServer(Integer idServer);

}
