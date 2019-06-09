package gestiCert.service;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Application;

public interface ApplicationService
{

	ResponseEntity<?> getAllApplications();

	ResponseEntity<?> getApplicationById(Integer idApplication);

	ResponseEntity<?> getApplicationByCCX(String codeCCX);

	ResponseEntity<?> getApplicationByName(String nameApplication);

	ResponseEntity<?> createApplication(Application application);

	ResponseEntity<?> updateApplication(Application application, Integer idApplication);

	ResponseEntity<String> deleteApplication(Integer idApplication);

//	List<Application> getApplicationByUser(Integer idRHUser);

//	List<Application> getApplicationByUser(AppUser users);

//	List<Application> getApplicationByUser(List<AppUser> users);

//	List<Application> getApplicationByOneUser(AppUser user);

//	ResponseEntity<?> getApplicationsByUser(AppUser user);

}
