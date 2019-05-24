package gestiCert.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Certificat est la classe representant un certificat d'une application
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Entity
@Table(name = "certificat")
public class Certificate implements Serializable
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
	@Column(name = "id_certificat")
	private Integer idCertificate;
	
	@Column(name = "nom_certificat")
	@Size(min = 16, max = 17)
	private String nameCertificate;
	
	@Column(name = "adresse_principale")
	private String linkAddressPrincipal;
	
	@Column(name = "lien_installation")
	private String linkInstallation;
	
	@Column(name = "mdp_certificat")
	private String passwordCertificate;
	
	@Column(name = "date_emission")
	@Type(type = "date")
	private Date dateIssue;
	
	@Column(name = "fin_validite")
	@Type(type = "date")
	private Date dateEndValidity;
	
	/**
	 * relation entre l'entite certificat et les entites application, environnement, plateforme, racine, serveur et adresse alternative
	 * 
	 * @see Application
	 * @see Environment
	 * @see Plateform
	 * @see Root
	 * @see Server
	 * @see addresseAlternative
	 */
	
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_application")
	private Application application;
	
	//@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_environnement")
	private Environment environment;
	
	//@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_plateforme")
	private Plateform plateform;
	
	//@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_racine")
	private Root root;
	
//	@JsonIgnore
	@JsonIgnoreProperties("certificate")
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name ="certificat_serveur", joinColumns = @JoinColumn(name = "id_certificat"), inverseJoinColumns = @JoinColumn(name = "id_serveur"))
	private List<Server> servers;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
	private List<AddressAlternative> addressAlternatives;
	
	/**
	 * constructeurs de la classe dont un vide par defaut
	 */

	public Certificate()
	{
		
	}

	public Certificate(Integer idCertificate, @Size(min = 16, max = 17) String nameCertificate,
			String linkAddressPrincipal, String linkInstallation, String passwordCertificate, Date dateIssue,
			Date dateEndValidity, Application application, Environment environment, Plateform plateform, Root root,
			List<Server> servers, List<AddressAlternative> addressAlternatives)
	{
		super();
		this.idCertificate = idCertificate;
		this.nameCertificate = nameCertificate;
		this.linkAddressPrincipal = linkAddressPrincipal;
		this.linkInstallation = linkInstallation;
		this.passwordCertificate = passwordCertificate;
		this.dateIssue = dateIssue;
		this.dateEndValidity = dateEndValidity;
		this.application = application;
		this.environment = environment;
		this.plateform = plateform;
		this.root = root;
		this.servers = servers;
		this.addressAlternatives = addressAlternatives;
	}
	
	

	public Certificate(@Size(min = 16, max = 17) String nameCertificate, String linkAddressPrincipal,
			String linkInstallation, String passwordCertificate) {
		this(nameCertificate, linkAddressPrincipal, linkInstallation, passwordCertificate, null, null);
	}

	public Certificate(@Size(min = 16, max = 17) String nameCertificate, String linkAddressPrincipal,
			String linkInstallation, String passwordCertificate, Date dateIssue, Date dateEndValidity) {
		super();
		this.nameCertificate = nameCertificate;
		this.linkAddressPrincipal = linkAddressPrincipal;
		this.linkInstallation = linkInstallation;
		this.passwordCertificate = passwordCertificate;
		this.dateIssue = dateIssue;
		this.dateEndValidity = dateEndValidity;
	}

	public Certificate(@Size(min = 16, max = 17) String nameCertificate, String linkAddressPrincipal,
			String linkInstallation, String passwordCertificate, Date dateIssue, Date dateEndValidity,
			Application application, Environment environment, Plateform plateform, Root root) {
		super();
		this.nameCertificate = nameCertificate;
		this.linkAddressPrincipal = linkAddressPrincipal;
		this.linkInstallation = linkInstallation;
		this.passwordCertificate = passwordCertificate;
		this.dateIssue = dateIssue;
		this.dateEndValidity = dateEndValidity;
		this.application = application;
		this.environment = environment;
		this.plateform = plateform;
		this.root = root;
	}

	/**
	 * getter et setter de la classe
	 * 
	 * @return valeur correspondant au get ou set
	 */
	
	public Integer getIdCertificate()
	{
		return idCertificate;
	}

	public void setIdCertificate(Integer idCertificate)
	{
		this.idCertificate = idCertificate;
	}

	public String getNameCertificate()
	{
		return nameCertificate;
	}

	public void setNameCertificate(String nameCertificate)
	{
		this.nameCertificate = nameCertificate;
	}

	public String getLinkAddressPrincipal()
	{
		return linkAddressPrincipal;
	}

	public void setLinkAddressPrincipal(String linkAddressPrincipal)
	{
		this.linkAddressPrincipal = linkAddressPrincipal;
	}

	public String getLinkInstallation()
	{
		return linkInstallation;
	}

	public void setLinkInstallation(String linkInstallation)
	{
		this.linkInstallation = linkInstallation;
	}

	public String getPasswordCertificate()
	{
		return passwordCertificate;
	}

	public void setPasswordCertificate(String passwordCertificate)
	{
		this.passwordCertificate = passwordCertificate;
	}

	public Date getDateIssue()
	{
		return dateIssue;
	}

	public void setDateIssue(Date dateIssue)
	{
		this.dateIssue = dateIssue;
	}

	public Date getDateEndValidity()
	{
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity)
	{
		this.dateEndValidity = dateEndValidity;
	}

	public Application getApplication()
	{
		return application;
	}

	public void setApplication(Application application)
	{
		this.application = application;
	}

	public Environment getEnvironment()
	{
		return environment;
	}

	public void setEnvironment(Environment environment)
	{
		this.environment = environment;
	}

	public Plateform getPlateform()
	{
		return plateform;
	}

	public void setPlateform(Plateform plateform)
	{
		this.plateform = plateform;
	}

	public Root getRoot()
	{
		return root;
	}

	public void setRoot(Root root)
	{
		this.root = root;
	}

	public List<Server> getServers()
	{
		return servers;
	}

	public void setServers(List<Server> servers)
	{
		this.servers = servers;
	}

	public List<AddressAlternative> getAddressAlternatives()
	{
		return addressAlternatives;
	}

	public void setAddressAlternatives(List<AddressAlternative> addressAlternatives)
	{
		this.addressAlternatives = addressAlternatives;
	}

}
