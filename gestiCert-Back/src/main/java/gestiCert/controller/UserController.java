//package gestiCert.controller;
//
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import gestiCert.dto.JsonWebToken;
//import gestiCert.exception.ExistingIdRHUserException;
//import gestiCert.exception.InvalidCredentialsException;
//import gestiCert.model.User;
//import gestiCert.service.UserService;
//
///**
// * UserController route les requetes au UserService
// * 
// * @see User
// * @see UserService
// * 
// * @author Samuel Sabot
// * @version 0.0
// */
//
//@RestController
//@RequestMapping("/api/utilisateur")
////@CrossOrigin("http://localhost:4200")
//public class UserController
//{
//	/**
//	 * injection des dependances de UserService
//	 * 
//	 * @see UserService
//	 */
//	
//	@Autowired
//	private UserService userServ;
//	
//	/**
//	 * constructeur
//	 * 
//	 * @param userServ
//	 */
//	public UserController() {
//
//	}
//	
//	public UserController(UserService userServ)
//	{
//		super();
//		this.userServ = userServ;
//	}
//
////	// authentification
////	@PostMapping("/sign-up")
////	public ResponseEntity<JsonWebToken> signUp(@RequestBody User user) {
////		try {
////			return ResponseEntity.ok(new JsonWebToken(userServ.signup(user)));
////		} catch (ExistingUsernameException e) {
////			return ResponseEntity.badRequest().build();
////		}
////	}
////	@PostMapping("/sign-in")
////	public ResponseEntity<JsonWebToken> signIn(@RequestBody User user) {
////		try {
////			return ResponseEntity.ok(new JsonWebToken(userServ.signin(user.getIdRHUser(), user.getPasswordUser())));
////		} catch (InvalidCredentialsException e) {
////			return ResponseEntity.badRequest().build();
////		}
////	}
//
//	/**
//	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
//	 * 
//	 * @param idUser
//	 * @param nameUser
//	 * @param word
//	 * @param firstNameUser
//	 * @param user
//	 * @return 
//	 * 
//	 * @return
//	 */
//	
//	@GetMapping()
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> getAllUsers()
//	{
//		List<User> listUsers = null;
//		System.out.println("1 : "  + listUsers);
//
//		try
//		{
//			listUsers = userServ.getAllUsers();
//			System.out.println("2 : "  + listUsers);
//		} catch (Exception e)
//		{
//			System.out.println("3 : "  + listUsers);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
////			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		}
//			
////		if (listUsers == null)
////		{
////			System.out.println("4 : "  + listUsers);
////			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
////		}
//		System.out.println("5 : "  + listUsers);	
//		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
//	}
//	
//	@GetMapping("/idrh={idRHUser}")
//	public ResponseEntity<?> getUserByIdRH(@PathVariable String idRHUser)
//	{
//		return userServ.getUserByIdRH(idRHUser);
//	}
//	
////	// Authentification
////	@GetMapping("/auth")
//////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
////	public List<User> getAllUsersWithAuth() {
////		return userServ.findAllUsers().stream().map(user -> new User(user.getIdRHUser(), user.getRoleList())).collect(Collectors.toList());
////	}
////	@GetMapping("/idrh={idRHUser}")
//////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
////	public ResponseEntity<User> getUserByIdRH(@PathVariable String idRHUser) {
////		Optional<User> user = userServ.findUserByIdRHUser(idRHUser);
////		if (user.isPresent()) {
////			return ResponseEntity.ok(new User(user.get().getIdRHUser(), user.get().getRoleList()));
////		} else {
////			return ResponseEntity.notFound().build();
////		}
////	}
//
//	@GetMapping("/id={idUser}")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> getUserById(@PathVariable Integer idUser)
//	{
//		Optional<User> listUsers = null;
//		
//		try
//		{
//			listUsers = userServ.getUserById(idUser);
//			// = ResponseEntity.ok(userServ.getUserById(idUser));
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		if (listUsers == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur pour cet identifiant");
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
//		
//	}
//
////	@GetMapping("/nom={nameUser}")
////	public ResponseEntity<?> getUserByName(@PathVariable String nameUser)
////	{
////		return userServ.getUserByName(nameUser);
////	}
//	
//	@GetMapping("/nom={word}")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> getUserByName(@PathVariable String word)
//	{
//		Iterable<User> listUsers = null;
//		
//		try
//		{
//			listUsers = userServ.getUserByName(word);
//			//return ResponseEntity.ok(userServ.getUserByName(word));
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		if (listUsers == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
//	}
//	
//	@GetMapping("/prenom={firstNameUser}")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> getUserByFirstName(@PathVariable String firstNameUser)
//	{
//		Iterable<User> listUsers = null;
//		
//		try
//		{
//			listUsers = userServ.getUserByFirstName(firstNameUser);
//			//return ResponseEntity.ok(userServ.getUserByFirstName(firstNameUser));
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
//	
//	@GetMapping("/nom={nameUser}/prenom={firstNameUser}")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> getUserByNameAndFirstName(@PathVariable("nameUser") String nameUser, @PathVariable("firstNameUser") String firstNameUser)
//	{
//		Iterable<User> user = null;
//		
//		try
//		{
//			user = userServ.getUserByNameAndFirstName(nameUser, firstNameUser);
//			//return ResponseEntity.ok(userServ.getUserByNameAndFirstName(nameUser, firstNameUser));
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		if (user == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur corespondant à ce nom et à ce prénom");
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(user);
//	}
//	
//	@PostMapping("/ajout")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> postUser(@RequestBody User user)
//	{
//		User newUser = null;
//		
//		String nameUser = user.getNameUser();
//		if ((nameUser == null) || (nameUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de l'utilisateur doit être renseigné");
//		}
//		
//		String firstNameUser = user.getFirstNameUser();
//		if ((firstNameUser == null) || (firstNameUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
//		}
//		
//		String eMailUser = user.geteMailUser();
//		if ((eMailUser == null) || (eMailUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
//		}
//		
//		String idRHUser = user.getIdRHUser();
//		if ((idRHUser == null) || (idRHUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
//		}
//		
//		String passwordUser = user.getPasswordUser();
//		if ((passwordUser == null) || (passwordUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le mot de passe de l'utilisateur doit être renseigné");
//		}
//		
//		try
//		{
//			// user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
//			newUser = userServ.createUser(user);
//			//return ResponseEntity.ok(userServ.createUser(user));
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//	}
//	
//	@PutMapping("/modifid={idUser}")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> putUser(@RequestBody User user, @PathVariable Integer idUser)
//	{
//		User modifyUser = null;
//		
//		String nameUser = user.getNameUser();
//		if ((nameUser == null) || (nameUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nom de l'utilisateur doit être renseigné");
//		}
//		
//		String firstNameUser = user.getFirstNameUser();
//		if ((firstNameUser == null) || (firstNameUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
//		}
//		
//		String eMailUser = user.geteMailUser();
//		if ((eMailUser == null) || (eMailUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
//		}
//		
//		String idRHUser = user.getIdRHUser();
//		if ((idRHUser == null) || (idRHUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
//		}
//		
//		String passwordUser = user.getPasswordUser();
//		if ((passwordUser == null) || (passwordUser.isEmpty()))
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le mot de passe de l'utilisateur doit être renseigné");
//		}
//		
//		try
//		{
//			// user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
//			modifyUser = userServ.updateUser(user);
//			//return ResponseEntity.ok(userServ.updateUser(user));
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(modifyUser);
//	}
//	
//	@DeleteMapping("/supprid={idUser}")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
//	public ResponseEntity<?> deleteUser(@PathVariable Integer idUser)
//	{
//		try
//		{
//			userServ.deleteUser(idUser);
//			//return ResponseEntity.ok(userServ.deleteUser(idUser));
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
//	}
//
//}
