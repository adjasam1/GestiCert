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

import gestiCert.model.Application;
import gestiCert.service.ApplicationService;

/**
 * ApplicationController route les requetes au ApplicationService
 * 
 * @see Application
 * @see ApplicationService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/application")
public class ApplicationController
{
	
	/**
	 * injection des dependances de ApplicationService
	 * 
	 * @see ApplicationService
	 */
	
	@Autowired
	private ApplicationService applicationServ;

	/**
	 * constructeur
	 * 
	 * @param applicationServ
	 */
	
	public ApplicationController(ApplicationService applicationServ)
	{
		super();
		this.applicationServ = applicationServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * 
	 * @param idApplication
	 * @param codeCCX
	 * @param nameApplication
	 * @param application
	 * 
	 * @return
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllApplications()
	{
		return applicationServ.getAllApplications();
	}

	@GetMapping("/id={idApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getApplicationById(@PathVariable Integer idApplication)
	{
		return applicationServ.getApplicationById(idApplication);
	}

	@GetMapping("/ccx={codeCCX}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getApplicationByCCX(@PathVariable String codeCCX)
	{
		return applicationServ.getApplicationByCCX(codeCCX);
	}

	@GetMapping("/nom={nameApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getApplicationByName(@PathVariable String nameApplication)
	{
		return applicationServ.getApplicationByName(nameApplication);
	}

	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postApplication(@RequestBody Application application)
	{
		return applicationServ.createApplication(application);
	}

	@PutMapping("/modifid={idApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putApplication(@RequestBody Application application, @PathVariable Integer idApplication)
	{
		return applicationServ.updateApplication(application, idApplication);
	}

	@DeleteMapping("/supprid={idApplication}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteApplication(@PathVariable Integer idApplication)
	{
		return applicationServ.deleteApplication(idApplication);
	}

}
