package gestiCert.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import gestiCert.exception.InvalidJWTException;
import gestiCert.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length:86400000}")
    private long validityInMilliseconds = 86400000; // 24h avant de devoir se reconnecter
	
	@Autowired
	private UserDetailsService userDetailsServ;
	
	@PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
	
//	// utilise les classes utilitaires pour créer le token (sub est son username, auht est son role)
//	public String createToken(String idRHUser, List<Role> roles) {
//		
//		Claims claims = Jwts.claims().setSubject(idRHUser);
//        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()//
//                .setClaims(claims)//
//                .setIssuedAt(now)//
//                .setExpiration(validity)//
//                .signWith(SignatureAlgorithm.HS256, secretKey)//
//                .compact();
//    }
	
    /**
     * Method that creates a token with username as "sub" field, user roles as "auth" field, "iat" as now date,
     * "exp" as now date + validity time.
     * @param username the user username.
     * @param roles the user roles.
     * @return the created JWT as String.
     */
    public String createToken(String idRHUser, List<Role> roles) {

        Claims claims = Jwts.claims().setSubject(idRHUser);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }
	
	public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsServ.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
	
	public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
	
	public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
	
	public boolean validateToken(String token) throws InvalidJWTException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJWTException();
        }
    }

}










