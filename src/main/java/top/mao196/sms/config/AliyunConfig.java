package top.mao196.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 装配配置文件中aliyun相关的设置
 * @author susanbushisan
 */
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunConfig {
    private String AccessKeyID;
    private String AccessKeySecret;
    private String SignName;
    private String TemplateCode;

    public String getAccessKeyID() {

        return AccessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {

        AccessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {

        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {

        AccessKeySecret = accessKeySecret;
    }

    public String getSignName() {

        return SignName;
    }

    public void setSignName(String signName) {

        SignName = signName;
    }

    public String getTemplateCode() {

        return TemplateCode;
    }

    public void setTemplateCode(String templateCode) {

        TemplateCode = templateCode;
    }

}
