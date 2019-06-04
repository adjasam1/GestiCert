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
	
//	@Query(value = "FROM AppUser user "
//			+ "JOIN utilisateur_application ua ON user.idUser = ua.idUser "
//			+ "JOIN Application application ON application.idApplication = ua.idApplication "
//			+ "JOIN Certificate certificate ON certificate.idApplication = application.idApplication "
//			+ "WHERE user.idRHUser = ?1", nativeQuery = true)
//	public List<Certificate> findByIdRHUser(String idRHUser);
	
//	@Query(value = "FROM app_utilisateur u "
//			+ "JOIN utilisateur_application ua ON u.id_utilisateur = ua.id_utilisateur "
//			+ "JOIN application a ON a.id_application = ua.id_application "
//			+ "JOIN certificate c ON c.id_application = a.id_application "
//			+ "WHERE u.idrh = ?1", nativeQuery = true)
//	public List<Certificate> findByIdRHUser(String idRHUser);
	
	@Query(value = "SELECT * FROM certificat c " + 
			"JOIN application a ON c.id_application = a.id_application " + 
			"JOIN utilisateur_application ua ON a.id_application = ua.id_application " + 
			"JOIN app_utilisateur u ON u.id_utilisateur = ua.id_utilisateur " + 
			"WHERE u.idrh = ?", nativeQuery = true)
	public List<Certificate> findByIdRHUser(String idRHUser);

}
