package emoney.user.domain;

import java.util.Date;

public class LoginHistoryVO {
	
	int accntId;
	Date loginDate;
	String isMobile;
	String ip;
	String browser;
	String os;
	public int getAccntId() {
		return accntId;
	}
	public void setAccntId(int accntId) {
		this.accntId = accntId;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	@Override
	public String toString() {
		return "LoginHistoryVO [accntId=" + accntId + ", loginDate=" + loginDate + ", isMobile=" + isMobile + ", ip="
				+ ip + ", browser=" + browser + ", os=" + os + "]";
	}
}
