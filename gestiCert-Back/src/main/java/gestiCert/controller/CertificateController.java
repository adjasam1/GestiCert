package gestiCert.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.Certificate;
import gestiCert.service.CertificateService;

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
@RequestMapping("/certificat")
@CrossOrigin("http://localhost:4200")
public class CertificateController
{
	
	/**
	 * injection des dependances de CertificateService
	 * 
	 * @see CertificateService
	 */
	
	@Autowired
	private CertificateService certificateServ;

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
	
	@GetMapping("/id={idCertificate}")
	public ResponseEntity<?> getCertificateById(@PathVariable Integer idCertificate)
	{
		return certificateServ.getCertificateById(idCertificate);
	}
	
	@GetMapping("/nom={word}")
	public ResponseEntity<?> getCertificateByName(@PathVariable String word)
	{
		return certificateServ.getCertificateByName(word);
	}
	
	@GetMapping("/dateemission={number}")
	public ResponseEntity<?> getCertificateByDateIssue(@PathVariable Date number)
	{
		return certificateServ.getCertificateByDateIssue(number);
	}
	
	@PostMapping("/ajout")
	public ResponseEntity<?> postCertificate(@RequestBody Certificate certificate)
	{
		return certificateServ.createCertificate(certificate);
	}
	
	@PutMapping("/modifid={idCertificate}")
	public ResponseEntity<?> putCertificate(@RequestBody Certificate certificate, @PathVariable Integer idCertificate)
	{
		return certificateServ.updateCertificate(certificate, idCertificate);
	}
	
	@DeleteMapping("/supprid={idCertificate}")
	public ResponseEntity<String> deleteCertificate(@PathVariable Integer idCertificate)
	{
		return certificateServ.deleteCertificate(idCertificate);
	}

}