package emoney.board.domain;

import java.util.Date;

public class BoardVO {
	
	int brdId;
	String writer;
	String title;
	String content;
	int viewCnt;
	Date regDate;
	Date modDate;
	public int getBrdId() {
		return brdId;
	}
	public void setBrdId(int brdId) {
		this.brdId = brdId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	@Override
	public String toString() {
		return "BoardVO [brdId=" + brdId + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", viewCnt=" + viewCnt + ", regDate=" + regDate + ", modDate=" + modDate + "]";
	}
	
}
