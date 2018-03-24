package app.service;

import app.config.EmailProperties;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Created by Ravi Nain on 3/10/2018.
 */
@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailProperties emailProperties;
    private final Properties smtpProperties;
    private final ZipService zipService;
    private final Authenticator authenticator;
    private long prevLastModified = 0;

    @Autowired
    public EmailService(EmailProperties emailProperties,
                        Properties smtpProperties,
                        ZipService zipService,
                        Authenticator authenticator) {
        this.emailProperties = emailProperties;
        this.smtpProperties = smtpProperties;
        this.zipService = zipService;
        this.authenticator = authenticator;
    }

    public void sendEmail() {
        EmailProperties.Credential credential = emailProperties.getCredential();
        final Session session = Session.getInstance(smtpProperties, authenticator);

        final Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(credential.getUserName()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(credential.getUserName()));

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailProperties.getBody());

            BodyPart attachmentBodyPart = getAttachment();

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setSubject(emailProperties.getSubject());
            message.setContent(multipart);

            Transport.send(message);
            logger.info("Email sent successfully");
        } catch (MessagingException | ZipException e) {
            logger.error("Sending email failed: ", e);
        }
    }

    private BodyPart getAttachment() throws MessagingException, ZipException {
        BodyPart messageBodyPart = new MimeBodyPart();
        zipService.zipAndAddProtection(emailProperties.getAttachmentFilePath());
        String zipFileName = zipService.getZipFilePath(emailProperties.getAttachmentFilePath());
        DataSource source = new FileDataSource(zipFileName);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(source.getName());

        return messageBodyPart;
    }

//    @Scheduled(cron = "${email.emailCron}")
    @PostConstruct
    public void triggerSendEmail() {
        File file = new File(emailProperties.getAttachmentFilePath());
        if (file.exists() && prevLastModified != file.lastModified()) {
            this.sendEmail();
            prevLastModified = file.lastModified();
        } else {
            logger.info("File has not changed!");
        }
    }

}
