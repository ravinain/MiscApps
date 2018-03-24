package app.service;

import app.config.EmailProperties;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by Ravi Nain on 3/10/2018.
 */
@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailProperties emailProperties;
    private final ZipService zipService;
    private final JavaMailSender emailSender;
    private long prevLastModified = 0;

    @Autowired
    public EmailService(EmailProperties emailProperties,
                        ZipService zipService,
                        JavaMailSender emailSender) {
        this.emailProperties = emailProperties;
        this.zipService = zipService;
        this.emailSender = emailSender;
    }

    public void sendEmail() {
        final MimeMessage message = emailSender.createMimeMessage();

        try {
            final DataSource attachment = new FileDataSource(getAttachmentPath());
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailProperties.getFrom());
            helper.setTo(emailProperties.getTo());
            helper.setSubject(emailProperties.getSubject());
            helper.setText(emailProperties.getBody());

            helper.addAttachment(attachment.getName(), attachment);

            emailSender.send(message);
            logger.info("Email sent successfully");
        } catch (MessagingException | ZipException e) {
            logger.error("Sending email failed: ", e);
        }
    }

    private String getAttachmentPath() throws ZipException {
        zipService.zipAndAddProtection(emailProperties.getAttachmentFilePath());
        return zipService.getZipFilePath(emailProperties.getAttachmentFilePath());
    }

    @Scheduled(cron = "${email.emailCron}")
    @PostConstruct
    public void triggerSendEmail() {
        File file = new File(emailProperties.getAttachmentFilePath());

        if (!file.exists()) {
            logger.warn("File does not exists: {}", file.getAbsolutePath());
        } else if (prevLastModified != file.lastModified()) {
            this.sendEmail();
            prevLastModified = file.lastModified();
        } else {
            logger.info("File has not changed!");
        }
    }

}
