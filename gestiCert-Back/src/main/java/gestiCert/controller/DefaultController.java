package gestiCert.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * GestiCertServices est un service qui teste si le serveur a bien demarre
 * 
 * @author Utilisateur
 * @version 0.0
 */

@Controller
public class DefaultController
{
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	@GetMapping("/gesticert")
	public ResponseEntity<String> gestiCert()
	{
		logger.info("Démarrage des services OK");
		return new ResponseEntity<String> ("Réponse du serveur : " + HttpStatus.OK.name(), HttpStatus.OK);
	}

}
