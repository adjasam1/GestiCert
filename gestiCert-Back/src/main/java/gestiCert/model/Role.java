package gestiCert.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Roles possibles pour les utilisateurs
 */
public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_DEV;

    @Override
    public String getAuthority() {
        return name();
    }
    
}
