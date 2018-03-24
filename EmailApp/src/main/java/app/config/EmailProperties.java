package app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Ravi Nain on 3/10/2018.
 */
@ConfigurationProperties(prefix = "email.mail")
public class EmailProperties {

    private Smtp smtp;
    private Credential credential;
    private String attachmentFilePath;
    private String subject;
    private String body;
    private String attachmentPassword;

    public Smtp getSmtp() {
        return smtp;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

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

    public static class Smtp {
        private boolean starttls;
        private boolean auth;
        private String host;
        private String port;

        public boolean isStarttls() {
            return starttls;
        }

        public void setStarttls(boolean starttls) {
            this.starttls = starttls;
        }

        public boolean isAuth() {
            return auth;
        }

        public void setAuth(boolean auth) {
            this.auth = auth;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }

    public static class Credential {
        private String userName;
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
