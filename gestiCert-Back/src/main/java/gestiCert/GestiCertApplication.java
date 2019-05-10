package gestiCert;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gestiCert.model.AppUser;
import gestiCert.model.Application;
import gestiCert.model.Department;
import gestiCert.model.Environment;
import gestiCert.model.Plateform;
import gestiCert.model.Profile;
import gestiCert.model.Role;
import gestiCert.model.Root;
import gestiCert.model.Server;
import gestiCert.service.AppUserService;
import gestiCert.service.ApplicationService;
import gestiCert.service.CertificateService;
import gestiCert.service.DepartmentService;
import gestiCert.service.EnvironmentService;
import gestiCert.service.ProfileService;
import gestiCert.service.PlateformService;
import gestiCert.service.RootService;
import gestiCert.service.ServerService;

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
	// authent procedure stockée
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	ProfileService profileServ;
	
	@Autowired
	DepartmentService departmentServ;
	
	@Autowired
	AppUserService appUserServ;
	
	@Autowired
	ApplicationService applicationServ;
	
	@Autowired
	EnvironmentService environmentServ;
	
	@Autowired
	PlateformService plateformServ;
	
	@Autowired
	RootService rootServ;
	
	@Autowired
	ServerService serverServ;
	
	@Autowired
	CertificateService certificateServ;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	// Authentification
		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}

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

    /**
     * Init method that loads some data in database.
     * @param params
     * @throws Exception
     */
    @Override
    public void run(String... params) throws Exception {
    	
//    	User admin = new User("paaa111", "aaa", new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_SERVICE, Role.ROLE_DEV)));
//    	userServ.signup(admin);
//    	
//    	User service = new User("pbbb222", "bbb", new ArrayList<>(Arrays.asList(Role.ROLE_SERVICE, Role.ROLE_DEV)));
//    	userServ.signup(service);
//    	
//    	User dev = new User("xccc333", "ccc", new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//    	userServ.signup(dev);
    	
//        AppUser admin = new AppUser("admin", "admin", new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_SERVICE, Role.ROLE_DEV)));
//        appUserServ.signup(admin);
//
//        AppUser service = new AppUser("service", "service", new ArrayList<>(Arrays.asList(Role.ROLE_SERVICE, Role.ROLE_DEV)));
//        appUserServ.signup(service);
//
//        AppUser dev = new AppUser("dev", "dev", new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(dev);
    	
    	
//    	Department dep1 = new Department("Dev Web");
//    	departmentServ.createDepartment(dep1);
//    	Department dep2 = new Department("SIGP3");
//    	departmentServ.createDepartment(dep2);
//    	Department dep3 = new Department("Appli HR");
//    	departmentServ.createDepartment(dep3);
//    	Department dep4 = new Department("Dev HR");
//    	departmentServ.createDepartment(dep4);
//    	
//    	Profile pro1 = new Profile("Développeur(se)");
//    	profileServ.createProfile(pro1);
//    	Profile pro2 = new Profile("Chef(fe) de Projet");
//    	profileServ.createProfile(pro2);
//    	Profile pro3 = new Profile("Chef(fe) de Service");
//    	profileServ.createProfile(pro3);
//        
//        AppUser ala = new AppUser("paaa001", "aaa", "Zanzibar", "Alain", "alain.zanzibar@laposte.fr", "0678564321", new Department(1, "Dev Web"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(ala);
//        AppUser bea = new AppUser("pbbb002", "bbb", "Yemen", "Béatrice", "beatrice.yemen@laposte.fr", "0659527564", new Department(1, "Dev Web"), new Profile(2, "Chef(fe) de Projet"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(bea);
//        AppUser cla = new AppUser("xccc003", "ccc", "Xéres", "Claude", "claude.xeres@laposte.fr", "0765098765", new Department(2, "SIGP3"), new Profile(1, "Développeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(cla);
//        AppUser den = new AppUser("pddd004", "ddd", "Wapiti", "Denise", "denise.wapiti@laposte.fr", "0664598765", new Department(1, "Dev Web"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(den);
//        AppUser eti = new AppUser("xeee005", "eee", "Vanuatu", "Etienne", "etienne.vanuatu@laposte.fr", "0743867290", new Department(1, "Dev Web"), new Profile(1, "Développeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(eti);
//        AppUser fra = new AppUser("pfff006", "fff", "Uruguay", "François", "francois.uruguay@laposte.fr", "0749715064", new Department(3, "Appli HR"), new Profile(1, "Développeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(fra);
//        AppUser gus = new AppUser("pggg007", "ggg", "Tuvalu", "Gustave", "gustave.tuvalu@laposte.fr", "0603528677", new Department(2, "SIGP3"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(gus);
//        AppUser hel = new AppUser("xhhh008", "hhh", "Suriname", "Hélène", "helene.suriname@laposte.fr", "0602352963", new Department(1, "Dev Web"), new Profile(1, "Développeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(hel);
//        AppUser ire = new AppUser("piii009", "iii", "Rwanda", "Irène", "irene.rwanda@laposte.fr", "0620954826", new Department(1, "Dev Web"), new Profile(2, "Chef(fe) de Projet"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(ire);
//        AppUser qua = new AppUser("pjjj010", "jjj", "Quatar", "François", "francois.quatar@laposte.fr", "0644623978", new Department(2, "SIGP3"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(qua);
//        AppUser jul = new AppUser("xkkk011", "kkk", "Tuvalu", "Julie", "julie.tuvalu@laposte.fr", "0762327490", new Department(1, "Dev Web"), new Profile(1, "Développeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(jul);
//        
//        Application ap1 = new Application("LPM", "Intranet HA", "Albanie", "Zoé", "DHAG", "0166557845", "zoe.albanie@laposte.fr");
//        applicationServ.createApplication(ap1);
//        Application ap2 = new Application("GOB", "BAHIA", "Benin", "Yann", "CsORH/GID", "0162890464", "yann.benin@laposte.fr");
//        applicationServ.createApplication(ap2);
//        Application ap3 = new Application("TE_", "Elections", "Congo", "Xavier", "CsORH/GID", "0169464959", "xavier.congo@laposte.fr");
//        applicationServ.createApplication(ap3);
//        Application ap4 = new Application("LR_", "Net-RH", "Danemark", "William", "CsORH/GAPF", "0169464762", "william.danemark@laposte.fr");
//        applicationServ.createApplication(ap4);
//        Application ap5 = new Application("J5P", "Carrières V2", "Equateur", "Viviane", "CsORH/GID", "0169464959", "viviane.equateur@laposte.fr");
//        applicationServ.createApplication(ap5);
//        
//        Environment en1 = new Environment("Développement");
//        environmentServ.createEnvironment(en1);
//        Environment en2 = new Environment("Intégration");
//        environmentServ.createEnvironment(en2);
//        Environment en3 = new Environment("Recette");
//        environmentServ.createEnvironment(en3);
//        Environment en4 = new Environment("Production");
//        environmentServ.createEnvironment(en4);
//        
//        Plateform pl1 = new Plateform("Amont");
//        plateformServ.createPlateform(pl1);
//        Plateform pl2 = new Plateform("Dév/Amont");
//        plateformServ.createPlateform(pl2);
//        Plateform pl3 = new Plateform("Int/Amont");
//        plateformServ.createPlateform(pl3);
//        Plateform pl4 = new Plateform("Rec/Amont");
//        plateformServ.createPlateform(pl4);
//        Plateform pl5 = new Plateform("Production");
//        plateformServ.createPlateform(pl5);
//        
//        Root ro1 = new Root("DSI Centrale");
//        rootServ.createRoot(ro1);
//        Root ro2 = new Root("CertiNomis");
//        rootServ.createRoot(ro2);
//        Root ro3 = new Root("DigiCert");
//        rootServ.createRoot(ro3);
//        Root ro4 = new Root("DASU");
//        rootServ.createRoot(ro4);
//        
//        Server se1 = new Server("Apache");
//        serverServ.createServer(se1);
//        Server se2 = new Server("Red Hat");
//        serverServ.createServer(se2);
//        Server se3 = new Server("Microsoft");
//        serverServ.createServer(se3);
//        Server se4 = new Server("iPlanet");
//        serverServ.createServer(se4);
//        Server se5 = new Server("Javasoft");
//        serverServ.createServer(se5);
//        Server se6 = new Server("IBM");
//        serverServ.createServer(se6);
//        Server se7 = new Server("Web Sphere");
//        serverServ.createServer(se7);
//        Server se8 = new Server("Inet LONDRES");
//        serverServ.createServer(se8);
//        Server se9 = new Server("Londres V5.1");
//        serverServ.createServer(se9);
//        Server se10 = new Server("Londres V5.2");
//        serverServ.createServer(se10);
    	
//    	Certificate ce1 = new Certificate("GOB-20141217-prod", "www.bahia.rh.intra.laposte.fr", "telechargements/gob1", passwordEncoder.encode(("MDP01")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce1);
//    	Certificate ce2 = new Certificate("GOB-20190125-int", "www.bahia-int.rh.intra.laposte.fr", "telechargements/gob2", passwordEncoder.encode(("MDP02")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce2);
//    	Certificate ce3 = new Certificate("J5P-20170131-rec", "www.e-recrutement-rec.rh.intra.laposte.fr", "telechargements/j5p1", passwordEncoder.encode(("MDP03")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce3);
//    	Certificate ce4 = new Certificate("J5P-20170803-prod", "www.laposterecrute.fr", "telechargements/j5p2", passwordEncoder.encode(("MDP04")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce4);
//    	Certificate ce5 = new Certificate("J5P-20170318-int", "www.int.laposterecrute.fr", "telechargements/j5p3", passwordEncoder.encode(("MDP05")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce5);
//    	Certificate ce6 = new Certificate("J5P-20170329-dev", "www.e-recrutement-dev.rh.intra.laposte.fr", "telechargements/j5p4", passwordEncoder.encode(("MDP06")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce6);
//    	Certificate ce7 = new Certificate("LPM-20180806-int", "www.mon-espace-ha-int.extra.laposte.fr", "telechargements/lpm1", passwordEncoder.encode(("MDP07")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce7);
//    	Certificate ce8 = new Certificate("LPM-20180806-prod", "www.mon-espace-ha.extra.laposte.fr", "telechargements/lpm2", passwordEncoder.encode(("MDP08")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce8);
//    	Certificate ce9 = new Certificate("LR_-20180410-dev", "www.intranet-rh-preprod.rh.intra.laposte.fr", "telechargements/lr_1", passwordEncoder.encode(("MDP09")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce9);
//    	Certificate ce10 = new Certificate("LR_-20180412-prod", "www.netrh.extra.laposte.fr", "telechargements/lr_2", passwordEncoder.encode(("MDP10")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce10);
//    	Certificate ce11 = new Certificate("LR_-20180412-dev", "www.netrh-int.extra.laposte.fr", "telechargements/lr_3", passwordEncoder.encode(("MDP11")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce11);
//    	Certificate ce12 = new Certificate("TE_-20170718-prod", "www.elections.rh.intra.laposte.fr", "telechargements/te_1", passwordEncoder.encode(("MDP12")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce12);
//    	Certificate ce13 = new Certificate("TE_-20170718-dev", "www.elections-int.rh.intra.laposte.fr", "telechargements/te_2", passwordEncoder.encode(("MDP13")), null, null, null, null, null, null);
//    	certificateServ.createCertificate(ce13);
    	

    }


}
