package gestiCert.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * User possible roles.
 */
public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_DEV;

    @Override
    public String getAuthority() {
        return name();
    }
    
}
