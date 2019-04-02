package gestiCert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gestiCert.model.Root;
import gestiCert.repository.RootRepository;

/**
 * RootServiceImpl contient les methodes CRUD de l'application pour l'entite Root
 * 
 * @see Root
 * @see RootRepository
 * 
 * @author Utilisateur
 * @version 0.0
 */

@Service
public class RootServiceImpl implements RootService
{
	
	/**
	 * injection des dependances de RootRepository
	 * 
	 * @see RootRepository
	 */

	private RootRepository rootRepo;

	/**
	 * constructeur
	 * 
	 * @param rootRepo
	 */
	
	public RootServiceImpl(RootRepository rootRepo)
	{
		super();
		this.rootRepo = rootRepo;
	}
	
	
	/**
	 * methode qui cherche toutes les racines
	 * 
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getAllRoots()
	{
		
		List<Root> listRoots = null;
		
		try
		{
			listRoots = rootRepo.findAll();
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		if (listRoots == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listRoots);
	}


	/**
	 * methode qui cherche une racine par son identifiant
	 * 
	 * @param idRoot
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getRootById(Integer idRoot)
	{

		Optional<Root> listRoots = null;
		
		try
		{
			listRoots = rootRepo.findById(idRoot);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listRoots == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listRoots);
	}
	
	/**
	 * methode qui cherche une racine par son son nom ou partie de nom
	 * 
	 * @param word
	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<?> getRootByName(String word)
	{
		List<Root> listRoots = null;
		
		try
		{
			listRoots = rootRepo.findByNameRoot(word);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (listRoots == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listRoots);
	}
	
	/**
	 * methode qui ajoute une racine
	 * 
	 * @param root
	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> createRoot(Root root)
	{
		Root newRoot = null;
		
		String nameRoot = root.getNameRoot();
		if ((nameRoot == null) || nameRoot.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la racine doit être renseigné");
		}
		
		try
		{
			newRoot = rootRepo.saveAndFlush(root);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newRoot);
	}
	
	/**
	 * methode qui modifie une racine
	 * 
	 * @param root
	 * @param idRoot
	 * @return modification ou code 404 (not found) ou code 400 (bad request)
	 */
	
	@Override
	public ResponseEntity<?> updateRoot(Root root, Integer idRoot)
	{
		Root modifyRoot = null;
		getRootById(idRoot);
		
		String nameRoot = root.getNameRoot();
		if ((nameRoot == null) || (nameRoot.isEmpty()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom de la racine doit être renseigné");
		}
		
		try
		{
			modifyRoot = rootRepo.saveAndFlush(root);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(modifyRoot);
	}
	
	/**
	 * methode qui supprime une racine par son identifiant
	 * 
	 * @param idRoot
	 * @return suppression ou code 404 (not found)
	 */
	
	@Override
	public ResponseEntity<String> deleteRoot(Integer idRoot)
	{
		 
		try
		{
			rootRepo.deleteById(idRoot);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
	}

}
