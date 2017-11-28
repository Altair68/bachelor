package de.fhaachen.gpm.practicum;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) throws Exception {
        String emailSender = (String)execution.getVariable("EmailSenderAdress");
        String emailPassword = (String)execution.getVariable("EmailSenderPassword");
        String subject = (String)execution.getVariable("EmailSubject");
        String body = (String)execution.getVariable("EmailBody");
        String receiver = (String)execution.getVariable("EmailReceiver");
        String cc = (String)execution.getVariable("EmailCC");
        generateAndSendEmail(emailSender, emailPassword, subject, body, receiver, cc);
    }

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void generateAndSendEmail(String sender, String password, String subject, String body, String receiver, String cc) throws AddressException, MessagingException {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(body, "text/html");

        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", sender, password);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
