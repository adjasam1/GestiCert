package gestiCert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import gestiCert.dto.AppUserDto;
import gestiCert.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer>{
	
    Optional<AppUser> findByIdRHUser(String idRHUser);

    boolean existsByIdRHUser(String idRHUser);

    void deleteByIdRHUser(String idRHUser);
    
	@Query("FROM AppUser user WHERE user.idRHUser LIKE %?1%")
	public List<AppUser> getByIdRHUser(String idRHUser);
    
	/**
	 * utilise la methode findById du CrudRepository en utilisant la requete sql recherchant un utilisateur avec une partie d'un nom
	 * 
	 * @param word
	 * @return une liste d'utilisateur
	 */
	
	@Query("FROM AppUser user WHERE user.nameUser LIKE %?1%")
	public Optional<AppUser> findByNameUser(String word);
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant en utilisant la requete sql recherchant un utilisateur avec une partie d'un prenom
	 * 
	 * @param firstNameUser
	 * @return une liste d'utilisateurs
	 */
	
	@Query("FROM AppUser user WHERE user.firstNameUser LIKE %?1%")
	public Optional<AppUser> findByFirstNameUser(String word);
    
	/**
	 * utilise la methode findById du CrudRepository en utilisant la requete sql recherchant un utilisateur avec un nom et un prenom
	 * 
	 * @param nameUser
	 * @param firstNameUser
	 * @return un utilisateur
	 */
	
	@Query("FROM AppUser user WHERE user.nameUser = ?1 AND user.firstNameUser = ?2")
	public Optional<AppUser> findByNameUserAndFirstNameUser(String nameUser, String firstNameUser);
	
	//authentification procedure stockee
	@Query(value = "SELECT id_utilisateur FROM app_utilisateur WHERE idRH LIKE 'paaa001'", nativeQuery = true)
	Integer findUserByIdRH(String idRHUser);
	
	
}
