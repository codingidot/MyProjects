<%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery-3.6.0.js"></script>
  <!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<meta charset="utf-8">



<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<meta charset="EUC-KR">
<title>결제 완료</title>
<script>
var price122=${realpay};
	var name122="${name}";
	var basket="${basket}";
	
	window.onload=function(){
		//가맹점 식별코드
		var result="${checkStock }";
		var nopay="${nopay}";
	if(result!="enough"){
		alert("this");
		window.history.back();
			return false;
	}
		
	if(nopay===""||nopay===null){
		
		IMP.init('imp61633154');
		IMP.request_pay({
		    pg : 'kcp',
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : name122 , //결제창에서 보여질 이름
		    amount : price122, //실제 결제되는 가격
		    buyer_email : 'iamport@siot.do',
		    buyer_name : '구매자이름',
		    buyer_tel : '010-1234-5678',
		    buyer_addr : '서울 강남구 도곡동',
		    buyer_postcode : '123-456'
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	var msg = '결제가 완료되었습니다.';
		    
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        var form=document.getElementById("form");
		    	form.action="paypay.do";
		    	if(basket=='basket'){
		    		form.action="paypayList2.do";	
		    	}
		    	form.submit();
		    } else {
		    	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
		         alert(msg);
		         window.history.back();
		         window.history.back();
		         window.history.back();
		         return false;
		         
		         
		    }
		    alert(msg);
		}); 
	}
	}


	
</script>
</head>
<body>
<form href="paypay.do" id="form" method="post" >
<input type=hidden name=usepoint value="${usepoint }">
<input type=hidden name=key value="ok">
<input type=hidden name=nowpoint value="${nowpoint }">
<input type=hidden name=roadFullAddr value="${roadFullAddr }">
<input type=hidden name=paydate value="${payDate }">
<input type=hidden name=name value="${name }">
<input type=hidden name=size value="${size }">
<input type=hidden name=count value="${count }">
<input type=hidden name=price value="${price }">
<input type=hidden name=no value="${no }">
<input type=hidden name=realpay value="${realpay }">
<input type=hidden name=paylist value=${paylist }>
<input type=hidden name=totPrice value=${totPrice }>
<c:forEach var="list1" items="${requestScope.paylist}">
        <input type=hidden name=paylist2 value="${list1 }">       
 </c:forEach> 	

</form>


<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>


<center>
 <c:if test="${checkStock !='enough'}">
<h1> ${checkStock }</h1>
 </c:if>
  <c:if test="${checkStock =='enough'}">
<h1>결제가 완료되었습니다! 또 이용해주십쇼~~</h1>
</c:if>

</center>

<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>
<p>&nbsp</p>





</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>

