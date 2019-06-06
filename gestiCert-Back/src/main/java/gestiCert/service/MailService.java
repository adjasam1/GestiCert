package gestiCert.service;

import javax.mail.MessagingException;

import gestiCert.model.Certificate;

public interface MailService {

	Object sendHtmlEmail(Certificate certificate) throws MessagingException;

}



