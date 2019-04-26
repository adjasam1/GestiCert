//package gestiCert.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
////import gestiCert.dto.UserDto;
//import gestiCert.model.User;
//
///**
// * UtilisateurRepository indique que la classe amelioree Utilisateur est un referentiel qui etend JpaRepository
// * 
// * @see User
// * 
// * @author Samuel Sabot
// * @version 0.0
// */
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Integer>
//{
//	
////	/**
////	 * utilise la methode findById du CrudRepository en utilisant le nom de l'utilisateur comme parametre
////	 * 
////	 * @param nameUser
////	 * @return une liste d'utilisateurs
////	 */
////	
////	public List<User> findByNameUser(String nameUser);
//	
//	/**
//	 * utilise la methode findById du CrudRepository en utilisant le prenom de l'utilisateur comme parametre
//	 * 
//	 * @param firstNameUser
//	 * @return une liste d'utilisateurs
//	 */
//	
//	public Iterable<User> findByFirstNameUser(String firstNameUser);
//	
//	/**
//	 * utilise la methode findById du CrudRepository en utilisant la requete sql recherchant un utilisateur avec un nom et un prenom
//	 * 
//	 * @param nameUser
//	 * @param firstNameUser
//	 * @return un utilisateur
//	 */
//	
//	@Query("FROM User user WHERE user.nameUser = ?1 AND user.firstNameUser = ?2")
//	public Iterable<User> findByNameUserAndFirstNameUser(String nameUser, String firstNameUser);
//	
//	/**
//	 * utilise la methode findById du CrudRepository en utilisant la requete sql recherchant un utilisateur avec une partie d'un nom
//	 * 
//	 * @param word
//	 * @return une liste d'utilisateur
//	 */
//	
//	@Query("FROM User user WHERE user.nameUser LIKE %?1%")
//	public Iterable<User> findByNameUser(String word);
//	
////	/**
////	 * utilise la methode delete du CrudRepository en utilisant l'identifiant de l'utilisateur comme parametre
////	 * 
////	 * @param idUser
////	 * @return supprime un utilisateur
////	 */
////	public User deleteByIdUser(Integer idUser);
//	
//	/**
//	 * utilise la methode findById du CrudRepository en utilisant l'idRH de l'utilisateur comme parametre
//	 * 
//	 * @param idRHUser
//	 * @return un utilisateur
//	 */
//	
//	@Query("FROM User user WHERE user.idRHUser LIKE %?1%")
//	public List<User> findByIdRHUser(String idRHUser);
//	
////	Optional<User> findByIdRHUser(String idRHUser);
////	boolean existsByIdRHUser(String idRHUser);
////	void deleteByIdRHUser(String idRHUser);
//
//}
