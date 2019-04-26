package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Profile;
import gestiCert.repository.ProfileRepository;

/**
 * ProfilServiceImpl contient les methodes CRUD de l'application pour l'entite Profile
 * 
 * @see Profile
 * @see ProfileRepository
 * 
 * @author Samuel Sabot
 * @version 0.0
 */
@Service
public class ProfileServiceImpl implements ProfileService
{
	
	/**
	 * injection des dependances de ProfileRepository
	 * 
	 * @see ProfileRepository
	 */
	
	private ProfileRepository profileRepo;
	
	/**
	 * constructeur
	 * 
	 * @param profileRepo
	 */

	public ProfileServiceImpl(ProfileRepository profileRepo)
	{
		super();
		this.profileRepo = profileRepo;
	}
	
	/**
	 * methode qui cherche tous les profils
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllProfiles()
	{
		
		List<Profile> listProfiles = null;
		
		try
		{
			listProfiles = profileRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listProfiles == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listProfiles);
	}

	/**
	 * methode qui cherche un profil par son identifiant
	 * 
	 * @param idProfile
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getProfileById(Integer idProfile)
	{

		Optional<Profile> listProfiles = null;
		
		try
		{
			listProfiles = profileRepo.findById(idProfile);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listProfiles == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listProfiles);
	}
	
	/**
	 * methode qui cherche un profil par son type
	 * 
	 * @param typeProfile
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getProfileByType(String typeProfile)
	{
		List<Profile> listProfiles = null;
		
		try
		{
			listProfiles = profileRepo.findByTypeProfile(typeProfile);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listProfiles == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listProfiles);
	}
	
	/**
	 * methode qui ajoute un profil
	 * 
	 * @param profile
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createProfile(Profile profile)
	{
		Profile newProfile = null;
		
		String typeProfile = profile.getTypeProfile();
		if ((typeProfile == null) || typeProfile.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type de profil non renseigné");
		}
		
		try {
			newProfile = profileRepo.saveAndFlush(profile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
	}
	
	/**
	 * methode qui modifie un profil
	 * 
	 * @param profile
	 * @param idProfile
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateProfile(Profile profile, Integer idProfile)
	{
		Profile modifyProfile = null;
		getProfileById(idProfile);
		
		String typeProfile = profile.getTypeProfile();
		if ((typeProfile == null) || (typeProfile.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type du profil non renseigné");
		}
		
		try
		{
			modifyProfile = profileRepo.saveAndFlush(profile);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyProfile);
	}
	
	/**
	 * methode qui supprime un profil par son identifiant
	 * 
	 * @param idProfile
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteProfile(Integer idProfile)
	{
		 
		try
		{
			profileRepo.deleteById(idProfile);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}
}
