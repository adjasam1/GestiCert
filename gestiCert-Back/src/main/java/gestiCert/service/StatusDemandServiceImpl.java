package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.StatusDemand;
import gestiCert.repository.StatusDemandRepository;

/**
 * StatusDemandServiceImpl contient les methodes CRUD de l'application pour l'entite StatusDemand
 * 
 * @see StatusDemand
 * @see StatusDemandRepository
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Service
public class StatusDemandServiceImpl implements StatusDemandService
{
	
	/**
	 * injection des dependances de StatusDemandRepository
	 * 
	 * @see StatusDemandRepository
	 */

	private StatusDemandRepository statusDemandRepo;

	/**
	 * constructeur
	 * 
	 * @param StatusDemandRepo
	 */
	
	public StatusDemandServiceImpl(StatusDemandRepository statusDemandRepo)
	{
		super();
		this.statusDemandRepo = statusDemandRepo;
	}
	
	/**
	 * methode qui cherche tous les statuts de demande
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllStatusDemand()
	{
		
		List<StatusDemand> listStatusDemands = null;
		
		try
		{
			listStatusDemands = statusDemandRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listStatusDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listStatusDemands);
	}

	/**
	 * methode qui cherche un statut de demande par son identifiant
	 * 
	 * @param idStatusDemand
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getStatusDemandById(Integer idStatusDemand)
	{

		Optional<StatusDemand> listStatusDemands = null;
		
		try
		{
			listStatusDemands = statusDemandRepo.findById(idStatusDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listStatusDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listStatusDemands);
	}
	
	/**
	 * methode qui cherche un statut de demande par son son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getStatusDemandByName(String word)
	{
		List<StatusDemand> listStatusDemands = null;
		
		try
		{
			listStatusDemands = statusDemandRepo.findByNameStatusDemand(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listStatusDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listStatusDemands);
	}
	
	/**
	 * methode qui ajoute un statut de demande
	 * 
	 * @param statusDemand
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createStatusDemand(StatusDemand statusDemand)
	{
		StatusDemand newStatusDemand = null;
		
		String nameStatusDemand = statusDemand.getNameStatusDemand();
		if ((nameStatusDemand == null) || nameStatusDemand.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut de la demande doit être renseigné");
		}
		
		try
		{
			newStatusDemand = statusDemandRepo.saveAndFlush(statusDemand);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newStatusDemand);
	}
	
	/**
	 * methode qui modifie un statut de demande
	 * 
	 * @param statusDemand
	 * @param idStatusDemand
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateStatusDemand(StatusDemand statusDemand, Integer idStatusDemand)
	{
		StatusDemand modifyStatusDemand = null;
		getStatusDemandById(idStatusDemand);
		
		String nameStatusDemand = statusDemand.getNameStatusDemand();
		if ((nameStatusDemand == null) || (nameStatusDemand.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut de la demande doit être renseigné");
		}
		
		try
		{
			modifyStatusDemand = statusDemandRepo.saveAndFlush(statusDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyStatusDemand);
	}
	
	/**
	 * methode qui supprime un statut de demande par son identifiant
	 * 
	 * @param idStatusDemand
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteStatusDemand(Integer idStatusDemand)
	{
		 
		try
		{
			statusDemandRepo.deleteById(idStatusDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
