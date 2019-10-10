package top.mao196.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 装配自定义配置类
 * @author susanbushisan
 */
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {

    private String rootSecret;
    private String sendEmailName;
    private String projectName;
    private String sendEmailAddr;
    private String emailSubject;

    public String getRootSecret() {

        return rootSecret;
    }

    public void setRootSecret(String rootSecret) {

        this.rootSecret = rootSecret;
    }

    public String getSendEmailName() {

        return sendEmailName;
    }

    public void setSendEmailName(String sendEmailName) {

        this.sendEmailName = sendEmailName;
    }

    public String getProjectName() {

        return projectName;
    }

    public void setProjectName(String projectName) {

        this.projectName = projectName;
    }

    public String getSendEmailAddr() {

        return sendEmailAddr;
    }

    public void setSendEmailAddr(String sendEmailAddr) {

        this.sendEmailAddr = sendEmailAddr;
    }

    public String getEmailSubject() {

        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {

        this.emailSubject = emailSubject;
    }

}
