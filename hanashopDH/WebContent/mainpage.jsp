<!-- 

	작성자 : 박하나
	메인페이지
	최종수정일자 : 22-06-13

 -->

<%@page import="com.product.my.ProductShowAll"%>
<%@page import="product.productVO.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<head>
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
</style>
<meta charset="UTF-8">
<title>더~ 사랑스러운♥하니니♥</title>
</head>
<!-- <header> -->
<header>
<%@ include file="/layout/header.jsp" %>
</header>
<!-- </header> -->
<script>
//팝업 관련 내용#########################################################################################################
var myPopup=document.querySelector('.popup'),
checkbox=document.querySelector('#popup'),
popupClose=document.querySelector('.close');


//쿠키생성
function setCookies(name,value,day){
   var date=new Date(); //현재날짜지정
   date.setDate(date.getDate()+day);
   
   var mycookie='';
   mycookie+=name+'='+value+';';
   mycookie+='Expires=' +date.toUTCString();
   
   document.cookie=mycookie; //쿠키 설정, 생성
   
}



function checkCookie(name){
   var cookies=document.cookie.split(';');
   var visited=false; //방문 거짓
      for(var i in cookies){
         if(cookies[i].indexOf(name)>-1){
            
            visited=true; //방문한적 있음
            
         }
      }
   if(visited){
      //쿠키가 있는 상태로 팝업이 나타나지 않는다
      
   }else{
      setCookies('cookiename','Hi',7);
      window.open("http://localhost:8080/hanashopDH/popup1.jsp", "PopupWin", "width=500,height=600");
      }
}

window.onload = function(){
   checkCookie('cookiename');
}
//###############################################################################################

</script>
<body>

<br>


<div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active" data-interval="3000">
      <img src="./image/site.jpg" class="d-block w-100" alt="대문이미지">
    </div>
  <button class="carousel-control-prev" type="button" data-target="#carouselExampleFade" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExampleFade" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </button>
</div>


<br><hr><br><br>
<p align="center"><b>BEST ITEM</b></p>
<br>
<br>

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
      
      
      
	  <ul class="list-group list-group-flush">
	    <li class="list-group-item"><p>${vo.p_name }</p></li>
	    <li class="list-group-item"><p class="price">${vo.p_price}원</p></li>
	  </ul>

      </a>
    </div>
  </div>
  </c:forEach>
</div>







<hr><br><br>
<p align="center"><b>NEW ARRIVAL ITEM</b></p>
<br>
<br>

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
      
      
      
	  <ul class="list-group list-group-flush">
	    <li class="list-group-item"><p>${vo.p_name }</p></li>
	    <li class="list-group-item"><p class="price">${vo.p_price}원</p></li>
	  </ul>

      </a>
    </div>
  </div>
  </c:forEach>
</div>






<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>