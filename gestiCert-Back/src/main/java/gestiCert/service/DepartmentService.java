package gestiCert.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import gestiCert.model.Department;

/**
 * 
 * 
 * @see Department
 * 
 * @author Utilisateur
 * @version 0.0
 */
public interface DepartmentService
{

	public ResponseEntity<List<Department>> getAllDepartments();
	
	public ResponseEntity<?> getDepartmentById(Integer idDepartment);

	public ResponseEntity<?> getDepartmentByName(String nameDepartment);

	public ResponseEntity<?> createDepartment(Department department);

	public ResponseEntity<?> updateDepartment(Department department, Integer idDepartment);

	public ResponseEntity<?> deleteDepartment(Integer idDepartment);

}
