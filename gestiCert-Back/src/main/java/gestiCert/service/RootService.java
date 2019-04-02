package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Root;

/**
 * 
 * 
 * @see Root
 * 
 * @author Utilisateur
 * @version 0.0
 */

public interface RootService
{

	ResponseEntity<?> getAllRoots();

	ResponseEntity<?> getRootById(Integer idRoot);

	ResponseEntity<?> getRootByName(String word);

	ResponseEntity<?> createRoot(Root root);

	ResponseEntity<?> updateRoot(Root root, Integer idRoot);

	ResponseEntity<String> deleteRoot(Integer idRoot);

}
