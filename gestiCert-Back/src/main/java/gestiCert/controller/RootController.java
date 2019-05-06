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

import gestiCert.model.Root;
import gestiCert.service.RootService;

/**
 * RootController  route les requetes au RootService
 * 
 * @see Root
 * @see RootService
 * 
 * @author Utilisateur
 * @version 0.0
 */

@RestController
@RequestMapping("/api/racine")
//@CrossOrigin("http://localhost:4200")
public class RootController
{
	
	/**
	 * injection des dependances de RootService
	 * 
	 * @see RootService
	 */
	
	@Autowired
	private RootService rootServ;

	/**
	 * constructeur
	 * 
	 * @param rootServ
	 */
	
	public RootController(RootService rootServ)
	{
		super();
		this.rootServ = rootServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idRoot
	 * @param word
	 * @param root
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllRoots()
	{
		return rootServ.getAllRoots();
	}
	
	@GetMapping("/id={idRoot}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getRootById(@PathVariable Integer idRoot)
	{
		return rootServ.getRootById(idRoot);
	}
	
	@GetMapping("/nom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getRootByName(@PathVariable String word)
	{
		return rootServ.getRootByName(word);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postRoot(@RequestBody Root root)
	{
		return rootServ.createRoot(root);
	}
	
	@PutMapping("/modifid={idRoot}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putEnvironment(@RequestBody Root root, @PathVariable Integer idRoot)
	{
		return rootServ.updateRoot(root, idRoot);
	}
	
	@DeleteMapping("/supprid={idRoot}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteEnvironment(@PathVariable Integer idRoot)
	{
		return rootServ.deleteRoot(idRoot);
	}

}
