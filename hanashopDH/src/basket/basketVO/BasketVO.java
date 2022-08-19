package basket.basketVO;

public class BasketVO {

	
	private String b_id;
	private String b_name;
	private int b_price;
	private int b_count;
	private String b_size;
	private int b_no;
	
	

	


	

	public BasketVO(String b_id, String b_name, int b_price, int b_count, String b_size, int b_no) {
		super();
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_price = b_price;
		this.b_count = b_count;
		this.b_size=b_size;
		this.b_no=b_no;
	}
	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	
	public String getB_size() {
		return b_size;
	}

	public void setB_size(String b_size) {
		this.b_size = b_size;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public int getB_price() {
		return b_price;
	}

	public void setB_price(int b_price) {
		this.b_price = b_price;
	}

	public int getB_count() {
		return b_count;
	}

	public void setB_count(int b_count) {
		this.b_count = b_count;
	}
	
	
	
	
	
}

