package gestiCert.controller;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import gestiCert.service.AddressAlternativeService;
import gestiCert.service.AppUserService;
import gestiCert.service.ApplicationService;
import gestiCert.service.CertificateService;
import gestiCert.service.DemandService;
import gestiCert.service.DepartmentService;
import gestiCert.service.EnvironmentService;
import gestiCert.service.PlateformService;
import gestiCert.service.RootService;
import gestiCert.service.ServerService;
import gestiCert.service.StatusDemandService;
import gestiCert.service.TypeDemandService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS, listeners = {
        WithSecurityContextTestExecutionListener.class })
public class DepartmentControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private AppUserService userServ;
	
	@MockBean
	private AddressAlternativeService addressAlternativeServ;
	
	@MockBean
	private ApplicationService applicationServ;
	
	@MockBean
	private CertificateService certificateServ;
	
	@MockBean
	private DemandService demandServ;
	
	@Autowired
	private DepartmentService departmentServ;
	
	@MockBean
	private EnvironmentService environmentServ;
	
	@MockBean
	private PlateformService plateformServ;
	
	@MockBean
	private RootService rootServ;
	
	@MockBean
	private ServerService serverServ;
	
	@MockBean
	private StatusDemandService statusDemandService;
	
	@MockBean
	private TypeDemandService typeDemandServ;
	
	@Test
//	@WithMockUser(roles = { "ADMIN" })
	public void getAllDepartments() throws Exception {
		System.out.println(departmentServ);
//		when(this.departmentServ.getAllDepartments()).thenReturn(new ArrayList<Department>());
		this.mockMvc.perform(get("/api/service")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentById() throws Exception {
//		Department departmentToReturn = new Department(2, "SIGP3");
//		when(this.departmentServ.getDepartmentById(2)).thenReturn(Optional.of(departmentToReturn));
		this.mockMvc.perform(get("/api/service/id=2")).andExpect(status().isOk())
			.andExpect(jsonPath("idDepartment").value(2))
			.andExpect(jsonPath("nameDepartment").value("SIGP3"));
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByIdServerError() throws Exception {
//		when(this.departmentServ.getDepartmentById(any())).thenReturn(null);
		this.mockMvc.perform(get("/api/service/id=9")).andExpect(status().isInternalServerError());
	}
	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getDepartmentByIdNotFound() throws Exception {
////		when(this.departmentServ.getDepartmentById(any())).thenReturn(Optional.of(null));
//		this.mockMvc.perform(get("/service/id=6")).andExpect(status().isNotFound());
//	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByName() throws Exception {
//		when(this.departmentServ.getDepartmentByName("test42")).thenReturn(new Department(16, "test42"));
		this.mockMvc.perform(get("/api/service/nom=Appli HR")).andExpect(status().isOk())
			.andExpect(jsonPath("idDepartment").value(3))
			.andExpect(jsonPath("nameDepartment").value("Appli HR"));
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByNameNotFound() throws Exception {
//		when(this.departmentServ.getDepartmentByName(any())).thenReturn(null);
		this.mockMvc.perform(get("/api/service/nom=absent")).andExpect(status().isNotFound());
	}
	
//	@Test
//	@WithMockUser(roles = { "ADMIN" })
//	public void getDepartmentByNameServerError() throws Exception {
////		when(this.departmentServ.getDepartmentByName(any())).thenReturn(null);
//		this.mockMvc.perform(get("/service/nom=")).andExpect(status().isInternalServerError());
//	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void addDepartment() throws Exception {
//		when(this.departmentServ.createDepartment(any())).thenReturn(new Department(5, "Test42"));
		this.mockMvc.perform(post("/api/service/ajout").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"nameDepartment\": \"Test42\"}"))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("nameDepartment").value("Test42"));
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void modifyDepartment() throws Exception {
//		when(this.departmentServ.createDepartment(any())).thenReturn(new Department(4, "SIGP3"));
//		when(this.departmentServ.updateDepartment(any(), any())).thenReturn(new Department(4, "TEST42"));
		this.mockMvc.perform(put("/api/service//modifid=4").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"idDepartment\": \"4\", \"nameDepartment\": \"Dev_HR\"}"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("idDepartment").value(4))
		.andExpect(jsonPath("nameDepartment").value("Dev_HR"));
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void deleteDepartment() throws Exception {
		this.mockMvc.perform(delete("/api/service/supprid=8")).andExpect(status().isOk());
	}
	
	
	


}


