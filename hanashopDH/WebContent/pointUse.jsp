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
	  var price122=${count*price }-point12.value;
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
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/hanashopDH33/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadFullAddr.value = roadFullAddr;
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.roadAddrPart2.value = roadAddrPart2;
		document.form.addrDetail.value = addrDetail;
		document.form.engAddr.value = engAddr;
		document.form.jibunAddr.value = jibunAddr;
		document.form.zipNo.value = zipNo;
		document.form.admCd.value = admCd;
		document.form.rnMgtSn.value = rnMgtSn;
		document.form.bdMgtSn.value = bdMgtSn;
		document.form.detBdNmList.value = detBdNmList;
		/** 2017년 2월 추가제공 **/
		document.form.bdNm.value = bdNm;
		document.form.bdKdcd.value = bdKdcd;
		document.form.siNm.value = siNm;
		document.form.sggNm.value = sggNm;
		document.form.emdNm.value = emdNm;
		document.form.liNm.value = liNm;
		document.form.rn.value = rn;
		document.form.udrtYn.value = udrtYn;
		document.form.buldMnnm.value = buldMnnm;
		document.form.buldSlno.value = buldSlno;
		document.form.mtYn.value = mtYn;
		document.form.lnbrMnnm.value = lnbrMnnm;
		document.form.lnbrSlno.value = lnbrSlno;
		/** 2017년 3월 추가제공 **/
		document.form.emdNo.value = emdNo;
		
}

function paygo(){
	/* alert("포인트가 초과되었습니다"); */
	var point12=document.getElementById("point");
	var point22=${point };
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
		form.submit();
	}else{
		alert("포인트 입력란에 숫자를 입력해주세요");
		return false;
	} 
	
	
}

</script>
</head>




<body>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
%>


<form action="paypay.do" name="form" id="form" method="post">
<table border=1>
<tr>
<td>주문날짜 :</td><td><input type=text name=paydate value=<%=sf.format(nowTime) %>  readonly></td>
</tr>
<div id="callBackDiv">

<tr>																								
<td>배송지 :</td><td><input type="text"  style="width:500px;" id="roadFullAddr"  name="roadFullAddr" /><button type="button"  onclick="goPopup()" >주소검색</button></td>
</tr>

</div>
<tr>		
<td>상품명 :</td><td> ${name}</td>
</tr>
<tr>
<td>사이즈 :</td><td> ${size }</td>
</tr>
<tr>
<td>개수 :</td><td>${count }</td>
</tr>
<tr>
<td>총 가격 :</td><td>${count*price }</td>
</tr>
<tr >
<td colspan="2">현재 포인트:${point }</td>
</tr>
<tr>
<td>사용할 포인트를 입력해 주세요</td>  <td><input type=text id=point name=usepoint value=${point }></td>
</tr>
<tr >
<td colspan="2">결제방법(카카오페이만 지원됩니다)</td>
</tr>
</table>
<center>
 <button type=button onclick="iamport()" >결제하기</button>
</center>
<input type=hidden name=nowpoint value="${point }">
 <input type=hidden name=size value="${size }">
 <input type=hidden name=count value="${count }">
 <input type=hidden name=price value="${price }">
 <input type=hidden name=totPrice value="${count*price }">
 <input type=hidden name=name value="${name}">
 <input type=hidden name=no value="${no }">
 <input type=hidden name=realpay value="">
</form>
 
</body>

<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>