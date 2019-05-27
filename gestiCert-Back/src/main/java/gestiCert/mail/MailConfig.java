package gestiCert.mail;


import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
    public static final String MY_EMAIL = "samuel.sabot.lp2@gmail.com";
    public static final String MY_PASSWORD = "s1plon4mail";
    public static final String OTHER_EMAIL = "samuel.sabot@laposte.fr";

	  @Bean
	  public JavaMailSender getJavaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	 
	        mailSender.setUsername(MailConfig.MY_EMAIL);
	        mailSender.setPassword(MailConfig.MY_PASSWORD);
	 
	        Properties props = mailSender.getJavaMailProperties();
	//        props.put("mail.smtp.host", "smtp.gmail.com");
	//        props.put("mail.smtp.port", "25");
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	 
	        return mailSender;
	    }

}
