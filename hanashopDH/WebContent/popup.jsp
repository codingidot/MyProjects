<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.popup(
		position: fixed;
		left:100px;
		top:100px;
		border: 1px solid
		padding: 40px 60px;
	)
</style>
</head>
<script>
// 팝업 관련 내용#########################################################################################################
var myPopup=document.querySelector('.popup'),
checkbox=document.querySelector('#popup'),
popupClose=document.querySelector('.close');


//쿠키생성
function setCookies(name,value,day){
	var date=new Date(); //현재날짜지정
	date.setDate(date.getDate()+day);
	
	var mycookie='';
	mycookie+=name+'='+value+';';
	mycookie+='Expires=' +date.toUTCString();
	
	document.cookie=mycookie; //쿠키 설정, 생성
	
}



function checkCookie(name){
	var cookies=document.cookie.split(';');
	var visited=false; //방문 거짓
		for(var i in cookies){
			if(cookies[i].indexOf(name)>-1){
				alert(cookies[i]);
				visited=true; //방문한적 있음
				
			}
		}
	if(visited){
		//쿠키가 있는 상태로 팝업이 나타나지 않는다
		
	}else{
		setCookies('cookiename','Hi',3);
		window.open("http://localhost:8080/hanashopDH33/popup1.jsp", "PopupWin", "width=500,height=600");
		}
}

window.onload = function(){
	checkCookie('cookiename');
}
//###############################################################################################
</script>
<body>
<div class=popup>
<h1>팝업창</h1>

<label for=popup>하루동안 보지않기 </label><input type=checkbox id=popup ><br>
<button id=close>닫기</button> 
</div>

	
</body>
</html>