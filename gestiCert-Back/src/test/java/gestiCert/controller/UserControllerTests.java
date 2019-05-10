//package gestiCert.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import gestiCert.model.AppUser;
//import gestiCert.model.Department;
//import gestiCert.model.Profile;
////import gestiCert.model.User;
//import gestiCert.security.JwtTokenProvider;
//import gestiCert.service.AddressAlternativeService;
//import gestiCert.service.AppUserService;
//import gestiCert.service.ApplicationService;
//import gestiCert.service.CertificateService;
//import gestiCert.service.DemandService;
//import gestiCert.service.DepartmentService;
//import gestiCert.service.EnvironmentService;
//import gestiCert.service.PlateformService;
//import gestiCert.service.RootService;
//import gestiCert.service.ServerService;
//import gestiCert.service.StatusDemandService;
//import gestiCert.service.TypeDemandService;
////import gestiCert.service.UserService;
//
//import org.springframework.test.context.TestExecutionListeners.MergeMode;
//import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS, listeners = {
//        WithSecurityContextTestExecutionListener.class })
//public class UserControllerTests {
//	
//	@Autowired
//	MockMvc mockMvc;
//	
//	@MockBean
//	private AppUserService userServ;
//	
//	@MockBean
//	private AddressAlternativeService addressAlternativeServ;
//	
//	@MockBean
//	private ApplicationService applicationServ;
//	
//	@MockBean
//	private CertificateService certificateServ;
//	
//	@MockBean
//	private DemandService demandServ;
//	
//	@MockBean
//	private DepartmentService departmentServ;
//	
//	@MockBean
//	private EnvironmentService environmentServ;
//	
//	@MockBean
//	private PlateformService plateformServ;
//	
//	@MockBean
//	private RootService rootServ;
//	
//	@MockBean
//	private ServerService serverServ;
//	
//	@MockBean
//	private StatusDemandService statusDemandService;
//	
//	@MockBean
//	private TypeDemandService typeDemandServ;
//	
//	@MockBean
//	private JwtTokenProvider jwtTokenProvider;
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getAllUsers() throws Exception {
//		when(this.userServ.getAllUsers()).thenReturn(new ArrayList<>());
//		this.mockMvc.perform(get("/api/utilisateur")).andExpect(status().isOk()).andDo(print());
//	}
//
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserById() throws Exception {
//		when(this.userServ.getUserById(2)).thenReturn(Optional.of(new AppUser(2, "pbbb002", "bbb", "Yemen", "Béatrice", "beatrice.yemen@laposte.fr", "0659527564", new Department(3, "Appli"), new Profile(1, "Développeur(se)"))));
//		this.mockMvc.perform(get("/api/utilisateur/id=2")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb002"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(3))
//		.andExpect(jsonPath("department.nameDepartment").value("Appli"))
//		.andExpect(jsonPath("profile.idProfile").value(1))
//		.andExpect(jsonPath("profile.typeProfile").value("Développeur(se)"))
//		.andDo(print());
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserByName() throws Exception {
//		when(this.userServ.getUserByName("men")).thenReturn(Optional.of(new AppUser(2, "pbbb002", "bbb", "Yemen", "Béatrice", "beatrice.yemen@laposte.fr", "0659527564", new Department(3, "Appli"), new Profile(1, "Développeur(se)"))));
//		this.mockMvc.perform(get("/api/utilisateur/nom=men")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb002"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(3))
//		.andExpect(jsonPath("department.nameDepartment").value("Appli"))
//		.andExpect(jsonPath("profile.idProfile").value(1))
//		.andExpect(jsonPath("profile.typeProfile").value("Développeur(se)"))
//		.andDo(print());
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserByFirstName() throws Exception {
//		when(this.userServ.getUserByFirstName("Beatrice")).thenReturn(Optional.of(new AppUser(2, "pbbb002", "bbb", "Yemen", "Béatrice", "beatrice.yemen@laposte.fr", "0659527564", new Department(3, "Appli"), new Profile(1, "Développeur(se)"))));
//		this.mockMvc.perform(get("/api/utilisateur/prenom=Beatrice")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("idRHUser").value("pbbb002"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("department.idDepartment").value(3))
//		.andExpect(jsonPath("department.nameDepartment").value("Appli"))
//		.andExpect(jsonPath("profile.idProfile").value(1))
//		.andExpect(jsonPath("profile.typeProfile").value("Développeur(se)"))
//		.andDo(print());
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserByNameAndFirstName() throws Exception {
//		when(this.userServ.getUserByNameAndFirstName("Yemen", "Béatrice")).thenReturn(Optional.of(new AppUser(2, "pbbb002", "bbb", "Yemen", "Béatrice", "beatrice.yemen@laposte.fr", "0659527564", new Department(3, "Appli"), new Profile(1, "Développeur(se)"))));
//		this.mockMvc.perform(get("/api/utilisateur/nom=Yemen/prenom=Béatrice")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb002"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(3))
//		.andExpect(jsonPath("department.nameDepartment").value("Appli"))
//		.andExpect(jsonPath("profile.idProfile").value(1))
//		.andExpect(jsonPath("profile.typeProfile").value("Développeur(se)"))
//		.andDo(print());
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void createUser() throws Exception {
//		when(this.userServ.createUser(any())).thenReturn(new AppUser("pzzz222", "zzz", "Z", "Z", "z.z@laposte.fr", "0612345678", new Department(2, "SIGP3"), new Profile(3, "Chef(fe) de Service")));
//		this.mockMvc.perform(post("/api/utilisateur/ajout").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content("{\"idRHUser\": \"pzzz222\", \"passwordUser\": \"zzz\", \"nameUser\": \"Z\", \"firstNameUser\": \"Z\", \"eMailUser\": \"z.z@laposte.fr\", \"phoneUser\": \"0612345678\", \"department.idDepartment\": \"2\", \"department.nameDepartment\": \"SIGP3\", \"profile.idProfile\": \"3\", \"typeProfile\": \"Chef(fe) de Service\"}"))
//		.andExpect(status().isCreated())
//		.andExpect(jsonPath("nameUser").value("Z"))
//		.andExpect(jsonPath("firstNameUser").value("Z"))
//		.andExpect(jsonPath("eMailUser").value("z.z@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0612345678"))
//		.andExpect(jsonPath("idRHUser").value("pzzz222"))
//		.andExpect(jsonPath("passwordUser").value("zzz"))
//		.andExpect(jsonPath("department.idDepartment").value(2))
//		.andExpect(jsonPath("department.nameDepartment").value("SIGP3"))
//		.andExpect(jsonPath("profile.idProfile").value(3))
//		.andExpect(jsonPath("profile.typeProfile").value("Chef(fe) de Service"))
//		.andDo(print());
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void modifyUser() throws Exception {
//		when(this.userServ.updateUser(any())).thenReturn(new AppUser(2, "pzzz000", "zzz", "Y", "B", "b.y@laposte.fr", "0612345678", new Department(2, "SIGP3"), new Profile(3, "Chef(fe) de Service")));
//		this.mockMvc.perform(put("/api/utilisateur//modifid=2").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content("{\"idUser\": \"2\", \"idRHUser\": \"pzzz000\", \"passwordUser\": \"zzz\", \"nameUser\": \"Y\", \"firstNameUser\": \"B\", \"eMailUser\": \"b.y@laposte.fr\", \"phoneUser\": \"0612345678\", \"department.idDepartment\": \"2\", \"department.nameDepartment\": \"SIGP3\", \"profile.idProfile\": \"3\", \"profile.typeProfile\": \"Chef(fe) de Service\"}"))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Y"))
//		.andExpect(jsonPath("firstNameUser").value("B"))
//		.andExpect(jsonPath("eMailUser").value("b.y@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0612345678"))
//		.andExpect(jsonPath("idRHUser").value("pzzz000"))
//		.andExpect(jsonPath("passwordUser").value("zzz"))
//		.andExpect(jsonPath("department.idDepartment").value(2))
//		.andExpect(jsonPath("department.nameDepartment").value("SIGP3"))
//		.andExpect(jsonPath("profile.idProfile").value(3))
//		.andExpect(jsonPath("profile.typeProfile").value("Chef(fe) de Service"))
//		.andDo(print());
//	
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void deleteUser() throws Exception {
//		this.mockMvc.perform(delete("/api/utilisateur/supprid=2")).andExpect(status().isOk()).andDo(print());
//	}
//
//}
