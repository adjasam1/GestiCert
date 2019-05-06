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

import gestiCert.model.Plateform;
import gestiCert.service.PlateformService;

/**
 * PlateformController  route les requetes au PlateformService
 * 
 * @see Plateform
 * @see PlateformService
 * 
 * @author Utilisateur
 * @version 0.0
 */

@RestController
@RequestMapping("/api/plateforme")
//@CrossOrigin("http://localhost:4200")
public class PlateformController
{
	
	/**
	 * injection des dependances de PlateformService
	 * 
	 * @see PlateformService
	 */
	
	@Autowired
	private PlateformService plateformServ;

	/**
	 * constructeur
	 * 
	 * @param plateformServ
	 */
	
	public PlateformController(PlateformService plateformServ)
	{
		super();
		this.plateformServ = plateformServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idPlateform
	 * @param word
	 * @param plateform
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllPlatforms()
	{
		return plateformServ.getAllPlateforms();
	}
	
	@GetMapping("/id={idPlateform}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getPlateformById(@PathVariable Integer idPlateform)
	{
		return plateformServ.getPlateformById(idPlateform);
	}
	
	@GetMapping("/nom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getPlateformByName(@PathVariable String word)
	{
		return plateformServ.getPlateformByName(word);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postPlateform(@RequestBody Plateform plateform)
	{
		return plateformServ.createPlateform(plateform);
	}
	
	@PutMapping("/modifid={idPlateform}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putPlateform(@RequestBody Plateform plateform, @PathVariable Integer idPlateform)
	{
		return plateformServ.updatePlateform(plateform, idPlateform);
	}
	
	@DeleteMapping("/supprid={idPlateform}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deletePlateform(@PathVariable Integer idPlateform)
	{
		return plateformServ.deletePlateform(idPlateform);
	}
	
}
