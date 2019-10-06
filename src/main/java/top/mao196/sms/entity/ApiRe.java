package top.mao196.sms.entity;

/**
 * 调用阿里云API返回结果封装的实体类
 * @author susanbushisan
 */
public class ApiRe {

	private String Message;
	private String RequestId;
	private String BizId;
	private String Code;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getBizId() {
		return BizId;
	}

	public void setBizId(String bizId) {
		BizId = bizId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	@Override
	public String toString() {
		return "ApiRe [Message=" + Message + ", RequestId=" + RequestId + ", BizId=" + BizId + ", Code=" + Code + "]";
	}

}
