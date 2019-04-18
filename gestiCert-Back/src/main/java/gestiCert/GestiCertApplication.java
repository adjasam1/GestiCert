package gestiCert;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gestiCert.model.User;
import gestiCert.model.AppUser;
import gestiCert.model.Role;
import gestiCert.service.AddressAlternativeService;
import gestiCert.service.AppUserService;
import gestiCert.service.UserService;
/**
 * 
 * GestiertApplication est la classe contenant la methode principale de l'application
 * 
 * @author Samuel Sabot
 * @version 0.0
 */
@SpringBootApplication
public class GestiCertApplication implements CommandLineRunner
{
	// Authentification
	@Autowired
	AppUserService appUserServ;

	/**
	 * methode principale
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// demarre et lance l'application en application Spring a partir de la methode principale
		SpringApplication.run(GestiCertApplication.class, args);
	}

	// Authentification
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    /**
     * Init method that loads some data in database.
     * @param params
     * @throws Exception
     */
    @Override
    public void run(String... params) throws Exception {
    	
//        AppUser admin = new AppUser("admin", "admin", new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_SERVICE, Role.ROLE_DEV)));
//        appUserServ.signup(admin);
//
//        AppUser service = new AppUser("service", "service", new ArrayList<>(Arrays.asList(Role.ROLE_SERVICE, Role.ROLE_DEV)));
//        appUserServ.signup(service);
//
//        AppUser dev = new AppUser("dev", "dev", new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(dev);
    }
	



	

	
//	@Override
//	public void run(String... params) throws Exception {
//        User admin = new User("Aaa", "Bbb", "a.b@laposte.fr", "aaaa000", "aze", new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        userService.signup(admin);
//    }
	
}
