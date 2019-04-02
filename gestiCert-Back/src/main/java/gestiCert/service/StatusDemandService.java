package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.StatusDemand;

/**
 * 
 * 
 * @see StatusDemand
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface StatusDemandService {

	ResponseEntity<?> getAllStatusDemand();

	ResponseEntity<?> getStatusDemandById(Integer idStatusDemand);

	ResponseEntity<?> getStatusDemandByName(String word);

	ResponseEntity<?> createStatusDemand(StatusDemand statusDemand);

	ResponseEntity<?> updateStatusDemand(StatusDemand statusDemand, Integer idStatusDemand);

	ResponseEntity<String> deleteStatusDemand(Integer idStatusDemand);

}
