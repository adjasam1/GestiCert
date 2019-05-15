package gestiCert.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailHTMLController {
	
	@Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public String sendHtmlEmail() throws MessagingException {
 
    MimeMessage message = emailSender.createMimeMessage();
    boolean multipart = true;
    MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
    StringBuffer htmlMsg= new StringBuffer();
    htmlMsg.append("<h1>Email avec les balises HTML</h1>");
    htmlMsg.append("<h2>Vanessa Paradis</h2>");
    htmlMsg.append("<img alt='Portrait de Vanessa Paradis - Agnès Lanchon' src='https://numerosoft.fr/caricatures/vanessaparadis250x354.jpg'>");
    message.setContent(htmlMsg.toString(), "text/html"); // on précise le format HTML
    helper.setTo(MailConfig.OTHER_EMAIL);
    helper.setSubject("Test : Envoyer un email avec du HTML + image");
    this.emailSender.send(message);
    return "Email envoyé !";
    }


}
