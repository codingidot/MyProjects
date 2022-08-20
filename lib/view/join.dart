import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:crypto/crypto.dart';
import '../env.dart';

class Join extends StatefulWidget {


  @override
  _JoinState createState() => _JoinState();
}

class _JoinState extends State<Join> {

  final formKey = GlobalKey<FormState>();
  Dio dio = Dio();
  TextEditingController idController = TextEditingController();
  TextEditingController pwController = TextEditingController();
  TextEditingController pwCheckController = TextEditingController();

  Future _createMember() async {

    List<int> bytes = utf8.encode(pwController.text);
    print(bytes);
    String shapw1 = sha256.convert(bytes).toString();
    print(shapw1);
    var formData = FormData.fromMap(
      {
        "memId" : idController.text,
        "memPw" : shapw1,
      },
    );
    return await dio.post("${Env.URL_PREFIX}/memberCreate.php", data: formData);
  }

  Future _idCheckMember() async{
    print('여기까지만 옴');
    var idchecks;
    var formData = FormData.fromMap(
      {
        "memId" : idController.text,
      },
    );
    idchecks= dio.post("${Env.URL_PREFIX}/memberidCheck.php", data: formData);
    print ('여기 ${idchecks}');
    return idchecks;
  }

   void _idCheck(context) async {
     var result= await _idCheckMember();
     var res = result.toString();
     print(result);

     if (res == '{"success":null}') {
       print('true');
       if (pwController.text == pwCheckController.text) {
         print('비밀번호 일치 확인');
         await _createMember();
         showDialog(
             context: context,
             builder: (context) =>
                 AlertDialog(
                   title: Text('회원가입 완료'),
                   content: Text('회원가입이 완료되었습니다. \n',
                       textAlign: TextAlign.center),
                   actions: [
                     TextButton(onPressed: () {
                       Navigator.pop(context);
                     }, child: Text('Yes')),
                   ],
                 ));
         Navigator.of(context).pushNamedAndRemoveUntil(
             '/', (Route<dynamic> route) => false);
         // 실행문1
       } else {
         showDialog(
             context: context,
             builder: (context) =>
                 AlertDialog(
                   title: Text('비밀번호 일치'),
                   content: Text('비밀번호가 일치하지 않습니다. \n',
                       textAlign: TextAlign.center),
                   actions: [
                     TextButton(onPressed: () {
                       Navigator.pop(context);
                     }, child: Text('Yes')),
                   ],
                 ));
       }
     } else {
       showDialog(
           context: context,
           builder: (context) =>
               AlertDialog(
                 title: Text('아이디 중복'),
                 content: Text('아이디가 중복되었습니다. \n',
                     textAlign: TextAlign.center),
                 actions: [
                   TextButton(onPressed: () {
                     Navigator.pop(context);
                   }, child: Text('Yes')),
                 ],
               ));
     }
   }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
            resizeToAvoidBottomInset: false,
      body: SingleChildScrollView(
        child: Container(
          child: Column(
              children: <Widget>[
                Text(
                  '회원가입', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 31, ), textAlign: TextAlign.left,
                ),
                Divider(
                  thickness: 3,
                  color: Colors.grey,
                ),
                Container(
                  padding: EdgeInsets.fromLTRB(20, 5, 20, 10),
                      child: TextField(
                        controller: idController,
                        decoration: InputDecoration(
                            border: OutlineInputBorder(),labelText: '아이디'),
                    ),
                ),
                Container(
                  padding: EdgeInsets.fromLTRB(20, 5, 20, 10),
                    child: TextField(
                      controller: pwController,
                      decoration: InputDecoration(
                          border: OutlineInputBorder(),labelText: '패스워드'),
                      obscureText: true,
                    ),

                ),
                Container(
                  padding: EdgeInsets.fromLTRB(20, 5, 20, 20),
                  child: TextField(
                    controller: pwCheckController,
                    decoration: InputDecoration(
                        border: OutlineInputBorder(),labelText: '패스워드재입력'),
                    obscureText: true,
                  ),

                ),
                Container(
                  child: RaisedButton(
                    child: Text("회원가입"),
                    color: Colors.orange,
                    textColor: Colors.black,
                    onPressed: (){
                        _idCheck(context);

                        print('회원가입 버튼 작동하니?');
                    },
                  ),
                )
              ],
          ),

        ),
      ),
    );
  }
}