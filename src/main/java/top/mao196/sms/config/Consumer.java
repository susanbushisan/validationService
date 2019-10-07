package top.mao196.sms.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import top.mao196.sms.entity.Constant;
import top.mao196.sms.service.SmsService;
import top.mao196.sms.service.ex.CodeFormatException;
import top.mao196.sms.service.ex.PhoneFormatException;
import top.mao196.sms.util.ValidatorUtil;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author susanbushisan
 */
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Constant.InvokeWay INVOKE_WAY = Constant.InvokeWay.MQ;

    @Autowired
    SmsService smsService;

    @JmsListener(destination = "email.queue")
    public void receiveEmailQueue(final TextMessage text, Session session) throws JMSException {

        try {
            JSONObject jsonObject = JSON.parseObject(text.getText());
            String email = jsonObject.get("email").toString();
            String code = jsonObject.get("code").toString();
            if (!ValidatorUtil.isEmail(email)) {
                throw new PhoneFormatException("email format can't be analysis");
            }
            if (!ValidatorUtil.isCheckCode(code)) {
                throw new CodeFormatException("code format can't be analysis");
            }
            smsService.sendEmail(email,code,INVOKE_WAY);
            logger.info("发送邮件验证码成功");
        } catch (Exception e) {
            logger.info("发送邮件验证码失败",e);
            session.recover();
        }

    }

    @JmsListener(destination = "sms.queue")
    public void receiveSmsQueue(final TextMessage text, Session session) throws JMSException {

        try {
            JSONObject jsonObject = JSON.parseObject(text.getText());
            String phone = jsonObject.get("phone").toString();
            String code = jsonObject.get("code").toString();
            if (!ValidatorUtil.isMobile(phone)) {
                throw new PhoneFormatException("phone format can't be analysis");
            }
            if (!ValidatorUtil.isCheckCode(code)) {
                throw new CodeFormatException("code format can't be analysis");
            }
            smsService.sendSms(phone,code,INVOKE_WAY);
            logger.info("发送短信验证码成功");
        } catch (Exception e) {
            logger.info("发送短信验证码失败",e);
            session.recover();
        }
    }

}
