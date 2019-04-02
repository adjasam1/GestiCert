package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Profile;

/**
 * 
 * 
 * @see Profile
 * 
 * @author Utilisateur
 * @version 0.0
 */

public interface ProfileService
{

	public ResponseEntity<?> getAllProfiles();

	public ResponseEntity<?> getProfileById(Integer idProfile);

	public ResponseEntity<?> getProfileByType(String typeProfile);

	public ResponseEntity<?> createProfile(Profile profile);

	public ResponseEntity<?> updateProfile(Profile profile, Integer idProfile);

	public ResponseEntity<String> deleteProfile(Integer idProfile);

}
