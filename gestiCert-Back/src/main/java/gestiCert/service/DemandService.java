package gestiCert.service;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Demand;

/**
 * 
 * 
 * @see Demand
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface DemandService {

	ResponseEntity<?> getAllDemands();

	ResponseEntity<?> getDemandById(Integer idDemand);

	ResponseEntity<?> createDemand(Demand demand);

	ResponseEntity<?> updateDemand(Demand demand, Integer idDemand);

	ResponseEntity<String> deleteDemand(Integer idDemand);

//	Object sendHtmlEmail(Demand demand) throws MessagingException;

}
