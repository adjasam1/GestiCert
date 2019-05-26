package gestiCert.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import gestiCert.mail.MailConfig;
import gestiCert.model.AddressAlternative;
import gestiCert.model.Demand;

@Service
public class MailServiceImpl implements MailService
{
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Override
	public Object sendHtmlEmail(Demand demand) throws MessagingException
    {
    MimeMessage message = emailSender.createMimeMessage();
    boolean multipart = true;
    MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf8");
    StringBuffer htmlMsg= new StringBuffer();
    SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
    htmlMsg.append("<head>" + 
    				"	<style type=\"text/css\">" + 
    				"		h1{text-align: center;}" +
    				"		h1, table, tr {border: 1px solid black; border-collapse: collapse;}" +
    				"		table, h2{width: 700px; font-size: 20px;}" +
    				"		h2 {border: 1px solid black; background-color: black; color: white; text-align: left; padding: 3px 10px;}" +
    				"		tr {height: 30px;}" + 
    				"		.td1 {text-align: right; min-width: 300px;}" + 
    				"		.td2 {text-align: left; padding-left: 20px;}" + 
    				"	</style>" + 
    				"</head>" + 
    				"" + 
    				"<body>" + 
    				"" + 
    				"<section>" + 
    				"	<h1>DASU - DEMANDE DE CERTIFICAT SSL</h1>" +  
    				"	<h2>1. IDENTIFICATION DE LA DEMANDE</h2>" + 
    				"	<table>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Service : </strong></td>" + 
    				"			<td class=\"td2\">"+demand.getUser().getDepartment().getNameDepartment()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Nom du demandeur : </strong></td>" + 
    				"			<td class=\"td2\">"+demand.getUser().getNameUser()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Prénom du demandeur : </strong></td>" + 
    				"			<td class=\"td2\">"+demand.getUser().getFirstNameUser()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Fonction du demandeur : </strong></td>" + 
    				"			<td class=\"td2\">"+demand.getUser().getProfile().getTypeProfile()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Téléphone du demandeur : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getUser().getPhoneUser()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>E-mail du demandeur : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getUser().geteMailUser()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>E-mail du postier référent : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.geteMailReferent()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Nom du client : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getApplication().getNameClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Prénom du client : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getApplication().getFirstNameClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Direction du client : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getUser().getNameUser()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Téléphone du client : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getApplication().getPhoneClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>E-mail du client : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getApplication().geteMailClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Date de la demande : </strong></td>" + 
    				"           <td class=\"td2\">"+formDate.format(demand.getDateDemand())+"</td>" +
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Transmis le : </strong></td>" + 
    				"           <td class=\"td2\">"+formDate.format(demand.getDateTransmission())+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Date de réalisation souhaitée : </strong></td>" + 
    				"           <td class=\"td2\">"+formDate.format(demand.getDateCreationDesired())+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Description du contexte : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getDescriptionContext()+"</td>" + 
    				"       </tr>" + 
    				"    </table>" +   
    				"	<h2>2. INFORMATION SUR LA DEMANDE</h2>" +
    				"	<table>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Demande : </strong></td>" + 
    				"			<td class=\"td2\">"+demand.getTypeDemand().getTypeTypeDemand()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Code CCX : </strong></td>" + 
    				"			<td class=\"td2\">"+demand.getApplication().getCodeCCX()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Nom commun </strong><br><em>\"URL Principale du site\"</em> : </td>" + 
    				"			<td class=\"td2\">"+demand.getCertificate().getLinkAddressPrincipal()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Adresse(s) alternative(s) </strong><br>" +
    				"				<em>\"URL(s) supplémentaire(s)</em> : </td>" + 
    				"			<td class=\"td2\">"+demand.getCertificate().getAddressAlternatives()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Plateforme : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getCertificate().getPlateform().getNamePlateform()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Serveur(s) : </strong></td>" + 
    				"           <td class=\"td2\">"+demand.getCertificate().getServers().toString()+"</td>" + 
    				"       </tr>" + 
    				"    </table>" + 
    				"</section>" + 
    				"" +  
    				"</body>");
    message.setContent(htmlMsg.toString(), "text/html"); // on précise le format HTML
    helper.setTo(MailConfig.OTHER_EMAIL);
    helper.setSubject("Demande du certificat SSL : " +demand.getCertificate().getNameCertificate());
    this.emailSender.send(message);
	return htmlMsg;
    }

}
