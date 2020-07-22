package emoney.user.dto;

public class LoginDTO {
	int accntId;
	String nickname;
	String name;
	String userType;
	String phone;
	String id;
	String sPasswd;
	boolean useCookie;
	public int getAccntId() {
		return accntId;
	}
	public void setAccntId(int accntId) {
		this.accntId = accntId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getsPasswd() {
		return sPasswd;
	}
	public void setsPasswd(String sPasswd) {
		this.sPasswd = sPasswd;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [accntId=" + accntId + ", nickname=" + nickname + ", name=" + name + ", userType=" + userType
				+ ", phone=" + phone + ", id=" + id + ", sPasswd=" + sPasswd + ", useCookie=" + useCookie + "]";
	}
}
