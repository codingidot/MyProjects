import 'dart:ui';
import 'package:dio/dio.dart';
import 'package:intl/intl.dart';
import 'package:flutter/material.dart';
import 'package:wow/env.dart';
import 'package:wow/model/dtoAll.dart';
import 'package:wow/view/search.dart';
import 'package:url_launcher/url_launcher.dart';

import 'rank.dart';
import 'keyword.dart';
import 'login.dart';
import 'home.dart';

class All extends StatefulWidget {
  @override
  State<All> createState() => _KeyWordState();
}

class _KeyWordState extends State<All> {


  @override
  // 초기 로드
  void initState() {
    super.initState();
    keyword = getAllList();
  }

  /// + 전역 메모리 set 시작 ==========================================
  // 접속 아이디 메모리로 저장
  var memId = 'test1';

  // 가격 저장용
  dynamic conprice;


  // 키워드 리스트 준비
  late Future<List<DTOAll>> keyword;

  // 서버 통신 패키지 준비
  Dio dio = Dio();

  /// - 전역 메모리 set 끝 ==========================================


  /// + Select 시작 =============================================
  Future<List<DTOAll>> getAllList() async {
    var responseWithDio;
    late List<DTOAll> keyword;

    // Dio 이용하여 통신
    try {
      // 서버로 요청
      responseWithDio = await dio.get(
        "${Env.URL_PREFIX}/allSelect.php",
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
  /// - Select 끝 =============================================


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
                Container(
                  padding: EdgeInsets.fromLTRB(20, 0, 20, 0),
                  child: Column(
                    children: [
                      /// + 제목 시작 ============================
                      Container(
                        child: const Center(
                            child: Text(
                              "전체 목록",
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
                        height: 520,
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
