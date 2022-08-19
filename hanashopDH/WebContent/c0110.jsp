<%@ include file ="../layout/header.jsp" %>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>상품목록(관리자)</title>
</head>
<style>
table.list2 {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.list2 th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
  background-color : #ffe5dd;
}
table.list2 td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
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
.div1{position:absolute; left:80%;}
.div2{position:absolute; left:90%;}
</style>
<script>

function delete12(){
	  var checkArray = document.getElementsByName("deleteList1");
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
			
			form1.action="delete.do";
			form1.submit();
		} 
}





</script>
<body >
<h1>상품목록(관리자)</h1>
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

<form id=frm1 action="delete.do" method="post">

<table class=list2>
<tr>
<th>번호</th><th>상품명</th><th>가격</th><th>카테고리1</th><th>카테고리2</th><th>S재고</th><th>M재고</th><th>L재고</th><th>선택</th>
</tr>
 <c:forEach var="vo1" items="${requestScope.sangpum}">
 		<tr>
 		<td>${vo1.p_no}</td>
		<td><a href="productUpdate.do?p_no=${vo1.p_no}&p_name=${vo1.p_name}&p_price=${vo1.p_price }&p_stockS=${vo1.p_stockS }&p_stockM=${vo1.p_stockM }&p_stockL=${vo1.p_stockL }">${vo1.p_name}</a></td> 
		<td>${vo1.p_price }</td>
		<td>${vo1.p_category }</td>
		<td>${vo1.p_category2 }</td>
		<td>${vo1.p_stockS }</td>
		<td>${vo1.p_stockM }</td>
		<td>${vo1.p_stockL }</td>
		<td><input type="checkbox" name="deleteList1" value="${vo1.p_no}"></td>
 		</tr>
 </c:forEach>
 </table>


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
			 
			out.print("<a href='merchandise.do?start="+nexts2+"&end="+nexte2+"&currentgroup="+nextg2+"'>이전</a>&nbsp&nbsp");
		 }	 
 		 
 			
 			for(int i=groupsize*(groupnum-1);i<(groupsize*(groupnum-1)+groupsize);i++){//현재 그룹에 따른 페이지버튼 숫자를 설정
 	 			
 	 			out.print("<a href='merchandise.do?start="+number+"&end="+number2+"&currentpage="+(i+1)+"&currentgroup="+groupnum+"'>"+"["+(i+1)+"]</a>&nbsp&nbsp");
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
 			 
 			out.print("<a href='merchandise.do?start="+nexts+"&end="+nexte+"&currentgroup="+nextg+"'>다음</a>");
 		 }
 %>

 </center>
 <div class=div1><button type=button name=option onclick="delete12()" >삭제하기</button></div>
</form>
<div class=div2><a href="c0310.jsp">상품 등록하기</a></div><br><br><br><br>
</body>

<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>
</html>