package bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Board {
	private int seq_brd;
	private String mid;
	private int brd_type;
	private String brd_inputdate;
	private int brd_hitnum;
	
	//join 용 boardfile
	private String file_name;
	
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	//join 용 member
	private String name;
	
	
	//join 용 comments
	
	private int seq_comt;
	private String comt_content;
	private String comt_inputdate;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	@Length(min = 1, max = 20, message = "최소 1글자, 최대 20자입니다")
	private String brd_subject;
	
	@NotEmpty(message = "필수 입력 사항입니다.")
	@Length(min=1, max = 300,message = "최소 1글자, 최대 300자 작성 가능합니다.")
	private String brd_content;
	
	
	
	
	
	
	
	//join 용 member getter / setter
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//join 용 comments getter / setter
	
	public int getSeq_comt() {
		return seq_comt;
	}
	
	
	public void setSeq_comt(int seq_comt) {
		this.seq_comt = seq_comt;
	}
	public String getComt_content() {
		return comt_content;
	}
	public void setComt_content(String comt_content) {
		this.comt_content = comt_content;
	}
	public String getComt_inputdate() {
		return comt_inputdate;
	}
	public void setComt_inputdate(String comt_inputdate) {
		this.comt_inputdate = comt_inputdate;
	}
	
	// board용 getter/setter
	
	public int getSeq_brd() {
		return seq_brd;
	}
	public void setSeq_brd(int seq_brd) {
		this.seq_brd = seq_brd;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getBrd_type() {
		return brd_type;
	}
	public void setBrd_type(int brd_type) {
		this.brd_type = brd_type;
	}
	public String getBrd_subject() {
		return brd_subject;
	}
	public void setBrd_subject(String brd_subject) {
		this.brd_subject = brd_subject;
	}
	public String getBrd_content() {
		return brd_content;
	}
	public void setBrd_content(String brd_content) {
		this.brd_content = brd_content;
	}
	public String getBrd_inputdate() {
		return brd_inputdate;
	}
	public void setBrd_inputdate(String brd_inputdate) {
		this.brd_inputdate = brd_inputdate;
	}
	public int getBrd_hitnum() {
		return brd_hitnum;
	}
	public void setBrd_hitnum(int brd_hitnum) {
		this.brd_hitnum = brd_hitnum;
	}
	
	
}
