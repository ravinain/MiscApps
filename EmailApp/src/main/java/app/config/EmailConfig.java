package app.config;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

/**
 * Created by Ravi Nain on 3/11/2018.
 */
@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public Properties smtpProperties() {
        EmailProperties.Smtp smtp = emailProperties.getSmtp();
        EmailProperties.Credential credential = emailProperties.getCredential();

        final Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", smtp.isStarttls());
        props.put("mail.smtp.auth", smtp.isAuth());
        props.put("mail.smtp.host", smtp.getHost());
        props.put("mail.smtp.port", smtp.getPort());

        return props;
    }

    @Bean
    public String tempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }

    @Bean
    public Authenticator authenticator() {
        EmailProperties.Credential credential = emailProperties.getCredential();

        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        credential.getUserName(),
                        credential.getPassword());
            }
        };
    }

    @Bean
    public ZipParameters zipParameters() {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters.setPassword(emailProperties.getAttachmentPassword());

        return parameters;
    }
}
