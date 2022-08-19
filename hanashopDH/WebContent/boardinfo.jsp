<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내가 작성한 게시글</title>
<style type="text/css">

#btn1{
  border-top-left-radius: 30px;
   border-top-right-radius: 30px;
    border-bottom-left-radius: 30px;
      border-bottom-right-radius: 30px; 
    
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

#th1:first-of-type {
  border-top-left-radius: 30px;
}
#th1:first-of-type {
  border-bottom-left-radius: 0px;
}
#th1:last-of-type {
  border-top-right-radius: 30px;
}

#th1:last-of-type {
  border-bottom-right-radius: 30px; 
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


#th3:first-of-type {
  border-top-left-radius: 0px;
}
#th3:first-of-type {
  border-bottom-left-radius: 30px;
}
#th3:last-of-type {
  border-top-right-radius: 30px;
}

#th3:last-of-type {
  border-bottom-right-radius: 30px; 
}



</style>
</head>
<body>
	
	<center>
	<h1>Review<hr></h1>
	<table>
	<tr><td height=100></tr>		
	</table>
	<table id="table1" border="1" align="center">			
	
	<tr> <th id="th1" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd">작성자 아이디</th>
	<th>${vo4.r_id}</th>
	<tr><th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">별점</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">
	<c:choose> 
	<c:when test="${vo4.r_star == 5}">
		<img src="./starimage/star5.jpg">
	</c:when> 
	<c:when test="${vo4.r_star == 4}">
		<img src="./starimage/star4.jpg">
	</c:when>  
	<c:when test="${vo4.r_star == 3}">
		<img src="./starimage/star3.jpg">
	</c:when> 
	<c:when test="${vo4.r_star == 2}">
		<img src="./starimage/star2.jpg">
	</c:when> 
	<c:when test="${vo4.r_star == 1}">
		<img src="./starimage/star1.jpg">
	</c:when> 
</c:choose> </th>
</tr>
	<tr><th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">제목</th>
	
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo4.r_title }</th></tr>
	<tr><th id="th3" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd">내용</th>
	
	<th  style="WIDTH:  200pt; HEIGHT: 40pt">${vo4.r_content }</th></tr>
	
	</table>
	
	<table>
	<tr><td height=100></tr>		
	</table>
	
	<table border="0" align="center">			
	<th><form action="ImsiUpdatereview.do" >
	<input id="btn1" type=submit style="WIDTH: 100pt; HEIGHT: 40pt" value="수정하기">
	<input type="hidden" name="no" value=${vo4.r_no }>
	</form></th>
	<th >
	<form action="myreviewdelete.do" >
	<input id="btn1" type=submit style="WIDTH: 100pt; HEIGHT: 40pt" value="삭제하기">
	<input type="hidden" name="no" value=${vo4.r_no }>
	</form></th>
	</table>

	<table>
	<tr><td height=280></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>