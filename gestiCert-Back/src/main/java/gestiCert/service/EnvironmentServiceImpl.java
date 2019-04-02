package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Environment;
import gestiCert.repository.EnvironmentRepository;

/**
 * EnvironmentServiceImpl contient les methodes CRUD de l'application pour l'entite Environment
 * 
 * @see Environment
 * @see EnvironmentRepository
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Service
public class EnvironmentServiceImpl implements EnvironmentService
{
	
	/**
	 * injection des dependances de EnvironmentRepository
	 * 
	 * @see EnvironmentRepository
	 */

	private EnvironmentRepository environmentRepo;

	/**
	 * constructeur
	 * 
	 * @param environmentRepo
	 */
	
	public EnvironmentServiceImpl(EnvironmentRepository environmentRepo)
	{
		super();
		this.environmentRepo = environmentRepo;
	}
	
	/**
	 * methode qui cherche tous les environnements
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllEnvironments()
	{
		
		List<Environment> listEnvironments = null;
		
		try
		{
			listEnvironments = environmentRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listEnvironments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listEnvironments);
	}

	/**
	 * methode qui cherche un environnement par son identifiant
	 * 
	 * @param idEnvironment
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getEnvironmentById(Integer idEnvironment)
	{

		Optional<Environment> listEnvironments = null;
		
		try
		{
			listEnvironments = environmentRepo.findById(idEnvironment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listEnvironments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listEnvironments);
	}
	
	/**
	 * methode qui cherche un environnement par son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getEnvironmentByName(String word)
	{
		List<Environment> listEnvironments = null;
		
		try
		{
			listEnvironments = environmentRepo.findByNameEnvironnement(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listEnvironments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listEnvironments);
	}
	
	/**
	 * methode qui ajoute un environnement
	 * 
	 * @param environment
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createEnvironment(Environment environment)
	{
		Environment newEnvironment = null;
		
		String nameEnvironment = environment.getNameEnvironment();
		if ((nameEnvironment == null) || nameEnvironment.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type du profil non renseigné");
		}
		
		try
		{
			newEnvironment = environmentRepo.saveAndFlush(environment);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newEnvironment);
	}
	
	/**
	 * methode qui modifie un environnement
	 * 
	 * @param environment
	 * @param idEnvironment
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateEnvironment(Environment environment, Integer idEnvironment)
	{
		Environment modifyEnvironment = null;
		getEnvironmentById(idEnvironment);
		
		String nameEnvironment = environment.getNameEnvironment();
		if ((nameEnvironment == null) || (nameEnvironment.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type du profil non renseigné");
		}
		
		try
		{
			modifyEnvironment = environmentRepo.saveAndFlush(environment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyEnvironment);
	}
	
	/**
	 * methode qui supprime un environnement par son identifiant
	 * 
	 * @param idEnvironment
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteEnvironment(Integer idEnvironment)
	{
		 
		try
		{
			environmentRepo.deleteById(idEnvironment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
