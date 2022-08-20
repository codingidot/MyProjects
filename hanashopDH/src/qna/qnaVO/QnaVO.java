package qna.qnaVO;

import java.sql.Date;

public class QnaVO {
	
	private int q_no;
	private String q_id;
	private int q_pno;
	private String q_title;
	private String q_content;
	private Date q_date;
	private int q_hit;
	private int q_parentno;
	private String q_pw;
	private String q_image;
	private String q_state;
	private String q_category;
	
	
	public QnaVO() {}


	public QnaVO(int q_no, String q_id, int q_pno, String q_title, String q_content, Date q_date, int q_hit,
			int q_parentno, String q_pw, String q_image, String q_state, String q_category) {
		super();
		this.q_no = q_no;
		this.q_id = q_id;
		this.q_pno = q_pno;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_date = q_date;
		this.q_hit = q_hit;
		this.q_parentno = q_parentno;
		this.q_pw = q_pw;
		this.q_image = q_image;
		this.q_state = q_state;
		this.q_category = q_category;
	}


	public int getQ_no() {
		return q_no;
	}


	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}


	public String getQ_id() {
		return q_id;
	}


	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}


	public int getQ_pno() {
		return q_pno;
	}


	public void setQ_pno(int q_pno) {
		this.q_pno = q_pno;
	}


	public String getQ_title() {
		return q_title;
	}


	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}


	public String getQ_content() {
		return q_content;
	}


	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}


	public Date getQ_date() {
		return q_date;
	}


	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}


	public int getQ_hit() {
		return q_hit;
	}


	public void setQ_hit(int q_hit) {
		this.q_hit = q_hit;
	}


	public int getQ_parentno() {
		return q_parentno;
	}


	public void setQ_parentno(int q_parentno) {
		this.q_parentno = q_parentno;
	}


	public String getQ_pw() {
		return q_pw;
	}


	public void setQ_pw(String q_pw) {
		this.q_pw = q_pw;
	}


	public String getQ_image() {
		return q_image;
	}


	public void setQ_image(String q_image) {
		this.q_image = q_image;
	}


	public String getQ_state() {
		return q_state;
	}


	public void setQ_state(String q_state) {
		this.q_state = q_state;
	}


	public String getQ_category() {
		return q_category;
	}


	public void setQ_category(String q_category) {
		this.q_category = q_category;
	}


	
	
	
	
}