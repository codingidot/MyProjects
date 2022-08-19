<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<header>
<%@ include file="/layout/header.jsp" %>

    <%
	if(session.getAttribute("id")==null){ %>
		<script>
			alert("로그인이 필요한 서비스 입니다.")
			location.href="LoginForm.do"	
		</script>
	<%} %>
</header>
<style>
.wirteForm{
   container: center;
}
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center><h1>배송문의</h1></center> <hr>
        <div class ="container">
           <form action="qanswerwrite.do" method="post">
                <table class ="table table-bordered">
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td><input type ="text" name = "title" class="form-control" placeholder = "제목을 입력하세요" required></td>
                        </tr>
                        <tr>    
                            <td>작성자</td>
                            <td><input type="text"  name = "id" size=14 value=${id} readonly></td>
                        </tr>
                        <tr>
                            <td>내용</td>
                     <td><textarea rows="10" cols="10" name = "content" class="form-control" placeholder = "내용을 입력하세요" required></textarea></td>
                        </tr>
                    </tbody>
                </table>
                
                <input type="hidden" value=${id} name = "sid">
			<input type="submit" value="등록">
			<input type="reset" value="취소">
            </form>
        </div>

</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>