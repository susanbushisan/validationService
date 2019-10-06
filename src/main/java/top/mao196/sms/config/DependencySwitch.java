package top.mao196.sms.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author susanbushisan
 */

@ConfigurationProperties(prefix="app")
@Component
public class DependencySwitch {

    private boolean usedRedis = true;
    private boolean usedMq = true;
    private boolean usedDatabase = true;
    private boolean emailService = true;
    private boolean smsService = true;

    public boolean isUsedRedis() {

        return usedRedis;
    }

    public void setUsedRedis(boolean usedRedis) {

        this.usedRedis = usedRedis;
    }

    public boolean isUsedMq() {

        return usedMq;
    }

    public void setUsedMq(boolean usedMq) {

        this.usedMq = usedMq;
    }

    public boolean isUsedDatabase() {

        return usedDatabase;
    }

    public void setUsedDatabase(boolean usedDatabase) {

        this.usedDatabase = usedDatabase;
    }

    public boolean isEmailService() {

        return emailService;
    }

    public void setEmailService(boolean emailService) {

        this.emailService = emailService;
    }

    public boolean isSmsService() {

        return smsService;
    }

    public void setSmsService(boolean smsService) {

        this.smsService = smsService;
    }

}
