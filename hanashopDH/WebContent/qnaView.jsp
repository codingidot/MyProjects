<%@page import="com.qna.my.QnaView"%>
<%@page import="qna.qnaDAO.QnaDAO"%>
<%@page import="qna.qnaVO.QnaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
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


table {
    margin-left:20%; 
    margin-right:20%;
}

.content{
	padding-left: 80px;
    border-bottom : 1px solid #D8D8D8;
}

.content2{
	padding-left: 80px;
    border-bottom : 1px solid #6E6E6E;
}

.content3{
	padding-left: 0px;
    border-bottom : 1px solid #D8D8D8;
}

.category{
  text-align: center;
  border-bottom : 1px solid #D8D8D8;
}

.category2{
  text-align: center;
  border-bottom : 1px solid #6E6E6E;
}

.forheight{
  line-height: 50px;
}
body {
z-index: 2;
}

</style>
    
    
        
    <%
    	//비밀번호가 맞거나 // 혹은 
    	try{
    	if(request.getAttribute("pwcheck") != null || session.getAttribute("id").equals("admin") ){
    	//그외의 모든 경우에 대해서
    	
   		}else{%>
   	    	<script>
    		alert("비밀번호가 일치하지 않습니다.")
    		history.go(-2);
    	</script>
   		<% }
    	}catch(Exception e){%>

    	<script>
    		alert("비밀번호가 일치하지 않습니다.")
    		history.go(-2);
    	</script>
    <%
    }
    %>
    


    	

		
<head>
<meta charset="UTF-8">
<title>더~ 사랑스러운♥하니니♥</title>
</head>
<header>
<%@ include file="/layout/header.jsp" %>
</header>
</head>
<body>
<div class="forheight">
<br>
</div>


<c:if test="${type == 0 }">
<!-- 답변글 -->

<table>
	<tr>
	<td class="category" height="50px;">등록일</td><td class="content">${vo.q_date }</td>
	</tr>
	<tr>
	<td class="category" height="50px;">제목</td><td class="content">${vo.q_title }</td>
	</tr>

	<!-- if 이미지가 있으면 -->
	<tr>
		<td class="category2"height="300px;" width="200px">내용</td>
		<c:choose>
		<c:when test="${vo.q_image !=null }">
		<td class="content2" width="1400px;" colspan="3"><img src="./image/${vo.q_image }" alt="./image/nono.jpg" height="100px;" width="100px;"><br>${content }</td>
		</c:when>
		<c:otherwise>
		<td class="content2" width="1400px;" colspan="3">${content }</td>
		</c:otherwise>
		</c:choose>
	</tr>
	 
	<tr>
	<td class="category"height="300px;" width="200px">답변</td><td class="content" width="1300px;">${vo.q_content }</td>
	</tr>
	<tr>
		<td colspan="4"  style="text-align: right; padding-right: 20px;">
	<a href="qnalist.do?pno=${param.pno }">목록으로</a>
	</tr>
</table>
<!-- 	<label  style="text-align: right; padding-right: 20px;">목록으로</label> -->
</c:if>


<c:if test="${type == 1 && vo.q_state =='처리완료'}">
<!-- 질문글이고 답변이 달려있으면 -->

<table>
	<tr>
	<td class="category" height="50px;" width="400px;">등록일</td><td class="content3" width="400px;">${vo.q_date }</td>
	<td class="category" height="50px;" width="400px;">처리상태</td><td class="content3" width="400px;">${vo.q_state}</td>
	</tr>
	<tr>
	<td class="category" height="50px;">제목</td><td class="content" colspan="3">${vo.q_title }</td>
	</tr>
	<tr>
		<td class="category2"height="300px;" width="200px">내용</td>
		<c:choose>
		<c:when test="${vo.q_image !=null }">
		<td class="content2" width="1400px;" colspan="3"><img src="./image/${vo.q_image }" alt="./image/nono.jpg" height="100px;" width="100px;"><br>${content }</td>
		</c:when>
		<c:otherwise>
		<td class="content2" width="1400px;" colspan="3">${content }</td>
		</c:otherwise>
		</c:choose>
	</tr>
	<tr>
	<td class="category"height="300px;" width="200px">답변</td><td class="content" width="1300px;" colspan="3">${content2 }</td>
	</tr>
	
	
	<c:if test = "${id =='admin'}">
	<tr>
	<td colspan="4" style="text-align: right; padding-right: 20px;" height="50px">
	<a href="qnareactionForm.do?parentno=${vo.q_parentno }&qno=${vo.q_no }&pno=${param.pno }&pw=${vo.q_pw}&date=${vo.q_date }&title=${vo.q_title }&content=${vo.q_content }&category=${vo.q_category}">
	답변달기</a>
	<a href="qnadelete.do?qparentno=${vo.q_parentno}&action='delete'&pno=${param.pno}">리뷰삭제</a>
	<a href="qnalist.do?pno=${param.pno }">목록으로</a>
	</td>
	</tr>
	</c:if>
	
	<c:if test = "${id == vo.q_id}">
	<tr>
	<td colspan="4" style="text-align: right; padding-right: 20px;" height="50px">
	<a href="qnadelete.do?qparentno=${vo.q_parentno}&action='delete'&pno=${param.pno}">리뷰삭제</a>
	<a href="qnalist.do?pno=${param.pno }">목록으로</a>
	</td>
	</tr>
	</c:if>
	
	
	
</table>
</c:if>


<c:if test="${type == 1 && vo.q_state =='답변대기'}">
<!-- 질문글이고 답변이 없으면 -->

<table>
	<tr>
	<td class="category" height="50px;" width="400px;">등록일</td><td class="content3" width="400px;">${vo.q_date }</td>
	<td class="category" height="50px;" width="400px;">처리상태</td><td class="content3" width="400px;">${vo.q_state}</td>
	</tr>
	<tr>
	<td class="category" height="50px;">제목</td><td class="content" colspan="3">${vo.q_title }</td>
	</tr>
	<tr>
		<td class="category2"height="300px;" width="200px">내용</td>
		<c:choose>
		<c:when test="${vo.q_image !=null }">
		<td class="content2" width="1400px;" colspan="3"><img src="./image/${vo.q_image }" alt="./image/nono.jpg" height="100px;" width="100px;"><br>${content }</td>
		</c:when>
		<c:otherwise>
		<td class="content2" width="1400px;" colspan="3">${content }</td>
		</c:otherwise>
		</c:choose>
	</tr>
	
	
	<c:if test = "${id =='admin'}">
	<tr>
	<td colspan="4" style="text-align: right; padding-right: 20px;" height="50px">
	<a href="qnareactionForm.do?parentno=${vo.q_parentno }&qno=${vo.q_no }&pno=${param.pno }&pw=${vo.q_pw}&date=${vo.q_date }&title=${vo.q_title }&content=${vo.q_content }&category=${vo.q_category}">
	답변달기</a>
	<a href="qnadelete.do?qparentno=${vo.q_parentno}&action='delete'&pno=${param.pno}">리뷰삭제</a>
	<a href="qnalist.do?pno=${param.pno }">목록으로</a>
	</td>
	</tr>	
	</c:if>
	
	
	<c:if test = "${id == vo.q_id}">
	<tr>
	<td colspan="4" style="text-align: right; padding-right: 20px;" height="50px">
	<a href="qnadelete.do?qparentno=${vo.q_parentno}&action='delete'&pno=${param.pno}&start=0">리뷰삭제</a>
	&emsp;<a href="qnaupdateForm.do?qno=${vo.q_no}&content=${vo.q_content}">리뷰수정</a>
	<a href="qnalist.do?pno=${param.pno }">목록으로</a>
	</td>
	</tr>
	</c:if>
	
</table>
</c:if>



</body>

<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>