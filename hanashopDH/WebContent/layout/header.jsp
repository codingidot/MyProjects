<!-- 

   작성자 : 박하나
   헤더
   최종수정일자 : 22-06-13

 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<style>
header {
  position: sticky;
  top: 0;
  opacity: 0.9;
  backdrop-filter: blur(30px);
  z-index: 3;
input {

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
</style>
<head>
<meta charset="UTF-8">
<title>더~ 사랑스러운♥하니니♥</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="mainpage.do"  style="font-size:20pt">하나니</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto" style="font-size:10pt">
      <li class="nav-item active">
        <a class="nav-link" href="selectbest.do">베스트 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="selectcategory.do?category='상의'">상의 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="selectcategory.do?category='하의'">하의 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="selectcategory.do?category='원피스'">원피스 <span class="sr-only">(current)</span></a>
      </li>
    </ul>


<c:choose>
<c:when test = "${id =='admin'}">
<a href="c0010.jsp">관리자페이지</a>
</c:when>
</c:choose>
    
    	
    
      <ul class="nav justify-content-end" style="font-size:10pt">
      <c:choose>
    <c:when test = "${id ==null}">
		     <li class="nav-item">
		       <a class="nav-link" href="login.jsp">LOGIN</a>
		     </li>
	</c:when>
	<c:otherwise>
		<li class="nav-item">
		    <a class="nav-link" href="Logout.do">LOGOUT</a>
		 </li>
	</c:otherwise>
	</c:choose>
	<c:if test = "${id == null }">
    <li class="nav-item">
       <a class="nav-link" href="joinus2.do">JOIN US</a>
     </li>
     </c:if>
     <li class="nav-item">
         <a class="nav-link" href="basketList.do?start=1&end=5&currentpage=1&currentgroup=1">CART</a>
            </li>
            
       <li class="nav-item">
       <a class="nav-link" href="wishList.do?start=1&end=5&currentpage=1&currentgroup=1">WISHLIST</a>
            </li>
            
            
            <li class="nav-item">
              <a class="nav-link" href="Imsimypage.do">MYPAGE</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="community.do">COMMUNITY</a>
            </li>
   </ul>
   
 
   <form action = "searchItem.do">
      <input name="item" type="text" placeholder="search" style = "width: 80px; border: 0; border-radius: 15px; outline: none; padding-left: 10px; background-color: rgb(233, 233, 233);" min="2">
<!--       <input type="submit" value="검섹"> -->
   </form>
   

  </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
</body>
</html>