package gestiCert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * GestiertApplication est la classe contenant la methode principale de l'application
 * 
 * @author Samuel Sabot
 * @version 0.0
 */
@SpringBootApplication
public class GestiCertApplication
{

	/**
	 * methode principale
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
	
		/**
		 * demarre et lance l'application en application Spring a partir de la methode principale
		 */
		SpringApplication.run(GestiCertApplication.class, args);
		
	}
	
}
