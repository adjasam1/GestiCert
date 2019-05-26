package gestiCert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Profil est la classe representant un profil d'utilisateur de l'application gesticert
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "profil")
public class Profile implements Serializable
{

	/**
	 * numero de version par defaut de la classe pour que les objets generes soient reconnus
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * attributs de l'entite
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profil")
	private Integer idProfile;
	
	@Column(name = "type", unique = true)
	@Size(max = 50)
	private String typeProfile;
	
	/**
	 * relation entre l'entite profil et l'entite utilisateur
	 * 
	 * @see User
	 */

	@JsonIgnore
	@OneToMany(mappedBy = "profile")
	private List<AppUser> users;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Profile()
	{
		
	}
	

	
	
	public Profile(Integer idProfile) {
		super();
		this.idProfile = idProfile;
	}




	public Profile(@Size(max = 50) @NotNull String typeProfile) {
		super();
		this.typeProfile = typeProfile;
	}




	public Profile(Integer idProfile, @Size(max = 50) @NotNull String typeProfile) {
		super();
		this.idProfile = idProfile;
		this.typeProfile = typeProfile;
	}




	public Profile(Integer idProfile, @Size(max = 50) @NotNull String typeProfile, List<AppUser> users) {
		super();
		this.idProfile = idProfile;
		this.typeProfile = typeProfile;
		this.users = users;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */



	public Integer getIdProfile() {
		return idProfile;
	}




	public void setIdProfile(Integer idProfile) {
		this.idProfile = idProfile;
	}




	public String getTypeProfile() {
		return typeProfile;
	}




	public void setTypeProfile(String typeProfile) {
		this.typeProfile = typeProfile;
	}




	public List<AppUser> getUsers() {
		return users;
	}




	public void setUsers(List<AppUser> users) {
		this.users = users;
	}




	@Override
	public String toString() {
		return " " + typeProfile;
	}



	

	

	


	
	

}
