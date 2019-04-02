package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.StatusDemand;
import gestiCert.model.TypeDemand;

/**
 * 
 * 
 * @see StatusDemand
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface TypeDemandService {

	ResponseEntity<?> getAllTypeDemands();

	ResponseEntity<?> getTypeDemandById(Integer idTypeDemand);

	ResponseEntity<?> getTypeDemandByType(String word);

	ResponseEntity<?> createTypeDemand(TypeDemand typeDemand);

	ResponseEntity<?> updateTypeDemand(TypeDemand typeDemand, Integer idTypeDemand);

	ResponseEntity<String> deleteTypeDemand(Integer idTypeDemand);

}
