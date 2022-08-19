  <%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 관리자페이지 -->
<!DOCTYPE html>
<html>

 <style>
       table {
        width: 100%;
        border-top: 1px solid #444444;
        border-collapse: collapse;
      }
      th, td {
        border-bottom: 1px solid #444444;
        padding: 10px;
        text-align: center;
      }
      
      #table1 {

  border-collapse: collapse;
  border-radius: 30px;
  border-style: hidden;
  box-shadow: 0 0 0 1px #000;
}
 
th, td {
  text-align: center;
}


    </style>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style>
#btn1{
   background-color : #ffe5dd;
   border : 0;
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
<body>
<h1>관리자 페이지<hr></h1>
<table>
<tr>
<td><b><a href="merchandise.do?start=1&end=5&currentpage=1&currentgroup=1">상품관리</a></b></td>
						<!--여기서 페이지 들어갔을 때 처음에 출력되는 범위를 설정한다  -->
<td><b><a href="member.do?start=1&end=5&currentpage=1&currentgroup=1">회원관리</a></b></td>
<td><b><a href="money.do">매출관리</a></b></td>
<td><b><a href="c0500.jsp">공지사항 등록</a></b></td>
</tr>
<tr><td colspan=4; height=400></tr>
</table>


</body>

<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>