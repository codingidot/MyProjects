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
	<center><h3>수정된 회원 정보 보기</h3></center>

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
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">아이디 : ${vo.u_id }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">비밀번호 : ${vo.u_password }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">이름 : ${vo.u_name }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">전화번호 : ${vo.u_tel}</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">주소 : ${vo.u_address}</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">생년월일 : ${vo.u_d }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">이메일 : ${vo.u_email }</td></tr>
			<tr><td style="WIDTH:  200pt; HEIGHT: 40pt">포인트 : ${vo.u_point }</td></tr>
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