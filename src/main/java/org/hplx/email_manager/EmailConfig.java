package org.hplx.email_manager;


import org.hplx.config_manager.ConfigFactory;
import org.hplx.constants.FrameworkConstants;

public class EmailConfig {
//        public static final String SERVER = "smtp.gmail.com";
//        public static final String PORT = "587";
//        public static final String FROM = "automation@healthplix.com";
//        public static final String PASSWORD = "hplx@123";
//        public static final String[] TO = ConfigFactory.getConfig().email_user_to();
//        public static final String SUBJECT = FrameworkConstants.REPORT_SUBJECT;
//
//}
        private final String server;
        private final String port;
        private final String from;
        private final String password;
        private final String[] to;
        private final String subject;

    public EmailConfig(String server, String port, String from, String password, String[] to, String subject) {
        this.server = server;
        this.port = port;
        this.from = from;
        this.password = password;
        this.to = to;
        this.subject = subject;
    }

    public String getServer() {
        return server;
    }

    public String getPort() {
        return port;
    }

    public String getFrom() {
        return from;
    }

    public String getPassword() {
        return password;
    }

    public String[] getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public static EmailConfig createDefaultConfig() {
        String server = "smtp.gmail.com";
        String port = "587";
        String from = "automation@healthplix.com";
        String password = "hplx@123";
        String[] to = ConfigFactory.getConfig().email_user_to();
        String subject = FrameworkConstants.REPORT_SUBJECT;

        return new EmailConfig(server, port, from, password, to, subject);
    }
}