package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.AddressAlternative;
import gestiCert.model.AppUser;
import gestiCert.model.Application;
import gestiCert.model.Certificate;
import gestiCert.repository.ApplicationRepository;

/**
 * ApplicationServiceImpl contient les methodes CRUD de l'application pour l'entite Application
 * 
 * @see
 * @see
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Service
public class ApplicationServiceImpl implements ApplicationService
{

	/**
	 * injection des dependances de ApplicationRepository
	 * 
	 * @see ApplicationRepository
	 */
	
	private ApplicationRepository applicationRepo;

	/**
	 * constructeur
	 * 
	 * @param applicationRepo
	 */
	
	public ApplicationServiceImpl(ApplicationRepository applicationRepo)
	{
		super();
		this.applicationRepo = applicationRepo;
	}
	
	/**
	 * methode qui cherche toutes les applications 
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllApplications()
	{
		List<Application> listApplications = null;
		
		try
		{
			listApplications = applicationRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listApplications == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listApplications);
	}
	
	/**
	 * methode qui cherche une application par son identifiant
	 * 
	 * @param idApplication
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getApplicationById(Integer idApplication)
	{

		Optional<Application> listApplications = null;
		
		try
		{
			listApplications = applicationRepo.findById(idApplication);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listApplications == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listApplications);
	}
	
	/**
	 * methode qui cherche une application par son code CCX
	 * 
	 * @param codeCCX
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getApplicationByCCX(String codeCCX)
	{
		List<Application> listApplications = null;
		
		try
		{
			listApplications = applicationRepo.findByCodeCCX(codeCCX);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listApplications == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listApplications);
	}
	
	/**
	 * methode qui cherche une application par son nom
	 * 
	 * @param nameApplication
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getApplicationByName(String nameApplication)
	{
		List<Application> listApplications = null;
		
		try
		{
			listApplications = applicationRepo.findByNameApplication(nameApplication);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listApplications == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listApplications);
	}
	
//	@Override
//	public List<Application> getApplicationByUser(Integer idRHUser) {
//		return this.applicationRepo.findByIdRHUser(idRHUser);
//	}
	
//	@Override
//	public List<Application> getApplicationByOneUser(AppUser user) {
//		return this.applicationRepo.findByUser(user);
//	}
//	
	/**
	 * methode qui ajoute une application
	 * 
	 * @param application
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createApplication(Application application)
	{
		Application newApplication = null;
		
		String codeCCX = application.getCodeCCX();
		if ((codeCCX == null) || codeCCX.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le code CCX de l'application doit être renseigné");
		}
		
		try {
			newApplication = applicationRepo.saveAndFlush(application);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newApplication);
	}
	
	/**
	 * methode qui modifie une application
	 * 
	 * @param application
	 * @param idApplication
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateApplication(Application application, Integer idApplication)
	{
		Application modifyApplication = null;
		getApplicationById(idApplication);
		
		String codeCCX = application.getCodeCCX();
		if ((codeCCX == null) || codeCCX.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le code CCX de l'application doit être renseigné");
		}
		
		try {
			modifyApplication = applicationRepo.saveAndFlush(application);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyApplication);
	}
	
	/**
	 * methode qui supprime une application par son identifiant
	 * 
	 * @param idApplication
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteApplication(Integer idApplication)
	{
		 
		try
		{
			applicationRepo.deleteById(idApplication);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}
	
}
