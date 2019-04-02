package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Plateform;
import gestiCert.repository.PlateformRepository;

/**
 * PlateformServiceImpl contient les methodes CRUD de l'application pour l'entite Plateform
 * 
 * @see idPlateform
 * @see word
 * @see plateform
 * 
 * @author Utilisateur
 * @version 0.0
 */

@Service
public class PlateformServiceImpl implements PlateformService
{
	
	/**
	 * injection des dependances de PlateformRepository
	 * 
	 * @see PlateformRepository
	 */

	private PlateformRepository plateformRepo;

	/**
	 * constructeur
	 * 
	 * @param plateformRepo
	 */
	
	public PlateformServiceImpl(PlateformRepository plateformRepo)
	{
		super();
		this.plateformRepo = plateformRepo;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idEnvironment
	 * @param word
	 * @param environment
	 * 
	 * @return
	 */
	
	/**
	 * methode qui cherche tous les plateformes
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllPlateforms()
	{
		
		List<Plateform> listPlateforms = null;
		
		try
		{
			listPlateforms = plateformRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listPlateforms == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listPlateforms);
	}

	/**
	 * methode qui cherche une plateforme par son identifiant
	 * 
	 * @param idPlateform
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getPlateformById(Integer idPlateform)
	{

		Optional<Plateform> listPlateforms = null;
		
		try
		{
			listPlateforms = plateformRepo.findById(idPlateform);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listPlateforms == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listPlateforms);
	}
	
	/**
	 * methode qui cherche une plateforme par son son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getPlateformByName(String word)
	{
		List<Plateform> listPlateforms = null;
		
		try
		{
			listPlateforms = plateformRepo.findByNamePlateform(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listPlateforms == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listPlateforms);
	}
	
	/**
	 * methode qui ajoute une plateforme
	 * 
	 * @param plateform
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createPlateform(Plateform plateform)
	{
		Plateform newPlateform = null;
		
		String namePlateform = plateform.getNamePlateform();
		if ((namePlateform == null) || namePlateform.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la plateforme doit être renseigné");
		}
		
		try
		{
			newPlateform = plateformRepo.saveAndFlush(plateform);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newPlateform);
	}
	
	/**
	 * methode qui modifie une plateforme
	 * 
	 * @param plateform
	 * @param idPlateform
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updatePlateform(Plateform plateform, Integer idPlateform)
	{
		Plateform modifyPlateform = null;
		getPlateformById(idPlateform);
		
		String namePlateform = plateform.getNamePlateform();
		if ((namePlateform == null) || (namePlateform.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la plateforme doit être renseigné");
		}
		
		try
		{
			modifyPlateform = plateformRepo.saveAndFlush(plateform);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyPlateform);
	}
	
	/**
	 * methode qui supprime une plateforme par son identifiant
	 * 
	 * @param idPlateform
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deletePlateform(Integer idPlateform)
	{
		 
		try
		{
			plateformRepo.deleteById(idPlateform);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}
}
