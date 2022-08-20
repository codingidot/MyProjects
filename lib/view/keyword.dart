import 'dart:ui';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:dio/src/form_data.dart' as fd;
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:wow/env.dart';
import 'package:wow/model/dtoKeyword.dart';
import 'package:wow/view/search.dart';
import 'variable.dart';
import 'all.dart';
import 'rank.dart';
import 'login.dart';
import 'home.dart';


class KeyWord extends StatefulWidget {
  @override
  State<KeyWord> createState() => _KeyWordState();
}

class _KeyWordState extends State<KeyWord> {


  @override
  // 초기 로드
  void initState() {
    super.initState();
    memId=va.id;
    keyword = getKeywordList();
  }

  /// + 전역 메모리 set 시작 ==========================================
  // 접속 아이디 메모리로 저장
  var memId ;
  variable va=Get.put(variable()); // 전역변수 아이디 정보값
  // 고유 식별자(pk)
  late var keyUniq ;

  // 수정 후 키워드
  late var toKeyWord;

  // 키워드 리스트 준비
  late Future<List<DTOKeyword>> keyword;
  
  // 서버 통신 패키지 준비
  Dio dio = Dio();

  // 폼필드 컨트롤러 준비
  //final formKey = GlobalKey<FormState>();
  TextEditingController keyController = new TextEditingController();
  TextEditingController tokeyController = new TextEditingController();
  /// - 전역 메모리 set 끝 ==========================================



  /// + Insert 시작 =============================================
  // DB에 추가 (1)
  void _onInsertConfirm(context) async {
    // 쿼리 처리 결과값 저장할 깡통 result 준비
    late var result;

    if (keyController.text.length < 2 ){
      // 글자수가 2글자 이하라면
      showDialog(
          context: context,
          barrierDismissible: false,
          builder: (BuildContext ctx){
            return AlertDialog(
              content: ElevatedButton(
                child: Text('2글자 이상 입력해주세요.'),
                onPressed: () {
                  Navigator.pop(context, "Cancel");
                },
              ),
            );
          }
      );

    }else{
      // 글자수가 2글자 이상이라면
      result = await _keywordConfirm();
      print(result.toString());

      // 등록에 실패 한 경우 ( 대부분 중복.)
      if (result.toString() == '{"success":false}') {
        showDialog(
            context: context,
            barrierDismissible: false,
            builder: (BuildContext ctx){
              return AlertDialog(
                content: ElevatedButton(
                  child: Text('이미 등록된 키워드입니다.'),
                  onPressed: () {
                    Navigator.pop(context, "Cancel");
                  },
                ),
              );
            }
        );
      }

      // 성공한 경우
      if (result.toString() == '{"success":true}'){
        // 폼필드 초기화
        keyController.clear();
        // 새로고침
        setState((){
          keyword = getKeywordList();
        });

        // 얼럿 출력
        alertMsg('키워드 등록이 완료되었습니다');
      }
    }
  }

  // DB에 추가 (2)
  Future _keywordConfirm() async {
    //리스트 준비


    var formData = fd.FormData.fromMap({"memId" : memId,
      "keyWord": keyController.text,
    });




    // php 호출 및 리스트 파라미터 전송
    return await dio.post("${Env.URL_PREFIX}/keywordInsert.php", data: formData);
  }
  /// - Insert 끝 =============================================


  /// + Select 시작 =============================================
  Future<List<DTOKeyword>> getKeywordList() async {
    var responseWithDio;
    late List<DTOKeyword> keyword;

    var formData = fd.FormData.fromMap({"memId" : memId});

    // Dio 이용하여 통신
    try {
      // 서버로 요청
      responseWithDio = await dio.post(
        "${Env.URL_PREFIX}/keywordSelect.php",
          data: formData
      );


      keyword = (responseWithDio.data).map<DTOKeyword>((json) {
        return DTOKeyword.fromJson(json);
      }).toList();

    } catch (e) {
      print(e);
    }

    // Dio가 통신하여 keyword 리스트 채워서 반환
    print("출력 목록 : ${responseWithDio.toString()}");
    return keyword;
  }
  /// - Select 끝 =============================================




  /// + Delete 시작 =============================================

  // 삭제 확인
  void confirmDelete(context, str) {
    keyUniq = str;
    print(keyUniq);

    showDialog(
      context: context as BuildContext,
      builder: (BuildContext context) {
        return AlertDialog(
          content: Text('선택한 키워드를 삭제할까요?'),
          actions: <Widget>[
            ElevatedButton(
              child: Icon(
                Icons.close,
                color: Colors.white,
              ),
              onPressed: () => Navigator.pop(context, "Cancel"),
            ),
            ElevatedButton(
              child: Icon(
                Icons.check,
                color: Colors.white
              ),
              onPressed: () {
                keywordDelete(context);
                Navigator.pop(context, "Cancel");
              }
            ),
          ],
        );
      },
    );
  }

  // 삭제 승인시 진행
  void keywordDelete(context) async {
    var formData = fd.FormData.fromMap({

      "keyUniq": keyUniq
    });

    late var result;
    result = await dio.post("${Env.URL_PREFIX}/keywordDelete.php", data: formData);
    print(result);

    // 새로고침
    setState((){
      keyword = getKeywordList();
    });

    // 얼럿 출력
    alertMsg('키워드 삭제가 완료되었습니다');

  }
  /// - Delete 끝 =============================================



  /// + Update 시작 ===========================================
  void onUpdateConfirm(context, keyword) async {
    keyUniq = '${memId}%_%${keyword}';
    print(keyUniq);

    showDialog(
      context: context as BuildContext,
      builder: (BuildContext context) {
        return AlertDialog(
          content: Text('수정 후 키워드를 입력해주세요.'),

          actions: <Widget>[
            TextFormField(
              controller: tokeyController,
            ),
            SizedBox(
              height: 10,
            ),
            Row(
              children: [
                Container(
                  width: 143,
                  child: ElevatedButton(
                    child: Icon(
                      Icons.close,
                      color: Colors.white,
                    ),
                    onPressed: () {
                      Navigator.pop(context, "Cancel");
                      tokeyController.clear();
                    }
                  ),
                ),
                SizedBox(
                  width: 10,
                ),
                Container(
                  width: 143,
                  child: ElevatedButton(
                    child: Icon(
                        Icons.check,
                        color: Colors.white
                    ),
                    onPressed: () {
                      toKeyWord = tokeyController.text;
                      keywordUpdate();
                      Navigator.pop(context, "Cancel");
                      tokeyController.clear();
                    }
                  ),
                )
              ],
            )
          ],
        );
      },
    );
  }

  Future keywordUpdate() async {
    var formData = fd.FormData.fromMap({

      "toKeyWord": toKeyWord,
      "keyUniq": keyUniq,
      "toKeyUniq": '${memId}%_%${toKeyWord}'

    });

    await dio.post("${Env.URL_PREFIX}/keywordUpdate.php", data: formData);

    // 폼필드 초기화
    keyController.clear();

    // 새로고침
    setState((){
      keyword = getKeywordList();
    });

    // 얼럿 출력
    alertMsg('키워드 수정이 완료되었습니다');
  }
  /// - Update 끝 ============================================




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
                            "키워드",
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


                    /// + 등록창 시작 =========================
                    const SizedBox(
                      height: 10,
                    ),
                    Row(
                      children: [
                        SizedBox(
                          width: 217,
                          child: TextFormField(
                            controller: keyController,
                            decoration: const InputDecoration(
                              hintText: '키워드를 입력해주세요',
                              contentPadding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
                              border: OutlineInputBorder(
                              ),
                              enabledBorder: OutlineInputBorder(
                                borderSide: BorderSide(color: Colors.deepOrange, width: 1.0),
                              ),
                              focusedBorder: OutlineInputBorder(
                                borderSide: BorderSide(color: Colors.deepOrange, width: 2.0),
                              ),
                            ),
                          ),
                        ),
                        const SizedBox(
                          width: 10,
                        ),
                        SizedBox(
                            height: 46,
                            width: 125,
                            child: ElevatedButton(
                                style: ElevatedButton.styleFrom(
                                  primary: Colors.deepOrange,
                                  textStyle: TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.w500
                                  ),
                                  onPrimary: Colors.white,
                                  elevation: 0
                                ),
                                onPressed: (){
                                  _onInsertConfirm(context);
                                },
                                child: Text('등록')
                            )
                        ),
                      ],
                    ),
                    const SizedBox(
                      height: 30,
                    ),
                    /// - 등록창 끝 =========================


                    /// + 목록 출력 시작 =====================
                    SizedBox (
                      height: 440,
                      child: FutureBuilder<List<DTOKeyword>>(
                        future: keyword,
                        builder: (BuildContext context, AsyncSnapshot snapshot) {
                          // By default, show a loading spinner.
                          if (!snapshot.hasData) return CircularProgressIndicator();
                          print(memId);
                          // Render student lists
                          return ListView.builder(
                            itemCount: snapshot.data.length,
                            itemBuilder: (BuildContext context, int index) {
                              var data = snapshot.data[index];
                              return Container(
                                child: Column(
                                  children: [
                                    Container(
                                      height: 40,
                                      decoration: BoxDecoration(
                                        border: Border.all(
                                          width: 1,
                                          color: Colors.black12,
                                        ),
                                      ),
                                      child: Row(
                                        children: [
                                          Padding(padding: EdgeInsets.all(10)),
                                          Container(
                                            width: 200,
                                            child: Text('${data.keyword}'),
                                          ),
                                          Container(
                                            width: 60,
                                            child: IconButton(
                                              icon: Icon(Icons.edit),
                                              onPressed: () {
                                                onUpdateConfirm(context, '${data.keyword}');
                                                print('수정');
                                              },

                                              //color: Colors.white,
                                            ),
                                            //color: Colors.white,
                                          ),
                                          SizedBox(
                                            width: 10,
                                          ),
                                          Container(
                                            width: 60,
                                            child: IconButton(
                                              icon: Icon(Icons.delete),
                                              onPressed: () {
                                                confirmDelete(context,'${memId}%_%${data.keyword}');
                                              },
                                              //color: Colors.white,
                                            ),
                                          ),
                                        ],
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
