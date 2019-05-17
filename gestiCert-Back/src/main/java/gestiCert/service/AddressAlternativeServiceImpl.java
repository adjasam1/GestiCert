package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.AddressAlternative;
import gestiCert.repository.AddressAlternativeRepository;

/**
 * AddressAlternativeServiceImpl contient les methodes CRUD de l'application pour l'entite AddressAlternative
 * 
 * @see AddressAlternative
 * @see AddressAlternativeService
 * 
 * @author Utilisateur
 * @version Samuel Sabot
 */

@Service
public class AddressAlternativeServiceImpl implements AddressAlternativeService
{
	
	/**
	 * injection des dependances de addressAlternativeRepository
	 * 
	 * @see addressAlternativeRepository
	 */

	private AddressAlternativeRepository addressAlternativeRepo;

	/**
	 * constructeur
	 * 
	 * @param addressAlternativeRepo
	 */
	
	public AddressAlternativeServiceImpl(AddressAlternativeRepository addressAlternativeRepo)
	{
		super();
		this.addressAlternativeRepo = addressAlternativeRepo;
	}
	
	/**
	 * methode qui cherche toutes les adresses alternatives
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllAddressAlternative()
	{
		
		List<AddressAlternative> listAddressAlternatives = null;
		
		try
		{
			listAddressAlternatives = addressAlternativeRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listAddressAlternatives == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternatives);
	}

	/**
	 * methode qui cherche une adresse alternative par son identifiant
	 * 
	 * @param idAddressAlternative
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAddressAlternativeById(Integer idAddressAlternative)
	{

		Optional<AddressAlternative> listAddressAlternatives = null;
		
		try
		{
			listAddressAlternatives = addressAlternativeRepo.findById(idAddressAlternative);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listAddressAlternatives == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternatives);
	}
	
	/**
	 * methode qui cherche une adresse alternative par son son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAddressAlternativeByLink(String word)
	{
		List<AddressAlternative> listAddressAlternatives = null;
		
		try
		{
			listAddressAlternatives = addressAlternativeRepo.findByLinkAddressAlternative(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listAddressAlternatives == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternatives);
	}
	
	/**
	 * methode qui ajoute une adresse alternative
	 * 
	 * @param addressAlternative
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createAddressAlternative(AddressAlternative addressAlternative)
	{
		AddressAlternative newAddressAlternative = null;
		
		String linkAddressAlternative = addressAlternative.getLinkAddressAlternative();
		if ((linkAddressAlternative == null) || linkAddressAlternative.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le lien de l'adresse alternative doit être renseigné");
		}
		
		Integer id_certificat = addressAlternative.getCertificate().getIdCertificate();
		if ((id_certificat == null) || (id_certificat == 0))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'identifiant du certificat de l'adresse alternative doit être renseigné");
		}
		
		try
		{
			newAddressAlternative = addressAlternativeRepo.saveAndFlush(addressAlternative);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newAddressAlternative);
	}
	
	/**
	 * methode qui modifie une adresse alternative
	 * 
	 * @param addressAlternative
	 * @param idAddressAlternative
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateAddressAlternative(AddressAlternative addressAlternative, Integer idAddressAlternative)
	{
		AddressAlternative modifyAddressAlternative = null;
		getAddressAlternativeById(idAddressAlternative);
		
		String linkAddressAlternative = addressAlternative.getLinkAddressAlternative();
		if ((linkAddressAlternative == null) || linkAddressAlternative.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le lien de l'adresse alternative doit être renseigné");
		}
		
		Integer id_certificat = addressAlternative.getCertificate().getIdCertificate();
		if ((id_certificat == null) || (id_certificat == 0))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'identifiant du certificat de l'adresse alternative doit être renseigné");
		}
		
		try
		{
			modifyAddressAlternative = addressAlternativeRepo.saveAndFlush(addressAlternative);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyAddressAlternative);
	}
	
	/**
	 * methode qui supprime une adresse alternative par son identifiant
	 * 
	 * @param idAddressAlternative
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteAddressAlternative(Integer idAddressAlternative)
	{
		 
		try
		{
			addressAlternativeRepo.deleteById(idAddressAlternative);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}
	
//	@Override
//	public ResponseEntity<?> getAddressAlternativeByIdCertificate(Integer idCertificate)
//	{
//
//		List<AddressAlternative> listAddressAlternatives = null;
//		
//		try
//		{
//			listAddressAlternatives = addressAlternativeRepo.findByIdCertificate(idCertificate);
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		if (listAddressAlternatives == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(listAddressAlternatives);
//	}

}
