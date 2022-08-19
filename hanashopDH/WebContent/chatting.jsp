   <%@ include file ="../layout/header.jsp" %>
   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>상담채팅</title>
    <%
	if(session.getAttribute("id")==null){ %>
		<script>
			alert("로그인이 필요한 서비스 입니다.")
			location.href="login.jsp"	
		</script>
	<%} %>
    
</head>
<body>

<div id=chatting1234 style=" background-color: #faedda">
	<center>
    <fieldset>
        <textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
        <br/>
        <input id="inputMessage" type="text"/>
        <input type="submit" value="전송" onclick="send()" onkeyup="enterkey()"/>
    </fieldset>${id}
    </center>
</div>
</body>
    <script type="text/javascript">
        var textarea = document.getElementById("messageWindow");
        var webSocket = new WebSocket("ws://localhost:8080" + "<%=request.getContextPath()%>/gogochat");
        var inputMessage = document.getElementById('inputMessage');
    webSocket.onerror = function(event) {
      onError(event)
    };

    webSocket.onopen = function(event) {
      onOpen(event)
    };

    webSocket.onmessage = function(event) {
      onMessage(event)
    };

    function onMessage(event) {
    	var id="${id}";
    	var length=id.length;
    	var totlength=length+6;
    	if(event.data.substr(0,6)==="admin:"&&event.data.substr(6,length)==="${id}"){
        textarea.value += "관리자 :"+ event.data.substr(totlength) +"\n";
    
    	}else{
    		if(id==="admin"){
    			textarea.value +=  event.data +"\n";
    		}
    	}
    }
    function onOpen(event) {
        textarea.value += "안녕하세요~~ 궁금한 사항이 있으시면 친절하게 답변드리겠습니다!\n";
    }

    function onError(event) {
      alert(event.data);
    }

    function send() {
        textarea.value += "나 : " + inputMessage.value + "\n";
        webSocket.send("${id}:"+inputMessage.value);
        inputMessage.value = "";
    }
    
    function enterkey() {
    	if (window.event.keyCode == 13) {
    		 textarea.value += "나 : " + inputMessage.value + "\n";
    	        webSocket.send("${id}:"+inputMessage.value);
    	        inputMessage.value = "";
        }
    }
  </script>
  <footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>
  
  