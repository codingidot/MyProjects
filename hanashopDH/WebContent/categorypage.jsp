<!-- 

	작성자 : 박하나
	카테고리페이지. 헤더에서 분류 선택시 선택된 분류 나열
	최종수정일자 : 22-06-13

 -->

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<head>
<style>

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
.noiteam{
  text-align: center;
  font-size: 12px;
}
</style>
<meta charset="UTF-8">
<title>더~ 사랑스러운♥하니니♥</title>
</head>
<!-- <header> -->
<header>
<%@ include file="/layout/header.jsp" %>
</header>
<!-- </header> -->
<body>

<hr>
<br>

<c:if test = "${count == 1 }">
<p align="center"><b>BEST5</b></p><br>


<div class="row row-cols-2 row-cols-md-5" style= "border: 10px; margin-left: 3%; margin-right: 3%;">
<c:forEach var="vo" items="${requestScope.list2 }">
  <div class="col mb-3">
    <div class="card">
    <a href="showDetail.do?p_no=${vo.p_no }">
      	<c:choose>
		<c:when test="${vo.p_image != null}">
		<img src="./image/${vo.p_image }" class="card-img-top" height="300px" width="100px">
		</c:when>
		<c:otherwise>
		<img src="./image/nono.jpg" class="card-img-top" height="300px" width="100px">
		</c:otherwise>
		</c:choose>
      <div class="card-body">
        <p class="card-title">${vo.p_name }</p><hr>
        <p class="card-title"><p class="price">${vo.p_price}원</p>
<!--         <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p> -->
      </div>
      </a>
    </div>
  </div>
  </c:forEach>
</div>
</c:if>



<c:choose>
<c:when test="${count == 1 || count == 2}">
<div  style="margin-left: 3%; magin-right: 3%;">

<br><br>
<hr>
<br>

<%--     <% --%>
<!--     	if(request.getAttribute("check") == null){%> -->
    <a href="productsort.do?category=${param.category}&item=${param.item}&orderby='p_count DESC'"><span class="badge badge-pill badge-light">인기순으로 보기</span></a>
	<a href="productsort.do?category=${param.category}&item=${param.item}&orderby='p_price DESC'"><span class="badge badge-pill badge-light">높은가격순으로 보기</span></a>
	<a href="productsort.do?category=${param.category}&item=${param.item}&orderby='p_price ASC'"><span class="badge badge-pill badge-light">낮은가격순으로 보기</span></a>
<%--     <%}%> --%>
    
<br><br>
    
    
</div>
<div class="row row-cols-2 row-cols-md-4" style= "border: 10px; margin-left: 3%; margin-right: 3%;">
<c:forEach var="vo" items="${requestScope.list }">
  <div class="col mb-3">
    <div class="card">
    <a href="showDetail.do?p_no=${vo.p_no }">
      	<c:choose>
		<c:when test="${vo.p_image != null}">
		<img src="./image/${vo.p_image }" class="card-img-top" height="300px" width="100px">
		</c:when>
		<c:otherwise>
		<img src="./image/nono.jpg" class="card-img-top" height="300px" width="100px">
		</c:otherwise>
		</c:choose>
      <div class="card-body">
        <p class="card-title">${vo.p_name }</p><hr>
        <p class="card-title"><p class="price">${vo.p_price}원</p>
<!--         <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p> -->
      </div>
      </a>
    </div>
  </div>
  </c:forEach>
</div>
</c:when>
<c:otherwise>
<div class="noiteam">
<b class="notice">검색결과가 없습니다.<br>
정확한 검색어 인지 확인하시고 다시 검색해 주세요.<br></b><br>
<p>검색어/제외검색어의 입력이 정확한지 확인해 보세요.<br>
두 단어 이상의 검색어인 경우, 띄어쓰기를 확인해 보세요.<br>
검색 옵션을 다시 확인해 보세요.</p>
</div>


<br><hr><br><br>
<p align="center"><b>BEST ITEM</b></p>
<br>
<br>

<div class="row row-cols-2 row-cols-md-5" style= "border: 10px; margin-left: 3%; margin-right: 3%;">
<c:forEach var="vo" items="${requestScope.list3 }">
  <div class="col mb-3">
    <div class="card">
    <a href="showDetail.do?p_no=${vo.p_no }">
      	<c:choose>
		<c:when test="${vo.p_image != null}">
		<img src="./image/${vo.p_image }" class="card-img-top" height="300px" width="100px">
		</c:when>
		<c:otherwise>
		<img src="./image/nono.jpg" class="card-img-top" height="300px" width="100px">
		</c:otherwise>
		</c:choose>
      
      
      
	  <ul class="list-group list-group-flush">
	    <li class="list-group-item"><p>${vo.p_name }</p></li>
	    <li class="list-group-item"><p class="price">${vo.p_price}원</p></li>
	  </ul>

      </a>
    </div>
  </div>
  </c:forEach>
</div>


</c:otherwise>
</c:choose>





<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
</body>
</html>
<%@ include file="/layout/footer.jsp" %>