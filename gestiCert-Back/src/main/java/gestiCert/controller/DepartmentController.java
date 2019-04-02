package gestiCert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/service")
@CrossOrigin("http://localhost:4200")
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
	public ResponseEntity<List<Department>> getAllDepartments()
	{
		return departmentServ.getAllDepartments();
	}
	
	@GetMapping("/id={idDepartment}")
	public ResponseEntity<?> getDepartmentById(@PathVariable Integer idDepartment)
	{
		return departmentServ.getDepartmentById(idDepartment);
	}
	
	@GetMapping("/nom={nameDepartment}")
	public ResponseEntity<?> getDepartmentByName(@PathVariable String nameDepartment)
	{
		return departmentServ.getDepartmentByName(nameDepartment);
	}
	
	@PostMapping("/ajout")
	public ResponseEntity<?> postDepartment(@RequestBody Department department)
	{
		return departmentServ.createDepartment(department);
	}
	
	@PutMapping("/modifid={idDepartment}")
	public ResponseEntity<?> putDepartment(@RequestBody Department department, @PathVariable Integer idDepartment)
	{
		return departmentServ.updateDepartment(department, idDepartment);
	}
	
	@DeleteMapping("/supprid={idDepartment}")
	public ResponseEntity<?> deleteDepartment(@PathVariable Integer idDepartment)
	{
		return departmentServ.deleteDepartment(idDepartment);
	}
	
}
