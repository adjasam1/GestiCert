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

import gestiCert.model.TypeDemand;
import gestiCert.service.TypeDemandService;

/**
 * TypeDemandController route les requetes au TypeDemandService
 * 
 * @see TypeDemand
 * @see TypeDemandService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/typedemande")
//@CrossOrigin("http://localhost:4200")
public class TypeDemandController
{
	
	/**
	 * injection des dependances de TypeDemandService
	 * 
	 * @see TypeDemandService
	 */
	
	@Autowired
	private TypeDemandService typeDemandServ;

	/**
	 * constructeur
	 * 
	 * @param typeDemandServ
	 */
	
	public TypeDemandController(TypeDemandService typeDemandServ)
	{
		super();
		this.typeDemandServ = typeDemandServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idTypeDemand
	 * @param word
	 * @param typeDemand
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllTypeDemands()
	{
		return typeDemandServ.getAllTypeDemands();
	}
	
	@GetMapping("/id={idTypeDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getTypeDemandById(@PathVariable Integer idTypeDemand)
	{
		return typeDemandServ.getTypeDemandById(idTypeDemand);
	}
	
	@GetMapping("/type={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getTypeDemandByType(@PathVariable String word)
	{
		return typeDemandServ.getTypeDemandByType(word);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postTypeDemand(@RequestBody TypeDemand typeDemand)
	{
		return typeDemandServ.createTypeDemand(typeDemand);
	}
	
	@PutMapping("/modifid={idTypeDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putTypeDemand(@RequestBody TypeDemand typeDemand, @PathVariable Integer idTypeDemand)
	{
		return typeDemandServ.updateTypeDemand(typeDemand, idTypeDemand);
	}
	
	@DeleteMapping("/supprid={idTypeDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteTypeDemand(@PathVariable Integer idTypeDemand)
	{
		return typeDemandServ.deleteTypeDemand(idTypeDemand);
	}

}
