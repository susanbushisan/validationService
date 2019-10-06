package top.mao196.sms.entity;

/**
 * 封装返回数据，最后转换为json
 * @author susanbushisan
 */
public class Return {

	public static final Integer STATUS_OK = 200;
	public static final Integer STATUS_PARMERR = 400;
	public static final Integer STATUS_AUTHORIZATION = 401;
	public static final Integer STATUS_APIERR = 403;
	public static final Integer STATUS_SERVICEERR = 500;

	private Integer status;
	private String message;

	public Return() {
		super();
	}

	public Return(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Return [status=" + status + ", message=" + message + "]";
	}

}
