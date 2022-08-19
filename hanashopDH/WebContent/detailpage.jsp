<!-- 

	작성자 : 박하나
	제품상세페이지
	최종수정일자 : 22-06-13

 -->

<%@page import="product.productDAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
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
</style>



<header>
<%@ include file="/layout/header.jsp" %>
</header>
<head>
<meta charset="UTF-8">
<title>${vo.p_name }</title>
</head>

<script>

function buy(){
	var form1=document.getElementById("frm1");

	 form1.action="getPoint.do"
	form1.submit(); 

}


function wish(){
	   var form1=document.getElementById("frm1");

	    form1.action="insertWish.do"
	   form1.submit(); 

	}


</script>




<body>
<br><br>


<form action="enrollBasket.do" id="frm1">
<input type=hidden name=price value="${vo.p_price }"> 
<input type=hidden name=name value="${vo.p_name }"> 
<input type=hidden name=no value="${vo.p_no }"> 
<input type=hidden name=key value=0>
<div class="media">

      	<c:choose>
		<c:when test="${vo.p_image != null}">
		<img src="./image/${vo.p_image }" class="mr-3" alt="제품이미지" width="400px" height=400px style="margin-left: 15%;">
		</c:when>
		<c:otherwise>
		<img src="./image/nono.jpg"  class="mr-3" alt="제품이미지" width="400px" height=400px style="margin-left: 15%;">
		</c:otherwise>
		</c:choose>
		
<%--   <img src="./image/${vo.getP_image() }" class="mr-3" alt="제품이미지" width="20%" height="600%" style="margin-left: 15%;"> --%>
  <div class="media-body" style="margin-left: 10%; margin-right: 10%">
    <h5 class="mt-3">${vo.p_name }</h5>
    <hr>
    <p>판매가 &emsp;&emsp; ${vo.p_price } 원</p>
    <p>적립금 &emsp;&emsp; <fmt:parseNumber var="price" integerOnly="true" value="${vo.p_price/100 }"/>${price } 원</p>
    <hr>
    <p>사이즈 &emsp;&emsp;
     <select name = "size">
    			<option value="S">S</option>
    			<option value="M">M</option>
    			<option value="L">L</option>
    </select>
        
    <p>구매수량&emsp;&nbsp;&nbsp;<input type="number" name="count" min="1" max="300" step="1" value="1"><br>
    </p>
    
    <hr>
    <button type="button" class="btn btn-outline-success" onclick="buy()" >구매하기</button></a>
    <input type="submit"  name="option12" value="장바구니넣기" class="btn btn-outline-info">
    <button type="button" class="btn btn-outline-success" onclick="wish()" >위시리스트추가</button>
    <hr>
     <a href="productReview.do?p_no=${vo.p_no }&start=0"><button type="button" class="btn btn-outline-primary">구매후기</button></a>
    <a href="qnalist.do?pno=${vo.p_no }"><button type="button" class="btn btn-outline-secondary">상품 Q&amp;A</button></a>
     
    </form> 
  </div>
</div>


<br><br>
</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>