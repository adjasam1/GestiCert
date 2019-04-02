package gestiCert.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import gestiCert.model.Profile;
import gestiCert.service.ProfileService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProfileControllerTests
{

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProfileService profileServ;
	
//	@Test
//	public void getAllProfiles() throws Exception
//	{
//		when(this.profileServ.getAllProfiles()).thenReturn(new ArrayList<>());
//		
//		this.mockMvc.perform(get("/profil/tous")).andExpect(status().isOk());
//	}
	
}
