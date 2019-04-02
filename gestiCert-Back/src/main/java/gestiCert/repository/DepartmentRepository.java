package gestiCert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestiCert.model.Department;
/**
 * ServiceRepository indique que la classe amelioree Service est un referentiel qui etend JpaRepository
 * 
 * @see Department
 * 
 * @author Samuel Sabot
 * @version 0.0
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
	
	/**
	 * utilise la methode findById du CrudRepository en utilisant le nom du service comme parametre
	 * 
	 * @param nameDepartment
	 * @return une liste de services
	 */
	
	public List<Department> findByNameDepartment(String nameDepartment);
	
}
