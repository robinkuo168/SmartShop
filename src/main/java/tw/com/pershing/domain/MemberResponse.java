package tw.com.pershing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberResponse {
	@JsonProperty("ReturnCode")
	private String code;
	
	@JsonProperty("ReturnMessage")
	private String msg;
	
	@JsonProperty("CardNumber")
	private String cardNo;

	public String getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public String getCardNo() {
		return cardNo;
	}
}
