package top.mao196.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mao196.sms.config.DependencySwitch;
import top.mao196.sms.entity.Constant;
import top.mao196.sms.entity.QueryCode;
import top.mao196.sms.entity.Return;
import top.mao196.sms.service.SmsService;
import top.mao196.sms.service.ex.CodeFormatException;
import top.mao196.sms.service.ex.PhoneFormatException;
import top.mao196.sms.util.ValidatorUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author susanbushisan
 */
@RestController
@RequestMapping("sms")
public class SmsController {


    @Autowired
    private SmsService smsService;


    @PostMapping("sms")
    public Return sendSms(@RequestParam String phone, @RequestParam String code) {

        if (!ValidatorUtil.isMobile(phone)) {
            throw new PhoneFormatException("phone format can't be analysis");
        }
        if (!ValidatorUtil.isCheckCode(code)) {
            throw new CodeFormatException("code format can't be analysis");
        }
        smsService.sendSms(phone, code);
        return new Return(Return.STATUS_OK, "send sms success");
    }

    @PostMapping("email")
    public Return sendEmail(@RequestParam String email, @RequestParam String code) {

        if (!ValidatorUtil.isEmail(email)) {
            throw new PhoneFormatException("email format can't be analysis");
        }
        if (!ValidatorUtil.isCheckCode(code)) {
            throw new CodeFormatException("code format can't be analysis");
        }
        smsService.sendEmail(email, code);
        return new Return(Return.STATUS_OK, "send email success");
    }

    /**
     * 查询验证码
     *
     * @param codeType 验证码类型
     * @param value    被查询的值
     * @return {
     * lastTime : Date,
     * code : string,
     * codeType : Constant.CodeType,
     * resTime : Date,
     * value : string
     * }
     */
    @GetMapping("checkInfo")
    public Map<String, Object> getSmsInfo(@RequestParam String codeType, @RequestParam String value) {

        if (!ValidatorUtil.isCodeType(codeType)) {
            throw new CodeFormatException("codeType can't be analysis");
        }

        Constant.CodeType type = Constant.CodeType.valueOf(codeType.trim().toUpperCase());
        Map<String, Object> result = new HashMap<>(10);
        result.put("codeType", codeType);
        result.put("value", value);
        QueryCode info;
        if (type == Constant.CodeType.SMS) {
            info = smsService.getSmsInfo(value);
        } else {
            info = smsService.getEmailInfo(value);
        }
        result.put("lastTime", info.getLastTime());
        result.put("code", info.getCode());
        result.put("resTime", new Date());
        return result;
    }

}
