package top.mao196.sms.entity;

import java.util.Date;

/**
 * email表实体类
 * @author susanbushisan
 */
public class Email {

  private Integer id;
  private String email;
  private String code;
  private Date cTime;
  private Integer caseType;

  public Email() {

  }

  public Email(Integer id, String email, String code, Date cTime, Integer caseType) {

    this.id = id;
    this.email = email;
    this.code = code;
    this.cTime = cTime;
    this.caseType = caseType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public Date getCTime() {
    return cTime;
  }

  public void setCTime(Date cTime) {
    this.cTime = cTime;
  }


  public Integer getCaseType() {
    return caseType;
  }

  public void setCaseType(Integer caseType) {
    this.caseType = caseType;
  }

}
