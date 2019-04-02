package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.AddressAlternative;

/**
 * 
 * 
 * @see AddressAlternative
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface AddressAlternativeService {

	ResponseEntity<?> getAllAddressAlternative();

	ResponseEntity<?> getAddressAlternativeById(Integer idAddressAlternative);

	ResponseEntity<?> getAddressAlternativeByLink(String word);

	ResponseEntity<?> createAddressAlternative(AddressAlternative addressAlternative);

	ResponseEntity<?> updateAddressAlternative(AddressAlternative addressAlternative, Integer idAddressAlternative);

	ResponseEntity<String> deleteAddressAlternative(Integer idAddressAlternative);

}
