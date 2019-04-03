//package gestiCert.repository;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import gestiCert.model.Application;
//import gestiCert.model.Profile;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ProfileRepositoryTests
//{
//
//	@Autowired
//	private ProfileRepository profileRepo;
//	
//	@Autowired
//	private TestEntityManager testEntityManager;
//	
//	public void findByTypeProfile() throws Exception
//	{
//		Profile savedProfile = testEntityManager.persistFlushFind(new Profile(5, "Profil 1", new Application()));
//		Profile profile1 = this.profileRepo.findByTypeProfile("Profile 1");
//	}
//	
//}
