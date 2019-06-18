package gestiCert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestiCert.model.Department;
import gestiCert.service.DepartmentService;

/**
 * DepartmentController route les requetes au DepartmentService
 * 
 * @see Department
 * @see DepartmentService
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@RestController
@RequestMapping("/api/service")
public class DepartmentController
{

	/**
	 * injection des dependances de DepartmentService
	 * 
	 * @see DepartmentService
	 */
	
	@Autowired
	private DepartmentService departmentServ;
	
	/**
	 * constructeur
	 * 
	 * @param departmentServ
	 */
	
	public DepartmentController(DepartmentService departmentServ)
	{
		super();
		this.departmentServ = departmentServ;
	}

	/**
	 * methodes qui gerent les methodes HTTP entrantes appropriees (GET, POST, PUT, DELETE) avec url
	 * 
	 * @param idDepartment
	 * @param nameDepartment
	 * @param department
	 * 
	 * @return
	 */
	
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<List<Department>> getAllDepartments()
	{
		List<Department> listDepartments = null;
		
		try
		{
			listDepartments = departmentServ.getAllDepartments();
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
	
	@GetMapping("/id={idDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getDepartmentById(@PathVariable Integer idDepartment)
	{
		Department department = null;
		
		try
		{
			department = departmentServ.getDepartmentById(idDepartment);
			System.out.println("1 " + department.getIdDepartment());
		} catch (Exception e)
		{
			System.out.println("2 " + department);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (department.getIdDepartment() == null)
		{
			System.out.println("3 " + department);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		System.out.println("4 " + department);
		return ResponseEntity.status(HttpStatus.OK).body(department);
	}
	
	@GetMapping("/nom={nameDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEV')")
	public ResponseEntity<?> getDepartmentByName(@PathVariable String nameDepartment)
	{
		Department department = null;
		
		try
		{
			department = departmentServ.getDepartmentByName(nameDepartment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (department == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(department);
	}
	
	@PostMapping("/ajout")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> postDepartment(@RequestBody Department department)
	{
		Department newDepartment = null;
		
		String nameDepartment = department.getNameDepartment();
		if ((nameDepartment == null) || (nameDepartment.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du service doit être renseigné");
		}
		
		try
		{
			newDepartment = departmentServ.createDepartment(department);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
	}
	
	@PutMapping("/modifid={idDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> putDepartment(@RequestBody Department department, @PathVariable Integer idDepartment)
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
			modifyDepartment = departmentServ.updateDepartment(department, idDepartment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyDepartment);
	}
	
	@DeleteMapping("/supprid={idDepartment}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteDepartment(@PathVariable Integer idDepartment)
	{
		try
		{
			departmentServ.deleteDepartment(idDepartment);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression : OK");
	}
	
}
