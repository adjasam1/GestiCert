package gestiCert.service;

import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import gestiCert.mail.MailConfig;
import gestiCert.model.Certificate;

@Service
public class MailServiceImpl implements MailService
{
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Override
	public Object sendHtmlEmail(Certificate certificate) throws MessagingException
    {
    MimeMessage message = emailSender.createMimeMessage();
    boolean multipart = true;
    MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf8");
    StringBuffer htmlMsg= new StringBuffer();
    SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
    htmlMsg.append("<head>" + 
    				"	<style type=\"text/css\">" + 
    				"		h1 {text-align: center;}" +
    				"		h1, table, tr {border: 1px solid black; border-collapse: collapse;}" +
    				"		h1, h2, table {width: 700px; font-size: 20px;}" +
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
    				"			<td class=\"td2\">"+certificate.getUser().getDepartment()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Nom du demandeur : </strong></td>" + 
    				"			<td class=\"td2\">"+certificate.getUser().getNameUser()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Prenom du demandeur : </strong></td>" + 
    				"			<td class=\"td2\">"+certificate.getUser().getFirstNameUser()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Fonction du demandeur : </strong></td>" + 
    				"			<td class=\"td2\">"+certificate.getUser().getProfile().getTypeProfile()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Telephone du demandeur : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getUser().getPhoneUser()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>E-mail du demandeur : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getUser().geteMailUser()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>E-mail du postier referent : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.geteMailReferent()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Nom du client : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getApplication().getNameClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Prenom du client : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getApplication().getFirstNameClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Direction du client : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getApplication().getManagementClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Telephone du client : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getApplication().getPhoneClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>E-mail du client : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getApplication().geteMailClient()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Date de la demande : </strong></td>" + 
    				"           <td class=\"td2\">"+formDate.format(certificate.getDateDemand())+"</td>" +
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Transmis le : </strong></td>" + 
    				"           <td class=\"td2\">"+formDate.format(certificate.getDateTransmission())+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Date de realisation souhaitee : </strong></td>" + 
    				"           <td class=\"td2\">"+formDate.format(certificate.getDateCreationDesired())+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Description du contexte : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getDescriptionContext()+"</td>" + 
    				"       </tr>" + 
    				"    </table>" +   
    				"	<h2>2. INFORMATION SUR LA DEMANDE</h2>" +
    				"	<table>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Demande : </strong></td>" + 
    				"			<td class=\"td2\">"+certificate.getTypeDemand().getTypeTypeDemand()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Code CCX : </strong></td>" + 
    				"			<td class=\"td2\">"+certificate.getApplication().getCodeCCX()+"</td>" + 
    				"		</tr>" + 
    				"		<tr>" + 
    				"			<td class=\"td1\"><strong>Nom commun </strong><br><em>\"URL Principale du site\"</em> : </td>" + 
    				"			<td class=\"td2\">"+certificate.getLinkAddressPrincipal()+"</td>" + 
    				"		</tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Plateforme : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getPlateform().getNamePlateform()+"</td>" + 
    				"       </tr>" + 
    				"       <tr>" + 
    				"           <td class=\"td1\"><strong>Serveur(s) : </strong></td>" + 
    				"           <td class=\"td2\">"+certificate.getServers().toString()+"</td>" + 
    				"       </tr>" + 
    				"    </table>" + 
    				"</section>" + 
    				"" +  
    				"</body>");
    message.setContent(htmlMsg.toString(), "text/html"); // on précise le format HTML
    helper.setTo(MailConfig.OTHER_EMAIL);
    helper.setSubject("Demande du certificat SSL : " +certificate.getNameCertificate());
    this.emailSender.send(message);
	return htmlMsg;
    }
}
