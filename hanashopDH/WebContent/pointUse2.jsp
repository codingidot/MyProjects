<%@ include file ="../layout/header.jsp" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-{SDK-최신버전}.js"></script>
<meta charset="utf-8">



<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
table{
	width:60%;
	height:100px;
	margin:auto;
	text-align=center;
	background-color: #ffe5dd; 
}
body { background-color: #fffff0; }
</style>
<script>
  
function iamport(){
	
	var point22=${point };
  var point12=document.getElementById("point");
  var price122=${total }-point12.value;
  $('input[name=realpay]').attr('value',price122);
	
	var form=document.getElementById("form");
	var address=document.getElementById("roadFullAddr");
	if(address.value==""){
		alert("주소를 입력해주세요");
		return false;
	}
	
	 if(point12.value>point22){
		alert("포인트가 초과되었습니다");
		return false;
	}else if(point12.value<=point22 && point12.value>=0){
	
	}else{
		alert("포인트 입력란에 숫자를 입력해주세요");
		return false;
	} 
	 
	 if(price122<0){
			alert("사용할 포인트가 상품 가격보다 높게 작성하셨습니다.");
			return false;
	 }		
	 	form.submit(); 
	
}
  </script>





<title>결제창</title>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. 
// (＂팝업 API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";
function goPopup(){
//경로는 시스템에 맞게 수정하여 사용
//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를
//호출하게 됩니다.
var pop = window.open("/hanashopDH33/jusoPopup2.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
//** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서
// 실제 주소검색 URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, 
rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, 
buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
 // 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
 document.form.roadFullAddr.value = roadFullAddr;
 document.form.roadAddrPart1.value = roadAddrPart1;
 document.form.roadAddrPart2.value = roadAddrPart2;
 documentform.addrDetail.value = addrDetail;
 document.form.zipNo.value = zipNo;
}
</script>
</head>




<body>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
%>


<form action="paypayList2.do" name="form" id="form" method="post">
<table border=1>
<tr>
<td>주문날짜 :</td><td><input type=text name=paydate value=<%=sf.format(nowTime) %>  readonly></td>
</tr>
<div id="callBackDiv">

<tr>
<td>배송지 :</td><td><input type="text"  style="width:500px;" id="roadFullAddr"  name="roadFullAddr" /><input type="button" onClick="goPopup();" value="주소검색"/></td>
</tr>

</div>
<tr>
<td>주문목록</td>
<td>
<c:forEach var="list1" items="${requestScope.arr_paydo}">
        <input type=hidden name=paylist value="${list1 }">       
 </c:forEach> 	
<ul>
<c:forEach var="list" items="${requestScope.arr_payList}">
<li> ${list }</li>               
 </c:forEach>       
</ul>
</td>
</tr>
<tr>
<td>총 가격:</td><td> ${total } </td>
</tr>
<tr>
<td colspan=2>현재 포인트:${point }</td>
</tr>
<tr>
<td>사용할 포인트를 입력해 주세요</td><td>  <input type=text id=point name=usepoint value=${point }></td>
</tr>
<tr>
<td colspan=2> 결제방법(카카오페이만 지원됩니다)</td>
</tr> 
</table>
<center>
 <button type=button onclick="iamport()" >결제하기</button>
 </center>
 <input type=hidden name=nowpoint value="${point }">
 <input type=hidden name=totPrice value="${total }">
 <input type=hidden name=name value="장바구니 상품">
 <input type=hidden name=realpay value="">
</form>
 
</body>

<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>