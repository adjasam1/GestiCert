package gestiCert.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.mail.MailConfig;
import gestiCert.model.Demand;
import gestiCert.service.DemandService;

/**
 * DemandController route les requetes au DemandService
 * 
 * @see Demand
 * @see DemandService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/demande")
//@CrossOrigin("http://localhost:4200")
public class DemandController
{
	
	/**
	 * injection des dependances de DemandService
	 * 
	 * @see DemandService
	 */
	
	@Autowired
	private DemandService demandServ;
	
	@Autowired
    public JavaMailSender emailSender;

	/**
	 * constructeur
	 * 
	 * @param demandServ
	 */
	
	public DemandController(DemandService demandServ)
	{
		super();
		this.demandServ = demandServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idDemand
	 * @param word
	 * @param demand
	 * 
	 * @return
	 */
	
	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllDemands()
	{
		return demandServ.getAllDemands();
	}
	
	@GetMapping("/id={idDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getDemandById(@PathVariable Integer idDemand)
	{
		return demandServ.getDemandById(idDemand);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postDemand(@RequestBody Demand demand)
	{
		return demandServ.createDemand(demand);
	}
	
	@PutMapping("/modifid={idDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putDemand(@RequestBody Demand demand, @PathVariable Integer idDemand)
	{
		return demandServ.updateDemand(demand, idDemand);
	}
	
	@DeleteMapping("/supprid={idDemand}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteDemand(@PathVariable Integer idDemand)
	{
		return demandServ.deleteDemand(idDemand);
	}
 
    @ResponseBody
    @RequestMapping("/mail")
    public ResponseEntity<?> sendHtmlEmail(@RequestBody Demand demand) throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    boolean multipart = true;
    MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
    StringBuffer htmlMsg= new StringBuffer();
    htmlMsg.append("<h1>Test envoi mail</h1>");
    htmlMsg.append("<h2>"+demand.getIdDemand()+"</h2>");
    htmlMsg.append("<h3>"+demand.getApplication().getNameApplication()+"</h3>");
    htmlMsg.append("<h4>Nom Demandeur : "+demand.getUser().getNameUser()+"</h4>");
    message.setContent(htmlMsg.toString(), "text/html"); // on précise le format HTML
    helper.setTo(MailConfig.OTHER_EMAIL);
    helper.setSubject("Essai 1 : Envoyer un email avec du HTML + image");
    this.emailSender.send(message);
    return ResponseEntity.status(HttpStatus.OK).body("envois OK : " + null);
    }

}
