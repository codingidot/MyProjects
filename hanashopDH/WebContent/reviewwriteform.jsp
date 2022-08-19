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
    		location.href="LoginForm.do";
    	</script>
    <%}%>

</header>
<style>
.wirteForm{
	container: center;
}

  .star {
    position: relative;
    font-size: 2rem;
    color: #ddd;
  }
  
  .star input {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    opacity: 0;
    cursor: pointer;
  }
  
  .star span {
    width: 0;
    position: absolute; 
    left: 0;
    color: red;
    overflow: hidden;
    pointer-events: none;
  }
  
.category{
  align-content: center;
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
<title>Insert title here</title>
</head>
<body>


        <div class ="container">
            <form id ="form_data" action="ReviewWrite.do?p_no=${param.p_no }" method="post" enctype="multipart/form-data">
<%--             <form id ="form_data" action="ReviewWrite.do?pno=${param.p_no}&start=0" method="post" enctype="multipart/form-data"> --%>
                <table class ="table table-bordered">
                    <tbody>
                        <tr>
                            <td style="align-content: center;">제목</td>
                            <td><input type ="text" name = "title" class="form-control" placeholder = "제목을 입력하세요" required></td>
                        </tr>
                        <tr>    
                            <td class="category">작성자</td>
                            <td>${id}</td>
                        </tr>
                        <tr>    
                            <td class="category">별점</td>
<!--                             <td><input type = "text" name = "star" required></td> -->

							<td>
							<select name = "star" required>
					    			<option value="5">5</option>
					    			<option value="4">4</option>
					    			<option value="3">3</option>
					    			<option value="2">2</option>
					    			<option value="1">1</option>
  						  </select>
    					</td>
                        </tr>
                        <tr>
                            <td class="category">내용</td>
							<td><textarea rows="10" cols="10" name = "content" class="form-control" placeholder = "상품에대한 후기를 입력하세요" required></textarea></td>
                        </tr>
                    </tbody>
                </table>
                
                  <input type="hidden" name="id" value="${id}"> 
                  <input type="hidden" name="pno" value="${param.p_no}">
                  <input type="file" name="image" id="image accept="image/*" onchange="setImage(event);"/><br><br>

                <div id="image_container"></div><!--                  <input type="file" name="SelectFile" />  <br><br> -->
                <input type = "submit" class="btn btn-outline-secondary"value = "후기작성">
            </form>
        </div>


</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>