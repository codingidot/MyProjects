

import 'dart:async';

import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:get/get_instance/src/extension_instance.dart';
import 'package:intl/intl.dart';

import 'Contents.dart';
import 'home.dart';
import 'local_notification.dart';
import 'variable.dart';


class Setting2 extends StatelessWidget {
  const Setting2({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: AlarmSetting2(),

    );
  }
}


class AlarmSetting2 extends StatefulWidget {
  const AlarmSetting2({Key? key}) : super(key: key);

  @override
  State<AlarmSetting2> createState() => _AlarmSetting2State();
}

class _AlarmSetting2State extends State<AlarmSetting2> {
  //변수 선언부
  String id=''; //로그인 성공시 아이디 담을  변수 일단 초기값을 빈문자열로 둔다.
  final Time time=Get.put(Time()); //전역변수 정보를 가져오거나 바꾼값을 저장할 곳
  variable va=Get.put(variable()); // 전역변수 아이디 정보값
  late int _value; //라디오버튼 선택된 값
  String result = '';
  late bool dayIsOn;
  late bool dayIsOff;
  late bool nightIsOn;
  late bool nightIsOff;
  late List<
      bool> dayIsSelected; //late는 현재는 값을 설정할 수 없지만 null이 되지 않게 나중에 설정하겠다는 것
  late List<bool> nightIsSelected;
  static final storage =
  new FlutterSecureStorage(); //flutter_secure_storage 사용을 위한 초기화 작업
  late Future<List<Contents>> students;//출력상품 List 받을것
  LocalNotification ln=LocalNotification();//notification용도
  late int key;

  //Dio 통신
  Dio dio = Dio(); //contentType을 json으로 받음
  Dio dio2=Dio(BaseOptions(//contentType을 text로 받음
    connectTimeout: 10000,
    receiveTimeout: 10000,

    contentType: 'text/plain', //json에서 text로 바꿈
    responseType: ResponseType.plain,
  ));


//=====================================================================================
  @override
  void initState() {
    //설정 정보의 초기값들을 Time에서 가져와서 설정해줌
    super.initState();
    //비동기로 flutter secure storage 정보를 불러오는 작업.
    // initState는 asny, await을 못하니 따로 메소드 만들어줘야 함
    WidgetsBinding.instance?.addPostFrameCallback((_) {
      _asyncMethod();//<<<<<<<<<<<<<<<<<<<<<이거
      //실행시 flutter secure storage에서 기존의 저장된 값을 가져와 업데이트 주기와
      //알람 on off 설정을 해줌
    });
    //ln.requestPermission(); //ios일 경우 권한 요청

    key=time.key;
    print('va.id는???????');
    print(va.id);
    id=va.id; //id를 전역변수로 바꿔줘야함


    dayIsOn=time.dayIsOn;
    dayIsOff=time.dayIsOff;
    nightIsOn=time.nightIsOn;
    nightIsOff=time.nightIsOff;
    dayIsSelected = [time.dayIsOn, time.dayIsOff];
    nightIsSelected = [time.nightIsOn, time.nightIsOff];
    _value=time.second;
  }
  //======================================================================


  // 프로그램 종료전에 설정했던 설정정보들을 가져와 프로그램 시작시에도 설정정보를 똑같이 하는 작업업
  _asyncMethod() async {//<<<<<<<<<<<<<<<<<<<<<<<<<<여기 메소드~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //read 함수를 통하여 key값에 맞는 정보를 불러dha. 이때 불러오는 결과의 타입은 String 타입임.
    //(데이터가 없을때는 null을 반환)
    var result1 = await storage.read(key: "periodInfo");//업데이트 주기
    var result2= await storage.read(key: 'dayOnOff');//낮 알람 on off 정보
    var result3=await storage.read(key: 'nightOnOff');// 밤 알람 on off 정보




// null값인 것들은 기본 초기값으로 나오게하고 종료전에 설정한 설정정보가 있다면 그 설정정보로 바꿔줌

    //업데이트 주기
    if(result1==null){
      print('초기값>> 기본 주기로 설정됨(5분)');

    }else{
      print('초기값>> ${result1}초의 주기로 설정됨');
      if(result1=='1'){
        time.second=1;
        _value=1;
      }else if( result1=='5'){
        time.second=5;
        _value=5;
      }else if(result1=='10'){
        time.second=10;
        _value=10;
      }
    }

    //낮 알람
    if(result2==null){
      print('초기값>> 기본 설정으로 설정됨(낮 알람 on)');
    }else{
      print('초기값>> 낮 알람 ${result2}으로 설정됨');
      time.day=result2;

      //알람 토글 스위치 상태를 위한것
      if(result2=='on'){
        //토글 스위치 on
        time.dayIsOn=true;
        time.dayIsOff=false;
        dayIsSelected = [true, false];

      }else{
        //토글 스위치 off
        time.dayIsOn=false;
        time.dayIsOff=true;
        dayIsSelected = [false, true];
      }

    }

    //밤 알람
    if(result3==null){
      print('초기값>> 기본 설정으로 설정됨(밤 알람 off)');
    }else{
      print('초기값>> 밤 알람 ${result3}으로 설정됨');
      //알람 토글 스위치 상태를 위한것
      if(result3=='on'){
        //토글 스위치 on
        time.nightIsOn=true;
        time.nightIsOff=false;
        nightIsSelected=[true, false];
      }else{
        //토글 스위치 off
        time.nightIsOn=false;
        time.nightIsOff=true;
        nightIsSelected=[false, true];
      }
    }


  }




  //================================================================================
//알람 on off 누르는 시점의 날짜가져옴
  String getToday() {

    DateTime now = DateTime.now();
    DateFormat('yyyy년 MM월 dd일').format(now);
    return DateFormat('yyyy년 MM월 dd일').format(now);
  }

//========================================================================
//낮 알람 토글 스위치 눌렀을때 실행 되는 메소드
//스위치 위치를 바꿔주고 값도 바꿔줌
  void toggleSelect(value) {
    //낮 알람설정 토글 버튼 눌렀을때 0이면 on 다른것들은 off
    //on
    if (value == 0) {
      //전역변수 변경
      time.day='on';
      time.dayIsOn=true;
      time.dayIsOff=false;
      //스위치 위치 변경
      dayIsOn=true;
      dayIsOff=false;
      //flutter secure storage에 변경된 값을 넣어줌
      storage.write(key: 'dayOnOff', value: 'on');

      //alert 메시지창
      showDialog(
          context: context,
          builder: (context) =>
              AlertDialog(
                title: Text('주간알람 설정이 켜졌습니다(ON)'),
                content: Text('08시~21시 사이에 알람을 받습니다 \n' + getToday(),
                    textAlign: TextAlign.center),
                actions: [
                  TextButton(onPressed: () {
                    Navigator.pop(context);
                  }, child: Text('Yes')),
                ],
              ));
    } else {
      //off
      //전역변수 변경
      time.day='off';
      time.dayIsOn = false;
      time.dayIsOff = true;
      //스위치 위치 변경
      dayIsOn=false;
      dayIsOff=true;
      //flutter secure storage에 변경된 값을 넣어줌
      storage.write(key: 'dayOnOff', value: 'off');



      //알람 버튼 눌렀을때 alert창
      showDialog(
          context: context,
          builder: (context) =>
              AlertDialog(
                title: Text('주간알람 설정이 꺼졌습니다(OFF)'),
                content: Text('08시~21시 사이에 알람을 받지 않습니다 \n' + getToday(),
                    textAlign: TextAlign.center),
                actions: [
                  TextButton(onPressed: () {
                    Navigator.pop(context);
                  }, child: Text('Yes')),
                ],
              ));
    }
    // 스위치 위치 반영해줌
    setState(() {
      dayIsSelected = [dayIsOn, dayIsOff];
      print('낮 알림이 ${time.day} 되었습니다');
    });
  }


  //========================================================================
  //밤 알람 토글 스위치 눌렀을때 실행 되는 메소드
  //스위치 위치를 바꿔주고 값도 바꿔줌
  void toggleSelect1(value) {
    //밤 알람설정 토글 버튼 눌렀을때 0이면 on 다른것들은 off
    //on
    if (value == 0) {
      //전역변수 변경
      time.night='on';
      time.nightIsOn = true;
      time.nightIsOff = false;
      //스위치 위치 바꿈
      nightIsOn=true;
      nightIsOff=false;
      //flutter secure storage에 변경된 값을 넣어줌
      storage.write(key: 'nightOnOff', value: 'on');

      //알람 버튼 눌렀을때 alert창
      showDialog(
          context: context,
          builder: (context) =>
              AlertDialog(
                title: Text('야간알람 설정이 켜졌습니다(ON)'),
                content: Text('21시~08시 사이에 알람을 받습니다 \n' + getToday(),
                    textAlign: TextAlign.center),
                actions: [
                  TextButton(onPressed: () {
                    Navigator.pop(context);
                  }, child: Text('Yes')),
                ],
              ));
    } else {
      //off
      //전역변수 변경
      time.night='off';
      time.nightIsOn = false;
      time.nightIsOff = true;
      //스위치 위치 변경
      nightIsOn=false;
      nightIsOff=true;
      //flutter secure storage에 변경된 값을 넣어줌
      storage.write(key: 'nightOnOff', value: 'off');

      //알람 버튼 눌렀을때 alert창
      showDialog(
          context: context,
          builder: (context) =>
              AlertDialog(
                title: Text('야간알람 설정이 꺼졌습니다(OFF)'),
                content: Text('21시~08시 사이에 알람을 받지 않습니다 \n' + getToday(),
                    textAlign: TextAlign.center),
                actions: [
                  TextButton(onPressed: () {
                    Navigator.pop(context);
                  }, child: Text('Yes')),
                ],
              ));
    }
    //스위치 위치 반영해줌
    setState(() {
      dayIsSelected = [dayIsOn, dayIsOff];
      nightIsSelected = [nightIsOn, nightIsOff];
      print('밤 알림이 ${time.night} 되었습니다');
    }


    );
  }
//===================================================================


  @override
  Widget build(BuildContext context) {
    return Scaffold(


        resizeToAvoidBottomInset: false,
        body:SingleChildScrollView(
          child: Container(

            child: Builder(builder: (BuildContext ctx) {
              return Column(

                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    '설정', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 31, ), textAlign: TextAlign.left,
                  ),
                  Divider(
                    thickness: 3,
                    color: Colors.grey,
                  ),
                  Text('데이터 갱신', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 21, ), textAlign: TextAlign.left,),
                  Divider(
                    thickness: 3,
                    color: Colors.grey,
                  )
                  ,
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [

                      //갱신 주기를 선택하는 라디오버튼 부분
                      Radio(
                        value: 1,
                        groupValue: _value,
                        onChanged: (value) {
                          //flutter secure storage에 변경된 값을 넣어줌
                          storage.write(key: 'periodInfo', value: '1');

                          //변경된 버튼 반영
                          setState(() {
                            _value=value as int;
                          });

                          //전역변수 변경
                          time.second=1;
                        },
                      ),
                      SizedBox(width: 10.0,),
                      Text('1분'),
                      SizedBox(width: 20.0,),

                      Radio(
                        value: 5,
                        groupValue: _value ,
                        onChanged: (value) {
                          //flutter secure storage에 변경된 값을 넣어줌
                          storage.write(key: 'periodInfo', value: '5');

                          //변경된 버튼 반영
                          setState(() {
                            _value=value as int;
                          });

                          //전역변수 변경
                          time.second=5;
                        },
                      ),
                      SizedBox(width: 10.0,),
                      Text('5분'),
                      SizedBox(width: 20.0,),

                      Radio(
                        value: 10,
                        groupValue: _value,
                        onChanged: (value) {
                          //flutter secure storage에 변경된 값을 넣어줌
                          storage.write(key: 'periodInfo', value: '10');

                          //변경된 버튼 반영
                          setState(() {
                            _value=value as int;
                          });

                          //전역변수 변경
                          time.second=10;
                        },
                      ),
                      SizedBox(width: 10.0,),
                      Text('10분'),
                      SizedBox(width: 20.0,),
                    ],
                  ),
                  Text('알림', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 41, ), textAlign: TextAlign.left,),
                  Divider(
                    thickness: 3,
                    color: Colors.grey,
                  ),
                  SizedBox(width: 30.0,),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text('주간알람(08시~21시)', style: TextStyle(fontSize: 18)),
                      //알람 on off 토글 버튼
                      ToggleButtons(
                        children: [
                          Padding(
                              padding: EdgeInsets.symmetric(horizontal: 16),
                              child: Text('ON', style: TextStyle(fontSize: 18))

                          ),
                          Padding(
                              padding: EdgeInsets.symmetric(horizontal: 16),
                              child: Text('OFF', style: TextStyle(fontSize: 18))

                          )
                        ],
                        isSelected: dayIsSelected, //스위치 위치 변경을 위한것
                        onPressed: toggleSelect, //스위치 눌렀을때 이벤트
                      ),
                    ],
                  ),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text('야간알람(21시~08시)', style: TextStyle(fontSize: 18)),
                      //알람 on off 토글 버튼
                      ToggleButtons(
                        children: [
                          Padding(
                              padding: EdgeInsets.symmetric(horizontal: 16),
                              child: Text('ON', style: TextStyle(fontSize: 18))

                          ),
                          Padding(
                              padding: EdgeInsets.symmetric(horizontal: 16),
                              child: Text('OFF', style: TextStyle(fontSize: 18))

                          ),
                        ],
                        isSelected: nightIsSelected,//스위치 변경을 위한것
                        onPressed: toggleSelect1,//스위치 눌렀을때 이벤트

                      ),


                    ],
                  ),

                  Text('맴버존', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 41, ), textAlign: TextAlign.left,),
                  Divider(
                    thickness: 3,
                    color: Colors.grey,
                  ),
                  SizedBox(width: 30.0,),
                  ListTile(
                    title: Text('로그아웃'),
                    onTap: (){
                      showDialog(
                        context: context,
                        builder: (context) =>
                            AlertDialog(
                              title: Text('로그아웃'),
                              content: Text('로그아웃 하시겠습니까?. \n',
                                  textAlign: TextAlign.center),
                              actions: [
                                TextButton(onPressed: () {
                                  storage.delete(key: "login");
                                  storage.delete(key: "checklogin");
                                  storage.delete(key: "date");
                                  va.id="초기값";
                                  Navigator.pop(context);
                                }, child: Text('Yes')),
                              ],
                            ),
                      );
                      Navigator.pushReplacement(
                        context,
                        CupertinoPageRoute(
                            builder: (context) => Home()),
                      );
                    },
                  ),
                ],
              );
            },),
          ),
        )
    );
  }
}