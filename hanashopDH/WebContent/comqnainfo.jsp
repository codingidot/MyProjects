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



#th4:first-of-type {
  border-top-left-radius: 30px;
}
#th4:first-of-type {
  border-bottom-left-radius: 30px;
}
#th4:last-of-type {
  border-top-right-radius: 30px;
}

#th4:last-of-type {
  border-bottom-right-radius: 30px; 
}


</style>


</head>
<body>




	 <c:choose>
    <c:when test="${id == vo.q_id}">
	<center>
	<h1>Q&A<hr></h1>
	
	<table>
	<tr><td height=100></tr>		
	</table>

	<table id="table1" border=1>			
	
	<tr> <th id="th1" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd ">작성자</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_id}</th>
	<tr><th id="th2" style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">제목</th>
	
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_title }</th>
	<tr><th  id="th3" style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">내용</th>
	
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_content }</th></tr>
	
	</table>
	</center>
	<table>
	<tr><td height=80></tr>		
	</table>
	<center>
	<table id="table1" border=1>
	<tr> <th id="th4" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd ">답변</th>
	
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${content}</th></tr>
	
	</table> 
	</center>		
    </c:when>
    
    <c:when test="${id == 'admin'}">
    <center>
	<h1>Q&A</h1>
	<hr>
	<table>
	<tr><td height=100></tr>		
	</table>

	<table id="table1" border=1>			
	
	<tr> <th id="th1" style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd">작성자</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_id}</th>
	<tr><th id="th2" style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">제목</th>
	
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_title }</th>
	<tr><th id="th3" style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">내용</th>
	
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_content}</th></tr>
	
	</table>
	</center>
	<table>
	<tr><td height=80></tr>		
	</table>
	
	<center>
	<table id="table1" border=1>
	<tr> <th id="th4"style="WIDTH:  200pt; HEIGHT: 40pt; background-color:#ffe5dd">답변</th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${content}</th></tr>
	</table>
	<form action="comqnaanswer.do" method = "post">
	답변<input type="text" name="q_answer"><br>
	<input type="submit" value = "등록">
	<input type="hidden" name="q_no" value=${vo.q_no } >
	<input type="hidden" name="qparentno" value=${vo.q_parentno } >
	<input type="hidden" name="q_pno" value=${vo.q_pno }>
	<input type="hidden" name="q_pw" value=${vo.q_pw } >
	<input type="hidden" name="q_date" value=${vo.q_date } >
	<input type="hidden" name="q_title" value=${vo.q_title } >
	<input type="hidden" name="q_content" value=${vo.q_content } >
	<input type="hidden" name="q_id" value=${vo.q_id } >
	</form>
	
	
	</center>		
    	
    
    </c:when>
    <c:otherwise>
		 <script>
		 
		 alert("본인만 열람가능합니다.");
		 location.href="comqna.do";
	
		 </script>
    </c:otherwise>
</c:choose>
	
	<table>
	<tr><td height=150></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>