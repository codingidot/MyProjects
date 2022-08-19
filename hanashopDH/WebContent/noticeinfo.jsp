<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �ۼ��� �Խñ�</title>

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
<html>
<body>

<c:choose>
    <c:when test="${id == 'admin'}">
    
	<center>
	<h1>NOTICE<hr></h1>
	
	<table>
	<tr><td height=100></tr>		
	</table>

	<table id="table1" border=1>			
	
	<tr> <th id="th1" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd">�ۼ���
	</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">������</th>
	<tr><th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">����</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.n_title }</th></tr>
	<tr><th id="th3"style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">����</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.n_content }</th></tr>
	
	</table>
	
	<table>
	<tr><td height=100></tr>		
	</table>
	
	
	
	<table border="0" align="center">			
	<th><form action="ImsiUpdatenotice.do" >
	<input id="btn1" type=submit style="WIDTH: 100pt; HEIGHT: 40pt" value="�����ϱ�">
	<input type="hidden" name="no" value=${vo.n_no }>
	</form></th>
	<th >
	<form action="noticedelete.do" >
	<input id="btn1" type=submit style="WIDTH: 100pt; HEIGHT: 40pt" value="�����ϱ�">
	<input type="hidden" name="no" value=${vo.n_no }>
	</form></th>
	</table>
	</center>
  
  </c:when>
 
    <c:otherwise>
  <center>
	<h1>NOTICE<hr></h1>
	
	<table>
	<tr><td height=100></tr>		
	</table>

	<table id="table1" border=1>			
	
	<tr> <th id="th1" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd">�ۼ���
	</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">������</th>
	<tr><th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">����</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.n_title }</th></tr>
	<tr><th id="th3"style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">����</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.n_content }</th></tr>
	
	</table>
	</center>
  	 </c:otherwise>
</c:choose>
  
	<table>
	<tr><td height=280></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>