package gestiCert.service;

import java.util.List;

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

	public List<Department> getAllDepartments();
	
	public Department getDepartmentById(Integer idDepartment);

	public Department getDepartmentByName(String nameDepartment);

	public Department createDepartment(Department department);

	public Department updateDepartment(Department department, Integer idDepartment);

	public void deleteDepartment(Integer idDepartment);

}
