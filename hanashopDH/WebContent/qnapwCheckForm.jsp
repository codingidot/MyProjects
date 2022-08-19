<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
body{
text-align: center}
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><br><br><br>
이글은 비밀글입니다. 비밀번호를 입력해주세요<br>
관리자는 확인버튼만 누르셔도 됩니다.
<br><br>

<!-- <form action="qnapwcheck.do"> -->

<form action="qnaView.do">
비밀번호&nbsp; <input type="password" name="pw">

<input type ="hidden" name="qno" value="${param.qno }">
<input type ="hidden" name="pno" value="${param.pno }">
<input type ="hidden" name="parentno" value="${param.parentno }">
<input type="submit" value="확인">
</form>
<br>
<a href="qnalist.do?pno=${param.pno }"><button>목록</button></a>




</body>
</html>