<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>더~ 사랑스러운♥하니니♥</title>
</head>
<!-- <header> -->
<header>
<%@ include file="/layout/header.jsp" %>
</header>

<style>
.reviewlist {
margin-left: 10%;
margin-right: 10%;
margin-top: 30px;
margin-bottom: 30px;
}
.paging {
text-align: center;
}

.review-context{
margin-left: 10%;
margin-right: 10%;
}
p { margin: 0px 0px 0px 0px; line-height: 120%; }

.recommend {
color : gray;
font-size : 10px; 
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

.noreview {
text-align: center;
color : #6E6E6E;
}
</style>

     
    <%
    	if(request.getAttribute("writecheck") != null){%>
    	<script>
    		alert("감사합니다.")
    	</script>
    <%}%>
    
        
    <%
    	if(request.getAttribute("deletecheck") != null){%>
    	<script>
    		alert("삭제되었습니다.")
    	</script>
    <%}%>
    
    
    <%
    	String id = (String)session.getAttribute("id");
    	String r_id = (String)request.getAttribute("r_id");
    %>

    <%
    if(request.getAttribute("state") != null){
    	String state1 = (String)request.getAttribute("state");
    	int state = Integer.parseInt(state1);
    	
    	if(state == 0){%>
    		<script>
    			alert("로그인이 필요한 서비스입니다.")
    		</script>
    	<%}else if(state == 1){ %>
    	    <script>
    			alert("추천/비추천은 한번만 가능합니다.")
    		</script>
    	<%}else if(state == 2) { %>
    	    <script>
    			alert("감사합니다.")
    		</script>
		<%} }%>


    <%
    if(request.getAttribute("state2") != null){
    	String state2 = (String)request.getAttribute("state2");
    	int state = Integer.parseInt(state2);
    	
    	if(state == 0){%>
    		<script>
    			alert("로그인이 필요한 서비스입니다.")
    		</script>
    	<%}else if(state == 1){ %>
    	    <script>
    			alert("추천/비추천은 한번만 가능합니다.")
    		</script>
    	<%}else if(state == 2) { %>
    	    <script>
    			alert("감사합니다.")
    		</script>
		<%} }%>

<body>
<c:choose>

<c:when test="${count == 0 }">
<div class = "noreview">
<br><br>
리뷰가 없습니다.<br>
리뷰를 작성해 보세요!<br><br>
<a href="reivewWriteForm.do?p_no=${param.p_no}"><button type="button" class="btn btn-secondary btn-sm">상품 리뷰 작성하기</button></a>
</div>

</c:when>
<c:otherwise>
<br><br>
<div class = "review-context">
<p style="text-align: left; line-height: 0.0;">REVIEW</p>
<p style="text-align: right; line-height: 0.0;"><a href="reivewWriteForm.do?p_no=${param.p_no}">리뷰작성하기</a></p>
</div>
<div class="reviewlist">


<div class="sort">
<a href="productReview.do?rno=${vo.r_no }&p_no=${param.p_no}">최신순</a> <a href="ReviewSortlike.do?rno=${vo.r_no }&p_no=${param.p_no}">추천순</a>
</div>

 <hr>
<c:forEach var="vo" items="${requestScope.list}">

<c:if test="${vo.r_image != null}">
<img src="./image/${vo.r_image }" alt="./image/nopic.png" height="100px" width="100px"><br>
</c:if>
<c:if test="${vo.r_image == null}">
<br>
</c:if>

<%-- ${vo.r_star}<br> --%>

	<c:choose> 
	<c:when test="${vo.r_star == 5}">
		<img src="./image/star5.jpg"><br>
	</c:when> 
	<c:when test="${vo.r_star == 4}">
		<img src="./image/star4.jpg"><br>
	</c:when>  
	<c:when test="${vo.r_star == 3}">
		<img src="./image/star3.jpg"><br>
	</c:when> 
	<c:when test="${vo.r_star == 2}">
		<img src="./image/star2.jpg"><br>
	</c:when> 
	<c:when test="${vo.r_star == 1}">
		<img src="./image/star1.jpg"><br>
	</c:when>
	</c:choose>
${vo.r_title }<br><br>
${vo.r_content }<br><br>
<div style="font-size: 12px; color:gray;">
이 리뷰를 ${vo.r_like} 명이 좋아합니다.<br>
이 리뷰를 ${vo.r_dislike} 명이 싫어합니다.<br><br>
</div>
<div class="recommend">
<a href = "ReviewUpbutton.do?r_no=${vo.r_no }&p_no=${param.p_no}&id=${id} "><img src="./image/good.png" alt="도움돼요" height="15px" width="15px">도움되요</a>
<a href = "ReviewDownbutton.do?r_no=${vo.r_no }&p_no=${param.p_no}&id=${id}"><img src="./image/bad.png" alt="도움안돼요"height="15px" width="15px">도움안되요</a>

</div>
<div>

<c:choose>
<c:when test = "${id =='admin'}">
<a href = "ReviewDelete.do?r_no=${vo.r_no }">삭제</a>
</c:when>
<c:when test = "${id == vo.r_id}">
<a href = "ReviewUpdateForm.do?r_no=${vo.r_no }&r_title=${vo.r_title}&r_content=${vo.r_content}&p_no=${param.p_no}">수정</a><br>
<a href = "ReviewDelete.do?r_no=${vo.r_no }&p_no=${param.p_no}">삭제</a>
</c:when>
</c:choose>
</div>



<hr>
</c:forEach>

</div>

<div class=paging>
     		<c:if test="${nowPage > 2 }">
     			<a href="productReview.do?p_no=${param.p_no }&start=${start - 10 }">[이전]</a>
     		</c:if>     		
     		<c:if test="${nowPage > 1 }">
     			<a href="productReview.do?p_no=${param.p_no }&start=${start -5 }">[${nowPage - 1 }]</a>
     		</c:if>
     		[${nowPage }]
     		<c:if test="${nowPage < totalPage}">
     			<a href="productReview.do?p_no=${param.p_no }&start=${start +5 }">[${nowPage + 1 }]</a>
     		</c:if>
     		<c:if test="${nowPage+1 < totalPage}">
     			<a href="productReview.do?p_no=${param.p_no }&start=${start + 10 }">[다음]</a>
     		</c:if>
</div>


</c:otherwise>
</c:choose>



<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
</body>
</html>
<%@ include file="/layout/footer.jsp" %>