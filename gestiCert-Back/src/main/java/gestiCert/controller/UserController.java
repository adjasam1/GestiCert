package gestiCert.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.User;
import gestiCert.service.UserService;

/**
 * UserController route les requetes au UserService
 * 
 * @see User
 * @see UserService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin("http://localhost:4200")
public class UserController
{
	/**
	 * injection des dependances de UserService
	 * 
	 * @see UserService
	 */
	
	@Autowired
	private UserService userServ;
	
	/**
	 * constructeur
	 * 
	 * @param userServ
	 */
	
	public UserController(UserService userServ)
	{
		super();
		this.userServ = userServ;
	}

	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
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
	public ResponseEntity<?> getAllUsers()
	{
		return userServ.getAllUsers();
	}
	
	@GetMapping("/id={idUser}")
	public ResponseEntity<?> getUserById(@PathVariable Integer idUser)
	{
		return userServ.getUserById(idUser);
	}
	
	@GetMapping("/idrh={idRHUser}")
	public ResponseEntity<?> getUserByIdRH(@PathVariable String idRHUser)
	{
		return userServ.getUserByIdRH(idRHUser);
	}

//	@GetMapping("/nom={nameUser}")
//	public ResponseEntity<?> getUserByName(@PathVariable String nameUser)
//	{
//		return userServ.getUserByName(nameUser);
//	}
	
	@GetMapping("/nom={word}")
	public ResponseEntity<?> getUserByName(@PathVariable String word)
	{
		return userServ.getUserByName(word);
	}
	
	@GetMapping("/prenom={firstNameUser}")
	public ResponseEntity<?> getUserByFirstName(@PathVariable String firstNameUser)
	{
		return userServ.getUserByFirstName(firstNameUser);
	}
	
	@GetMapping("/nom={nameUser}/prenom={firstNameUser}")
	public ResponseEntity<?> getUserByNameAndFirstName(@PathVariable("nameUser") String nameUser, @PathVariable("firstNameUser") String firstNameUser)
	{
		return userServ.getUserByNameAndFirstName(nameUser, firstNameUser);
	}
	
	@PostMapping("/ajout")
	public ResponseEntity<?> postUser(@RequestBody User user)
	{
		return  userServ.createUser(user);
	}
	
	@PutMapping("/modifid={idUser}")
	public ResponseEntity<?> putUser(@RequestBody User user, @PathVariable Integer idUser)
	{
		return userServ.updateUser(user);
	}
	
	@DeleteMapping("/supprid={idUser}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer idUser)
	{
		return userServ.deleteUser(idUser);
	}

}
