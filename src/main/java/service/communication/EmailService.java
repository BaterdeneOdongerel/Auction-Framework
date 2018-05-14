package service.communication;

import props.EmailProp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/14/18.
 */
public class EmailService extends BaseCommunicationService {

    EmailProp prop = EmailProp.INSTANCE;

    @Override
    void _send(Option option, CommunicationType type) {
        final String username = prop.getProp("from");
        final String password = prop.getProp("password");
        Properties props = new Properties();
        String authen = "mail.smtp.auth";
        String enable = "mail.smtp.starttls.enable";
        String host = "mail.smtp.host";
        String port = "mail.smtp.port";

        String[] params = new String[]{authen, enable, host, port};
        for (String key : params) {
            props.put(key, prop.getProp(key));
        }

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(option.getTo()));
            message.setSubject(option.getSubject());
            message.setText(option.getContent());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
