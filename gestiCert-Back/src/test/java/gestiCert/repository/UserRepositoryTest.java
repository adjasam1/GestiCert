//package gestiCert.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import gestiCert.model.AppUser;
//import gestiCert.model.Department;
//import gestiCert.model.Profile;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserRepositoryTest {
//	
//	@Autowired
//	private AppUserRepository appUserRepo;
//	
//	@Autowired
//	private TestEntityManager testEntityManager;
//	
//	@Test
//	public void findByUserByName() throws Exception {
//		AppUser savedUser = testEntityManager.persistFlushFind(
//				new AppUser(
//						"pyyy000", "yyy", "Y", "Y", "y.y@laposte.fr", "0612344321", new Department(3, "Appli"), new Profile(1, "Développeur(se)")));
//		AppUser y = this.appUserRepo.findByName("Y");
//		assertThat(y.getIdRHUser()).isEqualTo(savedUser.getIdRHUser());
//		assertThat(y.getPasswordUser()).isEqualTo(savedUser.getPasswordUser());
//		assertThat(y.getNameUser()).isEqualTo(savedUser.getNameUser());
//		assertThat(y.getFirstNameUser()).isEqualTo(savedUser.getFirstNameUser());
//		assertThat(y.getEMailUser()).isEqualTo(savedUser.getEMailUser());
//		assertThat(y.getPhoneUser()).isEqualTo(savedUser.getPhoneUser());
//		assertThat(y.getDepartment().getIdDepartment()).isEqualTo(savedUser.getDepartment().getIdDepartment());
//		assertThat(y.getDepartment().getNameDepartment()).isEqualTo(savedUser.getDepartment().getNameDepartment());
//		assertThat(y.getProfile().getIdProfile()).isEqualTo(savedUser.getProfile().getIdProfile());
//		assertThat(y.getProfile().getTypeProfile()).isEqualTo(savedUser.getProfile().getTypeProfile());
//	}
//
//}
//
