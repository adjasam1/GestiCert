package gestiCert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Serveur est la classe representant un serveur d'un certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "serveur")
public class Server implements Serializable
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
	@Column(name = "id_serveur")
	private Integer idServer;
	
	@Column(name = "nom", unique = true)
	@Size(max = 50)
	@NotNull
	private String nameServer;
	
	/**
	 * relation entre l'entite serveur et l'entite certificat
	 * 
	 * @see Certificate
	 */
	
	@JsonIgnore
	@ManyToMany(mappedBy = "servers")
	private List<Certificate> certificates;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Server()
	{
		
	}
	
	public Server(Integer idServer, @Size(max = 50) @NotNull String nameServer, List<Certificate> certificates)
	{
		super();
		this.idServer = idServer;
		this.nameServer = nameServer;
		this.certificates = certificates;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdServer()
	{
		return idServer;
	}

	public void setIdServer(Integer idServer)
	{
		this.idServer = idServer;
	}

	public String getNameServer()
	{
		return nameServer;
	}

	public void setNameServer(String nameServer)
	{
		this.nameServer = nameServer;
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
