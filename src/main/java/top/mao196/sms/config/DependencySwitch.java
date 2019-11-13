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

    @Override
    public String toString() {

        return "\nDependencySwitch{" +
                "\n\t 使用redis = " + usedRedis +
                "\n\t 使用消息队列 = " + usedMq +
                "\n\t 使用数据库存储 = " + usedDatabase +
                "\n\t 开启邮件验证码功能 = " + emailService +
                "\n\t 开启短信验证码功能 = " + smsService +
                "\n}";
    }

}
