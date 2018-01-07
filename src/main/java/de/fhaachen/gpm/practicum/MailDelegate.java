package de.fhaachen.gpm.practicum;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Logger;

public class MailDelegate implements JavaDelegate {
    protected final Logger LOGGER = Logger.getLogger(RestConnectorBase.class.getName());

    public void execute(DelegateExecution execution) throws Exception {
        String emailSender = (String)execution.getVariable("EmailSenderAdress");
        String emailPassword = (String)execution.getVariable("EmailSenderPassword");
        String subject = (String)execution.getVariable("EmailSubject");
        String body = (String)execution.getVariable("EmailBody");
        String receiver = (String)execution.getVariable("EmailReceiver");
        String cc = (String)execution.getVariable("EmailCC");
        String attachement = (String) execution.getVariable("Attachement");
        LOGGER.info("Send Mail");
        LOGGER.info("emailSender: " + emailSender);
        LOGGER.info("emailPassword: " + emailPassword);
        LOGGER.info("subject: " + subject);
        LOGGER.info("body: " + body);
        LOGGER.info("receiver: " + receiver);
        LOGGER.info("cc: " + cc);
        if (attachement != null && !attachement.isEmpty()) {
            LOGGER.info("Attachement: " + attachement);
        } else {
            LOGGER.info("No attachement set");
        }
        generateAndSendEmail(emailSender, emailPassword, subject, body, receiver, cc, attachement);
    }

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void generateAndSendEmail(String sender, String password, String subject, String body, String receiver, String cc, String attachement) throws AddressException, MessagingException {
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

        if (attachement != null && !attachement.isEmpty()) {
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachement);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachement);
            multipart.addBodyPart(messageBodyPart);
            generateMailMessage.setContent(multipart);
        }

        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", sender, password);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
