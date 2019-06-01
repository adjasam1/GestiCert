package gestiCert.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestiCert.model.Application;
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
	
//	@Query("FROM Certificate certificate INNER JOIN utilisateur_application ON certificate.id_application = utilisateur_application.id_application WHERE utilisateur_application.id_utilisateur = ?1")
//	public List<Certificate> findCertificateByIdUser(Integer idUser);
//	
//	@Query("FROM Certificate certificate INNER JOIN User user ON certificate.application.idApplication = user.application.idApplication WHERE user.idUser =?1")
//	public List<Certificate> findCertifByIdUser(Integer idUser);
//	
//	@Query(value = "from certificat inner join utilisateur_application on certificat.id_application = utilisateur_application.id_application where utilisateur_application.id_utilisateur = ?1", nativeQuery = true)
//	public List<Certificate> findCertifByIdUser(Integer idUser);
	
	
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
	
	public List<Certificate> findByApplication(Application application);

}
