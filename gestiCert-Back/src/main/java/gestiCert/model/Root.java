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
 * Racine est la classe representant une racine fournissant un certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "racine")
public class Root implements Serializable
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
	@Column(name = "id_racine")
	private Integer idRoot;
	
	@Column(name = "nom", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameRoot;
	
	/**
	 * relation entre l'entite racine et l'entite certificat
	 * 
	 * @see Certificate
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "root")
	private List<Certificate> certificates;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Root() {
		
	}

	public Root(Integer idRoot, @Size(max = 50) @NotNull String nameRoot, List<Certificate> certificates)
	{
		super();
		this.idRoot = idRoot;
		this.nameRoot = nameRoot;
		this.certificates = certificates;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdRoot()
	{
		return idRoot;
	}

	public void setIdRoot(Integer idRoot)
	{
		this.idRoot = idRoot;
	}

	public String getNameRoot()
	{
		return nameRoot;
	}

	public void setNameRoot(String nameRoot)
	{
		this.nameRoot = nameRoot;
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
