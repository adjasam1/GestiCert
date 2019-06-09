package gestiCert.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Application;
import gestiCert.model.Certificate;
import gestiCert.repository.CertificateRepository;

/**
 * CertificateServiceImpl contient les methodes CRUD de l'application pour l'entite Certificate
 * 
 * @see Certificate
 * @see CertificateRepository
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Service
public class CertificateServiceImpl implements CertificateService
{
	
	/**
	 * injection des dependances de CertificateRepository
	 * 
	 * @see CertificateRepository
	 */

	private CertificateRepository certificateRepo;
	
//	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * constructeur
	 * 
	 * @param certificateRepo
	 */
	
	
	
	public CertificateServiceImpl(CertificateRepository certificateRepo)
	{
		super();
		this.certificateRepo = certificateRepo;
	}



//	public CertificateServiceImpl() {
//	}



//	public CertificateServiceImpl(CertificateRepository certificateRepo, BCryptPasswordEncoder passwordEncoder) {
//		super();
//		this.certificateRepo = certificateRepo;
//		this.passwordEncoder = passwordEncoder;
//	}



	/**
	 * methode qui cherche tous les certificats
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllCertificates()
	{
		
		List<Certificate> listCertificates = null;
		
		try
		{
			listCertificates = certificateRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listCertificates == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listCertificates);
	}
	
	@Override
	public List<Certificate> getCertificateByUser(String idRHUser)
	{
		return certificateRepo.findByIdRHUser(idRHUser);
	}

	/**
	 * methode qui cherche un certificat par son identifiant
	 * 
	 * @param idCertificate
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getCertificateById(Integer idCertificate)
	{

		Optional<Certificate> listCertificates = null;
		
		try
		{
			listCertificates = certificateRepo.findById(idCertificate);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listCertificates == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listCertificates);
	}
	
	/**
	 * methode qui cherche un certificat par son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getCertificateByName(String word)
	{
		List<Certificate> listCertificates = null;
		
		try
		{
			listCertificates = certificateRepo.findByNameCertificate(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listCertificates == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listCertificates);
	}
	
	/**
	 * methode qui cherche un certificat par sa date d'emission ou une partie de sa date d'emission
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getCertificateByDateIssue(Date number)
	{
		List<Certificate> listCertificates = null;
		
		try
		{
			listCertificates = certificateRepo.findByDateIssueCertificate(number);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listCertificates == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listCertificates);
	}
	
	@Override
	public ResponseEntity<?> getCertificateByApplication(Application application)
	{
		Iterable<Certificate> certificate = null;
		try {
			certificate = certificateRepo.findByApplication(application);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(certificate);
	}
	
	/**
	 * methode qui ajoute un certificat
	 * 
	 * @param certificate
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public Certificate createCertificate(Certificate certificate)
	{
		return certificateRepo.saveAndFlush(certificate);
		
	}
	
//	@Override
//	public Certificate createCertificate(Certificate certificate) {
//		return certificateRepo.saveAndFlush(newCertificate);
//	}
	
//	 @Override
//	    public String signup(AppUser user) throws ExistingIdRHUserException {
//	        if (!appUserRepo.existsByIdRHUser(user.getIdRHUser())) {
//	            AppUser userToSave = new AppUser(user.getIdRHUser(), passwordEncoder.encode(user.getPasswordUser()), user.getNameUser(), user.getFirstNameUser(), user.geteMailUser(), user.getPhoneUser(), user.getDepartment(), user.getFunction(), user.getRoleList());
//	            appUserRepo.save(userToSave);
//	            return jwtTokenProvider.createToken(user.getIdRHUser(), user.getRoleList());
//	        } else {
//	            throw new ExistingIdRHUserException();
//	        }
//	    }
	
//	this.nameCertificate = nameCertificate;
//	this.linkAddressPrincipal = linkAddressPrincipal;
//	this.linkInstallation = linkInstallation;
//	this.passwordCertificate = passwordCertificate;
//	this.dateIssue = dateIssue;
//	this.dateEndValidity = dateEndValidity;
//	this.application = application;
//	this.environment = environment;
//	this.plateform = plateform;
//	this.root = root;
	
	/**
	 * methode qui modifie un certificat
	 * 
	 * @param certificate
	 * @param idCertificate
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateCertificate(Certificate certificate, Integer idCertificate)
	{
		Certificate modifyCertificate = null;
		getCertificateById(idCertificate);
		
		try
		{
			modifyCertificate = certificateRepo.saveAndFlush(certificate);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyCertificate);
	}
	
	/**
	 * methode qui supprime un certificat par son identifiant
	 * 
	 * @param idCertificate
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteCertificate(Integer idCertificate)
	{
		 
		try
		{
			certificateRepo.deleteById(idCertificate);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
