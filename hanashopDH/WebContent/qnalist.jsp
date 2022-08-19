<%@page import="qna.qnaDAO.QnaDAO"%>
<%@page import="qna.qnaVO.QnaVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <%
    	if(request.getAttribute("writecheck") != null){%>
    	<script>
    		alert("성공적으로 등록되었습니다.")
    	</script>
    <%}%>
    
    
    <%
    	if(request.getAttribute("reactioncheck") != null){%>
    	<script>
    		alert("성공적으로 등록되었습니다.")
    	</script>
    <%}%>
    
    <%
    	if(request.getAttribute("deletecheck") != null){%>
    	<script>
    		alert("성공적으로 삭제되었습니다.")
    	</script>
    <%}%>    
    
    <%
    	if(request.getAttribute("updatecheck") != null){%>
    	<script>
    		alert("성공적으로 수정되었습니다.")
    		history.go(-4);
    	</script>
    <%}%>
    
    
<head>
<%
// int i=Integer.parseInt((request.getParameter("q_no")));
%>
<style>
body {
z-index: 2;
}


a:link {
  color : black;
}
a:visited {
  color : black;
}
a:hover {
  color : black;
}
a:active {
  color : black;
}

table {
margin-left:auto;
margin-right:auto;
}


.paging {
text-align: center;
}


.noqna {
text-align: center;
color : #6E6E6E;
}

table, tr, td{
 border-top: none;
 border-right: none;
 border-left: none;
 border-color: gray;
 
}

th{
 border-right: none;
 border-left: none;
 border-color: gray;
}

.reviewtable {
text-align: center;
}

.reviewtable tr, td{
 height: 40px;
}
</style>

<head>
<meta charset="UTF-8">
<title>더~ 사랑스러운♥하니니♥</title>
</head>
<header>
<%@ include file="/layout/header.jsp" %>
</header>

<body>
<div style="text-align: center; font-size: 10px;">
Q&A<br>
</div>
<div style="text-align: center; font-size: 10px; color: gray;">
상품에 대해 궁금한 점을 해결해 드립니다.
</div>
<br>
<c:choose>
<c:when test ="${count == 1 }">
<br>
<div class="reviewtable">
<table border="1">
	<tr>
	<th width="80px">번호</th>
	<th width="150px">분류</th>
	<th width="800px">제목</th>
	<th width="150px">작성자</th>
	<th width="150px">작성일</th>
	<th width="80px">조회</th>
	</tr>
	   <c:forEach var="vo" items="${requestScope.list}">
	<tr>
	<c:set var="i" value="${i+1 }"/>
		<td style="border-right: none;">${total -start - i +1}</td>
	
		
		
		<td>[${vo.q_category }]</td>
		
		<c:if test="${vo.q_parentno==vo.q_no}">
		<td align="left" style="border-right: none; padding-left: 30px;"><a href="qnapwCheckForm.do?qno=${vo.q_no}&pno=${param.pno }&parentno=${vo.q_parentno}">
		<img src="./image/lock.png" height="15px" width="15px">${vo.q_title }</a></td>
		</c:if>
		<c:if test="${vo.q_parentno!=vo.q_no}">
		<td align="left" style="border-right: none; padding-left: 40px;"><a href="qnapwCheckForm.do?qno=${vo.q_no}&pno=${param.pno }&parentno=${vo.q_parentno}">
		<img src="./image/lock.png" height="15px" width="15px">┗  ${vo.q_title }</a></td>
		</c:if>
		
		
		
		
		
		
		<td style="border-right: none;">${vo.q_id }</td>
		<td style="border-right: none;">${vo.q_date }</td>
		<td>${vo.q_hit }</td>
	</tr>
	
	</c:forEach>
	
	<tr style="text-align: right;">
		<td colspan="5"><a href="qnawriteForm.do?pno=${param.pno }"> 상품문의하기</a>	
		</td>
	</tr>
</table>
<br><br>
</div>
<br><br>

<div class=paging>
     		<c:if test="${nowPage > 2 }">
     			<a href="qnalist.do?pno=${param.pno }&start=${start - 20 }">[이전]</a>
     		</c:if>     		
     		<c:if test="${nowPage > 1 }">
     			<a href="qnalist.do?pno=${param.pno }&start=${start -10 }">[${nowPage - 1 }]</a>
     		</c:if>
     		[${nowPage }]
     		<c:if test="${nowPage < totalPage}">
     			<a href="qnalist.do?pno=${param.pno }&start=${start + 10 }">[${nowPage + 1 }]</a>
     		</c:if>
     		<c:if test="${nowPage < totalPage && totalPage >= nowPage+2}">
     			<a href="qnalist.do?pno=${param.pno }&start=${start + 20 }">[다음]</a>
     		</c:if>
</div>
</c:when>
<c:otherwise>
<div class="noqna">
<hr>
등록된 문의가 없습니다.<br><br>
<a href="qnawriteForm.do?pno=${param.pno }"><button type="button" class="btn btn-secondary btn-sm">상품문의하기</button></a>
</div>
</c:otherwise>
</c:choose>

</body>

<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>