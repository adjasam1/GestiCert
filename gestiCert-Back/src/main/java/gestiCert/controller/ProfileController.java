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

import gestiCert.model.Profile;
import gestiCert.service.ProfileService;

/**
 * ProfileController route les requetes au ProfileService
 * 
 * @see Profile
 * @see ProfileService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/profil")
//@CrossOrigin("http://localhost:4200")
public class ProfileController
{
	
	/**
	 * injection des dependances de ProfileService
	 * 
	 * @see ProfilService
	 */

	@Autowired
	private ProfileService profileServ;
	
	/**
	 * constructeur
	 * 
	 * @param profileServ
	 */
	
	public ProfileController(ProfileService profileServ) {
		super();
		this.profileServ = profileServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idProfile
	 * @param typeProfile
	 * @param profile
	 * 
	 * @return
	 */
	
	@GetMapping()
	public ResponseEntity<?> getAllProfiles()
	{
		return profileServ.getAllProfiles();
	}
	


	@GetMapping("/id={idProfile}")
	public ResponseEntity<?> getProfileById(@PathVariable Integer idProfile)
	{
		return profileServ.getProfileById(idProfile);
	}
	
	@GetMapping("/type={typeProfile}")
	public ResponseEntity<?> getProfileByType(@PathVariable String typeProfile)
	{
		return profileServ.getProfileByType(typeProfile);
	}
	
	@PostMapping("/ajout")
	public ResponseEntity<?> postProfile(@RequestBody Profile profile)
	{
		return profileServ.createProfile(profile);
	}
	
	@PutMapping("/modifid={idProfile}")
	public ResponseEntity<?> putProfile(@RequestBody Profile profile, @PathVariable Integer idProfile)
	{
		return profileServ.updateProfile(profile, idProfile);
	}
	
	@DeleteMapping("/supprid={idProfile}")
	public ResponseEntity<?> deleteProfile(@PathVariable Integer idProfile)
	{
		return profileServ.deleteProfile(idProfile);
	}

}
