<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<header>
<%@ include file="/layout/header.jsp" %>
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


        <div class ="container">
            <form id ="form_data" action="myreviewupdate.do">
                <table class ="table table-bordered">
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td><input type ="text" name = "r_title" class="form-control" value= ${vo4.r_title }></td>
                        </tr>
                        <tr>    
                            <td>작성자</td>
                            <td>${vo4.r_id}</td>
                        </tr>
                        <tr>
                            <td>내용</td>
							<td><textarea rows="10" cols="10" name = "r_content" class="form-control">${vo4.r_content }</textarea></td>
                        </tr>
                    </tbody>
                </table>
                 <input type="file" name="SelectFile" />  <br><br>
                <input type = "submit" class="btn btn-outline-secondary"value = "수정완료">
                <input type="hidden" name="no" value=${vo4.r_no }>
            </form>
        </div>

</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>