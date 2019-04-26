package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.dto.AppUserDto;
import gestiCert.exception.BusinessResourceException;
import gestiCert.exception.ExistingIdRHUserException;
import gestiCert.exception.InvalidCredentialsException;
import gestiCert.model.AppUser;

@Service
public interface AppUserService {
	
    /**
     * Method that signs a user in the application.
     * @param username the user username.
     * @param password the user password.
     * @return the JWT if credentials are valid, throws InvalidCredentialsException otherwise.
     * @throws InvalidCredentialsException
     */
    String signin(String idRHUser, String passwordUser) throws InvalidCredentialsException;

    /**
     * Method that signs up a new user in the application.
     * @param user the new user to create.
     * @return the JWT if user username is not already existing.
     * @throws ExistingIdRHUserException
     */
    String signup(AppUser user) throws ExistingIdRHUserException;

    /**
     * Method that finds all users from the application database.
     * @return the list of all application users.
     */
    List<AppUser> findAllUsers();
    
    /**
     * Method that finds a user based on its username.
     * @param username the username to look for.
     * @return an Optional object containing user if found, empty otherwise.
     */
    Optional<AppUser> findUserByIdRHUser(String idRHUser);
    
    public List<AppUser> getAllUsers() throws BusinessResourceException;
    
    public Optional<AppUser> getUserById(Integer idUser) throws BusinessResourceException;
    
    public ResponseEntity<?> getUserByIdRH(String idRHUser) throws BusinessResourceException;
    
    public List<AppUser> getUserByName(String word) throws BusinessResourceException;
    
    public List<AppUser> getUserByFirstName(String word) throws BusinessResourceException;
    
    public Iterable<AppUser> getUserByNameAndFirstName(String nameUser, String firstNameUser) throws BusinessResourceException;
    
    public AppUser createUser(AppUser user) throws BusinessResourceException;
    
    public AppUser updateUser(AppUser user)throws BusinessResourceException;

	public void deleteUser(Integer idUser) throws BusinessResourceException;


}
