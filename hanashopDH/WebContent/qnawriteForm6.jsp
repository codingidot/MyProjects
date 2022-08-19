<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <%
    	if(session.getAttribute("id") == null){%>
    	<script>
    		alert("로그인이 필요한 서비스입니다.")
    		window.history.back();
//     		location.href="start.do";
    	</script>
    <%}%>

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


        <div class ="container">
            <form id ="form_data" action="qnawrite6.do" method="post" enctype="multipart/form-data">
                <table class ="table table-bordered">
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td><input type ="text" name = "title" class="form-control" placeholder = "제목을 입력하세요" required></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type ="password" name = "pw" class="form-control" placeholder = "답변 확인시 필요하니 꼭 기억해주세요"></td>
                        </tr>
                        <tr>
                        	<td>분류</td>
                        	<td>
							<select name = "category" required>
					    			<option value="상품문의">상품문의</option>
					    			<option value="배송문의">배송문의</option>
					    			<option value="입고요청">입고요청</option>
					    			<option value="환불관련">환불관련</option>
					    			<option value="기타문의" selected>기타문의</option>
							</select>
  						  </td>
                        </tr>
                        <tr>
                            <td>내용</td>
							<td><textarea rows="10" cols="10" name = "content" class="form-control" 
							placeholder = "안녕하세요 하나니입니다:)" required></textarea></td>
                        </tr>
                    </tbody>
                </table>
                
					
				<!-- pno 히든으로 넘겼는데 안넘어감 -->
                  <input type="hidden" name="pno" value="${vo.pno}">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="qno" value="${qno}">
                  
                  <input type="file" name="image" id="image accept="image/*" onchange="setImage(event);"/><br><br>

                <div id="image_container"></div><!--                  <input type="file" name="SelectFile" />  <br><br> -->
                <input type = "submit" class="btn btn-outline-secondary"value = "등록">
            </form>
        </div>


</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>