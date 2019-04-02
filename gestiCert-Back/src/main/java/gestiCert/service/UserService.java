package gestiCert.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import gestiCert.exception.BusinessResourceException;
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
	
	public ResponseEntity<?> getAllUsers() throws BusinessResourceException;
	
	public ResponseEntity<?> getUserById(Integer idUser) throws BusinessResourceException;
	
	public ResponseEntity<?> getUserByIdRH(String idRHUser) throws BusinessResourceException;
	
//	public ResponseEntity<?> getUserByName(String nameUser);
	
	public ResponseEntity<?> getUserByName(String word) throws BusinessResourceException;
	
	public ResponseEntity<?> getUserByFirstName(String firstNameUser) throws BusinessResourceException;
	
	public ResponseEntity<?> getUserByNameAndFirstName(String nameUser, String firstNameUser) throws BusinessResourceException;
	
	public ResponseEntity<?> createUser(User user) throws BusinessResourceException;

	public ResponseEntity<?> updateUser(User user)throws BusinessResourceException;

	public ResponseEntity<String> deleteUser(Integer idUser) throws BusinessResourceException;

}
