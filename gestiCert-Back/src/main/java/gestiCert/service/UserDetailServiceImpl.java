package gestiCert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gestiCert.dto.AppUserDto;
import gestiCert.model.AppUser;
import gestiCert.repository.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String idRHUser) throws UsernameNotFoundException {
//		final Optional<gestiCert.model.User> user = userRepo.findByIdRHUser(idRHUser);
//		
//		if (!user.isPresent()) {
//			throw new UsernameNotFoundException("User not found");
//		}
//		
//		return User
//				.withUsername(idRHUser)
//                .password(user.get().getPasswordUser())
//                .authorities(user.get().getRoleList())
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)
//                .build();
//	}
	
	@Autowired
    private AppUserRepository userRepo;
	
    @Override
    public UserDetails loadUserByUsername(String idRHUser) throws UsernameNotFoundException {
        final Optional<AppUser> user = userRepo.findByIdRHUser(idRHUser);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("AppUser '" + idRHUser + "' not found");
        }

        return User
                .withUsername(idRHUser)
                .password(user.get().getPasswordUser())
                .authorities(user.get().getRoleList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
