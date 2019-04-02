package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.TypeDemand;
import gestiCert.repository.TypeDemandRepository;

/**
 * TypeDemandServiceImpl contient les methodes CRUD de l'application pour l'entite TypeDemand
 * 
 * @see TypeDemand
 * @see TypeDemandRepository
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Service
public class TypeDemandServiceImpl implements TypeDemandService
{
	
	/**
	 * injection des dependances de TypeDemandRepository
	 * 
	 * @see TypeDemandRepository
	 */

	private TypeDemandRepository typeDemandRepo;

	/**
	 * constructeur
	 * 
	 * @param typeDemandRepo
	 */
	
	public TypeDemandServiceImpl(TypeDemandRepository typeDemandRepo)
	{
		super();
		this.typeDemandRepo = typeDemandRepo;
	}
	
	/**
	 * methode qui cherche tous les types de demande
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllTypeDemands()
	{
		
		List<TypeDemand> listTypeDemands = null;
		
		try
		{
			listTypeDemands = typeDemandRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listTypeDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listTypeDemands);
	}

	/**
	 * methode qui cherche un type de demande par son identifiant
	 * 
	 * @param idTypeDemand
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getTypeDemandById(Integer idTypeDemand)
	{

		Optional<TypeDemand> listTypeDemands = null;
		
		try
		{
			listTypeDemands = typeDemandRepo.findById(idTypeDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listTypeDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listTypeDemands);
	}
	
	/**
	 * methode qui cherche un type de demande par son type ou partie de type
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getTypeDemandByType(String word)
	{
		List<TypeDemand> listTypeDemands = null;
		
		try
		{
			listTypeDemands = typeDemandRepo.findByTypeDemand(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listTypeDemands == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listTypeDemands);
	}
	
	/**
	 * methode qui ajoute un type de demande
	 * 
	 * @param typeDemand
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createTypeDemand(TypeDemand typeDemand)
	{
		TypeDemand newTypeDemand = null;
		
		String typeTypeDemand = typeDemand.getTypeTypeDemand();
		if ((typeTypeDemand == null) || typeTypeDemand.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type de la demande doit être renseigné");
		}
		
		try
		{
			newTypeDemand = typeDemandRepo.saveAndFlush(typeDemand);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newTypeDemand);
	}
	
	/**
	 * methode qui modifie un type de demande
	 * 
	 * @param typeDemand
	 * @param idTypeDemand
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateTypeDemand(TypeDemand typeDemand, Integer idTypeDemand)
	{
		TypeDemand modifyTypeDemand = null;
		getTypeDemandById(idTypeDemand);
		
		String typeTypeDemand = typeDemand.getTypeTypeDemand();
		if ((typeTypeDemand == null) || (typeTypeDemand.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type de la demande doit être renseigné");
		}
		
		try
		{
			modifyTypeDemand = typeDemandRepo.saveAndFlush(typeDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyTypeDemand);
	}
	
	/**
	 * methode qui supprime un type de demande par son identifiant
	 * 
	 * @param idTypeDemand
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteTypeDemand(Integer idTypeDemand)
	{
		 
		try
		{
			typeDemandRepo.deleteById(idTypeDemand);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
