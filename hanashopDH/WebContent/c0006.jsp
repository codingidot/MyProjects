<%@page import="java.util.ArrayList"%>
<%@page import="basket.basketVO.BasketVO"%>
 <%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 장바구니 -->
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style>
/* table.list3 {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.list3 th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
table.list3 td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
} */

/* table.list3 {

  border-collapse: collapse;
  border-radius: 30px;
  border-style: hidden;
  box-shadow: 0 0 0 1px #000;
}

table.list3 th, td {
  text-align: center;
}


table.list3 th {
  border-top-left-radius: 30px;
}
table.list3 td{
  border-bottom-left-radius: 30px;
}
table.list3 th {
  border-top-right-radius: 30px;
}

table.list3 td {
  border-bottom-right-radius: 30px; 
}
 */
#btn1{
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

#th2{

border-top: none; 
 border-bottom: none; 
 border-left: none; 
 border-right: none; 
 
}

#table1 {

  border-collapse: collapse;
  border-radius: 30px;
  border-style: hidden;
  box-shadow: 0 0 0 1px #000;
}

th, td {
  text-align: center;
}


#th1:first-of-type {
  border-top-left-radius: 30px;
}
#th1:first-of-type {
  border-bottom-left-radius: 0px;
}
#th1:last-of-type {
  border-top-right-radius: 0px;
}

#th1:last-of-type {
  border-bottom-right-radius: 30px; 
}


#th3:first-of-type {
  border-top-left-radius: 30px;
}
#th3:first-of-type {
  border-bottom-left-radius: 30px;
}
#th3:last-of-type {
  border-top-right-radius: 30px;
}

#th3:last-of-type {
  border-bottom-right-radius: 0px; 
}

#th2:first-of-type {
  border-top-left-radius: 0px;
}
#th2:first-of-type {
  border-bottom-left-radius: 30px;
}
#th2:last-of-type {
  border-top-right-radius: 0px;
}

#th2:last-of-type {
  border-bottom-right-radius: 30px; 
}


#th4:first-of-type {
  border-top-left-radius: 0px;
}
#th4:first-of-type {
  border-bottom-left-radius: 0px;
}
#th4:last-of-type {
  border-top-right-radius: 0px;
}

#th4:last-of-type {
  border-bottom-right-radius: 30px; 
}

#btn1{
  border-top-left-radius: 30px;
   border-top-right-radius: 30px;
    border-bottom-left-radius: 30px;
      border-bottom-right-radius: 30px; 
    
   background-color : #ffe5dd;
   border : 0;
}



</style>
<script>
function pay(){
	  var checkArray = document.getElementsByName("deleteList");
	  var form1=document.getElementById("frm1");
	
		var msg="";
		
		var flag = true;
		
		for (var i = 0; i < checkArray.length; i++)
		{
			//alert(checkArray[i].checked);
			
			if (checkArray[i].checked)
			{
				msg += checkArray[i].value + " ";
				
				
				flag = false;
				
			}
		}
		
		if (flag)
		{
	
			alert("구매할 목록을 체크해주세요");
		}
		else 
		{
			
			form1.action="paypayList.do"
			form1.submit();
		} 
}

function delete12(){
	  var checkArray = document.getElementsByName("deleteList");
	  var form1=document.getElementById("frm1");
	
		var msg="";
		
		var flag = true;
		
		for (var i = 0; i < checkArray.length; i++)
		{
			//alert(checkArray[i].checked);
			
			if (checkArray[i].checked)
			{
				msg += checkArray[i].value + " ";
				
				
				flag = false;
				
			}
		}
		
		if (flag)
		{
	
			alert("삭제할 목록을 체크해주세요");
		}
		else 
		{
			
			form1.action="deleteBasket.do"
			form1.submit();
		} 
}
</script>
<body>
<center>
<h1>${id } 장바구니</h1>
<%
	request.setCharacterEncoding("utf-8");
	String stotal=(String)request.getAttribute("stotal"); //등록된 총 상품수
	String currentpage=(String)request.getAttribute("currentpage"); //현재 페이지
	String currentgroup=(String)request.getAttribute("currentgroup");//현재 그룹
	int pagesize=5; //한페이지에 나오는 수
	int groupsize=5;// 페이지 그룹수
	int plus=pagesize*groupsize; //그룹이 바뀔때 더하거나 빼줘야하는 값
	int groupnum=Integer.parseInt(currentgroup); //현재 그룹(int)
	int numcurrent=Integer.parseInt(currentpage);//현재 페이지(int)
	int total12=Integer.parseInt(stotal);//등록된 총 상품수(int)
	int count=total12/pagesize; //페이지수(int)
	if(total12%pagesize!=0){ //상품수를 pagesize로 나눠서 나머지가 0이 아니면 한페이지 추가해줌
		count+=1;	
	}
	int totgroup=count/groupsize;// 그룹수
	if(count%groupsize!=0){ //페이지수를 groupsize로 나눠서 나머지가 0이 아니면 한그룹 추가해줌
		totgroup+=1;
	}
	

%>
 <c:if test="${inBasket !='no'}">
<a href="basketList.do">목록보기</a><br>
<table>
   <tr><td height=50></tr>      
   </table>
<form action="choice.do" method="post" id="frm1">
<table id=table1 border=1 class=list3>
<tr>
<th id="th1" style="WIDTH:  160pt; HEIGHT: 40pt; background-color:#ffe5dd ">번호</th>
<th  style="WIDTH:  160pt; HEIGHT: 40pt; background-color:#ffe5dd ">상품명</th>
<th style="WIDTH:  160pt; HEIGHT: 40pt; background-color:#ffe5dd ">개수</th>
<th style="WIDTH:  160pt; HEIGHT: 40pt; background-color:#ffe5dd ">사이즈</th>
<th  style="WIDTH:  160pt; HEIGHT: 40pt; background-color:#ffe5dd ">가격</th>
<th id="th3" style="WIDTH:  160pt; HEIGHT: 40pt; background-color:#ffe5dd ">선택</th>
</tr>
<%int number123=1; %>

 <c:forEach var="vo1" items="${requestScope.list}">
 		<tr>
 		<th  style="WIDTH:  160pt; HEIGHT: 40pt"><%=number123%></th>
 		<%number123+=1;%>
		<th  style="WIDTH:  160pt; HEIGHT: 40pt">${vo1.b_name}</th> 
		<th  style="WIDTH:  160pt; HEIGHT: 40pt">${vo1.b_count }</th>
		<th  style="WIDTH:  160pt; HEIGHT: 40pt">${vo1.b_size }</th>
		<th  style="WIDTH:  160pt; HEIGHT: 40pt">${vo1.b_price }</th>						
		<th  style="WIDTH:  160pt; HEIGHT: 40pt"><input type="checkbox" name="deleteList" value="${id },${vo1.b_name},${vo1.b_size },${vo1.b_count },${vo1.b_price },${vo1.b_no }"></th>
 		</tr>
 </c:forEach>
<!--  <tr>
 		<th  colspan=6 id="th2" style="WIDTH:  160pt; HEIGHT: 40pt ; background-color:#ffe5dd"></th>

 		</tr> -->
 </table>
 <input type=hidden name=key value=1>
 </center>
 <center>
 <%		 
 		
 		
 		int number=1; //초기에 출력하는 상품의 start값
 		 int number2=pagesize;//초기에 출력하는 상품의 end값
 		 number=number+plus*(groupnum-1);//그룹이 바뀜에 따른 start를 바꿔줌
 		number2=number2+plus*(groupnum-1);//그룹이 바뀜에 따른 end를 바꿔줌
 		 
 		 int nexts2=1+(plus*(groupnum-2));//이전 버튼 누르면 나오는 start값
			 int nexte2=pagesize+(plus*(groupnum-2));//이전 버튼 누르면 나오는 end값
			 int nextg2=groupnum-1;//이전버튼 누르면 현재 그룹에서-1
		 if(groupnum>1){//그룹이 1보다 큰 경우에만 이전버튼 출력
			 
			out.print("<a href='basketList.do?start="+nexts2+"&end="+nexte2+"&currentgroup="+nextg2+"'>이전</a>&nbsp&nbsp");
		 }	 
 		 
 			
 			for(int i=groupsize*(groupnum-1);i<(groupsize*(groupnum-1)+groupsize);i++){//현재 그룹에 따른 페이지버튼 숫자를 설정
 	 			out.print("<a href='basketList.do?start="+number+"&end="+number2+"&currentpage="+(i+1)+"&currentgroup="+groupnum+"'>"+"["+(i+1)+"]</a>&nbsp&nbsp");
 	 								//basketList.do?start=1&end=5&currentpage=1&currentgroup=1	
 			number+=pagesize; //다음 페이지의 start를 위해 pagesize 만큼 늘려줌
 	 			number2+=pagesize;//다음 페이지의 end를 위해 pagesize 만큼 늘려줌
 	 			if((i+1)==count){//만약 현재 페이지가 총페이지수랑 같으면 for문에서 나옴
 	 				break;
 	 			}
 	 		}  
 		
 			 int nexts=1+(plus*(groupnum));//다음 버튼 눌렀을때 start값 설정
 			 int nexte=pagesize+(plus*(groupnum));// 다음 버튼 눌렀을때 end값 설정
 			 int nextg=groupnum+1;//다음 버튼 눌렀을때 그룹을 1더해줌
 		 if(groupnum!=totgroup){//현재 그룹이 총 그룹인경우 다음버튼 출력하지 않음
 			 
 			out.print("<a href='basketList.do?start="+nexts+"&end="+nexte+"&currentgroup="+nextg+"'>다음</a>");
 		 }
 %>
 <br>
 <button type="button" name=option12  id=btn1  onclick="pay()" >구매하기</button>
 <button type="button" name=option12 id=btn1 onclick="delete12()">삭제하기</button><br>
  

</center>
</form>
<table>
   <tr><td height=250></tr>      
   </table>
   </c:if>
   <c:if test="${inBasket =='no'}">
<table>
   <tr><td height=200></tr>      
</table>
<h2>장바구니가 비어있습니다</h2>
<table>
   <tr><td height=200></tr>      
</table>
</c:if>
</body>

<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>