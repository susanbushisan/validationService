package top.mao196.sms.entity;

import java.util.Date;

/**
 * sms表实体类
 * @author susanbushisan
 */
public class Sms {

  private Integer id;
  private String phone;
  private String code;
  private String message;
  private String requestId;
  private String bizId;
  private Date cTime;
  private Integer caseType;

  public Sms() {
  }

  public Sms(Integer id, String phone, String code, String message, String requestId, String bizId, Date cTime, Integer caseType) {
    this.id = id;
    this.phone = phone;
    this.code = code;
    this.message = message;
    this.requestId = requestId;
    this.bizId = bizId;
    this.cTime = cTime;
    this.caseType = caseType;
  }

  public long getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }


  public String getBizId() {
    return bizId;
  }

  public void setBizId(String bizId) {
    this.bizId = bizId;
  }


  public Date getCTime() {
    return cTime;
  }

  public void setCTime(Date cTime) {
    this.cTime = cTime;
  }


  public long getCaseType() {
    return caseType;
  }

  public void setCaseType(Integer caseType) {
    this.caseType = caseType;
  }

}
