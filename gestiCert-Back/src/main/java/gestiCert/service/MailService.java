package gestiCert.service;

import javax.mail.MessagingException;

import gestiCert.model.Demand;

public interface MailService {

	Object sendHtmlEmail(Demand demand) throws MessagingException;

}
