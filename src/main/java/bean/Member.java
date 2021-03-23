package bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Member {
	@NotEmpty(message = "필수 입력 사항입니다.")
	@Length(min = 4, max = 15,message = "아이디는 최소 4자리, 최대 15자리 입니다.")
	private String mid;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	@Length(min = 2, max = 10,message = "이름은 최소 2자리, 최대 10자리 입니다.")
	private String name;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	@Pattern(regexp = "\\d{4}[/]\\d{2}[/]\\d{2}", message = "생일은 YYYY/MM/DD 형식으로 입력해 주세요.")
	private String age;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	private String password;
	private String passwordcheck;
	@NotEmpty(message = "필수 입력 사항입니다.")
	private String phone;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	@Length( max = 20,message = "이메일은 최대 20자리 입니다.")
	private String email;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	private String postcode;
	private String address1;
	private String address2;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordcheck() {
		return passwordcheck;
	}
	public void setPasswordcheck(String passwordcheck) {
		this.passwordcheck = passwordcheck;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
}
