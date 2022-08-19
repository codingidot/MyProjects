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
	
	
	
			
	<table >
	<tr><td height=100></tr>		
	</table>
	
	<c:if test="${isdelivery !='no'}">
	
	<table id="table1" border=1>	
	<%int num=0; %>
	<tr> <th style="WIDTH:  100pt; HEIGHT: 40pt; background-color:#ffe5dd ">번호</th>
			<th style="WIDTH: 300pt; HEIGHT: 40pt;  background-color:#ffe5dd">제목</th>
			<th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">작성자</th>
	</tr>	
	<c:forEach var="vo" items="${requestScope.my1}">
	
	
	<tr><th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_no }</th>
	
 	<th style="WIDTH: 200pt; HEIGHT: 40pt">
 	<a href="qanswerinfo.do?no=${vo.q_no}">${vo.q_title }</a></th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_id }</th>
    </tr>
	</c:forEach>
	
	</table>
	</c:if>
	
	<c:if test="${isdelivery =='no'}">
<table>
   <tr><td height=100></tr>      
</table>
<h2>배송문의란이 비어있습니다</h2>
</c:if>
	
	<table>
	<tr><td height=70></tr>		
	</table>
	
		<form action="qanswerwrite.jsp" >
	
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