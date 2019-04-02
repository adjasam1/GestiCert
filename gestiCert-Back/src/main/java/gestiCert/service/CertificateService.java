package gestiCert.service;

import java.util.Date;

import org.springframework.http.ResponseEntity;

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

	ResponseEntity<?> createCertificate(Certificate certificate);

	ResponseEntity<?> updateCertificate(Certificate certificate, Integer idCertificate);

	ResponseEntity<String> deleteCertificate(Integer idCertificate);

}
