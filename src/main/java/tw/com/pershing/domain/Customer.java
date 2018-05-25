package tw.com.pershing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	@Column(name="account_id")
	private String accountId;
	private String username;
	private String mobile;
	private String email;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	private byte age;
	private String occupation;
	private String marriage;
	private byte numbersofchildren;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="last_request_at")
	private Date lastRequestAt;
	@Column(name="update_at")
	private Date updateAt;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Customer [username=").append(username).append(", mobile=").append(mobile).append(", password=").append(password).append(", email=").append(email).append("]");
        return builder.toString();
    }

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public byte getNumbersofchildren() {
		return numbersofchildren;
	}

	public void setNumbersofchildren(byte numbersofchildren) {
		this.numbersofchildren = numbersofchildren;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getLastRequestAt() {
		return lastRequestAt;
	}

	public void setLastRequestAt(Date lastRequestAt) {
		this.lastRequestAt = lastRequestAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
