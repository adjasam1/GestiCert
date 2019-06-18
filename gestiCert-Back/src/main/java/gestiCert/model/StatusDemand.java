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
 * StatutDemande est la classe representant un statut d'une demande de certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "statutdemande")
public class StatusDemand implements Serializable
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
	@Column(name = "id_statut_demande")
	private Integer idStatusDemand;
	
	@Column(name = "statut", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameStatusDemand;
	
	/**
	 * relation entre l'entite statut demande et l'entite demande
	 * 
	 * @see Demand
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "statusDemand")
	private List<Certificate> certificates;

	/**
	 * constructeurs de la classe dont un vide par defaut
	 */
	
	public StatusDemand()
	{

	}

	public StatusDemand(Integer idStatusDemand, @Size(max = 50) @NotNull String nameStatusDemand, List<Certificate> certificates)
	{
		super();
		this.idStatusDemand = idStatusDemand;
		this.nameStatusDemand = nameStatusDemand;
		this.certificates = certificates;
	}
	
	public StatusDemand(@Size(max = 50) @NotNull String nameStatusDemand) {
		super();
		this.nameStatusDemand = nameStatusDemand;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */

	public Integer getIdStatusDemand()
	{
		return idStatusDemand;
	}

	public void setIdStatusDemand(Integer idStatusDemand)
	{
		this.idStatusDemand = idStatusDemand;
	}

	public String getNameStatusDemand()
	{
		return nameStatusDemand;
	}

	public void setNameStatusDemand(String nameStatusDemand)
	{
		this.nameStatusDemand = nameStatusDemand;
	}

	public List<Certificate> certificates()
	{
		return certificates;
	}

	public void setDemands(List<Certificate> certificates)
	{
		this.certificates = certificates;
	}

}
