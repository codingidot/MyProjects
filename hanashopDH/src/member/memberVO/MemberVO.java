package member.memberVO;

import java.sql.Date;

public class MemberVO {

	private String u_id;
	private String u_password;
	private String u_name;
	private String u_tel;
	private String u_address;
	private String u_email; 
	private int u_point;
	private Date u_d;
	
	


	public MemberVO(String u_id, String u_password, String u_name, String u_tel, String u_address, String u_email,
			Date u_d,int u_point) {
		super();
		this.u_id = u_id;
		this.u_password = u_password;
		this.u_name = u_name;
		this.u_tel = u_tel;
		this.u_address = u_address;
		this.u_email = u_email;
		this.u_d = u_d;
		this.u_point = u_point;
	}


	public Date getU_d() {
		return u_d;
	}


	public void setU_d(Date u_d) {
		this.u_d = u_d;
	}


	public String getU_id() {
		return u_id;
	}


	public void setU_id(String u_id) {
		this.u_id = u_id;
	}


	public String getU_password() {
		return u_password;
	}


	public void setU_password(String u_password) {
		this.u_password = u_password;
	}


	public String getU_name() {
		return u_name;
	}


	public void setU_name(String u_name) {
		this.u_name = u_name;
	}


	public String getU_tel() {
		return u_tel;
	}


	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}


	public String getU_address() {
		return u_address;
	}


	public void setU_address(String u_address) {
		this.u_address = u_address;
	}


	public String getU_email() {
		return u_email;
	}


	public void setU_email(String u_email) {
		this.u_email = u_email;
	}


	public int getU_point() {
		return u_point;
	}


	public void setU_point(int u_point) {
		this.u_point = u_point;
	}



}
