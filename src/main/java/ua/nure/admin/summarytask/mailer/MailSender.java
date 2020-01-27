package ua.nure.admin.summarytask.mailer;

import org.apache.log4j.Logger;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailSender {

    private static final Logger log = Logger.getLogger(MailSender.class);

    public static void send(String login, String password, String receiver) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/mail.properties"));

        try {
            Session session = Session.getDefaultInstance(properties, new MailAuthenticator(properties.getProperty("mail.smtp.user"),
                    properties.getProperty("mail.smtp.password")));
            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress(properties.getProperty("mail.smtp.user"));
            Address toAddress = new InternetAddress(receiver);

            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Registration");
            message.setText("You have been registered at the medical center!\nLogin: " + login + "\nPassword: "
                    + password);
            Transport transport = session.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"), properties.getProperty("mail.smtp.user"),
                    properties.getProperty("mail.smtp.password"));
            message.saveChanges();
            Transport.send(message);
            transport.close();
            log.info("Mail send succesfully");
        } catch (MessagingException e) {
            log.error("Cannot send mail", e);
        }
    }
}
