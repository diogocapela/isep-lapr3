package lapr.project.data;

import lapr.project.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class DataUtils {

    public DataUtils() {

    }

    /**
     * Send email notification to user email
     *
     * @param sender  : the user that is requesting notification
     * @param body    : body text of email
     * @param subject : subject text of email
     * @return : false if failed or true otherwise.
     */
    public boolean sendEmailNotification(User sender, String body, String subject) {
        String host = "mail.pushdword.pt";
        String user = "lapr3_g045";
        String pw = "kBs5[iQrHjaK";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.ssl.trust", "mail.pushdword.pt");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pw);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("lapr3_g045@pushdword.pt"));
            message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(sender.getEmail()));
            message.setSubject(subject);

            String msg = body;

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException me) {
            return false;
        }
        return true;
    }

}
