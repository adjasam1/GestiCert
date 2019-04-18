package gestiCert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Application est la classe representant une application
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "application")
public class Application implements Serializable
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
	@Column(name = "id_application")
	private Integer idApplication;
	
	@Column(name = "ccx", unique = true)
	@Size(min = 3, max = 3)
	@NotNull
	private String codeCCX;
	
	@Column(name = "nom")
	@Size(max = 50)
	@NotNull
	private String nameApplication;
	
	@Column(name = "nom_client")
	@Size(max = 50)
	private String nameClient;
	
	@Column(name = "prenom_client")
	@Size(max = 50)
	private String firstNameClient;
	
	@Column(name = "direction_client")
	@Size(max = 50)
	private String managementClient;
	
	@Column(name = "telephone_client")
	@Size(min = 10, max = 10)
	private String phoneClient;
	
	@Column(name = "email_client")
	private String eMailClient;
	
	/**
	 * relation entre l'entite application et les entites utilisateur et certificat
	 * 
	 * @see User
	 * @see Certificate
	 */

	//@JsonIgnore
	@ManyToMany(mappedBy = "applications")
	private List<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "application", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private List<Certificate> certificates;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Application() {
		
	}

	public Application(Integer idApplication, @Size(min = 3, max = 3) @NotNull String codeCCX,
			@Size(max = 50) @NotNull String nameApplication, @Size(max = 50) String nameClient,
			@Size(max = 50) String firstNameClient, @Size(max = 50) String managementClient,
			@Size(min = 10, max = 10) String phoneClient, String eMailClient, List<User> users,
			List<Certificate> certificates)
	{
		super();
		this.idApplication = idApplication;
		this.codeCCX = codeCCX;
		this.nameApplication = nameApplication;
		this.nameClient = nameClient;
		this.firstNameClient = firstNameClient;
		this.managementClient = managementClient;
		this.phoneClient = phoneClient;
		this.eMailClient = eMailClient;
		this.users = users;
		this.certificates = certificates;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdApplication()
	{
		return idApplication;
	}

	public void setIdApplication(Integer idApplication)
	{
		this.idApplication = idApplication;
	}

	public String getCodeCCX()
	{
		return codeCCX;
	}

	public void setCodeCCX(String codeCCX)
	{
		this.codeCCX = codeCCX;
	}

	public String getNameApplication()
	{
		return nameApplication;
	}

	public void setNameApplication(String nameApplication)
	{
		this.nameApplication = nameApplication;
	}

	public String getNameClient()
	{
		return nameClient;
	}

	public void setNameClient(String nameClient)
	{
		this.nameClient = nameClient;
	}

	public String getFirstNameClient()
	{
		return firstNameClient;
	}

	public void setFirstNameClient(String firstNameClient)
	{
		this.firstNameClient = firstNameClient;
	}

	public String getManagementClient()
	{
		return managementClient;
	}

	public void setManagementClient(String managementClient)
	{
		this.managementClient = managementClient;
	}

	public String getPhoneClient()
	{
		return phoneClient;
	}

	public void setPhoneClient(String phoneClient)
	{
		this.phoneClient = phoneClient;
	}

	public String geteMailClient()
	{
		return eMailClient;
	}

	public void seteMailClient(String eMailClient)
	{
		this.eMailClient = eMailClient;
	}

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}

	public List<Certificate> getCertificates()
	{
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates)
	{
		this.certificates = certificates;
	}

}
