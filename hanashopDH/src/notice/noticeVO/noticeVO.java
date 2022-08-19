package notice.noticeVO;

public class noticeVO {
	
	private int n_no;
	private String n_title;
	private String n_content;
	
	
	public noticeVO(int n_no, String n_title, String n_content) { 
		super();
		this.n_no = n_no;
		this.n_title= n_title;
		this.n_content = n_content;
		
	}


	public int getN_no() {
		return n_no;
	}


	public void setN_no(int n_no) {
		this.n_no = n_no;
	}


	public String getN_title() {
		return n_title;
	}


	public void setN_title(String n_title) {
		this.n_title = n_title;
	}


	public String getN_content() {
		return n_content;
	}


	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
}
