package gestiCert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gestiCert.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

}
