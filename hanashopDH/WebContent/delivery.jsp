<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A</title>

<style type="text/css">

#btn1{
	background-color : #ffe5dd;
	border : 0;
}


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

#table1 {

  border-collapse: collapse;
  border-radius: 30px;
  border-style: hidden;
  box-shadow: 0 0 0 1px #000;
}

th, td {
  text-align: center;
}


th:first-of-type {
  border-top-left-radius: 30px;
}
th:first-of-type {
  border-bottom-left-radius: 0px;
}
th:last-of-type {
  border-top-right-radius: 30px;
}
th:last-of-type{
  border-bottom-left-radius: 0px;
}
th:last-of-type {
  border-bottom-right-radius: 0px; 
}

#th2:first-of-type {
  border-top-left-radius: 0px;
}
#th2:first-of-type {
  border-bottom-left-radius: 30px;
}
#th2:last-of-type {
  border-top-right-radius: 0px;
}

#th2:last-of-type {
  border-bottom-right-radius: 30px; 
}

</style>

</head>
<body>

	<center>
	
	<h1>배송문의<hr></h1>
	
				
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
	<table >
	<tr><td height=100></tr>		
	</table>
	
	<c:if test="${isqna !='no'}">
	
	<table id="table1" border=1>	
	<%int num=0; %>
	<tr> <th style="WIDTH:  100pt; HEIGHT: 40pt; background-color:#ffe5dd ">번호</th>
			<th style="WIDTH: 300pt; HEIGHT: 40pt;  background-color:#ffe5dd">제목</th>
			<th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">작성자</th>
	</tr>	
	<c:forEach var="vo" items="${requestScope.vo}">
	
	<%num++;%>			
	<tr><th style="WIDTH:  200pt; HEIGHT: 40pt"><%=num%></th>
	
 	<th style="WIDTH: 200pt; HEIGHT: 40pt">
 	<a href="comdeliveryinfo.do?qno=${vo.q_no}&qparentno=${vo.q_parentno}">${vo.q_title }</a></th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_id }</th>
    </tr>
	</c:forEach>
	
	</table><%		 
 		
 		
 		int number=1; //초기에 출력하는 상품의 start값
 		 int number2=pagesize;//초기에 출력하는 상품의 end값
 		 number=number+plus*(groupnum-1);//그룹이 바뀜에 따른 start를 바꿔줌
 		number2=number2+plus*(groupnum-1);//그룹이 바뀜에 따른 end를 바꿔줌
 		 
 		 int nexts2=1+(plus*(groupnum-2));//이전 버튼 누르면 나오는 start값
			 int nexte2=pagesize+(plus*(groupnum-2));//이전 버튼 누르면 나오는 end값
			 int nextg2=groupnum-1;//이전버튼 누르면 현재 그룹에서-1
		 if(groupnum>1){//그룹이 1보다 큰 경우에만 이전버튼 출력
			 
			out.print("<a href='delivery.do?start="+nexts2+"&end="+nexte2+"&currentgroup="+nextg2+"'>이전</a>&nbsp&nbsp");
		 }	 
 		 
 			
 			for(int i=groupsize*(groupnum-1);i<(groupsize*(groupnum-1)+groupsize);i++){//현재 그룹에 따른 페이지버튼 숫자를 설정
 	 			
 	 			out.print("<a href='delivery.do?start="+number+"&end="+number2+"&currentpage="+(i+1)+"&currentgroup="+groupnum+"'>"+"["+(i+1)+"]</a>&nbsp&nbsp");
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
 			 
 			out.print("<a href='delivery.do?start="+nexts+"&end="+nexte+"&currentgroup="+nextg+"'>다음</a>");
 		 }
 %>
	</c:if>
	
	<c:if test="${isqna =='no'}">
<table>
   <tr><td height=100></tr>     
</table>
<h2>배송문의란이 비어있습니다</h2>
</c:if>
	
		
	 
	
	
	<table>
	<tr><td height=70></tr>		
	</table>
	
		<form action="qnawriteForm3.jsp" >
	
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="작성하기">
	<table>
	<tr><td height=250></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</center>
</footer>
</html>