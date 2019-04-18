package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import gestiCert.exception.BusinessResourceException;
import gestiCert.exception.ExistingUsernameException;
import gestiCert.exception.InvalidCredentialsException;
import gestiCert.model.User;

/**
 * 
 * 
 * @see User
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

public interface UserService
{
	
	public List<User> getAllUsers() throws BusinessResourceException;
	
	public Optional<User> getUserById(Integer idUser) throws BusinessResourceException;
	
	public ResponseEntity<?> getUserByIdRH(String idRHUser) throws BusinessResourceException;
	
//	public ResponseEntity<?> getUserByName(String nameUser);
	
	public Iterable<User> getUserByName(String word) throws BusinessResourceException;
	
	public Iterable<User> getUserByFirstName(String firstNameUser) throws BusinessResourceException;
	
	public Iterable<User> getUserByNameAndFirstName(String nameUser, String firstNameUser) throws BusinessResourceException;
	
	public User createUser(User user) throws BusinessResourceException;

	public User updateUser(User user)throws BusinessResourceException;

	public void deleteUser(Integer idUser) throws BusinessResourceException;


//	// Authentification
//	String signin(String idRHUser, String passwordUser) throws InvalidCredentialsException, InvalidCredentialsException;
//	String signup(User user) throws ExistingIdRHException, ExistingIdRHException;
//	List<User> findAllUsers();
//	Optional<User> findUserByIdRHUser(String idRHUser);

}
