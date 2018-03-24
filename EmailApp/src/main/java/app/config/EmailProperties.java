package app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Ravi Nain on 3/10/2018.
 */
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private String from;
    private String to;
    private String attachmentFilePath;
    private String subject;
    private String body;
    private String attachmentPassword;

    public String getAttachmentFilePath() {
        return attachmentFilePath;
    }

    public void setAttachmentFilePath(String attachmentFilePath) {
        this.attachmentFilePath = attachmentFilePath;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachmentPassword() {
        return attachmentPassword;
    }

    public void setAttachmentPassword(String attachmentPassword) {
        this.attachmentPassword = attachmentPassword;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
