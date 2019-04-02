package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Department>> getAllDepartments()
	{
		List<Department> listDepartments = null;
		
		try
		{
			listDepartments = departmentRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listDepartments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listDepartments);
	}
	
	/**
	 * methode qui cherche un service par son identifiant
	 * 
	 * @param idDepartment
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getDepartmentById(Integer idDepartment)
	{
		Optional<Department> listDepartments = null;
		
		try
		{
			listDepartments = departmentRepo.findById(idDepartment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listDepartments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listDepartments);
	}

	/**
	 * methode qui cherche un service par son nom
	 * 
	 * @param nameDepartment
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getDepartmentByName(String nameDepartment)
	{
		List<Department> listDepartments = null;
		
		try
		{
			listDepartments = departmentRepo.findByNameDepartment(nameDepartment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listDepartments == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listDepartments);
	}
	
	/**
	 * methode qui ajoute un service
	 * 
	 * @param department
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createDepartment(Department department)
	{
		Department newDepartment = null;
		
		String nameDepartment = department.getNameDepartment();
		if ((nameDepartment == null) || (nameDepartment.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du service doit être renseigné");
		}
		
		try
		{
			newDepartment = departmentRepo.saveAndFlush(department);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
	}
	
	/**
	 * methode qui modifie un service
	 * 
	 * @param department
	 * @param idDepartment
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateDepartment(Department department, Integer idDepartment)
	{
		Department modifyDepartment = null;
		getDepartmentById(idDepartment);
		
		String nameDepartment = department.getNameDepartment();
		if ((nameDepartment == null) || (nameDepartment.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du service doit être renseigné");
		}
		
		try
		{
			modifyDepartment = departmentRepo.saveAndFlush(department);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyDepartment);
	}
	
	
	/**
	 * methode qui supprime un service par son identifiant
	 * 
	 * @param idDepartment
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> deleteDepartment(Integer idDepartment)
	{
		try
		{
			departmentRepo.deleteById(idDepartment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
	}
	
}
