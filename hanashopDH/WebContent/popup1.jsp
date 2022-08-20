<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

<center>
<img src=./image/popup.png width=300,height=400>
</center><br>
<h3>하나샾이 쏜다~~~~~!!! 1+1 행사중^^ </h3>
<div  align="right" >
일주일 동안 보지않기<input type=checkbox id=popup12 ><br>
<button id=close onclick='closeclick()'>닫기</button> 
</div>
<script>
//쿠키삭제-만기시간을 과거로 해서 삭제시킴
function delCookie(name,value){
	var date=new Date(); //현재날짜지정
	date.setDate(date.getDate()-1);
	
	var mycookie='';
	mycookie+='cookiename'+'='+'Hi'+';';
	mycookie+='Expires=' +date.toUTCString();
	
	document.cookie=mycookie;
	
}

var popupClose=document.getElementById('close');
var checkbox=document.getElementById('popup12');



	 function closeclick(){
		
		if(checkbox.checked){
			//팝업을 다시 안보겠다.쿠키삭제 안하고 그래도 창 닫음
			window.close();
			//alert('체크박스 체크됨');
		}else{
			//팝업을 계속 본다. 팝업닫고 쿠키 제거
			delCookie('cookiename','Hi');
			window.close();
			//alert('체크박스 체크안됨');
		}
	}
</script>
</body>
</html>