package gestiCert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.Server;
import gestiCert.service.ServerService;

/**
 * ServerController route les requetes au ServerService
 * 
 * @see Server
 * @see ServerService
 * 
 * @author Utilisateur
 * @version 0.0
 */

@RestController
@RequestMapping("/serveur")
@CrossOrigin("http://localhost:4200")
public class ServerController
{
	
	/**
	 * injection des dependances de ServerService
	 * 
	 * @see ServerService
	 */
	
	@Autowired
	private ServerService serverServ;

	/**
	 * constructeur
	 * 
	 * @param serverServ
	 */
	
	public ServerController(ServerService serverServ)
	{
		super();
		this.serverServ = serverServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idServer
	 * @param word
	 * @param server
	 * 
	 * @return
	 */
	
	@GetMapping()
	public ResponseEntity<?> getAllServers()
	{
		return serverServ.getAllServers();
	}
	
	@GetMapping("/id={idServer}")
	public ResponseEntity<?> getServerById(@PathVariable Integer idServer)
	{
		return serverServ.getServerById(idServer);
	}
	
	@GetMapping("/nom={word}")
	public ResponseEntity<?> getServerByName(@PathVariable String word)
	{
		return serverServ.getServerByName(word);
	}
	
	@PostMapping("/ajout")
	public ResponseEntity<?> postServer(@RequestBody Server server)
	{
		return serverServ.createServer(server);
	}
	
	@PutMapping("/modifid={idServer}")
	public ResponseEntity<?> putServer(@RequestBody Server server, @PathVariable Integer idServer)
	{
		return serverServ.updateServer(server, idServer);
	}
	
	@DeleteMapping("/supprid={idServer}")
	public ResponseEntity<String> deleteServer(@PathVariable Integer idServer)
	{
		return serverServ.deleteServer(idServer);
	}

}
