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
 * Service est la classe representant un service auquel appartiennent des utilisateurs
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "service")
public class Department implements Serializable
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
	@Column(name = "id_service")
	private Integer idDepartment;
	
	@Column(name = "nom", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameDepartment;
	
	/**
	 * relation entre l'entite service et l'entite utilisateur
	 * 
	 * @see User
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "department")
	private List<AppUser> users;

	/**
	 * constructeurs de la classe dont un vide par defaut
	 */
	
	public Department()
	{
		
	}
	
	public Department(@Size(max = 50) @NotNull String nameDepartment) {
		super();
		this.nameDepartment = nameDepartment;
	}

	public Department(Integer idDepartment, @Size(max = 50) @NotNull String nameDepartment) {
		super();
		this.idDepartment = idDepartment;
		this.nameDepartment = nameDepartment;
	}

	public Department(Integer idDepartment, @Size(max = 50) @NotNull String nameDepartment, List<AppUser> users)
	{
		super();
		this.idDepartment = idDepartment;
		this.nameDepartment = nameDepartment;
		this.users = users;
	}

	public Integer getIdDepartment() {
		return idDepartment;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public void setIdDepartment(Integer idDepartment)
	{
		this.idDepartment = idDepartment;
	}

	public String getNameDepartment()
	{
		return nameDepartment;
	}

	public void setNameDepartment(String nameDepartment)
	{
		this.nameDepartment = nameDepartment;
	}

	public List<AppUser> getUsers()
	{
		return users;
	}

	public void setUsers(List<AppUser> users)
	{
		this.users = users;
	}
	
	

}
