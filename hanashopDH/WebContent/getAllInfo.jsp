<%@page import="member.memberVO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<style type="text/css">
</style>
<title>Insert title here</title>
</head>
<body>
	<center><h3>������ ȸ�� ���� ����</h3></center>

	<hr>
	<table>
	<tr><td height=100></tr>		
	</table>
	
	
						
		
			<form action="getInfo.do"> 
				<input type="hidden" name="id" value=${id}>
			</form>
		
	
	<c:forEach var="vo" items="${requestScope.my}">
	<center>
	<table>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">���̵� : ${vo.u_id }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">��й�ȣ : ${vo.u_password }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">�̸� : ${vo.u_name }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">��ȭ��ȣ : ${vo.u_tel}</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">�ּ� : ${vo.u_address}</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">������� : ${vo.u_d }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">�̸��� : ${vo.u_email }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">����Ʈ : ${vo.u_point }</td></tr>
		</center>
	</c:forEach>
		</table>
</body>
<table>
	<tr><td height=150></tr>		
	</table>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>