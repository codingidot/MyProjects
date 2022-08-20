package ScinfoVO;

import java.util.Date; //자바쪽은 util, sql족은 java.sql.date
/*create table TelTable5 (
id  number(5),
name  varchar2(20),
tel   varchar2(20),
d   date
);*/

//src-마오-class - 페키지명은 telinfoVO, class명은 TelInfoVO
										//main x

										
public class ScInfoVO { //TelInfoVO.java
private int sc_id; 		
private String sc_date;	
private int start_time ;
private int finish_time;
private String sc_contents;
private String mem_id;
private String mem_pw;
String rs;

//generating으로 만든 인자가 있는 생성자---------------------
//마오-Source - generate constructor using field
public ScInfoVO(int sc_id, String sc_date, int start_time, int finish_time, String sc_contents) {
//super();
this.sc_id = sc_id;
this.sc_date = sc_date;
this.start_time = start_time;
this.finish_time = finish_time;
this.sc_contents=sc_contents;

}



public ScInfoVO() {} //디폴트 생성

public String getSc_contents() {
	return sc_contents;
}

public void setSc_contents(String sc_contents) {
	this.sc_contents = sc_contents;
}

public int getSc_id() {
	return sc_id;
}

public void setSc_id(int sc_id) {
	this.sc_id = sc_id;
}

public String getSc_date() {
	return sc_date;
}

public void setSc_date(String sc_date) {
	this.sc_date = sc_date;
}

public int getStart_time() {
	return start_time;
}

public void setStart_time(int start_time) {
	this.start_time = start_time;
}

public int getFinish_time() {
	return finish_time;
}

public void setFinish_time(int finish_time) {
	this.finish_time = finish_time;
}



public String getMem_id() {
	return mem_id;
}



public void setMem_id(String mem_id) {
	this.mem_id = mem_id;
}



public String getMem_pw() {
	return mem_pw;
}



public void setMem_pw(String mem_pw) {
	this.mem_pw = mem_pw;
}



public String getRs() {
	return rs;
}



public void setRs(String rs) {
	this.rs = rs;
}


//getter,setter

}