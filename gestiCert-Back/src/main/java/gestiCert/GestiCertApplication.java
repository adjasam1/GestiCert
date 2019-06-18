package gestiCert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gestiCert.service.AppUserService;
import gestiCert.service.ApplicationService;
import gestiCert.service.CertificateService;
import gestiCert.service.DepartmentService;
import gestiCert.service.EnvironmentService;
import gestiCert.service.ProfileService;
import gestiCert.service.PlateformService;
import gestiCert.service.RootService;
import gestiCert.service.ServerService;
import gestiCert.service.StatusDemandService;
import gestiCert.service.TypeDemandService;

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
	StatusDemandService statusDemandServ;
	
	@Autowired
	TypeDemandService typeDemandServ;
	
	@Autowired
	CertificateService certificateServ;
	
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
    	
//    	Department dep1 = new Department("Dev Web");
//    	departmentServ.createDepartment(dep1);
//    	Department dep2 = new Department("SIGP3");
//    	departmentServ.createDepartment(dep2);
//    	Department dep3 = new Department("Appli HR");
//    	departmentServ.createDepartment(dep3);
//    	Department dep4 = new Department("Dev HR");
//    	departmentServ.createDepartment(dep4);
//    	
//    	Profile pro1 = new Profile("Developpeur(se)");
//    	profileServ.createProfile(pro1);
//    	Profile pro2 = new Profile("Chef(fe) de Projet");
//    	profileServ.createProfile(pro2);
//    	Profile pro3 = new Profile("Chef(fe) de Service");
//    	profileServ.createProfile(pro3);
//        
//        AppUser ala = new AppUser("paaa001", "aaa", "Zanzibar", "Alain", "alain.zanzibar@laposte.fr", "0678564321", new Department(1, "Dev Web"), new Profile(1, "Developpeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(ala);
//        AppUser bea = new AppUser("pbbb002", "bbb", "Yemen", "Beatrice", "beatrice.yemen@laposte.fr", "0659527564", new Department(1, "Dev Web"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(bea);
//        AppUser cla = new AppUser("xccc003", "ccc", "Xeres", "Claude", "claude.xeres@laposte.fr", "0765098765", new Department(2, "SIGP3"), new Profile(1, "Developpeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(cla);
//        AppUser den = new AppUser("pddd004", "ddd", "Wapiti", "Denise", "denise.wapiti@laposte.fr", "0664598765", new Department(1, "Dev Web"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(den);
//        AppUser eti = new AppUser("xeee005", "eee", "Vanuatu", "Etienne", "etienne.vanuatu@laposte.fr", "0743867290", new Department(1, "Dev Web"), new Profile(1, "Developpeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(eti);
//        AppUser fra = new AppUser("pfff006", "fff", "Uruguay", "Francois", "francois.uruguay@laposte.fr", "0749715064", new Department(3, "Appli HR"), new Profile(1, "Developpeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(fra);
//        AppUser gus = new AppUser("pggg007", "ggg", "Tuvalu", "Gustave", "gustave.tuvalu@laposte.fr", "0603528677", new Department(2, "SIGP3"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(gus);
//        AppUser hel = new AppUser("xhhh008", "hhh", "Suriname", "Helene", "helene.suriname@laposte.fr", "0602352963", new Department(1, "Dev Web"), new Profile(1, "Developpeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(hel);
//        AppUser ire = new AppUser("piii009", "iii", "Rwanda", "Irene", "irene.rwanda@laposte.fr", "0620954826", new Department(1, "Dev Web"), new Profile(2, "Chef(fe) de Projet"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(ire);
//        AppUser qua = new AppUser("pjjj010", "jjj", "Quatar", "Francois", "francois.quatar@laposte.fr", "0644623978", new Department(2, "SIGP3"), new Profile(3, "Chef(fe) de Service"), new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        appUserServ.signup(qua);
//        AppUser jul = new AppUser("xkkk011", "kkk", "Tuvalu", "Julie", "julie.tuvalu@laposte.fr", "0762327490", new Department(1, "Dev Web"), new Profile(1, "Developpeur(se)"), new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        appUserServ.signup(jul);
//        
//        Application ap1 = new Application("LPM", "Intranet HA", "Albanie", "Zoe", "DHAG", "0166557845", "zoe.albanie@laposte.fr");
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
//        Environment en1 = new Environment("Developpement");
//        environmentServ.createEnvironment(en1);
//        Environment en2 = new Environment("Integration");
//        environmentServ.createEnvironment(en2);
//        Environment en3 = new Environment("Recette");
//        environmentServ.createEnvironment(en3);
//        Environment en4 = new Environment("Production");
//        environmentServ.createEnvironment(en4);
//        
//        Plateform pl1 = new Plateform("Amont");
//        plateformServ.createPlateform(pl1);
//        Plateform pl2 = new Plateform("Dev/Amont");
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
//        
//    	StatusDemand sd1 = new StatusDemand("Non");
//    	statusDemandServ.createStatusDemand(sd1);
//    	StatusDemand sd2 = new StatusDemand("Oui");
//    	statusDemandServ.createStatusDemand(sd2);
//    	StatusDemand sd3 = new StatusDemand("En cours");
//    	statusDemandServ.createStatusDemand(sd3);
//    	
//    	TypeDemand tp1 = new TypeDemand("Creation");
//    	typeDemandServ.createTypeDemand(tp1);
//    	TypeDemand tp2 = new TypeDemand("Renouvellement");
//    	typeDemandServ.createTypeDemand(tp2);
//    	TypeDemand tp3 = new TypeDemand("Revocation");
//    	typeDemandServ.createTypeDemand(tp3);
    	
    }

}
