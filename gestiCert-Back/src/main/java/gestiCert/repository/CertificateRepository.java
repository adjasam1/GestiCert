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
	
	/**
	 * Requete SQL pour trouver les certificats d'un utilisateur
	 * en utilisant son idRH comme parametre
	 * 
	 * @param idRHUser
	 * @return liste des certificats d'un utilisateur
	 */
	@Query(value = "SELECT * FROM certificat c " + 
			"JOIN application a ON c.id_application = a.id_application " + 
			"JOIN utilisateur_application ua ON a.id_application = ua.id_application " + 
			"JOIN app_utilisateur u ON u.id_utilisateur = ua.id_utilisateur " + 
			"WHERE u.idrh = ?", nativeQuery = true)
	public List<Certificate> findByIdRHUser(String idRHUser);
}
























