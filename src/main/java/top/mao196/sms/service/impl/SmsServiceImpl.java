package top.mao196.sms.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.mao196.sms.config.AliyunConfig;
import top.mao196.sms.config.DependencySwitch;
import top.mao196.sms.config.SmsConfig;
import top.mao196.sms.entity.*;
import top.mao196.sms.mapper.EmailMapper;
import top.mao196.sms.mapper.SmsMapper;
import top.mao196.sms.service.SmsService;
import top.mao196.sms.service.ex.ApiInvokeException;
import top.mao196.sms.service.ex.CommonException;
import top.mao196.sms.util.CommonRpc;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author susanbushisan
 */
@Service
public class SmsServiceImpl implements SmsService {

    private Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    private static final String RETURN_RIGHT_MESSAGE = "OK";

    @Autowired
    private SmsMapper smsMapper;

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private AliyunConfig aliyunConfig;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DependencySwitch dependencySwitch;

    public List<Sms> smsList = new ArrayList<>();

    public List<Email> emailList = new ArrayList<>();


    /**
     * 发送
     *
     * @param phone 手机号
     * @param code  验证码
     * @return Sms信息
     */
    @Override
    public Sms sendSms(String phone, String code, Constant.InvokeWay ways) {

        try {
            //发送信息并得到返回结果
            ApiRe apiRe;
            if (dependencySwitch.isSmsService()) {
                String smsReturn = CommonRpc.sendSms(code, phone, aliyunConfig.getAccessKeyID(), aliyunConfig.getAccessKeySecret(),
                        aliyunConfig.getSignName(), aliyunConfig.getTemplateCode());
                //将json转换成对象
                apiRe = new Gson().fromJson(smsReturn, ApiRe.class);
            } else {
                apiRe = new ApiRe();
                apiRe.setMessage(RETURN_RIGHT_MESSAGE);
            }
            if (Objects.equals(apiRe.getMessage(), RETURN_RIGHT_MESSAGE)) {
                //将所有数据封装
                Sms data = new Sms(null, phone, code, apiRe.getMessage(), apiRe.getRequestId(), apiRe.getBizId(), new Date(), ways.ordinal());
                if (dependencySwitch.isUsedDatabase()) {
                    //保存到数据库中
                    smsMapper.insert(data);
                } else {
                    smsList.add(data);
                }

            } else {
                throw new ApiInvokeException("aliyun api used return unexpect result");
            }

        } catch (ClientException e) {
            e.printStackTrace();
            throw new ApiInvokeException("Not used correctly aliyun api");
        } catch (Exception e) {
            throw new CommonException(e);
        }
        return null;
    }

    /**
     * 获取sms验证码的信息,待使用steam特性重构
     *
     * @param phone 手机号码
     */
    @Override
    public QueryCode getSmsInfo(String phone) {

        if (dependencySwitch.isUsedDatabase()) {
            return smsMapper.selectSmsInfoByPhone(phone);
        } else {
            Sms result = new Sms();
            for (Sms s : smsList) {
                if (Objects.equals(phone, s.getPhone())) {
                    if (result.getCTime() == null) {
                        result = s;
                    } else {
                        if (result.getCTime().compareTo(s.getCTime()) < 0) {
                            result = s;
                        }
                    }
                }
            }
            return new QueryCode(result.getCTime(), result.getCode());

        }

    }

    /**
     * 获取email验证码的信息,待使用steam特性重构
     *
     * @param email 邮箱
     */
    @Override
    public QueryCode getEmailInfo(String email) {

        if (dependencySwitch.isUsedDatabase()) {
            return emailMapper.selectEmailInfoByEmail(email);
        } else {
            Email result = new Email();
            for (Email s : emailList) {
                if (Objects.equals(email, s.getEmail())) {
                    if (result.getCTime() == null) {
                        result = s;
                    } else {
                        if (result.getCTime().compareTo(s.getCTime()) < 0) {
                            result = s;
                        }
                    }
                }
            }
            return new QueryCode(result.getCTime(), result.getCode());

        }

    }

    /**
     * 发送email邮件验证码
     *
     * @param email 邮箱
     * @param code  验证码
     * @return email信息
     */
    @Override
    public Email sendEmail(String email, String code,Constant.InvokeWay ways) {
        //1.发送信息
        //1.1获取邮件html信息
        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("projectName", smsConfig.getProjectName());
        String emailString = templateEngine.process("email", context);
        //1.2调用邮件服务发送信息
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(smsConfig.getSendEmailAddr(), smsConfig.getSendEmailName()));
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(smsConfig.getEmailSubject());
            mimeMessageHelper.setText(emailString, true);
            if (dependencySwitch.isEmailService()) {
                javaMailSender.send(mimeMailMessage);
            }
            //2.数据入库
            Email emailBean = new Email(null, email, code, new Date(), ways.ordinal());
            if (dependencySwitch.isUsedDatabase()) {
                emailMapper.insert(emailBean);
            } else {
                emailList.add(emailBean);
            }

            return emailBean;
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("email settings has problem", e);
            return null;
        } catch (Exception e) {
            log.warn("email settings has problem,please check param", e);
            return null;
        }

    }

}
