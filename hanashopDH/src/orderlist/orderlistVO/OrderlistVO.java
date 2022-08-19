package orderlist.orderlistVO;

public class OrderlistVO {
	
	private String o_id;
	private String o_name;
	private int o_count;
	private String o_size;
	private int o_price;
	
	
	public OrderlistVO(String o_id, String o_name, int count, String size, int price) {
		super();
		this.o_id = o_id;
		this.o_name = o_name;
		this.o_count = count;
		this.o_size =size;
		this.o_price = price;
	}


	


	public String getO_size() {
		return o_size;
	}





	public void setO_size(String o_size) {
		this.o_size = o_size;
	}





	public int getO_price() {
		return o_price;
	}





	public void setO_price(int o_price) {
		this.o_price = o_price;
	}





	public String getO_id() {
		return o_id;
	}


	public void setO_id(String o_id) {
		this.o_id = o_id;
	}


	public String getO_name() {
		return o_name;
	}


	public void setO_name(String o_name) {
		this.o_name = o_name;
	}


	public int getO_count() {
		return o_count;
	}


	public void setO_count(int o_count) {
		this.o_count = o_count;
	}
	
	
	
}
