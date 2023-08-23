package dsoup.reservation.lecture.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Response {
	private int code;
	private String message;
	private Object data;
	
	public Response() {
		this.code = ResponseCode.NO_ERROR.value();
		this.message = ResponseCode.NO_ERROR.getReasonPhraseCode();
	}

	public Response(ResponseCode status) {
		this(status, null);
	}

	public Response(ResponseCode status, Object data) {
		this.code = status.value();
		this.message = status.getReasonPhraseCode();
		this.data = data;
	}
	
	public Response(Object data) {
		this();
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
