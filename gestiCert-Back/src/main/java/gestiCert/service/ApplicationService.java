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

}
