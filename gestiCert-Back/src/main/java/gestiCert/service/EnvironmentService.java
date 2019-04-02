package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Environment;

/**
 * 
 * 
 * @see Environment
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface EnvironmentService
{

	ResponseEntity<?> getAllEnvironments();

	ResponseEntity<?> getEnvironmentById(Integer idEnvironment);

	ResponseEntity<?> getEnvironmentByName(String word);

	ResponseEntity<?> createEnvironment(Environment environment);

	ResponseEntity<?> updateEnvironment(Environment environment, Integer idEnvironment);

	ResponseEntity<String> deleteEnvironment(Integer idEnvironment);

}
