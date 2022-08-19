 <%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항 등록</title>
</head>
<style>
body
{
  background-color: #fffff0;
}

h1{
 background-color : white;
}

footer{

  background-color : white;
  

}
table.type {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.type th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
table.type td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}


.div1{position:absolute; left:70%;}
.div2{position:absolute; left:70%;}
</style>
<body>
<h1>공지사항 작성</h1>
<form action=enrollNotice.do method=post>
<table   class=type>
<tr>
<td align="center">제목</td>
<td><input type="text" name="title"  ></td>
</tr>
            
<tr>
<td align="center">내용</td>               
<td><textarea name="content" style="width: 95%;height: 200px;" ></textarea></td>
</tr>                                        
</table>
<div class=div2><p><input type="submit" value="등록"></p></div><br><br>
</form>


</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>