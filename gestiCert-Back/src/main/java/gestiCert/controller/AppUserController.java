package gestiCert.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.dto.AppUserDto;
import gestiCert.dto.JsonWebToken;
import gestiCert.exception.ExistingUsernameException;
import gestiCert.exception.InvalidCredentialsException;
import gestiCert.model.AppUser;
import gestiCert.service.AppUserService;

/**
 * This controller is in charge of responding to http calls on /api/users.
 * It manages user registration, user authentication and can provide user related data to Admin users.
 */
@RestController
@RequestMapping("/api/user")
public class AppUserController {
	
    private AppUserService appUserServ;

    public AppUserController(AppUserService appUserService) {
        this.appUserServ = appUserService;
    }

    /**
     * Method to register a new user in database.
     * @param user the new user to create.
     * @return a JWT if sign up is ok, a bad response code otherwise.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<JsonWebToken> signUp(@RequestBody AppUser user) {
        try {
            return ResponseEntity.ok(new JsonWebToken(appUserServ.signup(user)));
        } catch (ExistingUsernameException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Method to sign in a user (already existing).
     * @param user the user to sign in to the app.
     * @return a JWT if sign in is ok, a bad response code otherwise.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<JsonWebToken> signIn(@RequestBody AppUser user) {
        try {
            return ResponseEntity.ok(new JsonWebToken(appUserServ.signin(user.getUsername(), user.getPassword())));
        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Method to get all users from the database.
     * This method is restricted to Admin users.
     * @return the list of all users registered in the database.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AppUserDto> getAllUsers() {
        return appUserServ.findAllUsers().stream().map(appUser -> new AppUserDto(appUser.getUsername(), appUser.getRoleList())).collect(Collectors.toList());
    }

    /**
     * Method to get one user from database based on its user name.
     * This method is restricted to Admin users.
     * @param appUserName the user name to look for.
     * @return a User object if found, a not found response code otherwise.
     */
    @GetMapping("/{appUserName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUserDto> getOneUser(@PathVariable String appUserName) {
        Optional<AppUser> appUser = appUserServ.findUserByUserName(appUserName);
        if (appUser.isPresent()) {
            return ResponseEntity.ok(new AppUserDto(appUser.get().getUsername(), appUser.get().getRoleList()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
