<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Q&A</title>

<style type="text/css">

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

#table1 {

  border-collapse: collapse;
  border-radius: 30px;
  border-style: hidden;
  box-shadow: 0 0 0 1px #000;
}

th, td {
  text-align: center;
}


th:first-of-type {
  border-top-left-radius: 30px;
}
th:first-of-type {
  border-bottom-left-radius: 0px;
}
th:last-of-type {
  border-top-right-radius: 30px;
}
th:last-of-type{
  border-bottom-left-radius: 0px;
}
th:last-of-type {
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

</style>

</head>
<body>

	<center>
	
	<h1>��۹���<hr></h1>
	
				
	<%
	request.setCharacterEncoding("utf-8");
	String stotal=(String)request.getAttribute("stotal"); //��ϵ� �� ��ǰ��
	String currentpage=(String)request.getAttribute("currentpage"); //���� ������
	String currentgroup=(String)request.getAttribute("currentgroup");//���� �׷�
	int pagesize=5; //���������� ������ ��
	int groupsize=5;// ������ �׷��
	int plus=pagesize*groupsize; //�׷��� �ٲ� ���ϰų� ������ϴ� ��
	int groupnum=Integer.parseInt(currentgroup); //���� �׷�(int)
	int numcurrent=Integer.parseInt(currentpage);//���� ������(int)
	int total12=Integer.parseInt(stotal);//��ϵ� �� ��ǰ��(int)
	int count=total12/pagesize; //��������(int)
	if(total12%pagesize!=0){ //��ǰ���� pagesize�� ������ �������� 0�� �ƴϸ� �������� �߰�����
		count+=1;	
	}
	int totgroup=count/groupsize;// �׷��
	if(count%groupsize!=0){ //���������� groupsize�� ������ �������� 0�� �ƴϸ� �ѱ׷� �߰�����
		totgroup+=1;
	}
	

%>
	<table >
	<tr><td height=100></tr>		
	</table>
	
	<c:if test="${isqna !='no'}">
	
	<table id="table1" border=1>	
	<%int num=0; %>
	<tr> <th style="WIDTH:  100pt; HEIGHT: 40pt; background-color:#ffe5dd ">��ȣ</th>
			<th style="WIDTH: 300pt; HEIGHT: 40pt;  background-color:#ffe5dd">����</th>
			<th style="WIDTH: 200pt; HEIGHT: 40pt; background-color:#ffe5dd">�ۼ���</th>
	</tr>	
	<c:forEach var="vo" items="${requestScope.vo}">
	
	<%num++;%>			
	<tr><th style="WIDTH:  200pt; HEIGHT: 40pt"><%=num%></th>
	
 	<th style="WIDTH: 200pt; HEIGHT: 40pt">
 	<a href="comdeliveryinfo.do?qno=${vo.q_no}&qparentno=${vo.q_parentno}">${vo.q_title }</a></th>
	<th style="WIDTH:  200pt; HEIGHT: 40pt">${vo.q_id }</th>
    </tr>
	</c:forEach>
	
	</table><%		 
 		
 		
 		int number=1; //�ʱ⿡ ����ϴ� ��ǰ�� start��
 		 int number2=pagesize;//�ʱ⿡ ����ϴ� ��ǰ�� end��
 		 number=number+plus*(groupnum-1);//�׷��� �ٲ� ���� start�� �ٲ���
 		number2=number2+plus*(groupnum-1);//�׷��� �ٲ� ���� end�� �ٲ���
 		 
 		 int nexts2=1+(plus*(groupnum-2));//���� ��ư ������ ������ start��
			 int nexte2=pagesize+(plus*(groupnum-2));//���� ��ư ������ ������ end��
			 int nextg2=groupnum-1;//������ư ������ ���� �׷쿡��-1
		 if(groupnum>1){//�׷��� 1���� ū ��쿡�� ������ư ���
			 
			out.print("<a href='delivery.do?start="+nexts2+"&end="+nexte2+"&currentgroup="+nextg2+"'>����</a>&nbsp&nbsp");
		 }	 
 		 
 			
 			for(int i=groupsize*(groupnum-1);i<(groupsize*(groupnum-1)+groupsize);i++){//���� �׷쿡 ���� ��������ư ���ڸ� ����
 	 			
 	 			out.print("<a href='delivery.do?start="+number+"&end="+number2+"&currentpage="+(i+1)+"&currentgroup="+groupnum+"'>"+"["+(i+1)+"]</a>&nbsp&nbsp");
 	 			number+=pagesize; //���� �������� start�� ���� pagesize ��ŭ �÷���
 	 			number2+=pagesize;//���� �������� end�� ���� pagesize ��ŭ �÷���
 	 			if((i+1)==count){//���� ���� �������� ������������ ������ for������ ����
 	 				break;
 	 			}
 	 		}  
 		
 			 int nexts=1+(plus*(groupnum));//���� ��ư �������� start�� ����
 			 int nexte=pagesize+(plus*(groupnum));// ���� ��ư �������� end�� ����
 			 int nextg=groupnum+1;//���� ��ư �������� �׷��� 1������
 		 if(groupnum!=totgroup){//���� �׷��� �� �׷��ΰ�� ������ư ������� ����
 			 
 			out.print("<a href='delivery.do?start="+nexts+"&end="+nexte+"&currentgroup="+nextg+"'>����</a>");
 		 }
 %>
	</c:if>
	
	<c:if test="${isqna =='no'}">
<table>
   <tr><td height=100></tr>     
</table>
<h2>��۹��Ƕ��� ����ֽ��ϴ�</h2>
</c:if>
	
		
	 
	
	
	<table>
	<tr><td height=70></tr>		
	</table>
	
		<form action="qnawriteForm3.jsp" >
	
			<input id="btn1" type=submit style="WIDTH: 120pt; HEIGHT: 40pt" value="�ۼ��ϱ�">
	<table>
	<tr><td height=250></tr>		
	</table>
</body>
<footer>
<%@ include file ="../layout/footer.jsp" %>
</center>
</footer>
</html>