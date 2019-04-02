package gestiCert.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.Certificate;

/**
 * CertificatRepository indique que la classe amelioree Certificat est un referentiel qui etend JpaRepository
 * 
 * @see Certificate
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom du certificat comme parametre
	 * 
	 * @param nameCertificate
	 * @return une liste de certificats
	 */
	
	@Query("FROM Certificate certificate WHERE certificate.nameCertificate LIKE %?1%")
	public List<Certificate> findByNameCertificate(String nameCertificate);
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant la date d'emission du certificat comme parametre
	 * 
	 * @param dateIssue
	 * @return une liste de certificats
	 */
	
	@Query("FROM Certificate certificate WHERE certificate.dateIssue LIKE %?1%")
	public List<Certificate> findByDateIssueCertificate(Date dateIssue);

}
