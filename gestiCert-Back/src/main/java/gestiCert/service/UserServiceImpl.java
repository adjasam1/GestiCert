package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gestiCert.exception.BusinessResourceException;
import gestiCert.exception.ExistingUsernameException;
import gestiCert.exception.InvalidCredentialsException;
import gestiCert.model.User;
import gestiCert.repository.UserRepository;
import gestiCert.security.JwtTokenProvider;

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
//	// authentification
//	private BCryptPasswordEncoder passwordEncoder;
//	private JwtTokenProvider jwtTokenProvider;
//	private AuthenticationManager authenticationManager;
//	public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder,
//			JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
//		super();
//		this.userRepo = userRepo;
//		this.passwordEncoder = passwordEncoder;
//		this.jwtTokenProvider = jwtTokenProvider;
//		this.authenticationManager = authenticationManager;
//	}
	
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
	public List<User> getAllUsers()
	{
		return userRepo.findAll();
	}
	
	/**
	 * methode qui cherche un utilisateur par son identifiant
	 * 
	 * @param idUser
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public Optional<User> getUserById(Integer idUser)
	{
		return userRepo.findById(idUser);
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
	public Iterable<User> getUserByName(String word)
	{
		return userRepo.findByNameUser(word);
	}
	
	/**
	 * methode qui cherche un utilisateur par son prénom
	 * 
	 * @param firstNameUser
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public Iterable<User> getUserByFirstName(String firstNameUser)
	{
		return userRepo.findByFirstNameUser(firstNameUser);
	}
	
	/**
	 * methode qui cherche un utilisateur par son nom et prenom
	 * 
	 * @param nameUser
	 * @param firstNameUser
	 * @return utilisateur ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public Iterable<User> getUserByNameAndFirstName(String nameUser, String firstNameUser)
	{
		return userRepo.findByNameUserAndFirstNameUser(nameUser, firstNameUser);
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
	public User createUser(User user)
	{
		return  userRepo.saveAndFlush(user);
	}
	
	/**
	 * methode qui modifie un utilisateur
	 * 
	 * @param user
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */

	@Override
	public User updateUser(User user)
	{
		return userRepo.save(user);
	}

	/**
	 * methode qui supprime un utilisateur par son identifiant
	 * 
	 * @param idUser
	 */
	
	@Override
	public void deleteUser(Integer idUser)
	{
		userRepo.deleteById(idUser);
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
	
//	// authentification
//	// permet de verifier coherence entre utilisateur et mot de passe
//	@Override
//	public String signin(String idRHUser, String passwordUser) throws InvalidCredentialsException {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(idRHUser, passwordUser));
//			return jwtTokenProvider.createToken(idRHUser, userRepo.findByIdRHUser(idRHUser).get().getRoleList());
//		} catch (AuthenticationException e) {
//			throw new InvalidCredentialsException();
//		}	
//	}
//	@Override
//	public String signup(User user) throws ExistingIdRHException {
//		if (!userRepo.existsByIdRHUser(user.getIdRHUser())) {
//			User userToSave = new User(user.getIdRHUser(), passwordEncoder.encode(user.getPasswordUser()), user.getRoleList());
//			userRepo.save(userToSave);
//			return jwtTokenProvider.createToken(user.getIdRHUser(), user.getRoleList());
//		} else {
//			throw new ExistingIdRHException();
//		}
//	}
//	@Override
//	public List<User> findAllUsers() {
//		return userRepo.findAll();
//	}
//	@Override
//	public Optional<User> findUserByIdRHUser(String idRHUser) {
//		return userRepo.findByIdRHUser(idRHUser);
//	}

//	@Override
//	public ResponseEntity<?> getUserByIdRHUser(String idRHUser) throws BusinessResourceException {
//		return ResponseEntity.status(HttpStatus.OK).body(userRepo.findByIdRHUser(idRHUser));
//		// TODO
//	}

	
	
}






















































