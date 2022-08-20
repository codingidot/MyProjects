<%@ include file ="../layout/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Noto Sans KR", sans-serif;
}

a {
  text-decoration: none;
  color: black;
}

li {
  list-style: none;
}

.wrap {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color:  #fffff0;
}

.login {
  width: 30%;
  height: 600px;
  background: white;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

h2 {
  color: tomato;
  font-size: 2em;
}




.login_id {
  margin-top: 20px;
  width: 80%;
}


.w-btn-indigo {
    background-color: aliceblue;
    color: #1e6b7b;
}

.w-btn-indigo-outline {
    border: 3px solid aliceblue;
    color: #1e6b7b;
}

.w-btn-indigo-outline:hover {
    color: #1e6b7b;
    background: aliceblue;
}

button {
    margin: 20px;
}

.w-btn {
    position: relative;
    border: none;
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

.w-btn-outline {
    position: relative;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

/* a:link { text-decoration: none; border: rgba(75, 112, 253, 0.3) solid; border-width: 0 0 6px 0; }
a:visited { text-decoration: none; border: rgba(140, 89, 185, 0.3) solid; border-width: 0 0 6px 0; }
a { display: inline; } */

</style>
<script>





function enterkey() {
        if (window.event.keyCode == 13) {
 
             // 엔터키가 눌렸을 때 실행할 내용
             valid_check();
        }
}


function valid_check(){
		 var id1=document.getElementById("cid");
	 var pw1=document.getElementById("cpw");
	 
	 if(id1.value==""){
			alert("아이디를 입력해주세요");
			
		
	 }else if(pw1.value==""){
		 alert("비밀번호를 입력해주세요");
	 }
	 else{
		 document.getElementById('frm1').submit();
	 }
	   
 }
 </script>
<body>
<center>

<form action="LoginForm.do" method = "post" id=frm1>
<h2>HANA shop</h2>
<div class="wrap">

  <div class="login">
      <h2>Log-in</h2>
      <div class="login_id">
      	<h4>ID</h4>
      	<input type="text" name="u_id1" id="cid" placeholder="ID" onkeyup="enterkey();">
      </div>
            
      <div class="login_pw">
     	 <h4>Password</h4>
      	<input type="password" name="u_pw" id="cpw" placeholder="Password" onkeyup="enterkey();">
      </div>
           <p>&nbsp</p>
      <div class="submit">
      	<button type="button" class="w-btn w-btn-indigo"  onclick="valid_check()"  >로그인</button>  
      </div>
      <p>&nbsp</p>
    <a href="joinus.jsp" id="123">회원가입</a>
  </div>
</div>

</form>
</center>

</body>
</html>
<footer>
<%@ include file="/layout/footer.jsp" %>
</footer>