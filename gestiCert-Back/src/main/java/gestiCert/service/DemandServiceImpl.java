//package gestiCert.service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import gestiCert.model.Demand;
//import gestiCert.repository.DemandRepository;
//
///**
// * DemandServiceImpl contient les methodes CRUD de l'application pour l'entite Demand
// * 
// * @see Demand
// * @see DemandRepository
// * 
// * @author Samuel Sabot
// * @version 0.0
// */
//
//@Service
//public class DemandServiceImpl implements DemandService
//{
//	
//	/**
//	 * injection des dependances de DemandRepository
//	 * 
//	 * @see DemandRepository
//	 */
//
//	private DemandRepository demandRepo;
//
////	@Autowired
////    public JavaMailSender emailSender;
//	/**
//	 * constructeur
//	 * 
//	 * @param demandRepo
//	 */
//	
//	public DemandServiceImpl(DemandRepository demandRepo)
//	{
//		super();
//		this.demandRepo = demandRepo;
//	}
//	
//	/**
//	 * methode qui cherche tous les demandes
//	 * 
//	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
//	 */
//	
//	@Override
//	public ResponseEntity<?> getAllDemands()
//	{
//		
//		List<Demand> listDemands = null;
//		
//		try
//		{
//			listDemands = demandRepo.findAll();
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		}
//		
//		if (listDemands == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(listDemands);
//	}
//
//	/**
//	 * methode qui cherche une demande par son identifiant
//	 * 
//	 * @param idDemand
//	 * @return liste ou code 500 (internal server error) ou code 404 (not found)
//	 */
//	
//	@Override
//	public ResponseEntity<?> getDemandById(Integer idDemand)
//	{
//
//		Optional<Demand> listDemands = null;
//		
//		try
//		{
//			listDemands = demandRepo.findById(idDemand);
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		if (listDemands == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(listDemands);
//	}
//	
//	/**
//	 * methode qui ajoute une demande
//	 * 
//	 * @param demand
//	 * @return creation ou code 500 (internal server error) ou code 400 (bad request)
//	 */
//	
//	@Override
//	public ResponseEntity<?> createDemand(Demand demand)
//	{
//		Demand newDemand = null;
//		
//		Date dateDemand = demand.getDateDemand();
//		if (dateDemand == null)
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La date d'émission doit être renseigné");
//		}
//		
//		try
//		{
//			newDemand = demandRepo.saveAndFlush(demand);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(newDemand);
//	}
//	
//	/**
//	 * methode qui modifie une demande
//	 * 
//	 * @param demand
//	 * @param idDemand
//	 * @return modification ou code 404 (not found) ou code 400 (bad request)
//	 */
//	
//	@Override
//	public ResponseEntity<?> updateDemand(Demand demand, Integer idDemand)
//	{
//		Demand modifyDemand = null;
//		getDemandById(idDemand);
//		
//		Date dateDemand = demand.getDateDemand();
//		if (dateDemand == null)
//		{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La date d'émission doit être renseigné");
//		}
//		
//		try
//		{
//			modifyDemand = demandRepo.saveAndFlush(demand);
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(modifyDemand);
//	}
//	
//	/**
//	 * methode qui supprime un environnement par son identifiant
//	 * 
//	 * @param idDemand
//	 * @return suppression ou code 404 (not found)
//	 */
//	
//	@Override
//	public ResponseEntity<String> deleteDemand(Integer idDemand)
//	{
//		 
//		try
//		{
//			demandRepo.deleteById(idDemand);
//		} catch (Exception e)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body("Suppression OK");
//	}
//	
////	@Override
////	public Object sendHtmlEmail(Demand demand) throws MessagingException
////    {
////    MimeMessage message = emailSender.createMimeMessage();
////    boolean multipart = true;
////    MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
////    StringBuffer htmlMsg= new StringBuffer();
////    htmlMsg.append("<h1>Test envoi mail</h1>");
////    htmlMsg.append("<h2>"+demand.getIdDemand()+"</h2>");
////    htmlMsg.append("<h3>"+demand.getApplication().getNameApplication()+"</h3>");
////    htmlMsg.append("<h4>Nom Demandeur : "+demand.getUser().getNameUser()+"</h4>");
////    message.setContent(htmlMsg.toString(), "text/html"); // on précise le format HTML
////    helper.setTo(MailConfig.OTHER_EMAIL);
////    helper.setSubject("Essai 1 : Envoyer un email avec du HTML + image");
////    this.emailSender.send(message);
////	return htmlMsg;
////    }
//
//}
