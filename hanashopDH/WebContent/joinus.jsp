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

table.join {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.join th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
table.join td {
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

   <script>
   
   function idCheck(){
	  var id2323= document.getElementById("id11");
      var form11=document.getElementById("frm");
      if(id2323.value==""||id2323.value==null){
          alert('아이디를 입력해주세요');
          return false
       } 
      form11.action = 'idCheckaction.do';
      form11.method = 'GET';
      form11.submit();
   
   }
   

   </script>
   
   <center>
   <h1>회원가입<hr></h1>   
   
   </center>
   
   <table>
   <tr><td height=100></tr>      
   </table>
   
   <center>
   <table class="join" align="center">
   <form action="joinus.do" method="post" id="frm">
         
         <tr><td width="250px">아이디</td><td width="px"><input type="text"  id=id11 name ="id" size=14 placeholder="${userid }" value="${userid }"><input type="button" onclick="idCheck();" value="ID 중복확인"><br>${result1 }</td></tr><br>
         <tr><td width="120px">이름 </td><td> <input type="text"  name = "name" size=14></td></tr>
         <tr><td  width="70px">비밀번호</td><td> <input type="password" name = "pw" size=14></td></tr>   
         <tr><td  width="70px">비밀번호 확인</td><td> <input type="password" name="pw_re" placeholder="PW를 재입력하세요"><br></td><td>
         <tr><td  width="70px">전화번호 </td><td><input type="text"  name = "tel" id=teltel size=11></td></tr>
         <tr><td  width="70px">생년월일</td><td> <input type="date"  name = "date" size=14 id=birth></td></tr>
         <tr><td  width="70px">주소   </td><td><input type="text"  name="address" size=14 id=address></td></tr>
         <tr><td  width="70px">이메일 </td><td> <input type="text" name = "email" size=14 id=email12></td></tr>
         
      </table>
      </center>
      <center>
         <p>
         </p>
         <p></p>
         <input id="btn1" type="submit" value="가입하기" style="WIDTH: 100pt; HEIGHT: 40pt">
         
      </center>

         </form>

<table>
   <tr><td height=150></tr>      
   </table>
</body>

   <script>
      $('#btn1').click(function(){
         // 간단한 유효성 검사
         var userPwd=$('input[name=pw]').val();
         var userPwd_re=$('input[name=pw_re]').val();
         var tel=document.getElementById("teltel");
         var birth=document.getElementById("birth");
         var address=document.getElementById("address");
         var email11=document.getElementById("email12");
         if(userPwd!=userPwd_re){
            alert('비밀번호와 비밀번호 재입력이 일치하지 않습니다.');
            return false
         } if(userPwd=="" ||userPwd==null ){
            alert('비밀번호를 입력해주세요');
            return false
         } if(userPwd_re==""||userPwd_re==null){
            alert('비밀번호를 입력해주세요');
            return false
         } 
         var idcheck1=${result };
         if(idcheck1!="1"){
            alert('아이디 중복체크를 해주세요');
            return false
         } 
         
         if(tel.value.length!="11"){
            alert('전화번호 11자리를 입력해주세요');
            return false
         } 
         if(birth.value==""||birth.value==null){
            alert('생일을 입력해주세요');
            return false
         } 
         if(address.value==""||address.value==null){
            alert('주소를 입력해주세요');
            return false
         } 
         if(email11.value==""||email11.value==null){
            alert('email을 입력해주세요');
            return false
         } 
         
         return true;
      });
   
   </script>
   
   
   
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>