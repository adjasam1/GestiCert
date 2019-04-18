package gestiCert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.Demand;
import gestiCert.service.DemandService;

/**
 * DemandController route les requetes au DemandService
 * 
 * @see Demand
 * @see DemandService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/demande")
//@CrossOrigin("http://localhost:4200")
public class DemandController
{
	
	/**
	 * injection des dependances de DemandService
	 * 
	 * @see DemandService
	 */
	
	@Autowired
	private DemandService demandServ;

	/**
	 * constructeur
	 * 
	 * @param demandServ
	 */
	
	public DemandController(DemandService demandServ)
	{
		super();
		this.demandServ = demandServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idDemand
	 * @param word
	 * @param demand
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllDemands()
	{
		return demandServ.getAllDemands();
	}
	
	@GetMapping("/id={idDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getDemandById(@PathVariable Integer idDemand)
	{
		return demandServ.getDemandById(idDemand);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postDemand(@RequestBody Demand demand)
	{
		return demandServ.createDemand(demand);
	}
	
	@PutMapping("/modifid={idDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putDemand(@RequestBody Demand demand, @PathVariable Integer idDemand)
	{
		return demandServ.updateDemand(demand, idDemand);
	}
	
	@DeleteMapping("/supprid={idDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteDemand(@PathVariable Integer idDemand)
	{
		return demandServ.deleteDemand(idDemand);
	}

}
