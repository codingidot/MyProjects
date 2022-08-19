<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내 정보 수정</title>
<style>

table.update {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.update th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
table.update td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}


#btn1{
  border-top-left-radius: 30px;
   border-top-right-radius: 30px;
    border-bottom-left-radius: 30px;
      border-bottom-right-radius: 30px; 
    
   background-color : #ffe5dd;
   border : 0;
}


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

</style>


</head>

<body>
	<center>
	<h1>내 정보 수정<hr></h1>	
	
	</center>
	
	<table>
	<tr><td height=100></tr>		
	</table>
	
	<center>
	<table class="update" align="center">
	<form action="UpdateInfo.do" method="post">
	
			<tr><td width="250px">아이디</td><td width="px"><input type="text"  name = "id" size=14 value=${id} readonly></td></tr>
			<tr><td width="120px">이름 </td><td> <input type="text"  name = "name" size=14></td></tr>
			<tr><td  width="70px">비밀번호</td><td> <input type="password" name = "pw" size=14></td></tr>	
			<tr><td  width="70px">전화번호 </td><td><input type="text"  name = "tel" size=14></td></tr>
			<tr><td  width="70px">생년월일</td><td> <input type="date"  name = "date" size=14></td></tr>
			<tr><td  width="70px">주소   </td><td><input type="text"  name="address" size=14></td></tr>
			<tr><td  width="70px">이메일 </td><td> <input type="text" name = "email" size=14></td></tr>
			
		</table>
		</center>
		<center>
			<p>
			</p>
			<p></p>
			<input type="hidden" value=${id} name = "sid">
			<input id="btn1" type="submit" value="수정" style="WIDTH: 100pt; HEIGHT: 40pt">
			<input id="btn1" type="reset" value="취소" style="WIDTH: 100pt; HEIGHT: 40pt">
		</center>

			</form>

<table>
	<tr><td height=150></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>