package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Plateform;

/**
 * 
 * 
 * @see Plateform
 * 
 * @author Utilisateur
 * @version 0.0
 */

public interface PlateformService {

	ResponseEntity<?> getAllPlateforms();

	ResponseEntity<?> getPlateformById(Integer idPlateform);

	ResponseEntity<?> getPlateformByName(String word);

	ResponseEntity<?> createPlateform(Plateform plateform);

	ResponseEntity<?> updatePlateform(Plateform plateform, Integer idPlateform);

	ResponseEntity<String> deletePlateform(Integer idPlateform);

}
