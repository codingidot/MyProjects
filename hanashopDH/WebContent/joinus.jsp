<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file ="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�� ���� ����</title>
<style>

table.join {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.join th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
table.join td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}


#btn1{
  border-top-left-radius: 30px;
   border-top-right-radius: 30px;
    border-bottom-left-radius: 30px;
      border-bottom-right-radius: 30px; 
    
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

</style>


</head>

<body>

   <script>
   
   function idCheck(){
	  var id2323= document.getElementById("id11");
      var form11=document.getElementById("frm");
      if(id2323.value==""||id2323.value==null){
          alert('���̵� �Է����ּ���');
          return false
       } 
      form11.action = 'idCheckaction.do';
      form11.method = 'GET';
      form11.submit();
   
   }
   

   </script>
   
   <center>
   <h1>ȸ������<hr></h1>   
   
   </center>
   
   <table>
   <tr><td height=100></tr>      
   </table>
   
   <center>
   <table class="join" align="center">
   <form action="joinus.do" method="post" id="frm">
         
         <tr><td width="250px">���̵�</td><td width="px"><input type="text"  id=id11 name ="id" size=14 placeholder="${userid }" value="${userid }"><input type="button" onclick="idCheck();" value="ID �ߺ�Ȯ��"><br>${result1 }</td></tr><br>
         <tr><td width="120px">�̸� </td><td> <input type="text"  name = "name" size=14></td></tr>
         <tr><td  width="70px">��й�ȣ</td><td> <input type="password" name = "pw" size=14></td></tr>   
         <tr><td  width="70px">��й�ȣ Ȯ��</td><td> <input type="password" name="pw_re" placeholder="PW�� ���Է��ϼ���"><br></td><td>
         <tr><td  width="70px">��ȭ��ȣ </td><td><input type="text"  name = "tel" id=teltel size=11></td></tr>
         <tr><td  width="70px">�������</td><td> <input type="date"  name = "date" size=14 id=birth></td></tr>
         <tr><td  width="70px">�ּ�   </td><td><input type="text"  name="address" size=14 id=address></td></tr>
         <tr><td  width="70px">�̸��� </td><td> <input type="text" name = "email" size=14 id=email12></td></tr>
         
      </table>
      </center>
      <center>
         <p>
         </p>
         <p></p>
         <input id="btn1" type="submit" value="�����ϱ�" style="WIDTH: 100pt; HEIGHT: 40pt">
         
      </center>

         </form>

<table>
   <tr><td height=150></tr>      
   </table>
</body>

   <script>
      $('#btn1').click(function(){
         // ������ ��ȿ�� �˻�
         var userPwd=$('input[name=pw]').val();
         var userPwd_re=$('input[name=pw_re]').val();
         var tel=document.getElementById("teltel");
         var birth=document.getElementById("birth");
         var address=document.getElementById("address");
         var email11=document.getElementById("email12");
         if(userPwd!=userPwd_re){
            alert('��й�ȣ�� ��й�ȣ ���Է��� ��ġ���� �ʽ��ϴ�.');
            return false
         } if(userPwd=="" ||userPwd==null ){
            alert('��й�ȣ�� �Է����ּ���');
            return false
         } if(userPwd_re==""||userPwd_re==null){
            alert('��й�ȣ�� �Է����ּ���');
            return false
         } 
         var idcheck1=${result };
         if(idcheck1!="1"){
            alert('���̵� �ߺ�üũ�� ���ּ���');
            return false
         } 
         
         if(tel.value.length!="11"){
            alert('��ȭ��ȣ 11�ڸ��� �Է����ּ���');
            return false
         } 
         if(birth.value==""||birth.value==null){
            alert('������ �Է����ּ���');
            return false
         } 
         if(address.value==""||address.value==null){
            alert('�ּҸ� �Է����ּ���');
            return false
         } 
         if(email11.value==""||email11.value==null){
            alert('email�� �Է����ּ���');
            return false
         } 
         
         return true;
      });
   
   </script>
   
   
   
<footer>
<%@ include file ="../layout/footer.jsp" %>
</footer>