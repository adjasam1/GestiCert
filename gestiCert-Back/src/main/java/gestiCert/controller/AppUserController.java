package gestiCert.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import gestiCert.dto.AppUserDto;
import gestiCert.dto.JsonWebToken;
import gestiCert.exception.ExistingIdRHUserException;
import gestiCert.exception.InvalidCredentialsException;
import gestiCert.model.AppUser;
import gestiCert.service.AppUserService;

/**
 * This controller is in charge of responding to http calls on /api/users. It
 * manages user registration, user authentication and can provide user related
 * data to Admin users.
 */
@RestController
@RequestMapping("/api/utilisateur")
public class AppUserController {

	private AppUserService appUserServ;
	
	@Autowired
	private EntityManager entityManager;

	public AppUserController(AppUserService appUserService) {
		this.appUserServ = appUserService;
	}
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();;

	/**
	 * Method to register a new user in database.
	 * 
	 * @param user the new user to create.
	 * @return a JWT if sign up is ok, a bad response code otherwise.
	 */
//	@PostMapping("/sign-up")
//	public ResponseEntity<JsonWebToken> signUp(@RequestBody AppUser user) {
//		try {
//			return ResponseEntity.ok(new JsonWebToken(appUserServ.signup(user)));
//		} catch (ExistingIdRHUserException ex) {
//			return ResponseEntity.badRequest().build();
//		}
//	}

	/**
	 * Method to sign in a user (already existing).
	 * 
	 * @param user the user to sign in to the app.
	 * @return a JWT if sign in is ok, a bad response code otherwise.
	 */
//	@PostMapping("/sign-in")
//	public ResponseEntity<JsonWebToken> signIn(@RequestBody AppUser user) {
//		try {
//			JsonWebToken token = new JsonWebToken(appUserServ.signin(user.getIdRHUser(), user.getPasswordUser()));
//			System.out.println("aaaaa : " + new JsonWebToken(appUserServ.signin(user.getIdRHUser(), user.getPasswordUser())));
//			return ResponseEntity.ok(token);
//		} catch (InvalidCredentialsException ex) {
//			System.out.println("ex : " + ex);
//			return ResponseEntity.badRequest().build();
//		} catch (Exception e) {
//			System.out.println("e : " + e);
//			return ResponseEntity.badRequest().build();
//		}
//	}

	/**
	 * Method to get all users from the database. This method is restricted to Admin
	 * users.
	 * 
	 * @return the list of all users registered in the database.
	 */
//	@GetMapping("/role")
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//	public List<AppUserDto> getAllUsersAuth() {
//		return appUserServ.findAllUsers().stream()
//				.map(appUser -> new AppUserDto(appUser.getIdRHUser(), appUser.getRoleList()))
//				.collect(Collectors.toList());
//	}

//    @GetMapping
////  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  public List<AppUser> getAllUsers() {
//      return appUserServ.findAllUsers().stream().map(appUser -> new AppUser(appUser.getIdUser(), appUser.getIdRHUser(), appUser.getRoleList())).collect(Collectors.toList());
//  }

	/**
	 * Method to get one user from database based on its user name. This method is
	 * restricted to Admin users.
	 * 
	 * @param appUserName the user name to look for.
	 * @return a User object if found, a not found response code otherwise.
	 */
//	@GetMapping("/{idRHUser}")
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ResponseEntity<AppUserDto> getOneUserByIdRH(@PathVariable String idRHUser) {
//		Optional<AppUser> appUser = appUserServ.findUserByIdRHUser(idRHUser);
//		if (appUser.isPresent()) {
//			return ResponseEntity.ok(new AppUserDto(appUser.get().getIdRHUser(), appUser.get().getRoleList()));
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
	// auth proc stockee
	@GetMapping("/getIdUser/{a}")
	public Integer getIdUser(@PathVariable String a) {
		System.out.println("aaaaa" + a);
		return appUserServ.findUserByIdRH(a);
	}
	
	@GetMapping("/role/{idUser}")
	public List<String> getRoleUser(@PathVariable Integer idUser) {
		System.out.println("essai");
		ArrayList<String> roles = new ArrayList<String>();
		// BUG ICI
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("proc_role");
		System.out.println(entityManager);
        storedProcedure.registerStoredProcedureParameter(1, Integer.class , ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(2, String.class , ParameterMode.OUT);
        
        storedProcedure.setParameter(1, idUser);
        System.out.println("idUser : " + idUser);
        storedProcedure.execute();
        String roleUser = (String) storedProcedure.getOutputParameterValue(2);
		roles.add(roleUser);
		return roles;
	}
	
	

	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT,
	 * DELETE) avec url
	 * 
	 * @param idUser
	 * @param nameUser
	 * @param word
	 * @param firstNameUser
	 * @param user
	 * @return
	 * 
	 * @return
	 */

	@GetMapping()
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getAllUsers() {
		List<AppUser> listUsers = null;

		try {
			listUsers = appUserServ.getAllUsers();
		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		if (listUsers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}

	@GetMapping("/id={idUser}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserById(@PathVariable Integer idUser) {
		Optional<AppUser> listUsers = null;

		try {
			listUsers = appUserServ.getUserById(idUser);
//			 = ResponseEntity.ok(userServ.getUserById(idUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listUsers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'utilisateur pour cet identifiant");
		}
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);

	}

	@GetMapping("/idrh={idRHUser}")
	public ResponseEntity<?> getUserByIdRH(@PathVariable String idRHUser) {
		return appUserServ.getUserByIdRH(idRHUser);
	}

	@GetMapping("/nom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserByName(@PathVariable String word) {
		Optional<AppUser> listUsers = null;

		try {
			listUsers = appUserServ.getUserByName(word);
			// return ResponseEntity.ok(userServ.getUserByName(word));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listUsers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}

	@GetMapping("/prenom={word}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserByFirstName(@PathVariable String word) {
		Optional<AppUser> listUsers = null;

		try {
			listUsers = appUserServ.getUserByFirstName(word);
			// return ResponseEntity.ok(userServ.getUserByFirstName(firstNameUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (listUsers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(listUsers);
	}

	@GetMapping("/nom={nameUser}/prenom={firstNameUser}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getUserByNameAndFirstName(@PathVariable("nameUser") String nameUser,
			@PathVariable("firstNameUser") String firstNameUser) {
		Optional<AppUser> user = null;

		try {
			user = appUserServ.getUserByNameAndFirstName(nameUser, firstNameUser);
			// return ResponseEntity.ok(userServ.getUserByNameAndFirstName(nameUser,
			// firstNameUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pas d'utilisateur corespondant à ce nom et à ce prénom");
		}

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping("/ajout")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> postUser(@RequestBody AppUser user) {
		AppUser newUser = null;

		String nameUser = user.getNameUser();
		if ((nameUser == null) || (nameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de l'utilisateur doit être renseigné");
		}

		String firstNameUser = user.getFirstNameUser();
		if ((firstNameUser == null) || (firstNameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
		}

		String eMailUser = user.geteMailUser();
		if ((eMailUser == null) || (eMailUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
		}

		String idRHUser = user.getIdRHUser();
		if ((idRHUser == null) || (idRHUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
		}

		String passwordUser = user.getPasswordUser();
		if ((passwordUser == null) || (passwordUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le mot de passe de l'utilisateur doit être renseigné");
		}

		try {
			// appUserServ.signup(user);
			newUser = null;
			 System.out.println(user.getPasswordUser());
			 user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
			// System.out.println(bCryptPasswordEncoder.encode(user.getPasswordUser()));
			 
			 System.out.println(user.getPasswordUser());
			newUser = appUserServ.createUser(user);
			// return ResponseEntity.ok(userServ.createUser(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PutMapping("/modifid={idUser}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> putUser(@RequestBody AppUser user, @PathVariable Integer idUser) {
		AppUser modifyUser = null;

		String nameUser = user.getNameUser();
		if ((nameUser == null) || (nameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nom de l'utilisateur doit être renseigné");
		}

		String firstNameUser = user.getFirstNameUser();
		if ((firstNameUser == null) || (firstNameUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le prénom de l'utilisateur doit être renseigné");
		}

		String eMailUser = user.geteMailUser();
		if ((eMailUser == null) || (eMailUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'eMail de l'utilisateur doit être renseigné");
		}

		String idRHUser = user.getIdRHUser();
		if ((idRHUser == null) || (idRHUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'idRH de l'utilisateur doit être renseigné");
		}

		String passwordUser = user.getPasswordUser();
		if ((passwordUser == null) || (passwordUser.isEmpty())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le mot de passe de l'utilisateur doit être renseigné");
		}

		try {
			// user.setPasswordUser(bCryptPasswordEncoder.encode(user.getPasswordUser()));
			modifyUser = appUserServ.updateUser(user);
			// return ResponseEntity.ok(userServ.updateUser(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(modifyUser);
	}

	@DeleteMapping("/supprid={idUser}")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SERVICE') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> deleteUser(@PathVariable Integer idUser) {
		try {
			appUserServ.deleteUser(idUser);
			// return ResponseEntity.ok(userServ.deleteUser(idUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
	}


}
