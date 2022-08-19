<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>

.context {
  margin-left: 20%;
  margin-right: 20%;
}
.content {
  height: 300px;
  line-height: 300px;
}


body {
z-index: 2;
}

</style>
<script type="text/javascript">
function setImage(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      img.setAttribute("src", event.target.result);
      document.querySelector("div#image_container").appendChild(img);
    };

    reader.readAsDataURL(event.target.files[0]);
  }
  
  
</script>
<header>
<%@ include file="/layout/header.jsp" %>
<%--     <% --%>
<!--     	if(session.getAttribute("id") != "admin"){%> -->
<!--     	<script> -->
<!-- //     		alert("관리자용 페이지입니다.") -->
<!-- //     		window.history.back(); -->
<!-- //     		location.href="start.do"; -->
<!--     	</script> -->
<%--     <%}%> --%>

</header>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div style="text-align: center; font-size: 10px;">
Q&A<br>
</div>
<div style="text-align: center; font-size: 10px; color: gray;">
상품 Q&A입니다.
</div>
<br><br>

<form action="qnareaction.do">
<div class="context">
동록일 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;${param.date }
<hr>
<input type="text" class="form-control"  name="title" size="30" value="${param.title }">
<hr>

<textarea rows="10" cols="10" name = "content" class="form-control" required>${content }</textarea>
<hr>



<!-- <div class="content"> -->
<%-- 문의내용 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;${param.content } --%>
<!-- <hr> -->

<input type="hidden" name="pno" value="${param.pno}">
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="qno" value="${qno}">
<input type="hidden" name="category" value="${param.category}">
<input type="hidden" name="parentno" value="${param.parentno}">
<input type="hidden" name="pw" value="${param.pw}">
<input type="hidden" name="title" value="${param.title }">

<input type = "submit" class="btn btn-outline-secondary"value = "등록">
</div>
</form>

<hr>
</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>