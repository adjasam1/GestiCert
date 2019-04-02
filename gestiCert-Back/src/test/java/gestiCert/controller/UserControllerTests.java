package gestiCert.controller;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import gestiCert.model.User;
import gestiCert.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private UserService userServ;
	
	@Test
	public void getAllUsers() throws Exception {
//		when(this.userServ.getAllUsers()).thenReturn(new ArrayList<User>());
//		
//		this.mockMvc.perform(get("/utilisateur")).andExpect(status().isOk());
	}

//	@Test
//	public void getUserById() throws Exception {
//		when(this.userServ.getUserById(2)).thenReturn(new User(2, "Yemen", "Béatrice", "cheffe de projet Dev Web", "beatrice.yemen@laposte.fr", "0659527564", "poej968", "bbbbbbbbbbb", 2, 1, [1]));
//
//		this.mockMvc.perform(get("/utilisateur/id=2")).andExpect(status().isOk())
//			.andExpect(jsonPath("idUser").value(2))
//			.andExpect(jsonPath("nameUser").value("Yemen"))
//			.andExpect(jsonPath("firstNameUser").value("Béatrice"))
//			.andExpect(jsonPath("roleUser").value("cheffe de projet Dev Web"))
//			.andExpect(jsonPath("eMailUser").value("beatrice.yemen@laposte.fr"))
//			.andExpect(jsonPath("phoneUser").value("0659527564"))
//			.andExpect(jsonPath("idRHUser").value("poej968"))
//			.andExpect(jsonPath("passwordUser").value("bbbbbbbbbbb"))
//			.andExpect(jsonPath("profile").value(2))
//			.andExpect(jsonPath("department").value(1))
//			.andExpect(jsonPath("applications").value([1]));
//	}
//	
//	@Test
//	public void getUserByName() throws Exception {
//		when(this.userServ.getUserByName("Yemen")).thenReturn(new User("Yemen", "Béatrice", "cheffe de projet Dev Web", "beatrice.yemen@laposte.fr", "0659527564", "poej968", "bbbbbbbbbbb", 2, 1));
//	}
	
}
