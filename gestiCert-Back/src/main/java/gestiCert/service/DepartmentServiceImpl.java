package gestiCert.service;

import java.util.List;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Department;
import gestiCert.repository.DepartmentRepository;

/**
 * DepartmentServiceImpl contient les methodes CRUD de l'application pour l'entite Department
 * 
 * @see Department
 * @see DepartmentRepository
 * 
 * @author Utilisateur
 * @version 0.0
 */

@Service
public class DepartmentServiceImpl implements DepartmentService
{

	/**
	 * injection des dependances de DepartmentRepository
	 * 
	 * @see DepartmentRepository
	 */
	
	private DepartmentRepository departmentRepo;
	
	/**
	 * constructeur
	 * 
	 * @param departmentRepo
	 */
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepo)
	{
		super();
		this.departmentRepo = departmentRepo;
	}

	/**
	 * methode qui cherche tous les services
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public List<Department>getAllDepartments()
	{
		return departmentRepo.findAll();
	}
	
	/**
	 * methode qui cherche un service par son identifiant
	 * 
	 * @param idDepartment
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public Department getDepartmentById(Integer idDepartment)
	{
		return departmentRepo.findByIdDepartment(idDepartment);
	}

	/**
	 * methode qui cherche un service par son nom
	 * 
	 * @param nameDepartment
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public Department getDepartmentByName(String nameDepartment)
	{
		return departmentRepo.findByNameDepartment(nameDepartment);
	}
	
	/**
	 * methode qui ajoute un service
	 * 
	 * @param department
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public Department createDepartment(Department department)
	{
		return departmentRepo.saveAndFlush(department);
	}
	
	/**
	 * methode qui modifie un service
	 * 
	 * @param department
	 * @param idDepartment
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public Department updateDepartment(Department department, Integer idDepartment)
	{
		return departmentRepo.saveAndFlush(department);
	}
	
	
	/**
	 * methode qui supprime un service par son identifiant
	 * 
	 * @param idDepartment
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public void deleteDepartment(Integer idDepartment)
	{
		departmentRepo.deleteById(idDepartment);
	}
	
}
