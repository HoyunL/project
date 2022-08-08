package kr.co.project.reply;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyVO {
	
	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	private int gno;
	private int ono;
	private int nested;
	
	
	
	//------------------------------
	private String member_name;
	private int comment_count;
	
	
	
	//------------------------------
	private int page;
	private String stype;
	private String sword;
	private int startIdx;
	private int pageRow;
	
	
	
	public ReplyVO() {
		//this.page = 1;
		//this.pageRow = 10;
		this(1,10);
	}
	public ReplyVO(int page, int pageRow) {
		this.page = page;
		this.pageRow = pageRow;
	}
}
