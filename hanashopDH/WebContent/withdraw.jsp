<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<style type="text/css">



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
<title>�ֹ�����</title>
</head>
<body>
	<center><h1>ȸ��Ż��<hr></h1>
		
		<table>
		<tr><td height=200></tr>		
		</table>
		
		<h4>
		ȸ�� Ż��� 7�ϰ� �簡���� ������<br>
		Ż��� ���ÿ� ������ ���������� �����˴ϴ�.<br>
		Ż�� �� ���� �Խñ��� �������� �ʽ��ϴ�.<br>
		�Ʒ� ��ư�� �����ø� Ż�� ����˴ϴ�.
		<br><br><br>
		</h4>
		
		<form action="withdraw.do" method="get">
		<input type="submit" value="ȸ�� Ż��">
		<input type="hidden" value=${id } name = "id">
		</form>

</center>

	<table>
	<tr><td height=300></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>