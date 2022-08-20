package review.reviewVO;

public class ReviewVO {

	private int r_no;
	private String r_id;
	private int r_pno;
	private String r_title;
	private String r_content;
	private int r_star;
	private int r_like;
	private int r_dislike;
	private String r_image;
	
	public ReviewVO() {}

	public ReviewVO(int r_no, String r_id, int r_pno, String r_title, String r_content, int r_star, int r_like,
			int r_dislike, String r_image) {
		this.r_no = r_no;
		this.r_id = r_id;
		this.r_pno = r_pno;
		this.r_title = r_title;
		this.r_content = r_content;
		this.r_star = r_star;
		this.r_like = r_like;
		this.r_dislike = r_dislike;
		this.r_image = r_image;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public int getR_pno() {
		return r_pno;
	}

	public void setR_pno(int r_pno) {
		this.r_pno = r_pno;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public int getR_star() {
		return r_star;
	}

	public void setR_star(int r_star) {
		this.r_star = r_star;
	}

	public int getR_like() {
		return r_like;
	}

	public void setR_like(int r_like) {
		this.r_like = r_like;
	}

	public int getR_dislike() {
		return r_dislike;
	}

	public void setR_dislike(int r_dislike) {
		this.r_dislike = r_dislike;
	}

	public String getR_image() {
		return r_image;
	}

	public void setR_image(String r_image) {
		this.r_image = r_image;
	}
	
	


	
	
}
