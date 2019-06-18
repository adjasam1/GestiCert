package gestiCert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.AddressAlternative;
import gestiCert.model.Certificate;
import gestiCert.service.AddressAlternativeService;

/**
 * AddressAlternativeController route les requetes au AddressAlternativeService
 * 
 * @see AddressAlternative
 * @see AddressAlternativeService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/adressealternative")
public class AddressAlternativeController
{
	
	/**
	 * injection des dependances de AddressAlternativeService
	 * 
	 * @see AddressAlternativeService
	 */
	
	@Autowired
	private AddressAlternativeService addressAlternativeServ;

	/**
	 * constructeur
	 * 
	 * @param AddressAlternativeServ
	 */
	
	public AddressAlternativeController()
	{
		super();
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idAddressAlternative
	 * @param word
	 * @param addressAlternative
	 * 
	 * @return
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllAddressAlternatives()
	{
		return addressAlternativeServ.getAllAddressAlternative();
	}
	
	@GetMapping("/id={idAddressAlternative}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAddressAlternativeById(@PathVariable Integer idAddressAlternative)
	{
		return addressAlternativeServ.getAddressAlternativeById(idAddressAlternative);
	}
	
	@GetMapping("/lien={word}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAddressAlternativeByLink(@PathVariable String word)
	{
		return addressAlternativeServ.getAddressAlternativeByLink(word);
	}
	
	@GetMapping("/certificat={certificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAddressAlternativeByCertificate(@PathVariable Certificate certificate)
	{
		return addressAlternativeServ.getAddressAlternativeByCertificate(certificate);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postAddressAlternative(@RequestBody AddressAlternative addressAlternative)
	{
		return addressAlternativeServ.createAddressAlternative(addressAlternative);
	}
	
	@PutMapping("/modifid={idAddressAlternative}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putAddressAlternative(@RequestBody AddressAlternative addressAlternative, @PathVariable Integer idAddressAlternative)
	{
		return addressAlternativeServ.updateAddressAlternative(addressAlternative, idAddressAlternative);
	}
	
	@DeleteMapping("/supprid={idAddressAlternative}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEnvironment(@PathVariable Integer idAddressAlternative)
	{
		return addressAlternativeServ.deleteAddressAlternative(idAddressAlternative);
	}

}
