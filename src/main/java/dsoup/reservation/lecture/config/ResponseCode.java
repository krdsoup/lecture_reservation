package dsoup.reservation.lecture.config;

import org.springframework.lang.Nullable;

/**
 * Response Code 정의
 */
public enum ResponseCode {

	NO_ERROR(1000, "No Error"),
	SERVICE_NOT_FOUND(2001, "Service not found"),
	BAD_REQUEST(3001, "Bad Request"),
	DUPLICATE_DATA(3002, "Duplicate Data"),
	NO_DATA(3003, "No Data"),
	OVER_LIMIT(3004, "Over Limit"),
	AUTHORIZED_REQUIRED(4001, "Authorized Required"),
	INTERNAL_SERVER_ERROR(5001, "Internal Server Error");

	private final int value;
	private final String reasonPhraseCode;

	ResponseCode(int value, String reasonPhraseCode) {
		this.value = value;
		this.reasonPhraseCode = reasonPhraseCode;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhraseCode() {
		return this.reasonPhraseCode;
	}

	@Nullable
	public static ResponseCode resolve(int statusCode) {
		for (ResponseCode status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}

}