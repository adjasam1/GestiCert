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
 * TypeDemande est la classe representant un type d'une demande de certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "typedemande")
public class TypeDemand implements Serializable
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
	@Column(name = "id_type_demande")
	private Integer idTypeDemand;
	
	@Column(name = "type", unique = true)
	@Size(max = 50)
	@NotNull
	private String typeTypeDemand;
	
	/**
	 * relation entre l'entite type demande et l'entite demande
	 * 
	 * @see Demand
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "typeDemand")
	private List<Demand> demands;

	/**
	 * constructeurs de la classe dont un vide par defaut
	 */
	
	public TypeDemand()
	{

	}

	public TypeDemand(Integer idTypeDemand, @Size(max = 50) @NotNull String typeTypeDemand, List<Demand> demands)
	{
		super();
		this.idTypeDemand = idTypeDemand;
		this.typeTypeDemand = typeTypeDemand;
		this.demands = demands;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdTypeDemand()
	{
		return idTypeDemand;
	}

	public void setIdTypeDemand(Integer idTypeDemand)
	{
		this.idTypeDemand = idTypeDemand;
	}

	public String getTypeTypeDemand()
	{
		return typeTypeDemand;
	}

	public void setTypeTypeDemand(String typeTypeDemand)
	{
		this.typeTypeDemand = typeTypeDemand;
	}

	public List<Demand> getDemands()
	{
		return demands;
	}

	public void setDemands(List<Demand> demands)
	{
		this.demands = demands;
	}

}
