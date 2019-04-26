//package gestiCert.model;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.ConstraintMode;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
///**
// * Utilisateur est la classe representant un utilisateur de l'application gesticert
// * 
// * @author Samuel Sabot
// * @version 0.0
// */
//
//@Entity
//@Table(name = "utilisateur")
//public class User implements Serializable
//{
//
//	/**
//	 * numero de version par defaut de la classe pour que les objets generes soient reconnus
//	 */
//	
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * attributs de l'entite
//	 */
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_utilisateur")
//	private Integer idUser;
//	
//	@Column(name = "nom")
//	@Size(max = 50)
////	@NotNull
//	private String nameUser;
//	
//	@Column(name = "prenom")
//	@Size(max = 50)
////	@NotNull
//	private String firstNameUser;
//	
//	@Column(name = "fonction")
//	@Size(max = 50)
//	private String roleUser;
//	
//	@Column(name = "email", unique = true)
////	@NotNull
//	private String eMailUser;
//	
//	@Column(name = "telephone")
//	@Size(min = 10, max = 10)
//	private String phoneUser;
//	
//	@Column(name = "idrh", unique = true)
//	@Size(min = 7, max = 7)
//	@NotNull
//	private String idRHUser;
//	
//	@Column(name = "mdp")
//	@NotNull
//	private String passwordUser;
//	
////	// authentification
////	@ElementCollection(fetch = FetchType.EAGER)
////	@Enumerated(EnumType.STRING)
////	private List<Role> roleList;
//	
//	/**
//	 * relation entre l'entite utilisateur et les entites profil, service et application
//	 * 
//	 * @see Function
//	 * @see Department
//	 * @see Application
//	 */
//	
////	//@JsonIgnore
////	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
////	@JoinColumn(name = "id_profil")
////	private Function profile;
//	
////	//@JsonIgnore
////	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
////	@JoinColumn(name = "id_service")
////	private Department department;
//	
////	@JsonIgnore
////	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
////	@JoinTable(name = "utilisateur_application", joinColumns = @JoinColumn(name = "id_utilisateur"),
////		inverseJoinColumns = @JoinColumn(name = "id_application"),
////		foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
////        inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
////	private List<Application> applications;
//	
//	/**
//	 * constructeurs de la classe dont un vide par defaut
//	 */
//
//	public User()
//	{
//		
//	}
//	
//	
//
////	public User(Integer idUser, @Size(max = 50) @NotNull String nameUser, @Size(max = 50) @NotNull String firstNameUser,
////			@Size(max = 50) String roleUser, @NotNull String eMailUser, @Size(min = 10, max = 10) String phoneUser,
////			@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, List<Role> roleList,
////			Profile profile, Department department, List<Application> applications) {
////		super();
////		this.idUser = idUser;
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.roleUser = roleUser;
////		this.eMailUser = eMailUser;
////		this.phoneUser = phoneUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.roleList = roleList;
////		this.profile = profile;
////		this.department = department;
////		this.applications = applications;
////	}
//
//
//
////	public User(@Size(max = 50) @NotNull String nameUser, @Size(max = 50) @NotNull String firstNameUser,
////			@NotNull String eMailUser, @Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser,
////			List<Role> roleList) {
////		super();
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.eMailUser = eMailUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.roleList = roleList;
////	}
//
////	// authentification
////	public User(Integer idUser, @Size(max = 50) String nameUser, @Size(max = 50) String firstNameUser,
////			@Size(max = 50) String roleUser, String eMailUser, @Size(min = 10, max = 10) String phoneUser,
////			@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, List<Role> roleList,
////			Department department, List<Application> applications) {
////		super();
////		this.idUser = idUser;
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.roleUser = roleUser;
////		this.eMailUser = eMailUser;
////		this.phoneUser = phoneUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.roleList = roleList;
////		this.department = department;
////		this.applications = applications;
////	}
//
////	public User(@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser) {
////		super();
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////	}
////	public User(@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, List<Role> roleList) {
////		super();
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.roleList = roleList;
////	}
//
////	public User(Integer idUser, @Size(max = 50) String nameUser, @Size(max = 50) String firstNameUser,
////			@Size(max = 50) String roleUser, String eMailUser,
////			@Size(min = 10, max = 10) String phoneUser, @Size(min = 7, max = 7) @NotNull String idRHUser,
////			@NotNull String passwordUser, Department department,
////			List<Application> applications)
////	{
////		super();
////		this.idUser = idUser;
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.roleUser = roleUser;
////		this.eMailUser = eMailUser;
////		this.phoneUser = phoneUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.department = department;
////		this.applications = applications;
////	}
//	
//	
//
//	public User(Integer idUser, @Size(max = 50) String nameUser, @Size(max = 50) String firstNameUser,
//			@Size(max = 50) String roleUser, String eMailUser, @Size(min = 10, max = 10) String phoneUser,
//			@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser) {
//		super();
//		this.idUser = idUser;
//		this.nameUser = nameUser;
//		this.firstNameUser = firstNameUser;
//		this.roleUser = roleUser;
//		this.eMailUser = eMailUser;
//		this.phoneUser = phoneUser;
//		this.idRHUser = idRHUser;
//		this.passwordUser = passwordUser;
//	}
//	
//	
//
////	public User(Integer idUser, @Size(max = 50) String nameUser, @Size(max = 50) String firstNameUser,
////			@Size(max = 50) String roleUser, String eMailUser, @Size(min = 10, max = 10) String phoneUser,
////			@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, Department department) {
////		super();
////		this.idUser = idUser;
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.roleUser = roleUser;
////		this.eMailUser = eMailUser;
////		this.phoneUser = phoneUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.department = department;
////	}
//	
//	
//
////	public User(Integer idUser, @Size(max = 50) @NotNull String nameUser, @Size(max = 50) @NotNull String firstNameUser,
////			@Size(max = 50) String roleUser, @NotNull String eMailUser, @Size(min = 10, max = 10) String phoneUser,
////			@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, Profile profile,
////			Department department, List<Application> applications) {
////		super();
////		this.idUser = idUser;
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.roleUser = roleUser;
////		this.eMailUser = eMailUser;
////		this.phoneUser = phoneUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.profile = profile;
////		this.department = department;
////		this.applications = applications;
////	}
//	
//	
////	public User(@Size(max = 50) String nameUser, @Size(max = 50) String firstNameUser,
////			@Size(max = 50) String roleUser, String eMailUser, @Size(min = 10, max = 10) String phoneUser,
////			@Size(min = 7, max = 7) @NotNull String idRHUser, @NotNull String passwordUser, List<Role> roleList,
////			Department department) {
////		super();
////		this.nameUser = nameUser;
////		this.firstNameUser = firstNameUser;
////		this.roleUser = roleUser;
////		this.eMailUser = eMailUser;
////		this.phoneUser = phoneUser;
////		this.idRHUser = idRHUser;
////		this.passwordUser = passwordUser;
////		this.roleList = roleList;
////		this.department = department;
////	}
//
//
//	/**
//	 * getter et setter de la classe
//	 * 
//	 * @return valeur correspondant au get ou set
//	 */
//	
////	// authentification
////	public List<Role> getRoleList() {
////		return roleList;
////	}
////	public User(@Size(min = 7, max = 7) @NotNull String idRHUser, List<Role> roleList) {
////		super();
////		this.idRHUser = idRHUser;
////		this.roleList = roleList;
////	}
//
//
////	public void setRoleList(List<Role> roleList) {
////		this.roleList = roleList;
////	}
//	
//	public Integer getIdUser()
//	{
//		return idUser;
//	}
//
//	public void setIdUser(Integer idUser)
//	{
//		this.idUser = idUser;
//	}
//
//	public String getNameUser()
//	{
//		return nameUser;
//	}
//
//	public void setNameUser(String nameUser)
//	{
//		this.nameUser = nameUser;
//	}
//
//	public String getFirstNameUser()
//	{
//		return firstNameUser;
//	}
//
//	public void setFirstNameUser(String firstNameUser)
//	{
//		this.firstNameUser = firstNameUser;
//	}
//
//	public String getRoleUser()
//	{
//		return roleUser;
//	}
//
//	public void setRoleUser(String roleUser)
//	{
//		this.roleUser = roleUser;
//	}
//
//	public String geteMailUser()
//	{
//		return eMailUser;
//	}
//
//	public void seteMailUser(String eMailUser)
//	{
//		this.eMailUser = eMailUser;
//	}
//
//	public String getPhoneUser()
//	{
//		return phoneUser;
//	}
//
//	public void setPhoneUser(String phoneUser)
//	{
//		this.phoneUser = phoneUser;
//	}
//
//	public String getIdRHUser()
//	{
//		return idRHUser;
//	}
//
//	public void setIdRHUser(String idRHUser)
//	{
//		this.idRHUser = idRHUser;
//	}
//
//	public String getPasswordUser()
//	{
//		return passwordUser;
//	}
//
//	public void setPasswordUser(String passwordUser)
//	{
//		this.passwordUser = passwordUser;
//	}
//
////	public Function getProfile()
////	{
////		return profile;
////	}
////
////	public void setProfile(Function profile)
////	{
////		this.profile = profile;
////	}
//
////	public Department getDepartment() {
////		return department;
////	}
////
////	public void setDepartment(Department department)
////	{
////		this.department = department;
////	}
//
////	public List<Application> getApplications()
////	{
////		return applications;
////	}
////
////	public void setApplications(List<Application> applications)
////	{
////		this.applications = applications;
////	}
//
//
//
////	@Override
////	public String toString() {
////		return "User [idUser=" + idUser + ", nameUser=" + nameUser + ", firstNameUser=" + firstNameUser + ", roleUser="
////				+ roleUser + ", eMailUser=" + eMailUser + ", phoneUser=" + phoneUser + ", idRHUser=" + idRHUser
////				+ ", passwordUser=" + passwordUser + ", roleList=" + roleList + ", department=" + department
////				+ ", applications=" + applications + "]";
////	}
//
//
//
////	@Override
////	public String toString() {
////		return "User [idUser=" + idUser + ", nameUser=" + nameUser + ", firstNameUser=" + firstNameUser + ", roleUser="
////				+ roleUser + ", eMailUser=" + eMailUser + ", phoneUser=" + phoneUser + ", idRHUser=" + idRHUser
////				+ ", passwordUser=" + passwordUser + ", profile=" + profile + ", department=" + department
////				+ ", applications=" + applications + "]";
////	}
//	
//	
//
//	
//	
//	
//
//}
