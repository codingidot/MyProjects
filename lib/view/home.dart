import 'dart:async';
import 'dart:ui';
import 'package:dio/dio.dart';
import 'package:dio/src/form_data.dart' as fd;
import 'package:flutter_beep/flutter_beep.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:intl/intl.dart';
import 'package:flutter/material.dart';
import 'package:wow/env.dart';
import 'package:wow/model/dtoAll.dart';
import 'package:wow/view/search.dart';
import 'package:url_launcher/url_launcher.dart';

import 'Contents.dart';
import 'local_notification.dart';
import 'variable.dart';

class Home extends StatefulWidget {
  @override
  State<Home> createState() => _KeyWordState();
}

class _KeyWordState extends State<Home> {

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



  @override
  // 초기 로드
  void initState() {
    super.initState();
    keyword = getAllList();
    rank = getRankList();
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
    if(key==0) {
      print('새로고침 메소드 처음 실행');

      time.key=1;
      key=1;


      updatesecond1(id); //새로고침 메소드 실행
    }

  }




  //새로고침 메소드!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
      time.night=result3;
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



//===============================================================
  //설정한 시간으로 새로고침 부분
  void updatesecond1(id){
    //1분마다 새로고침
    if (time.second == 1) {
      Timer.periodic(Duration(seconds: time.second), (timer1) {
        id=va.id;
        print("id는 ${id}");
        print("1분 새로고침");
        //print(time.second);

        int now=DateTime.now().hour;//현재시간
        print("${DateTime.now().hour}시 입니다");
        if(8<=now && now<21 && time.day=='on'){//08~21시 사이에 주간 알람이 켜져있는 경우 새로고침됨

          students = getStudentList(id);// 메소드가 실행되어  업데이트됨

        }
        if((24>=now && now>=21) || (0<=now&&now<8)){//21~08시에
          if(time.night=='on') {//야간 알림이 켜져있으면

            students = getStudentList(id);// 메소드가 실행되어  업데이트됨

          }
        }

        if (time.second != 1) {// 설정에서 새로고침을 1분이 아니라 다른걸 선택한 경우
          timer1.cancel(); //타이머 정지됨
          updatesecond1(id);//  타이머 메소드가 실행되어 해당되는 시간의 타이머작동
        }
      });
    }

    //5분마다 새로고침
    if (time.second == 5) {
      Timer.periodic(Duration(seconds: time.second), (timer5) {
        id=va.id;
        print("id는 ${id}");
        print("5분 새로고침");
        //print(time.second);

        int now=DateTime.now().hour;//현재시간
        print("${DateTime.now().hour}시 입니다");
        if(8<=now && now<21 && time.day=='on'){//08~21시 사이에 주간 알람이 켜져있는 경우 새로고침됨

          students = getStudentList(id);// 메소드가 실행되어  업데이트됨

        }
        if((24>=now && now>=21) || (0<=now&&now<8)){//21~08시에
          if(time.night=='on') {//야간 알림이 켜져있으면

            students = getStudentList(id);// 메소드가 실행되어  업데이트됨

          }
        }

        if (time.second != 5) {// 설정에서 새로고침을 5분이 아니라 다른걸 선택한 경우
          timer5.cancel(); //타이머 정지됨
          updatesecond1(id);//  타이머 메소드가 실행되어 해당되는 시간의 타이머작동
        }
      });
    }


    //10분마다 새로고침
    if (time.second == 10) {
      Timer.periodic(Duration(seconds: time.second), (timer10) {
        id=va.id;
        print("id는 ${id}");
        print("10분 새로고침");
        //print(time.second);

        int now=DateTime.now().hour;//현재시간
        print("${DateTime.now().hour}시 입니다");
        if(8<=now && now<21 && time.day=='on'){//08~21시 사이에 주간 알람이 켜져있는 경우 새로고침됨

          students = getStudentList(id);// 메소드가 실행되어  업데이트됨

        }
        if((24>=now && now>=21) || (0<=now&&now<8)){//21~08시에
          if(time.night=='on') {//야간 알림이 켜져있으면

            students = getStudentList(id);// 메소드가 실행되어  업데이트됨

          }
        }

        if (time.second != 10) {// 설정에서 새로고침을 10분이 아니라 다른걸 선택한 경우
          timer10.cancel(); //타이머 정지됨
          updatesecond1(id);//  타이머 메소드가 실행되어 해당되는 시간의 타이머작동
        }
      });
    }
  }



  //============================================================


  //새로고침시 리스트 업데이트 처리
  Future<List<Contents>> getStudentList(id) async {
    print('리스트가 업데이트됨');
    var response;//keyword 테이블에서 아이디에 해당하는 키워드들
    var response2;// contents중 checker에 없는것들 뽑아줌
    var response3;//checker에 없는 것들을 이용한 insert문
    var response4;//checker에 있는 conid와 일치하는 contents들
    late List<Contents> students;



    // Dio 이용하여 통신
    try {
      // 서버로 요청
      var formData0 = fd.FormData.fromMap({

        "memId" :id, //로그인 성공한 아이디를 여기 넣으면됨
      });


      response=await dio2.post("http://shared00.iptime.org/php/keywordFind.php", data: formData0);//해당 아이디 키워드 불러옴
      //print("$response");//아이디에 해당하는 키워드들

      var formData1 = fd.FormData.fromMap(//아이디와 키워드들 보내줌

        {
          "keyword" :'$response',//위에서 받은 키워드
          "memId" :id
        },
      );

      response2=  await dio2.post("http://shared00.iptime.org/php/findConidNotInChecker.php", data: formData1);
      //print("리스폰스2${response2}123");
      //아이디에 해당하는 키워드들과 아이디를 보내줘서 2일전부터 현재까지의 contents중 checker에 없는것들 뽑아줌
      if(response2.toString()!="no"){ //checker에 들어갈것이 없으면 효과음 나지 않음
        ln.initLocalNotificationPlugin();
        ln.sampleNotification();
        print('기존 리스트에서 추가된 리스트가 있습니다!!!!!!!!!!!');
        //ln.sampleNotification();//notification을 띄움
        await FlutterBeep.playSysSound(47);//알람 소리
      }
      var formData2 = fd.FormData.fromMap( //chekcer에 넣어줄 리스트와 아이디 보내줌
        {
          "insertList" :'$response2',//위에서 받은 넣어줄 리스트
          "memId" :id
        },
      );
      response3= await dio2.post("http://shared00.iptime.org/php/checkerInsert.php", data: formData2);
      //위에서 받은 리스트를 checker에 insert해줌
      // print(response3); //insert하는 sql문 출력

      var formData3 = fd.FormData.fromMap(
        {

          "memId" :id
          //보내주는 formData는 요청 하나당 하나밖에 사용못하니 아이디 보내는 fromData 또 만듬
        },
      );
      response4 = await dio.post("http://shared00.iptime.org/php/contentsSelectInChecker.php", data: formData3);
      //checker에  있는 conid를 이용해서 contents에서 해당 conid 상품정보 출력
      //print(response4); //checker에 있는 keywrord에 해당하는 contents들

      //SystemSound.play(SystemSoundType.click);



      students = (response4.data).map<Contents>((json) { //response4에서 받은 정보를 받아서 List로 students에 넣어줌
        return Contents.fromJson(json);
      }).toList();
    } catch (e) {
      print(e);
    }

    return students;
  }//getStudentList 메소드 끝

  //================================================================================
//알람 on off 누르는 시점의 날짜가져옴
  String getToday() {

    DateTime now = DateTime.now();
    DateFormat('yyyy년 MM월 dd일').format(now);
    return DateFormat('yyyy년 MM월 dd일').format(now);
  }

//========================================================================
  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

  /// + 전역 메모리 set 시작 ==========================================
  // 접속 아이디 메모리로 저장
  var memId;

  // 가격 저장용
  dynamic conprice;


  // 키워드 리스트 준비
  late Future<List<DTOAll>> keyword;
  late Future<List<DTOAll>> rank;

  // 서버 통신 패키지 준비


  /// - 전역 메모리 set 끝 ==========================================


  /// + all Select 시작 =============================================
  Future<List<DTOAll>> getAllList() async {
    var responseWithDio;
    late List<DTOAll> keyword;

    // Dio 이용하여 통신
    try {
      // 서버로 요청
      responseWithDio = await dio.get(
        "${Env.URL_PREFIX}/3Select.php",
      );


      keyword = (responseWithDio.data).map<DTOAll>((json) {
        return DTOAll.fromJson(json);
      }).toList();

    } catch (e) {
      print(e);
    }

    // Dio가 통신하여 keyword 리스트 채워서 반환
    return keyword;
  }
  /// - all Select 끝 =============================================



  /// + rank Select 시작 =============================================
  Future<List<DTOAll>> getRankList() async {
    var responseWithDio;
    late List<DTOAll> rank;

    // Dio 이용하여 통신
    try {
      // 서버로 요청
      responseWithDio = await dio.get(
        "${Env.URL_PREFIX}/5rankSelect.php",
      );


      rank = (responseWithDio.data).map<DTOAll>((json) {
        return DTOAll.fromJson(json);
      }).toList();

    } catch (e) {
      print(e);
    }

    // Dio가 통신하여 keyword 리스트 채워서 반환
    return rank;
  }
  /// - rank Select 끝 =============================================








  /// + 얼럿 메소드 =======
  void alertMsg(str){
    showDialog(
        context: context,
        barrierDismissible: false,
        builder: (BuildContext ctx){
          return AlertDialog(
            content: ElevatedButton(
              child: Text(str),
              onPressed: () {
                Navigator.pop(context, "Cancel");
              },
            ),
          );
        }
    );
  }
  /// - 얼럿 메소드 =====




  /// + URL 런쳐 =======
  launchURL(str) async {
    var url = str;
    print(url);
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
  /// - URL 런쳐 ========




  /// VIEW
  @override
  Widget build(BuildContext context) {
    return Scaffold(


      resizeToAvoidBottomInset : false,
      body: SafeArea(
        child: SingleChildScrollView(
            child: Column(
              children: [
                //Search(),
                Container(
                  padding: EdgeInsets.fromLTRB(20, 0, 20, 0),
                  child: Column(
                    children: [
                      /// + 제목 시작 ============================
                      Container(
                        child: const Center(
                            child: Text(
                              "최신 핫딜",
                              style: TextStyle(
                                  fontSize: 20,
                                  fontWeight: FontWeight.bold
                              ),
                            )
                        ),
                        //color: Colors.orange,
                        width: double.infinity - 100,
                        height: 40,
                      ),
                      /// - 제목 끝 ============================

                      const SizedBox(
                        height: 15,
                      ),


                      /// + 목록 출력 시작 =====================
                      SizedBox (
                        height: 250,
                        child: FutureBuilder<List<DTOAll>>(
                          future: keyword,
                          builder: (BuildContext context, AsyncSnapshot snapshot) {
                            // By default, show a loading spinner.
                            if (!snapshot.hasData) return CircularProgressIndicator();
                            return ListView.builder(
                              itemCount: snapshot.data.length,
                              itemBuilder: (BuildContext context, int index) {
                                var data = snapshot.data[index];


                                // 가격 형식 변경
                                int conpriceInt = int.parse(data.conprice.toString());
                                var f = NumberFormat('###,###,###,###');

                                if (conpriceInt == 0){
                                  conprice = '';
                                } else {
                                  conprice = f.format(conpriceInt)+'원';
                                }


                                return Container(
                                  child: Column(
                                    children: [
                                      Container(
                                        child: ElevatedButton(
                                          style: ElevatedButton.styleFrom(
                                              primary: Colors.amberAccent,
                                              shadowColor: Colors.white,
                                              shape: RoundedRectangleBorder(
                                                  borderRadius: BorderRadius.all(Radius.circular(3),
                                                  )

                                              )
                                          ),
                                          onPressed: (){
                                            launchURL('${data.conadr}');
                                          },
                                          child: Container(
                                            height: 75,
                                            width: double.infinity - 100,
                                            padding: EdgeInsets.fromLTRB(10, 5, 10, 5),
                                            decoration: BoxDecoration(
                                            ),
                                            child: Column(
                                              children: [
                                                Padding(padding: EdgeInsets.all(5)),

                                                Container(
                                                  height: 20,
                                                  width: double.infinity - 100,
                                                  child: Text('${data.contitle}',
                                                    overflow:TextOverflow.ellipsis,
                                                    style: TextStyle(
                                                        fontSize: 15,
                                                        color: Colors.black
                                                    ),
                                                  ),
                                                ),
                                                SizedBox(
                                                  height: 10,
                                                ),
                                                Container(
                                                    height: 20,
                                                    child: Row(
                                                      children: [
                                                        Container(
                                                          width: 180,
                                                          child: Text(
                                                              '${data.condate}',
                                                              style: TextStyle(
                                                                  fontSize: 12,
                                                                  color: Colors.grey
                                                              )
                                                          ) ,
                                                        ),
                                                        Container(
                                                          width: 120,
                                                          child: Row(
                                                            crossAxisAlignment: CrossAxisAlignment.stretch,
                                                            mainAxisAlignment: MainAxisAlignment.end,
                                                            children: [
                                                              Container(
                                                                child: Text(
                                                                    '${conprice}',
                                                                    style: TextStyle(
                                                                        fontSize: 12,
                                                                        color: Colors.grey
                                                                    )
                                                                ) ,
                                                              ),
                                                              SizedBox(
                                                                  width:15
                                                              ),
                                                              Container(
                                                                child: Icon(
                                                                    Icons.thumb_up,
                                                                    size: 12,
                                                                    color: Colors.grey
                                                                ),
                                                              ),
                                                              SizedBox(
                                                                width: 5,
                                                              ),
                                                              Container(
                                                                padding: EdgeInsets.fromLTRB(0, 3, 0, 0) ,
                                                                child: Text(
                                                                    '${data.consuggest}',
                                                                    style: TextStyle(
                                                                        fontSize: 12,
                                                                        color: Colors.grey
                                                                    )
                                                                ) ,
                                                              ),
                                                            ],
                                                          ),
                                                        )
                                                      ],
                                                    )


                                                )

                                              ],
                                            ),
                                          ),
                                        ),
                                      ),
                                      SizedBox(
                                        height: 10,
                                      ),
                                    ],
                                  ),
                                );
                              },
                            );
                          },
                        ),
                      ),
                      /// - 목록 출력 끝 =====================



                      Container(
                        child: const Center(
                            child: Text(
                              "TOP 5 핫딜",
                              style: TextStyle(
                                  fontSize: 20,
                                  fontWeight: FontWeight.bold
                              ),
                            )
                        ),
                        //color: Colors.orange,
                        width: double.infinity - 100,
                        height: 80,
                      ),
                      /// + 목록 출력 시작 =====================
                      SizedBox (
                        height: 450,
                        child: FutureBuilder<List<DTOAll>>(
                          future: rank,
                          builder: (BuildContext context, AsyncSnapshot snapshot) {
                            // By default, show a loading spinner.
                            if (!snapshot.hasData) return CircularProgressIndicator();
                            return ListView.builder(
                              itemCount: snapshot.data.length,
                              itemBuilder: (BuildContext context, int index) {
                                var data = snapshot.data[index];


                                // 가격 형식 변경
                                int conpriceInt = int.parse(data.conprice.toString());
                                var f = NumberFormat('###,###,###,###');

                                if (conpriceInt == 0){
                                  conprice = '';
                                } else {
                                  conprice = f.format(conpriceInt)+'원';
                                }


                                return Container(
                                  child: Column(
                                    children: [
                                      Container(
                                        child: ElevatedButton(
                                          style: ElevatedButton.styleFrom(
                                              primary: Colors.amberAccent,
                                              shadowColor: Colors.white,
                                              shape: RoundedRectangleBorder(
                                                  borderRadius: BorderRadius.all(Radius.circular(3),
                                                  )

                                              )
                                          ),
                                          onPressed: (){
                                            launchURL('${data.conadr}');
                                          },
                                          child: Container(
                                            height: 75,
                                            width: double.infinity - 100,
                                            padding: EdgeInsets.fromLTRB(10, 5, 10, 5),
                                            decoration: BoxDecoration(
                                            ),
                                            child: Column(
                                              children: [
                                                Padding(padding: EdgeInsets.all(5)),

                                                Container(
                                                  height: 20,
                                                  width: double.infinity - 100,
                                                  child: Text('${data.contitle}',
                                                    overflow:TextOverflow.ellipsis,
                                                    style: TextStyle(
                                                        fontSize: 15,
                                                        color: Colors.black
                                                    ),
                                                  ),
                                                ),
                                                SizedBox(
                                                  height: 10,
                                                ),
                                                Container(
                                                    height: 20,
                                                    child: Row(
                                                      children: [
                                                        Container(
                                                          width: 180,
                                                          child: Text(
                                                              '${data.condate}',
                                                              style: TextStyle(
                                                                  fontSize: 12,
                                                                  color: Colors.grey
                                                              )
                                                          ) ,
                                                        ),
                                                        Container(
                                                          width: 120,
                                                          child: Row(
                                                            crossAxisAlignment: CrossAxisAlignment.stretch,
                                                            mainAxisAlignment: MainAxisAlignment.end,
                                                            children: [
                                                              Container(
                                                                child: Text(
                                                                    '${conprice}',
                                                                    style: TextStyle(
                                                                        fontSize: 12,
                                                                        color: Colors.grey
                                                                    )
                                                                ) ,
                                                              ),
                                                              SizedBox(
                                                                  width:15
                                                              ),
                                                              Container(
                                                                child: Icon(
                                                                    Icons.thumb_up,
                                                                    size: 12,
                                                                    color: Colors.grey
                                                                ),
                                                              ),
                                                              SizedBox(
                                                                width: 5,
                                                              ),
                                                              Container(
                                                                padding: EdgeInsets.fromLTRB(0, 3, 0, 0) ,
                                                                child: Text(
                                                                    '${data.consuggest}',
                                                                    style: TextStyle(
                                                                        fontSize: 12,
                                                                        color: Colors.grey
                                                                    )
                                                                ) ,
                                                              ),
                                                            ],
                                                          ),
                                                        )
                                                      ],
                                                    )


                                                )

                                              ],
                                            ),
                                          ),
                                        ),
                                      ),
                                      SizedBox(
                                        height: 10,
                                      ),
                                    ],
                                  ),
                                );
                              },
                            );
                          },
                        ),
                      ),
                      /// - 목록 출력 끝 =====================
                    ],
                  ),
                ),
              ],
            )//SingleChildScrollView
        ),
      ),
    );
  }
}
