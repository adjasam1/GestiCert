package gestiCert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AddressAlternative est la classe representant une adresse alternative d'un certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "adressealternative")
public class AddressAlternative implements Serializable
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
	@Column(name = "id_adresse_alternative")
	private Integer idAddressAlternative;
	
	@Column(name = "adresse_alternative")
	@NotNull
	private String linkAddressAlternative;
	
	/**
	 * relation entre l'entite adresse alternative et l'entite certificat
	 * 
	 * @see Certificate
	 */
	
	@JsonIgnore
	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_certificat")
	private Certificate certificate;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public AddressAlternative()
	{
		
	}

	public AddressAlternative(Integer idAddressAlternative, @NotNull String linkAddressAlternative,
			Certificate certificate)
	{
		super();
		this.idAddressAlternative = idAddressAlternative;
		this.linkAddressAlternative = linkAddressAlternative;
		this.certificate = certificate;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdAddressAlternative()
	{
		return idAddressAlternative;
	}

	public void setIdAddressAlternative(Integer idAddressAlternative)
	{
		this.idAddressAlternative = idAddressAlternative;
	}

	public String getLinkAddressAlternative()
	{
		return linkAddressAlternative;
	}

	public void setLinkAddressAlternative(String linkAddressAlternative)
	{
		this.linkAddressAlternative = linkAddressAlternative;
	}

	public Certificate getCertificate()
	{
		return certificate;
	}

	public void setCertificate(Certificate certificate)
	{
		this.certificate = certificate;
	}

}
