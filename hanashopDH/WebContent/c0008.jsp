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
<%
	if(session.getAttribute("id")==null){ %>
		<script>
			alert("�α����� �ʿ��� ���� �Դϴ�.")
			location.href="login.jsp"	
		</script>
	<%} %>



<title>����������</title>
</head>


<body>

	
	
	<center><h1>����������<hr></h1></center>
	
		

	<table align="center">
		<tr><td colspan=4; height=100></tr>
		<tr>
		<td width=250>
		
			<form action="orderlist.do" ><!-- form�±׿��� ������Ʈ���� ���� �ʴ´� -->
			<input type=hidden name=start value=1>
			<input type=hidden name=end value=5>
			<input type=hidden name=currentpage value=1>
			<input type=hidden name=currentgroup value=1>
			
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="�ֹ�����">
			</form>	 </td>
			
			<td width=250>
		<form action="myboard.do"> 
			<!-- <input type="hidden" name="id" value="test1"> -->
			<input type=hidden name=start value=1>
			<input type=hidden name=end value=5>
			<input type=hidden name=currentpage value=1>
			<input type=hidden name=currentgroup value=1>
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="�� �ۼ��� ����">
			
			</form>	
			</td>
			
		<td width=250>
		<form action="Imsiupdate.do"> 			
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="�� ���� �����ϱ�">
			</form>	
			
		</td>
			<td width=250>
		<form action="Imsiwithdraw.do"> 
			<!-- <input type="hidden" name="name" value="test2"> -->
			
			<input  id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="ȸ��Ż��">
			</form>	
			
		</td>
		</tr>
		<tr><td colspan=4; height=350></tr>
	</table>


</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>

</footer>
</html>

