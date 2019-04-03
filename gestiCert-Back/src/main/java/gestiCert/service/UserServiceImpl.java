package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gestiCert.model.User;
import gestiCert.repository.UserRepository;

/**
 * UserServiceImpl contient les methodes CRUD de l'application pour l'entite User
 * 
 * @see User
 * @see UserRepository
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Service
public class UserServiceImpl implements UserService
{
	
	/**
	 * injection des dependances de UserRepository
	 * 
	 * @see UserRepository
	 */

	private UserRepository userRepo;
	
	// private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * constructeur
	 * 
	 * @param userRepo
	 */
	
	public UserServiceImpl(UserRepository userRepo)
	{
		super();
		this.userRepo = userRepo;
	}

	/**
	 * methode qui cherche tous les utilisateurs
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public ResponseEntity<?> getAllUsers()
	{
		List<User> listUsers = null;

		try
		{
			listUsers = userRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
			
		if (listUsers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}
	
	/**
	 * methode qui cherche un utilisateur par son identifiant
	 * 
	 * @param idUser
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getUserById(Integer idUser)
	{
		Optional<User> listUsers = null;
		
		try
		{
			listUsers = userRepo.findById(idUser);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listUsers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur pour cet identifiant");
		}
		System.out.println(listUsers);
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}
	
	@Override
	public ResponseEntity<?> getUserByIdRH(String idRHUser)
	{
		List<User> listUsers = null;
		
		try
		{
			listUsers = userRepo.findByIdRHUser(idRHUser);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listUsers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}
	
//	/**
//	 * methode qui cherche un utilisateur par son nom
//	 * 
//	 * @param nameUser
//	 * @return un utilisateur ou code 500 (internal server error) ou code 404 (not found)
//	 */
//	
//	@Override
//	public ResponseEntity<?> getUserByName(String nameUser)
//	{
//		List<User> listUsers = null;
//		
//		try
//		{
//			listUsers = userRepo.findByNameUser(nameUser);
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		if (listUsers == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	
//		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
//	}

	/**
	 * methode qui cherche des utilisateurs par nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getUserByName(String word)
	{
		List<User> listUsers = null;
		
		try
		{
			listUsers = userRepo.findByNameUser(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listUsers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}
	
	/**
	 * methode qui cherche un utilisateur par son prénom
	 * 
	 * @param firstNameUser
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public ResponseEntity<?> getUserByFirstName(String firstNameUser)
	{
		List<User> listUsers = null;
		
		try
		{
			listUsers = userRepo.findByFirstNameUser(firstNameUser);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listUsers == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}
	
	/**
	 * methode qui cherche un utilisateur par son nom et prenom
	 * 
	 * @param nameUser
	 * @param firstNameUser
	 * @return utilisateur ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public ResponseEntity<?> getUserByNameAndFirstName(String nameUser, String firstNameUser)
	{
		User user = null;
		
		try
		{
			user = userRepo.findByNameUserAndFirstNameUser(nameUser, firstNameUser);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (user == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur corespondant à ce nom et à ce prénom");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
//	{
//	List<User> listUsers = null;
//	User userFind = null;
//	String firstName = "";
//	List<User> notFind = null;
//	try {
//		listUsers = userRepo.findByNameUser(nameUser);
//		if (listUsers == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		for (int i = 0; i < listUsers.size(); i++) {
//			firstName = listUsers.get(i).getFirstNameUser();
//			System.out.println("firstNameUser url : " + firstNameUser);
//			System.out.println("firstName i" + i + " : " + firstName.toLowerCase());
//			notFind = null;
//			if (firstName.toLowerCase() == (String)firstNameUser) {
//				System.out.println(firstName + " = " + firstNameUser);
//				userFind = listUsers.get(i);
//				return ResponseEntity.status(HttpStatus.OK).body(userFind);
//			} else {
//				notFind = userRepo.findByNameUser(nameUser);
//			}
//			if (firstName.toLowerCase() != firstNameUser) {
//				System.out.println(firstName + " != " + firstNameUser);
//				userFind = listUsers.get(i);
//				return ResponseEntity.status(HttpStatus.OK).body("AAA" + userFind);
//			}
//			System.out.println("notFind i" + i + " : " + notFind);
//		}
//		if (notFind != null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur dans la saisie url : le prénom ne correspond pas au nom !");
//		}
//	} catch (Exception e)
//	{
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//	}
//	return null;
//}

	/**
	 * methode qui ajoute un utilisateur
	 * 
	 * @param user
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */

	@Override
	public ResponseEntity<?> createUser(User user)
	{
		User newUser = null;
		
		String nameUser = user.getNameUser();
		if ((nameUser == null) || (nameUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de l'utilisateur doit être renseigné");
		}
		
		String firstNameUser = user.getFirstNameUser();
		if ((firstNameUser == null) || (firstNameUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
		}
		
		String eMailUser = user.geteMailUser();
		if ((eMailUser == null) || (eMailUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
		}
		
		String idRHUser = user.getIdRHUser();
		if ((idRHUser == null) || (idRHUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
		}
		
		String passwordUser = user.getPasswordUser();
		if ((passwordUser == null) || (passwordUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le mot de passe de l'utilisateur doit être renseigné");
		}
		
		try
		{
			// user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
			newUser = userRepo.saveAndFlush(user);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	/**
	 * methode qui modifie un utilisateur
	 * 
	 * @param user
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */

	@Override
	public ResponseEntity<?> updateUser(User user)
	{
		User modifyUser = null;
		
		String nameUser = user.getNameUser();
		if ((nameUser == null) || (nameUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nom de l'utilisateur doit être renseigné");
		}
		
		String firstNameUser = user.getFirstNameUser();
		if ((firstNameUser == null) || (firstNameUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
		}
		
		String eMailUser = user.geteMailUser();
		if ((eMailUser == null) || (eMailUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
		}
		
		String idRHUser = user.getIdRHUser();
		if ((idRHUser == null) || (idRHUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
		}
		
		String passwordUser = user.getPasswordUser();
		if ((passwordUser == null) || (passwordUser.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le mot de passe de l'utilisateur doit être renseigné");
		}
		
		try
		{
			// user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
			modifyUser = userRepo.save(user);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyUser);
	}

	/**
	 * methode qui supprime un utilisateur par son identifiant
	 * 
	 * @param idUser
	 */
	
	@Override
	public ResponseEntity<String> deleteUser(Integer idUser)
	{
		try
		{
			userRepo.deleteById(idUser);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
	}
	
//	@Override
//	@Transactional(readOnly = false)
//	public void deleteUser(Integer idUser) throws BusinessResourceException{
//		try {
//			userRepo.deleteById(idUser);
//		} catch (Exception exception) {
//			throw new BusinessResourceException("Delete User Error", HttpStatus.INTERNAL_SERVER_ERROR, "Erreur suppression utilisateur avec id : " + idUser);
//		}
//	}
	
}
