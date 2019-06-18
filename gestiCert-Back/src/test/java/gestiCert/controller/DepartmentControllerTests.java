package gestiCert.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
		this.mockMvc.perform(get("/api/service")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentById() throws Exception {
		this.mockMvc.perform(get("/api/service/id=2")).andExpect(status().isOk())
			.andExpect(jsonPath("idDepartment").value(2))
			.andExpect(jsonPath("nameDepartment").value("SIGP3"))
			.andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByIdServerError() throws Exception {
		this.mockMvc.perform(get("/api/service/id=9")).andExpect(status().isInternalServerError()).andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByIdNotFound() throws Exception {
		this.mockMvc.perform(get("/service/id=6")).andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByName() throws Exception {
		this.mockMvc.perform(get("/api/service/nom=Appli")).andExpect(status().isOk())
			.andExpect(jsonPath("idDepartment").value(3))
			.andExpect(jsonPath("nameDepartment").value("Appli"))
			.andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void getDepartmentByNameNotFound() throws Exception {
		this.mockMvc.perform(get("/api/service/nom=absent")).andExpect(status().isNotFound()).andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void addDepartment() throws Exception {
		this.mockMvc.perform(post("/api/service/ajout").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"nameDepartment\": \"Ajout23\"}"))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("nameDepartment").value("Ajout23"))
		.andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void modifyDepartment() throws Exception {
		this.mockMvc.perform(put("/api/service//modifid=14").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"idDepartment\": \"14\", \"nameDepartment\": \"HR\"}"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("idDepartment").value(14))
		.andExpect(jsonPath("nameDepartment").value("HR"))
		.andDo(print());
	}
	
	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void deleteDepartment() throws Exception {
		this.mockMvc.perform(delete("/api/service/supprid=22")).andExpect(status().isOk()).andDo(print());
	}

}


