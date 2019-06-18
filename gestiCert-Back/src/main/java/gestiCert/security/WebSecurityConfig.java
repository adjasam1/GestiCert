package gestiCert.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Coeur de l'authentification
 * 
 * @author Utilisateur
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// utiliser dans UserDetailServiceImpl signin
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Methode configure HTTP security.
	 * 
	 * @param http the HttpSecurity object to configure.
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors();

		// Disable CSRF (cross site request forgery as our token will be stored in
		// crsf (nom de l'attaque) disable permet de bloquer cette attaque qui pourraient prendre des infos sensibles présent dans le coockie
		// session storage)
		http.csrf().disable();

		// No session will be created or used by spring security
		// comme le token est renvoyé à chaque connexion et requetes, stateless permet de ne pas garder la session en memoire
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		http.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/api/user/sign-in").permitAll()
				.antMatchers("/api/user/sign-up").permitAll()
				.anyRequest().authenticated();

		// Apply JWT
		http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * Method that configures web security. Useful here for development purposes to
	 * allow h2 console access.
	 * 
	 * @param web the WebSecurity object to configure.
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");// .anyRequest();
	}

	/**
	 * Generic configuration for CORS. Useful here for development purposes as front
	 * is developed with Angular.
	 * 
	 * @return the CorsConfigurationSource object.
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));

		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));

		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

}
