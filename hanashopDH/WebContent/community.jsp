<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<style type="text/css">
#table{

	border : 0;
}

#btn1{
	background-color : #ffe5dd;
	border : 0;
	 border-radius: 12px;
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
</style>
<title>커뮤니티</title>
</head>


<body>

	<center><h1>Community<hr></h1></center>
	
		

	<table align="center">
		<tr><td colspan=4; height=100></tr>
		<tr>
		<td width=250>
		
			<form action="notice.do?start=1&end=5&currentpage=1&currentgroup=1" >
			
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="공지사항">
			</form>	 </td>
			
			<td width=250>
			
			
		<form action="qnatype.jsp"> 
			
			
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="Q&A">
			</form>	
			</td>
			
	
		<tr><td colspan=4; height=350></tr>
	</table>


</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>

</footer>
</html>