package gestiCert.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Application;
import gestiCert.model.Certificate;

/**
 * 
 * 
 * @see Certificate
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface CertificateService {

	ResponseEntity<?> getAllCertificates();

	ResponseEntity<?> getCertificateById(Integer idCertificate);

	ResponseEntity<?> getCertificateByName(String word);

	ResponseEntity<?> getCertificateByDateIssue(Date number);

	Certificate createCertificate(Certificate certificate);

	ResponseEntity<?> updateCertificate(Certificate certificate, Integer idCertificate);

	ResponseEntity<String> deleteCertificate(Integer idCertificate);

	ResponseEntity<?> getCertificateByApplication(Application application);

	List<Certificate> getCertificateByUser(String idRHUser);

//	ResponseEntity<?> getCertificateByIdUser(Integer idUser);

//	ResponseEntity<?> getCertifByIdUser(Integer idUser);

//	ResponseEntity<?> getCertificateByIdUser(Integer idUser);

}
