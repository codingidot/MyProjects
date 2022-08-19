 <%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원목록(관리자)</title>
</head>
<style>
body
{
  background-color: #fffff0;
}

h1{
 background-color : white;
}

footer{

  background-color : white;
  

}


table.list {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
   background-color : #fffff0;
 
}



table.list th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
  background-color : #ffe5dd;
}
table.list td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
 .div1{position:absolute; left:90%;} 



</style>
<body>
<h1>회원목록(관리자)<hr></h1>
<%
	request.setCharacterEncoding("utf-8");
	String stotal=(String)request.getAttribute("stotal"); //등록된 총 상품수
	String currentpage=(String)request.getAttribute("currentpage"); //현재 페이지
	String currentgroup=(String)request.getAttribute("currentgroup");//현재 그룹
	int pagesize=5; //한페이지에 나오는 수
	int groupsize=5;// 페이지 그룹수
	int plus=pagesize*groupsize; //그룹이 바뀔때 더하거나 빼줘야하는 값
	int groupnum=Integer.parseInt(currentgroup); //현재 그룹(int)
	int numcurrent=Integer.parseInt(currentpage);//현재 페이지(int)
	int total12=Integer.parseInt(stotal);//등록된 총 상품수(int)
	int count=total12/pagesize; //페이지수(int)
	if(total12%pagesize!=0){ //상품수를 pagesize로 나눠서 나머지가 0이 아니면 한페이지 추가해줌
		count+=1;	
	}
	int totgroup=count/groupsize;// 그룹수
	if(count%groupsize!=0){ //페이지수를 groupsize로 나눠서 나머지가 0이 아니면 한그룹 추가해줌
		totgroup+=1;
	}
	

%>
<form action="memberDelete.do" method="post">
<table class=list>
<tr>
<th>아이디</th><th>비밀번호</th><th>이름</th><th>전화번호</th><th>주소</th><th>이메일</th><th>생일</th><th>포인트</th><th>선택</th>
</tr>
 <c:forEach var="vo2" items="${requestScope.mlist}">
 		<tr>
 		<td>${vo2.u_id}</td>
		<td>${vo2.u_password}</td> 
		<td>${vo2.u_name }</td>
		<td>${vo2.u_tel }</td>
		<td>${vo2.u_address }</td>
		<td>${vo2.u_email }</td>
		<td>${vo2.u_d}</td>
		<td>${vo2.u_point}</td>
		<td><input type="checkbox" name="delMemberList" value="${vo2.u_id}"></td>
 		</tr>
 </c:forEach>
 </table>
 <center>

 <%		 
 		
 		
 		int number=1; //초기에 출력하는 상품의 start값
 		 int number2=pagesize;//초기에 출력하는 상품의 end값
 		 number=number+plus*(groupnum-1);//그룹이 바뀜에 따른 start를 바꿔줌
 		number2=number2+plus*(groupnum-1);//그룹이 바뀜에 따른 end를 바꿔줌
 		 
 		 int nexts2=1+(plus*(groupnum-2));//이전 버튼 누르면 나오는 start값
			 int nexte2=pagesize+(plus*(groupnum-2));//이전 버튼 누르면 나오는 end값
			 int nextg2=groupnum-1;//이전버튼 누르면 현재 그룹에서-1
		 if(groupnum>1){//그룹이 1보다 큰 경우에만 이전버튼 출력
			 
			out.print("<a href='member.do?start="+nexts2+"&end="+nexte2+"&currentgroup="+nextg2+"'>이전</a>&nbsp&nbsp");
		 }	 
 		 
 			
 			for(int i=groupsize*(groupnum-1);i<(groupsize*(groupnum-1)+groupsize);i++){//현재 그룹에 따른 페이지버튼 숫자를 설정
 	 			
 	 			out.print("<a href='member.do?start="+number+"&end="+number2+"&currentpage="+(i+1)+"&currentgroup="+groupnum+"'>"+"["+(i+1)+"]</a>&nbsp&nbsp");
 	 			number+=pagesize; //다음 페이지의 start를 위해 pagesize 만큼 늘려줌
 	 			number2+=pagesize;//다음 페이지의 end를 위해 pagesize 만큼 늘려줌
 	 			if((i+1)==count){//만약 현재 페이지가 총페이지수랑 같으면 for문에서 나옴
 	 				break;
 	 			}
 	 		}  
 		
 			 int nexts=1+(plus*(groupnum));//다음 버튼 눌렀을때 start값 설정
 			 int nexte=pagesize+(plus*(groupnum));// 다음 버튼 눌렀을때 end값 설정
 			 int nextg=groupnum+1;//다음 버튼 눌렀을때 그룹을 1더해줌
 		 if(groupnum!=totgroup){//현재 그룹이 총 그룹인경우 다음버튼 출력하지 않음
 			 
 			out.print("<a href='member.do?start="+nexts+"&end="+nexte+"&currentgroup="+nextg+"'>다음</a>");
 		 }
 %>

 </center>
	<div class="div1"><input type="submit" name=option value="삭제하기"></div>
	
</form>
<p>&nbsp</p>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>