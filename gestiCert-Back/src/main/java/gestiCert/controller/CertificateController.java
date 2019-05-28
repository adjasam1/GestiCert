package gestiCert.controller;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.AppUser;
import gestiCert.model.Certificate;
import gestiCert.service.CertificateService;
import gestiCert.service.MailService;

/**
 * CertificateController route les requetes au CertificateService
 * 
 * @see Certificate
 * @see CertificateService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/certificat")
//@CrossOrigin("http://localhost:4200")
public class CertificateController
{
	
	/**
	 * injection des dependances de CertificateService
	 * 
	 * @see CertificateService
	 */
	
	@Autowired
	private CertificateService certificateServ;
//	private CertificateRepository certificateRepo;
	
	@Autowired
	private MailService mailServ;
	

	/**
	 * constructeur
	 * 
	 * @param certificateServ
	 */
	
	public CertificateController(CertificateService certificateServ)
	{
		super();
		this.certificateServ = certificateServ;
	}
	
	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idCertificate
	 * @param word
	 * @param certificate
	 * 
	 * @return
	 */
	
	@GetMapping()
	public ResponseEntity<?> getAllCertificates()
	{
		return certificateServ.getAllCertificates();
	}
	
//	@GetMapping("/idUser={idUser}")
//	public ResponseEntity<?> getCertificateByIdUser(@PathVariable Integer idUser)
//	{
//		return certificateServ.getCertificateByIdUser(idUser);
//	}
//	
//	@GetMapping("/idUs={idUser}")
//	public ResponseEntity<?> getCertifByIdUser(@PathVariable Integer idUser)
//	{
//		return certificateServ.getCertificateByIdUser(idUser);
//	}
	
	@GetMapping("/id={idCertificate}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateById(@PathVariable Integer idCertificate)
	{
		return certificateServ.getCertificateById(idCertificate);
	}
	
	@GetMapping("/nom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByName(@PathVariable String word)
	{
		return certificateServ.getCertificateByName(word);
	}
	
	@GetMapping("/dateemission={number}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByDateIssue(@PathVariable Date number)
	{
		return certificateServ.getCertificateByDateIssue(number);
	}
	
	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postCertificate(@RequestBody Certificate certificate)
	{
		Certificate newCertificate = null;
		
		try
		{
		//	certificate.setPasswordCertificate(bCryptPasswordEncoder.encode(certificate.getPasswordCertificate()));
//			newCertificate = new Certificate(certificate.getNameCertificate(), certificate.getLinkAddressPrincipal(), certificate.getLinkInstallation(), passwordEncoder.encode(certificate.getPasswordCertificate()), certificate.getDateIssue(), certificate.getDateEndValidity(), certificate.getApplication(), certificate.getEnvironment(), certificate.getPlateform(), certificate.getRoot());
			newCertificate = certificateServ.createCertificate(certificate);
//			newCertificate.setPasswordCertificate(passwordEncoder.encode(newCertificate.getPasswordCertificate()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
	}
	
	@PutMapping("/modifid={idCertificate}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putCertificate(@RequestBody Certificate certificate, @PathVariable Integer idCertificate)
	{
		return certificateServ.updateCertificate(certificate, idCertificate);
	}
	
	@DeleteMapping("/supprid={idCertificate}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<String> deleteCertificate(@PathVariable Integer idCertificate)
	{
		return certificateServ.deleteCertificate(idCertificate);
	}
	
	 @ResponseBody
	 @RequestMapping("/mail")
	 public Object sendHtmlEmail(@RequestBody Certificate certificate) throws MessagingException
	 {
//   MimeMessage message = emailSender.createMimeMessage();
//   boolean multipart = true;
//   MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
//   StringBuffer htmlMsg= new StringBuffer();
//   htmlMsg.append("<h1>Test envoi mail</h1>");
//   htmlMsg.append("<h2>"+demand.getIdDemand()+"</h2>");
//   htmlMsg.append("<h3>"+demand.getApplication().getNameApplication()+"</h3>");
//   htmlMsg.append("<h4>Nom Demandeur : "+demand.getUser().getNameUser()+"</h4>");
//   message.setContent(htmlMsg.toString(), "text/html"); // on précise le format HTML
//   helper.setTo(MailConfig.OTHER_EMAIL);
//   helper.setSubject("Essai 1 : Envoyer un email avec du HTML + image");
//   this.emailSender.send(message);
//   return ResponseEntity.status(HttpStatus.OK).body("envois OK : " + null);
   	return mailServ.sendHtmlEmail(certificate);
   }

}
