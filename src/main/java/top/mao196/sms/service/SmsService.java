package top.mao196.sms.service;

import top.mao196.sms.entity.Constant;
import top.mao196.sms.entity.Email;
import top.mao196.sms.entity.QueryCode;
import top.mao196.sms.entity.Sms;


/**
 * @author susanbushisan
 */
public interface SmsService {

    /**
     * 发送
     * @param phone 手机号
     * @param code 验证码
     * @param ways 发送的方式
     * @return Sms信息
     */
    Sms sendSms(String phone, String code, Constant.InvokeWay ways);

    /**
     * 获取sms验证码的信息
     * @param phone 手机号码
     * @return 请求信息
     */
    QueryCode getSmsInfo(String phone);

    /**
     * 获取email验证码的信息
     * @param email 邮箱
     * @return 请求信息
     */
    QueryCode getEmailInfo(String email);

    /**
     * 发送email邮件验证码
     * @param email 邮箱
     * @param code 验证码
     * @param ways 发送的方式
     * @return email信息
     */
    Email sendEmail(String email, String code, Constant.InvokeWay ways);

}
