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

import gestiCert.model.StatusDemand;
import gestiCert.service.StatusDemandService;

/**
 * StatusDemandController route les requetes au StatusDemandService
 * 
 * @see StatusDemand
 * @see StatusDemandService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/statutdemande")
//@CrossOrigin("http://localhost:4200")
public class StatusDemandController
{
	
	/**
	 * injection des dependances de StatusDemandService
	 * 
	 * @see StatusDemandService
	 */
	
	@Autowired
	private StatusDemandService statusDemandServ;

	/**
	 * constructeur
	 * 
	 * @param statusDemandServ
	 */
	
	public StatusDemandController(StatusDemandService statusDemandServ)
	{
		super();
		this.statusDemandServ = statusDemandServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idStatusDemand
	 * @param word
	 * @param statusDemand
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllStatusDemands()
	{
		return statusDemandServ.getAllStatusDemand();
	}
	
	@GetMapping("/id={idStatusDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getStatusDemandById(@PathVariable Integer idStatusDemand)
	{
		return statusDemandServ.getStatusDemandById(idStatusDemand);
	}
	
	@GetMapping("/nom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getStatusDemandByName(@PathVariable String word)
	{
		return statusDemandServ.getStatusDemandByName(word);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postStatusDemand(@RequestBody StatusDemand statusDemand)
	{
		return statusDemandServ.createStatusDemand(statusDemand);
	}
	
	@PutMapping("/modifid={idStatusDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putStatusDemand(@RequestBody StatusDemand statusDemand, @PathVariable Integer idStatusDemand)
	{
		return statusDemandServ.updateStatusDemand(statusDemand, idStatusDemand);
	}
	
	@DeleteMapping("/supprid={idStatusDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteStatusDemand(@PathVariable Integer idStatusDemand)
	{
		return statusDemandServ.deleteStatusDemand(idStatusDemand);
	}

}
