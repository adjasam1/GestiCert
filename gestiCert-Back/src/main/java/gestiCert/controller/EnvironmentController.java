package gestiCert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.Environment;
import gestiCert.service.EnvironmentService;

/**
 * EnvironmentController route les requetes au EnvironmentService
 * 
 * @see Environment
 * @see EnvironmentService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/environnement")
//@CrossOrigin("http://localhost:4200")
public class EnvironmentController
{
	
	/**
	 * injection des dependances de EnvironmentService
	 * 
	 * @see EnvironmentService
	 */
	
	@Autowired
	private EnvironmentService environmentServ;

	/**
	 * constructeur
	 * 
	 * @param environmentServ
	 */
	
	public EnvironmentController(EnvironmentService environmentServ)
	{
		super();
		this.environmentServ = environmentServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idEnvironment
	 * @param word
	 * @param environment
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllEnvironments()
	{
		return environmentServ.getAllEnvironments();
	}
	
	@GetMapping("/id={idEnvironment}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getEnvironmentById(@PathVariable Integer idEnvironment)
	{
		return environmentServ.getEnvironmentById(idEnvironment);
	}
	
	@GetMapping("/nom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getEnvironmentByName(@PathVariable String word)
	{
		return environmentServ.getEnvironmentByName(word);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postEnvironment(@RequestBody Environment environment)
	{
		return environmentServ.createEnvironment(environment);
	}
	
	@PutMapping("/modifid={idEnvironment}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putEnvironment(@RequestBody Environment environment, @PathVariable Integer idEnvironment)
	{
		return environmentServ.updateEnvironment(environment, idEnvironment);
	}
	
	@DeleteMapping("/supprid={idEnvironment}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteEnvironment(@PathVariable Integer idEnvironment)
	{
		return environmentServ.deleteEnvironment(idEnvironment);
	}

}
