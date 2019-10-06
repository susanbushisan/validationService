package top.mao196.sms.entity;

import java.util.Date;

/**
 * 返回最后的验证码查询数据
 * @author susanbushisan
 */
public class QueryCode {
    private Date lastTime;
    private String code;

    public QueryCode() {

    }

    public QueryCode(Date lastTime, String code) {

        this.lastTime = lastTime;
        this.code = code;
    }

    public Date getLastTime() {

        return lastTime;
    }

    public void setLastTime(Date lastTime) {

        this.lastTime = lastTime;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    @Override
    public String toString() {

        return "QueryCode{" +
                "lastTime=" + lastTime +
                ", code='" + code + '\'' +
                '}';
    }

}
