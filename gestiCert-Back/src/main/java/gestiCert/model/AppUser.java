package gestiCert.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "app_utilisateur")
public class AppUser implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
    private Integer idUser;

    @NotNull
    @Column(name = "idRH", nullable = false)
    private String idRHUser;

    @NotNull
    @Column(name = "mot_de_passe", nullable = false)
    private String passwordUser;
    
	@Column(name = "nom")
	@Size(max = 50)
//	@NotNull
	private String nameUser;
	
	@Column(name = "prénom")
	@Size(max = 50)
//	@NotNull
	private String firstNameUser;
	
	@Column(name = "email", unique = true)
//	@NotNull
	private String eMailUser;
	
	@Column(name = "téléphone")
	@Size(min = 10, max = 10)
	private String phoneUser;

	//@JsonIgnore
	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_service")
	private Department department;
	
	//@JsonIgnore
	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_profile")
	private Profile profile;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinTable(name = "utilisateur_application", joinColumns = @JoinColumn(name = "id_utilisateur"),
		inverseJoinColumns = @JoinColumn(name = "id_application"),
		foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
        inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private List<Application> applications;

	@ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roleList;

    public AppUser() {
	}

	public AppUser(@NotNull String idRHUser, @NotNull String passwordUser) {
        this.idRHUser = idRHUser;
        this.passwordUser = passwordUser;
    }
	
	

    public AppUser(Integer idUser, @NotNull String idRHUser, List<Role> roleList) {
		super();
		this.idUser = idUser;
		this.idRHUser = idRHUser;
		this.roleList = roleList;
	}

	public AppUser(@NotNull String idRHUser, @NotNull String passwordUser, List<Role> roleList) {
    	this.idRHUser = idRHUser;
        this.passwordUser = passwordUser;
        this.roleList = roleList;
    }
    
    

	public AppUser(Integer idUser, @NotNull String idRHUser, @NotNull String passwordUser, @Size(max = 50) String nameUser,
			@Size(max = 50) String firstNameUser, String eMailUser, @Size(min = 10, max = 10) String phoneUser,
			Department department, Profile profile, List<Role> roleList) {
		super();
		this.idUser = idUser;
		this.idRHUser = idRHUser;
        this.passwordUser = passwordUser;
		this.nameUser = nameUser;
		this.firstNameUser = firstNameUser;
		this.eMailUser = eMailUser;
		this.phoneUser = phoneUser;
		this.department = department;
		this.profile = profile;
		this.roleList = roleList;
	}

	public AppUser(@NotNull String idRHUser, @NotNull String passwordUser, @Size(max = 50) String nameUser,
			@Size(max = 50) String firstNameUser, String eMailUser, @Size(min = 10, max = 10) String phoneUser,
			Department department, Profile profile, List<Role> roleList) {
		super();
		this.idRHUser = idRHUser;
        this.passwordUser = passwordUser;
		this.nameUser = nameUser;
		this.firstNameUser = firstNameUser;
		this.eMailUser = eMailUser;
		this.phoneUser = phoneUser;
		this.department = department;
		this.profile = profile;
		this.roleList = roleList;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getIdRHUser() {
		return idRHUser;
	}

	public void setIdRHUser(String idRHUser) {
		this.idRHUser = idRHUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String geteMailUser() {
		return eMailUser;
	}

	public void seteMailUser(String eMailUser) {
		this.eMailUser = eMailUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	
	
	
	

}
