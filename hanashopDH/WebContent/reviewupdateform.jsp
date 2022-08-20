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
            <form id ="form_data" action="ReviewUpdate.do?p_no=${param.p_no}" method="post" enctype="multipart/form-data">
                <table class ="table table-bordered">
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td><input type ="text" value="${param.r_title}" name = "title" class="form-control" placeholder = "제목을 입력하세요" required></td>
                        </tr>
                        <tr>    
                            <td>작성자</td>
                            <td>${id}</td>
                        </tr>
                        <tr>    
                            <td>별점</td>
<!--                             <td><input type = "text" name = "star" required></td> -->

							<td>
							<select name = "star" required>
					    			<option value="5" selected>5</option>
					    			<option value="4">4</option>
					    			<option value="3">3</option>
					    			<option value="2">2</option>
					    			<option value="1">1</option>
  						  </select>
    					</td>
                        </tr>
                        <tr>
                            <td>내용</td>
							<td>
							
							<textarea rows="10" cols="10" name = "content" class="form-control"  placeholder = "상품에대한 후기를 입력하세요" required>${r_content}
							</textarea></td>
                        </tr>
                    </tbody>
                </table>
                
                  <input type="hidden" name="id" value="${id}"> 
                  <input type="hidden" name="pno" value="${param.p_no}">
                  <input type="hidden" name="rno" value="${param.r_no}">
                  <input type="file" name="image" id="image accept="image/*" onchange="setImage(event);"/><br><br>
	
				<input type="hidden" name="p_no" value="${param.p_no }">
                <div id="image_container"></div><!--                  <input type="file" name="SelectFile" />  <br><br> -->
                <input type = "submit" class="btn btn-outline-secondary"value = "후기수정">
            </form>
        </div>
<!-- <div class="wirteForm"> -->
<!-- <input type="text" name="r_title" placeholder="제목을 입력하세요"><br> -->
<!-- <textarea rows="10" cols="50" placeholder="상품에 대한 후기를 작성해주세요"></textarea> -->
<!-- </div> -->


</body>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>
</html>