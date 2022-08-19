<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
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
<body>
<form action=thisUpdate.do method=post enctype="multipart/form-data">
<table>
<tr>
<td>상품번호</td><td><input type=text name=no value=${p_no } readonly></td>
</tr>
<tr>
<td>상품명</td><td><input type=text name=name value=${p_name }></td>
</tr>
<tr>
<td>가격</td><td><input type=text name=price value=${p_price }></td>
</tr>
<tr>
<td>카테고리1</td><td>
<select name='cat1' id='cat1' onchange='changecat2(this.value);' >
   <option>선택하세요</option>
   <option value="상의">상의</option>
   <option value="하의">하의</option>
   <option value="원피스">원피스</option>
</select><br>
</td>
</tr>
<tr>
<td>카테고리2</td>
<td>
<span id='short'></span><input type=radio name=cat2 value=1><br>
<span id='long'></span><input type=radio name=cat2 value=2>
</td>
</tr>
<tr>
<td>S 재고</td><td><input type=text name=stockS value=${p_stockS }></td>
</tr>
<tr>
<td>M 재고</td><td><input type=text name=stockM value=${p_stockM }></td>
</tr>
<tr>
<td>L 재고</td><td><input type=text name=stockL value=${p_stockL }></td>
</tr>
<tr>
<td>사진</td><td><input type="file" name="image" id="image accept="image/*" onchange="setImage(event);"/></td>
</tr>
<tr>
<td colspan=2><div id="image_container"></div></td>
</tr>

</table>
<input type=submit value=수정하기>
</form>
</body>
</html>
