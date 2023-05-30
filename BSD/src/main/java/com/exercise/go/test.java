package com.exercise.go;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class test {
	/* db 연결 테스트
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "ehdgus309", "ehd123309");
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from MEMBER");
		System.out.println(rs);
		
		while(rs.next()){

		System.out.println(rs.getString("ID"));

		}
	}
	*/
	
/* 코테
	public static void main(String[] args) {
		
		int[] gogo = solution(10,20);
		for(int i = 0 ; i< gogo.length; i++) {
			System.out.println(gogo[i]);
		}

	}
	
	
	
    public static int[] solution(int l, int r) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        
        String str = "";
		Boolean result2;
        int index2 = -1;
        for(int a=l ;a<=r; a++){
            str = Integer.toString(a);
            result2 = true;
            
            
            for(int b=0;b<str.length();b++){
                if(str.charAt(b) != '5' && str.charAt(b) != '0'){
					result2 = false;
				}
            }
            
			if(result2){
				index2+=1;
				System.out.println(a+"는 조건에 맞는다.");
				System.out.println(index2 + "인덱스");
				System.out.println(a + " 값~~~~~~~~~~~");
				answer.add(a);
				
			}
        }
        
        if(index2 == -1) {
        	int[] answer3 = {-1};
        	return answer3;
        }else {

            int[] answer2 = new int[answer.size()];
            

            for(int i =0; i<answer.size(); i++) {
            	answer2[i] = answer.get(i);
            }
            
        	return answer2;
        }
    }
*/
}
