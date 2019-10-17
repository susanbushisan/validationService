package top.mao196.sms.entity;

/**
 * @author susanbushisan
 */

public class Constant {
    public enum CodeType{
        /**
         * 手机验证码
         */
        SMS("sms"),
        /**
         * 邮箱验证码
         */
        EMAIL("email");

        private String action;

        CodeType(String s) {
            this.action = s;
        }

        public String getAction() {
            return action;
        }
    }

    public enum InvokeWay{
        /**
         * 使用http协议操作
         */
        HTTP,
        /**
         * 使用消息队列操作
         */
        MQ
    }
}
