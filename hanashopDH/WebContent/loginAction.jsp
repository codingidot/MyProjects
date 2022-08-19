<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>

	
if(${result2='1' }){
	alert("아이디 및 비밀번호가 불일치합니다");
	history.back();
}else if(${result2='2' }){
	alert("아이디 및 비밀번호가 불일치합니다");
	history.back();
}
</script>

</body>
</html>