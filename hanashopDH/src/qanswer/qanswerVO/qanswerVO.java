package qanswer.qanswerVO;

public class qanswerVO {
	
	private int q_no;
	private String q_id;
	private String q_title;
	private String q_content;
	private String q_answer;
	
	
	public qanswerVO() {}


	
	public qanswerVO(int q_no, String q_id, String q_title, String q_content, String q_answer) {
		super();
		
		this.q_no = q_no;
		this.q_id = q_id;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_answer = q_answer;
		

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


	public String getQ_answer() {
		return q_answer;
	}


	public void setQ_answer(String q_answer) {
		this.q_answer = q_answer;
	}

}
