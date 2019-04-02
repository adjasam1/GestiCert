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
 * Plateforme est la classe representant une plateforme d'un certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "plateforme")
public class Plateform implements Serializable
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
	@Column(name = "id_plateforme")
	private Integer idPlateform;
	
	@Column(name = "nom", unique = true)
	@Size(max = 50)
	@NotNull
	private String namePlateform;
	
	/**
	 * relation entre l'entite plateforme et l'entite certificat
	 * 
	 * @see Certificate
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "plateform")
	private List<Certificate> certificates;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Plateform()
	{
		
	}

	public Plateform(Integer idPlateform, @Size(max = 50) @NotNull String namePlateform,
			List<Certificate> certificates)
	{
		super();
		this.idPlateform = idPlateform;
		this.namePlateform = namePlateform;
		this.certificates = certificates;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdPlateform()
	{
		return idPlateform;
	}

	public void setIdPlateform(Integer idPlateform)
	{
		this.idPlateform = idPlateform;
	}

	public String getNamePlateform()
	{
		return namePlateform;
	}

	public void setNamePlateform(String namePlateform)
	{
		this.namePlateform = namePlateform;
	}

	public List<Certificate> getCertificate()
	{
		return certificates;
	}

	public void setCertificate(List<Certificate> certificates)
	{
		this.certificates = certificates;
	}

}
