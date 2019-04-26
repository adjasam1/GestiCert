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
 * Environnement est la classe representant un environnement d'un certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "environnement")
public class Environment implements Serializable
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
	@Column(name = " id_environnement")
	private Integer idEnvironment;
	
	@Column(name = "nom", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameEnvironment;
	
	/**
	 * relation entre l'entite environnement et l'entite certificat
	 * 
	 * @see Certificate
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "environment")
	private List<Certificate> certificates;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Environment()
	{
		
	}
	
	
	
	public Environment(@Size(max = 50) @NotNull String nameEnvironment) {
		super();
		this.nameEnvironment = nameEnvironment;
	}



	public Environment(Integer idEnvironment, @Size(max = 50) @NotNull String nameEnvironment) {
		super();
		this.idEnvironment = idEnvironment;
		this.nameEnvironment = nameEnvironment;
	}



	public Environment(Integer idEnvironment, @Size(max = 50) @NotNull String nameEnvironment,
			List<Certificate> certificates)
	{
		super();
		this.idEnvironment = idEnvironment;
		this.nameEnvironment = nameEnvironment;
		this.certificates = certificates;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdEnvironment()
	{
		return idEnvironment;
	}

	public void setIdEnvironment(Integer idEnvironment)
	{
		this.idEnvironment = idEnvironment;
	}

	public String getNameEnvironment()
	{
		return nameEnvironment;
	}

	public void setNameEnvironment(String nameEnvironment)
	{
		this.nameEnvironment = nameEnvironment;
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
