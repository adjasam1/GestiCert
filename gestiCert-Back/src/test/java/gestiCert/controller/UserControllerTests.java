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
//import gestiCert.model.Department;
//import gestiCert.model.User;
//import gestiCert.security.JwtTokenProvider;
//import gestiCert.service.AddressAlternativeService;
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
//import gestiCert.service.UserService;
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
//	private UserService userServ;
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
////	@Test
////	@WithMockUser(roles = { "ADMIN" })
////	public void getAllUsersWithAuth() throws Exception {
////		when(this.userServ.findAllUsers()).thenReturn(new ArrayList<>());
////		this.mockMvc.perform(get("/utilisateur")).andExpect(status().isOk());
////	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getAllUsers() throws Exception {
//		when(this.userServ.getAllUsers()).thenReturn(new ArrayList<>());
//		this.mockMvc.perform(get("/api/utilisateur")).andExpect(status().isOk());
//	}
//
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserById() throws Exception {
//		when(this.userServ.getUserById(2)).thenReturn(Optional.of(new User(2, "Yemen", "Béatrice", "cheffe de projet Dev Web", "beatrice.yemen@laposte.fr", "0659527564", "pbbb222", "bbb", new Department(1, "Modif31"))));
//		this.mockMvc.perform(get("/api/utilisateur/id=2")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("roleUser").value("cheffe de projet Dev Web"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb222"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(1))
//		.andExpect(jsonPath("department.nameDepartment").value("Modif31"));
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserByName() throws Exception {
//		when(this.userServ.getUserByName("Yemen")).thenReturn(new ArrayList<>());
//			//	Optional.of(new User(2, "Yemen", "Béatrice", "cheffe de projet Dev Web", "beatrice.yemen@laposte.fr", "0659527564", "pbbb222", "bbb", new Department(1, "Modif31"))));
//		this.mockMvc.perform(get("/api/utilisateur/nom=Yemen")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("roleUser").value("cheffe de projet Dev Web"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb222"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(1))
//		.andExpect(jsonPath("department.nameDepartment").value("Modif31"));
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserByFirstName() throws Exception {
//		when(this.userServ.getUserByFirstName("Béatrice")).thenReturn(new ArrayList<>());
//				// Optional.of(new User(2, "Yemen", "Béatrice", "cheffe de projet Dev Web", "beatrice.yemen@laposte.fr", "0659527564", "pbbb222", "bbb", new Department(1, "Modif31"))));
//		this.mockMvc.perform(get("/api/utilisateur/prenom=Béatrice")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("roleUser").value("cheffe de projet Dev Web"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb222"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(1))
//		.andExpect(jsonPath("department.nameDepartment").value("Modif31"));
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getUserByNameAndFirstName() throws Exception {
//		when(this.userServ.getUserByNameAndFirstName("Yemen", "Béatrice")).thenReturn(new ArrayList<>());
//				// Optional.of(new User(2, "Yemen", "Béatrice", "cheffe de projet Dev Web", "beatrice.yemen@laposte.fr", "0659527564", "pbbb222", "bbb", new Department(1, "Modif31"))));
//		this.mockMvc.perform(get("/api/utilisateur/nom=Yemen/prenom=Béatrice")).andExpect(status().isOk())
//		.andExpect(jsonPath("idUser").value(2))
//		.andExpect(jsonPath("nameUser").value("Yemen"))
//		.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//		.andExpect(jsonPath("roleUser").value("cheffe de projet Dev Web"))
//		.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("pbbb222"))
//		.andExpect(jsonPath("passwordUser").value("bbb"))
//		.andExpect(jsonPath("department.idDepartment").value(1))
//		.andExpect(jsonPath("department.nameDepartment").value("Modif31"));
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void createUser() throws Exception {
//		when(this.userServ.createUser(any())).thenReturn(new User(2, "Yem", "Béa", "cheffe", "bea.yem@laposte.fr", "0659527564", "zzzz222", "zzz", new Department(1, "Modif31")));
//		this.mockMvc.perform(post("/api/utilisateur/ajout").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content("{\"nameUser\": \"Yem\", \"firstNameUser\": \"Béa\", \"roleUser\": \"cheffe\", \"eMailUser\": \"bea.yem@laposte.fr\", \"phoneUser\": \"0659527564\", \"idRHUser\": \"zzzz222\", \"passwordUser\": \"zzz\", \"department.idDepartment\": \"1\", \"department.nameDepartment\": \"Modif31\"}"))
//		.andExpect(status().isCreated())
//		.andExpect(jsonPath("nameUser").value("Yem"))
//		.andExpect(jsonPath("firstNameUser").value("Béa"))
//		.andExpect(jsonPath("roleUser").value("cheffe"))
//		.andExpect(jsonPath("eMailUser").value("bea.yem@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0659527564"))
//		.andExpect(jsonPath("idRHUser").value("zzzz222"))
//		.andExpect(jsonPath("passwordUser").value("zzz"))
//		.andExpect(jsonPath("department.idDepartment").value(1))
//		.andExpect(jsonPath("department.nameDepartment").value("Modif31"));
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void modifyUser() throws Exception {
//		when(this.userServ.updateUser(any())).thenReturn(new User(2, "Y", "B", "c", "b.y@laposte.fr", "0111111111", "zaaa000", "zaa", new Department(3, "Appli HR")));
//		this.mockMvc.perform(put("/api/utilisateur//modifid=2").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content("{\"nameUser\": \"Y\", \"firstNameUser\": \"B\", \"roleUser\": \"c\", \"eMailUser\": \"b.y@laposte.fr\", \"phoneUser\": \"0111111111\", \"idRHUser\": \"zaaa000\", \"passwordUser\": \"zaa\", \"department.idDepartment\": \"3\", \"department.nameDepartment\": \"Appli HR\"}"))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("nameUser").value("Y"))
//		.andExpect(jsonPath("firstNameUser").value("B"))
//		.andExpect(jsonPath("roleUser").value("c"))
//		.andExpect(jsonPath("eMailUser").value("b.y@laposte.fr"))
//		.andExpect(jsonPath("phoneUser").value("0111111111"))
//		.andExpect(jsonPath("idRHUser").value("zaaa000"))
//		.andExpect(jsonPath("passwordUser").value("zaa"))
//		.andExpect(jsonPath("department.idDepartment").value(3))
//		.andExpect(jsonPath("department.nameDepartment").value("Appli HR"));
//	
//	}
//	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void deleteUser() throws Exception {
////		when(this.userServ.deleteUser(2)).thenReturn(new User(2, "Y", "B", "c", "b.y@laposte.fr", "0111111111", "zaaa000", "zaa", new Department(3, "Appli HR")));
//		this.mockMvc.perform(delete("/api/utilisateur/supprid=2")).andExpect(status().isOk());
//	}
//
//}
