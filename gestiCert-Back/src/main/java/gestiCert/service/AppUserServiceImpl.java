package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import gestiCert.dto.AppUserDto;
import gestiCert.exception.ExistingIdRHUserException;
import gestiCert.exception.InvalidCredentialsException;
import gestiCert.model.AppUser;
import gestiCert.repository.AppUserRepository;
import gestiCert.security.JwtTokenProvider;

@Service
public class AppUserServiceImpl implements AppUserService {
	
    private AppUserRepository appUserRepo;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    public AppUserServiceImpl(AppUserRepository appUserRepo, BCryptPasswordEncoder passwordEncoder,
                              JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String signin(String idRHUser, String passwordUser) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(idRHUser, passwordUser));
            return jwtTokenProvider.createToken(idRHUser, appUserRepo.findByIdRHUser(idRHUser).get().getRoleList());
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public String signup(AppUser user) throws ExistingIdRHUserException {
        if (!appUserRepo.existsByIdRHUser(user.getIdRHUser())) {
            AppUser userToSave = new AppUser(user.getIdRHUser(), passwordEncoder.encode(user.getPasswordUser()), user.getNameUser(), user.getFirstNameUser(), user.geteMailUser(), user.getPhoneUser(), user.getDepartment(), user.getProfile(), user.getRoleList());
            appUserRepo.save(userToSave);
            return jwtTokenProvider.createToken(user.getIdRHUser(), user.getRoleList());
        } else {
            throw new ExistingIdRHUserException();
        }
    } 

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepo.findAll();
    }
    
    @Override
    public Optional<AppUser> findUserByIdRHUser(String idRHUser) {
        return appUserRepo.findByIdRHUser(idRHUser);
    }
    
	/**
	 * methode qui cherche tous les utilisateurs
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public List<AppUser> getAllUsers()
	{
		return appUserRepo.findAll();
	}
    
	/**
	 * methode qui cherche un utilisateur par son identifiant
	 * 
	 * @param idUser
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public Optional<AppUser> getUserById(Integer idUser)
	{
		return appUserRepo.findById(idUser);
	} 
    
	@Override
	public ResponseEntity<?> getUserByIdRH(String idRHUser)
	{
		List<AppUser> listUsers = null;
		
		try
		{
			listUsers = appUserRepo.getByIdRHUser(idRHUser);
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
	 * methode qui cherche des utilisateurs par nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public Optional<AppUser> getUserByName(String word)
	{
		return appUserRepo.findByNameUser(word);
	}
	
	/**
	 * methode qui cherche un utilisateur par son prénom
	 * 
	 * @param firstNameUser
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public Optional<AppUser> getUserByFirstName(String word)
	{
		return appUserRepo.findByFirstNameUser(word);
	}
    
	/**
	 * methode qui cherche un utilisateur par son nom et prenom
	 * 
	 * @param nameUser
	 * @param firstNameUser
	 * @return utilisateur ou code 500 (internal server error) ou code 404 (not found)
	 */

	@Override
	public Optional<AppUser> getUserByNameAndFirstName(String nameUser, String firstNameUser)
	{
		return appUserRepo.findByNameUserAndFirstNameUser(nameUser, firstNameUser);
	}
	
	/**
	 * methode qui ajoute un utilisateur
	 * 
	 * @param user
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */

	@Override
	public AppUser createUser(AppUser user)
	{
		return appUserRepo.saveAndFlush(user);
	}
    
	/**
	 * methode qui modifie un utilisateur
	 * 
	 * @param user
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */

	@Override
	public AppUser updateUser(AppUser user)
	{
		return appUserRepo.save(user);
	}
    
	/**
	 * methode qui supprime un utilisateur par son identifiant
	 * 
	 * @param idUser
	 */
	
	@Override
	public void deleteUser(Integer idUser)
	{
		appUserRepo.deleteById(idUser);
	}

	@Override
	public int findUserByIdRH(String idRHUser)
	{
		return appUserRepo.findUserByIdRH(idRHUser);
	}


}
