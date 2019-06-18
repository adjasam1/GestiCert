package gestiCert.controller;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.Application;
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
public class CertificateController
{
	
	/**
	 * injection des dependances de CertificateService et MailService
	 * 
	 * @see CertificateService
	 * @see MailService
	 */
	@Autowired
	private CertificateService certificateServ;
	
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
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllCertificates()
	{
		return certificateServ.getAllCertificates();
	}
	
	@GetMapping("/id={idCertificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateById(@PathVariable Integer idCertificate)
	{
		return certificateServ.getCertificateById(idCertificate);
	}
	
	@GetMapping("/nom={word}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByName(@PathVariable String word)
	{
		return certificateServ.getCertificateByName(word);
	}
	
	@GetMapping("/dateemission={number}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByDateIssue(@PathVariable Date number)
	{
		return certificateServ.getCertificateByDateIssue(number);
	}
	
	@GetMapping("/application={application}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByApplication(@PathVariable Application application)
	{
		return certificateServ.getCertificateByApplication(application);
	}
	
	
	/*
	 * methode GET certificate par idRHUser qui gere la methode HTTP entrante avec url
	 * 
	 * @param idRHUser
	 */
	@GetMapping("/utilisateur={idRHUser}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getCertificateByUser(@PathVariable String idRHUser)
	{
		Iterable<Certificate> certificate = null;
		try {
			certificate = certificateServ.getCertificateByUser(idRHUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(certificate);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postCertificate(@RequestBody Certificate certificate)
	{
		Certificate newCertificate = null;
		
		try
		{
			newCertificate = certificateServ.createCertificate(certificate);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
	}
	
	@PutMapping("/modifid={idCertificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putCertificate(@RequestBody Certificate certificate, @PathVariable Integer idCertificate)
	{
		return certificateServ.updateCertificate(certificate, idCertificate);
	}
	
	@DeleteMapping("/supprid={idCertificate}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCertificate(@PathVariable Integer idCertificate)
	{
		return certificateServ.deleteCertificate(idCertificate);
	}
	
	 @ResponseBody
	 @RequestMapping("/mail")
	 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	 public Object sendHtmlEmail(@RequestBody Certificate certificate) throws MessagingException
	 {
		 return mailServ.sendHtmlEmail(certificate);
	 }
}
