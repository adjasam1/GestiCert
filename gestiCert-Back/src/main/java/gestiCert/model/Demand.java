package gestiCert.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

/**
 * Demande est la classe representant une demande d'un certificat
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "demande")
public class Demand implements Serializable
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
	@Column(name = "id_demande")
	private Integer idDemand;
	
	@Column(name = "date_demande")
	@Type(type = "date")
	@NotNull
	private Date dateDemand;
	
	@Column(name = "date_realisation_souhaitee")
	@Type(type = "date")
	private Date dateCreationDesired;
	
	@Column(name = "date_transmission")
	@Type(type = "date")
	private Date dateTransmission;
	
	@Column(name = "email_referent")
	private String eMailReferent;
	
	@Column(name = "description_contexte")
	private String descriptionContext;
	
	@Column(name = "remarque_racine")
	private String remarkRoot;
	
	/**
	 * relation entre l'entite demande et les entites statut demande, type demande, utilisateur, application et certificat
	 * 
	 * @see StatusDemand
	 * @see TypeDemand
	 * @see User
	 * @see Application
	 * @see Certificate
	 */
	
	//@JsonIgnore
	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_statut_demande")
	private StatusDemand statusDemand;
	
	//@JsonIgnore
	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_type_demande")
	private TypeDemand typeDemand;
	
	@OneToOne
	@JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
	private AppUser user;
	
	@OneToOne
	@JoinColumn(name = "id_application", referencedColumnName = "id_application")
	private Application application;
	
	@OneToOne
	@JoinColumn(name = "id_certificat", referencedColumnName = "id_certificat")
	private Certificate certificate;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Demand()
	{
		
	}

	public Demand(Integer idDemand, @NotNull Date dateDemand, Date dateCreationDesired,
			Date dateTransmission, String eMailReferent, String descriptionContext, String remarkRoot,
			StatusDemand statusDemand, TypeDemand typeDemand, AppUser user, Application application,
			Certificate certificate)
	{
		super();
		this.idDemand = idDemand;
		this.dateDemand = dateDemand;
		this.dateCreationDesired = dateCreationDesired;
		this.dateTransmission = dateTransmission;
		this.eMailReferent = eMailReferent;
		this.descriptionContext = descriptionContext;
		this.remarkRoot = remarkRoot;
		this.statusDemand = statusDemand;
		this.typeDemand = typeDemand;
		this.user = user;
		this.application = application;
		this.certificate = certificate;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdDemand()
	{
		return idDemand;
	}

	public void setIdDemand(Integer idDemand)
	{
		this.idDemand = idDemand;
	}

	public Date getDateDemand()
	{
		return dateDemand;
	}

	public void setDateDemand(Date dateDemand)
	{
		this.dateDemand = dateDemand;
	}

	public Date getDateCreationDesired()
	{
		return dateCreationDesired;
	}

	public void setDateCreationDesired(Date dateCreationDesired)
	{
		this.dateCreationDesired = dateCreationDesired;
	}

	public Date getDateTransmission()
	{
		return dateTransmission;
	}

	public void setDateTransmission(Date dateTransmission)
	{
		this.dateTransmission = dateTransmission;
	}

	public String geteMailReferent()
	{
		return eMailReferent;
	}

	public void seteMailReferent(String eMailReferent)
	{
		this.eMailReferent = eMailReferent;
	}

	public String getDescriptionContext()
	{
		return descriptionContext;
	}

	public void setDescriptionContext(String descriptionContext)
	{
		this.descriptionContext = descriptionContext;
	}

	public String getRemarkRoot()
	{
		return remarkRoot;
	}

	public void setRemarkRoot(String remarkRoot)
	{
		this.remarkRoot = remarkRoot;
	}

	public StatusDemand getStatusDemand()
	{
		return statusDemand;
	}

	public void setStatusDemand(StatusDemand statusDemand)
	{
		this.statusDemand = statusDemand;
	}

	public TypeDemand getTypeDemand()
	{
		return typeDemand;
	}

	public void setTypeDemand(TypeDemand typeDemand)
	{
		this.typeDemand = typeDemand;
	}

	public AppUser getUser()
	{
		return user;
	}

	public void setUser(AppUser user)
	{
		this.user = user;
	}

	public Application getApplication()
	{
		return application;
	}

	public void setApplication(Application application)
	{
		this.application = application;
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
