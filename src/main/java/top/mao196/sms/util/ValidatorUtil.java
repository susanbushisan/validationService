package top.mao196.sms.util;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author susanbushisan
 */
public class ValidatorUtil {

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号 REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
     */

    public static final String REGEX_MOBILE = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],*$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：验证验证码格式
     */
    public static final String REGEX_CHECK_CODE = "^\\d{4,10}$";

    /**
     * 验证码类型集合
     */
    public static final String[] CODE_TYPE_SET = {"sms", "email"};


    public static boolean isUsername(String username) {

        return Pattern.matches(REGEX_USERNAME, username);
    }


    public static boolean isPassword(String password) {

        return Pattern.matches(REGEX_PASSWORD, password);
    }


    public static boolean isMobile(String mobile) {

        return Pattern.matches(REGEX_MOBILE, mobile);
    }


    public static boolean isEmail(String email) {

        return Pattern.matches(REGEX_EMAIL, email);
    }


    public static boolean isChinese(String chinese) {

        return Pattern.matches(REGEX_CHINESE, chinese);
    }


    public static boolean isIDCard(String idCard) {

        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    public static boolean isUrl(String url) {

        return Pattern.matches(REGEX_URL, url);
    }


    public static boolean isIPAddr(String ipAddr) {

        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }


    public static boolean isCheckCode(String code) {

        return Pattern.matches(REGEX_CHECK_CODE, code);
    }


    public static boolean isCodeType(String codeType) {

        for (String str : CODE_TYPE_SET) {
            if (Objects.equals(codeType.trim().toLowerCase(), str)) {
                return true;
            }
        }
        return false;
    }

}
