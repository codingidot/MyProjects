 <%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>상품등록(관리자)</title>
</head>
<script type= "text/javascript">   


function changecat2(value){
   
   var a=document.getElementById("short");
   var b=document.getElementById("long");
   /* var c=document.getElementById("cat1"); */
   
   if(value==="상의"){ 
      a.innerHTML="반팔";
      b.innerHTML="긴팔";
    }else if(value==="하의"){
      a.innerHTML="반바지";
      b.innerHTML="긴바지";
   }else if(value==="원피스"){
      a.innerHTML="롱원피스";
      b.innerHTML="미니원피스";
   }else{
      a.innerHTML="카테고리1 선택하시오";
      b.innerHTML="카테고리1 선택하시오";
   } 
}


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
<style>
 body{background-color: #fffff0;}
table.product {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
  background-color: #fffff0;
}
table.product th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
table.product td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc; 
}



.div1{position:absolute; left:70%;}
</style>
<body>
<center>
<h1>상품등록(관리자)</h1>
<form action="productEnroll.do" method="post" enctype="multipart/form-data">
<table  class=product>
<tr>
<td>상품명</td><td><input type=text name=nameEnroll><br></td></tr>
<tr><td>가격</td><td><input type=text name=priceEnroll>원<br></td></tr>
<tr><td>S재고&nbsp</td><td> <input type="text" name="stockS"></td></tr>
<tr><td>M재고</td><td> <input type="text" name="stockM"></td></tr>
<tr><td>L재고&nbsp</td><td> <input type="text" name="stockL"></td></tr>
<tr><td>카테고리1</td><td>
<select name='cat1' id='cat1' onchange='changecat2(this.value);' >
   <option>선택하세요</option>
   <option value="상의">상의</option>
   <option value="하의">하의</option>
   <option value="원피스">원피스</option>
</select><br>
</td></tr>
<tr><td>카테고리2</td>
<td>
<span id='short'></span><input type=radio name=cat2 value=1><br>
<span id='long'></span><input type=radio name=cat2 value=2>
</td></tr>
<tr><td>사진</td><td><input type="file" name="image" id="image accept="image/*" onchange="setImage(event);"/></td></tr>
<tr><td colspan=2>
<div id="image_container"></div>
</td></tr>

</table>
<input type=submit value="상품등록">
</form>
</center>
<div class=div1><a href=c0010.jsp>관리자 홈으로</a></div>
</body>
</html>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>