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
<title>주문내역</title>
</head>
<body>
	<center><h1>회원탈퇴<hr></h1>
		
		<table>
		<tr><td height=200></tr>		
		</table>
		
		<h4>
		회원 탈퇴시 7일간 재가입이 어려우며<br>
		탈퇴와 동시에 고객님의 개인정보는 삭제됩니다.<br>
		탈퇴 후 공개 게시글은 삭제되지 않습니다.<br>
		아래 버튼을 누르시면 탈퇴가 진행됩니다.
		<br><br><br>
		</h4>
		
		<form action="withdraw.do" method="get">
		<input type="submit" value="회원 탈퇴">
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