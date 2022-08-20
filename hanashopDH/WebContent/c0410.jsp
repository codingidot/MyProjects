<%@page import="product.productVO.ProductVO"%>
<%@page import="java.util.ArrayList"%>
 <%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>수익현황(관리자)</title>
</head>
<style>
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

table.money {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.money th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
   background-color : #ffe5dd;
}
table.money td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
.div1{position:absolute; left:70%;}
</style>
<body>
<h1>수익현황(관리자)</h1>
<table  class=money>
<tr>
<th>카테고리1</th><th>카테고리2</th><th>판매수</th><th>수익</th>
</tr> 
<c:forEach var="vo2" items="${requestScope.plist2}"> 
 		<tr>
 		<td>${vo2.p_category}</td>
		<td>${vo2.p_category2 }</td> 
		<td>${vo2.p_count }</td>
		<td>${vo2.p_count*vo2.p_price}</td>
		<c:set var="revenue" value="${revenue + (vo2.p_count*vo2.p_price) }" />
 		</tr>
</c:forEach>
 </table>
<center> <h3> 총 수익 :<c:out value="${revenue}"  default="0" />원</div></h3></center>

</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>