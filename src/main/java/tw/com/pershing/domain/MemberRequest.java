package tw.com.pershing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberRequest {
	@JsonProperty("AccessKey")
	private String accessKey;
	
	@JsonProperty("Plateform")
	private String platform;
	
	@JsonProperty("TimeStamp")
	private String timestamp;
	
	@JsonProperty("strUID")
	private String uid;

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
