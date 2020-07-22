package emoney.board.domain;

import java.util.Date;

public class BoardFileVO {
	
	int flId;
	int brdId;
	String path;
	String uuidName;
	String originName;
	String extension;
	int byteSize;
	Date regDate;
	
	public int getFlId() {
		return flId;
	}
	public void setFlId(int flId) {
		this.flId = flId;
	}
	public int getBrdId() {
		return brdId;
	}
	public void setBrdId(int brdId) {
		this.brdId = brdId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUuidName() {
		return uuidName;
	}
	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public int getByteSize() {
		return byteSize;
	}
	public void setByteSize(int byteSize) {
		this.byteSize = byteSize;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
