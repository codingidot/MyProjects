import 'dart:convert';

import 'package:crypto/crypto.dart';
import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:get/get_instance/src/extension_instance.dart';
import 'package:wow/view/setting.dart';
import 'package:wow/view/setting2.dart';
import 'package:wow/view/variable.dart';
import '../env.dart';
import 'home.dart';

// Setting에 있는 로그인버튼을 누르면 여기로 이동
class login extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: loginform(),

    );
  }
}

class login2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: loginaction(),
      // 자동로그인 저장값 or 아이디 인자 유무로 setting  / setting2 분배
    );
  }
}

class loginaction extends StatefulWidget {

  @override
  State<loginaction> createState() => _loginactionState();
}

class _loginactionState extends State<loginaction> {

  DateTime now = new DateTime.now();

  variable va = Get.put(variable()); // 아이디 인자 값 저장소
  static final storage = new FlutterSecureStorage(); // 디바이스에 저장하는 문법



  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance?.addPostFrameCallback((_) { // storage 에 저장된 값 불러옴
      _asyncMethod();
    });
  }

  _asyncMethod() async {

    if(await storage.read(key: "date") == null){
      va.logindate = "첫로그인";
    }else{
      va.logindate= (await storage.read(key: "date"))!;

    print("여기야?");
    print(va.logindate);
    print("너는?");
    print(now);
    Duration diff=now.difference(DateTime.parse(va.logindate)); // 전역변수에 저장된 로그인 일시를 현재 시간에 뺌
    if(diff.inDays >= 15){ // 뺸날짜가 15일 이면 로그인이랑 자동로그인 값 삭제
      print("들어는왔니..?");
      storage.delete(key: "login");
      storage.delete(key: "checklogin");
      va.id="초기값";
    }
    Navigator.pushReplacement(
      context,
      CupertinoPageRoute(
          builder: (context) => Home()),
    );
    };
    String? userInfo = "";
    String? autoInfo = "";
    userInfo = await storage.read(key: "login"); //  storage에 저장된 login 키값에 대한 value, userInfo에 저장
                                          // login = idController.text
    autoInfo = await storage.read(key: "checklogin"); //  storage에 저장된 checklogin 키값에 대한 value, userInfo에 저장
                                                      // checklogin = checkBox (자동로그인) false or true

    print(userInfo);
    print(autoInfo);
    print(va.id);

    // 메인에서 Setting을 눌렀을 때 여기서 아이디값 , storage값 유무 판단해서 나눔
    if (userInfo != null && autoInfo == "true") {
      // userInfo의 정보가 비어있지 않거나 자동로그인이 true면 Setting 페이지로 이동
      va.id= userInfo;
      Navigator.pushReplacement(
          context,
          CupertinoPageRoute(
              builder: (context) =>
                  Setting2(
                  )));
    }else if(va.id != "초기값"){
      // 자동로그인은 선택안했지만 va값에 아이디 값이 들어가 있으면 Setting 페이지로 이동
      Navigator.pushReplacement(
          context,
          CupertinoPageRoute(
              builder: (context) =>
                  Setting2(
                  )));
    }
    else {
      Navigator.pushReplacement(
        context,
        CupertinoPageRoute(
            builder: (context) => Setting()),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold();
  }
}

class loginform extends StatefulWidget {
  const loginform({Key? key}) : super(key: key);

  @override
  State<loginform> createState() => _loginformState();
}

class _loginformState extends State<loginform> {

  bool _checkBox = false; // 자동로그인 value 값
  variable va = Get.put(variable());
  DateTime Date = DateTime.now();

  final formKey = GlobalKey<FormState>();
  Dio dio = Dio();
  TextEditingController idController = TextEditingController();
  TextEditingController pwController = TextEditingController();
  static final storage = new FlutterSecureStorage();


  Future _idCheckMember() async{ // 아이디 체크 메소드
    print('여기까지만 옴111');
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

  Future _pwCheckeMember() async{ // 비밀번호 체크 메소드
    print('여기까지만 옴222');
    var pwchecks;
    List<int> bytes = utf8.encode(pwController.text);
    print(bytes);
    String shapw1 = sha256.convert(bytes).toString(); // sha256 형으로 변환
    print(shapw1);
    var formData = FormData.fromMap(
      {
        "memId" : idController.text,
        "memPw" : shapw1,
      },
    );
    pwchecks= dio.post("${Env.URL_PREFIX}/memberpwCheck.php", data: formData);
    print (pwchecks.toString());
    return pwchecks;
  }

  void _idCheck(context) async {
    var result= await _idCheckMember();
    var res = result.toString();
    print(result);

    if (res != '{"success":null}') {
      print('true');
      var result2 =await _pwCheckeMember();
      var res2 = result2.toString();
      print(res2);
      print(idController.text);
      if (res2 != '{"success":null}') {
        print('비밀번호 일치 확인');
        showDialog(
            context: context,
            builder: (context) =>
                AlertDialog(
                  title: Text('로그인 완료'),
                  content: Text('로그인이 완료되었습니다. \n',
                      textAlign: TextAlign.center),
                  actions: [
                    TextButton(onPressed: () async {
                      // 로그인 성공 액션 후 va에 id값 저장
                      va.id=idController.text;
                      if(_checkBox.toString()=="true"){
                        // 자동로그인 value값이 true일 경우
                        print(_checkBox.toString());
                      await storage.write(
                        // 자동로그인 storage에 값 저장
                          key: "login",
                          value: idController.text.toString());
                      }
                      await storage.write(
                        key: "date",
                        value: Date.toString()); // 로그인 날짜 storage에 저장
                      print('va에 담긴'+idController.text);
                      Navigator.pop(context);
                    }, child: Text('Yes')),
                  ],

                ));
        Navigator.pushReplacement(
          context,
          CupertinoPageRoute(
              builder: (context) => Home()),
        );
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
                title: Text('가입된 아이디가 아닙니다.'),
                content: Text('가입된 아이디가 아닙니다. \n',
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
          child: Column(children: <Widget>[
            Text(
              '로그인', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 31, ), textAlign: TextAlign.left,
            ),
            Divider(
              thickness: 3,
              color: Colors.grey,
            ),
            SizedBox(
              height: 30,
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 5, 20, 10),
              child: TextField(
                controller: idController,
                decoration: InputDecoration(
                    border: OutlineInputBorder(), labelText: '아이디'),
              ),
            ),
            Container(
              padding: EdgeInsets.fromLTRB(20, 5, 20, 10),
              child: TextField(
                controller: pwController,
                decoration: InputDecoration(
                    border: OutlineInputBorder(), labelText: '패스워드'),
                obscureText: true,
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children:<Widget> [
                Text('자동로그인'),
                Checkbox(
                  value: _checkBox,

                  onChanged: (value) {
                    print(_checkBox);
                    setState(() {
                      _checkBox = value!;
                      storage.write(key: 'checklogin', value: _checkBox.toString());
                    });
                  },
                ),
              ],
            ),
            Container(
              child: RaisedButton(
                child: Text("로그인"),
                color: Colors.orange,
                textColor: Colors.black,
                onPressed: (){
                  _idCheck(context);

                  print('로그인 버튼 작동');
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