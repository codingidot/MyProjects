import 'dart:core';
import 'dart:developer';

import 'package:colorful_iconify_flutter/icons/emojione.dart';
import 'package:dio/dio.dart';
import 'package:dio/src/form_data.dart' as fd;
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:wow/contentsVO/contentVO.dart';
import 'package:wow/loginkeywordVO/loginkeywordVO.dart';
import 'package:wow/model/variable.dart';
import 'package:iconify_flutter/iconify_flutter.dart';
import 'package:intl/intl.dart';
//import 'package:pull_to_refresh/pull_to_refresh.dart';
import 'package:url_launcher/url_launcher.dart';
import 'all.dart';
import 'keyword.dart';
import 'login.dart';
import 'home.dart';

String URL = "http://shared00.iptime.org/php/";
Variable varIdx = Get.put(Variable());
String loginId = varIdx.srcVO().loginId;

final formatCurrency = NumberFormat.simpleCurrency(locale: "ko_KR");
//RefreshController rfc = RefreshController(initialRefresh: false);

var borderColor = Colors.black54;
double borderWidth = 1;
List<String> kList = [];

class Rank extends StatefulWidget {
  const Rank({Key? key}) : super(key: key);

  @override
  State<Rank> createState() => _RankState();
}

class _RankState extends State<Rank> {
  late Future<List<ContentsVO>> contentsVO;
  late Future<List<LoginKeywordVO>> tempList;

  var dio = Dio();

  @override
  void initState() {
    super.initState();
    contentsVO = getConList();
    tempList = getTempList();
  }

  Future<List<ContentsVO>> getConList() async {
    //log("들어오긴하냐?");
    List<ContentsVO> contents;

    var responseWithDio = await dio.get("${URL}RankList.php");
    //log(responseWithDio.toString());
    contents = (responseWithDio.data).map<ContentsVO>((json) {
      return ContentsVO.fromJson(json);
    }).toList();

    //log("가져오긴하나? ${contents.toString()}");

    return contents;
  }

  Future<List<LoginKeywordVO>> getTempList() async {
    log("여긴 들어오냐?");
    List<LoginKeywordVO> tempList;
    var formData = fd.FormData.fromMap({"loginid": loginId,});

    var responseWithDio = await dio.post(
      "${URL}LoginKeywordList.php", data: formData,);
    log("출력 목록 : ${responseWithDio.toString()}");
    tempList = (responseWithDio.data).map<LoginKeywordVO>((json) {
      return LoginKeywordVO.fromJson(json);
    }).toList();

    log("이건 가져오긴하냐? ${tempList.toString()}");

    if (tempList.length != 0) {
      kList.clear();
      for (var idx in tempList) {
        kList.add(idx.keyWord);
      }
    } else {
      kList.clear();
    }
    log("잘 담았어? ${kList.toString()}");
    tempList.clear();

    return tempList;
  }


  Future<void> contentsOpen(url) async {
    var res = Uri.parse(url);
    await launchUrl(res, mode: LaunchMode.externalApplication);
  }

  void _onRefresh() async {
    await Future.delayed(Duration(seconds: 1));
    //rfc.refreshCompleted();
  }




  @override
  Widget build(BuildContext context) {
    return Scaffold(


        body: Container(
      child: Column(mainAxisAlignment: MainAxisAlignment.center, children: <
          Widget>[
        Container(
          margin: EdgeInsets.all(10),
          //color: Colors.blue,
          child: Text("핫딜 랭킹",
              style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold)),
          height: 50,
        ),
        Expanded(
          //color: Colors.yellow,
          child: FutureBuilder<List<ContentsVO>>(
            future: contentsVO,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              if (snapshot.hasData == false) {
                return Center(
                  child: SizedBox(
                    child: CircularProgressIndicator(),
                    width: 30,
                    height: 30,
                  ),
                );
              } else if (snapshot.hasError) {
                return Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    "리스트를 불러오지 못했습니다.",
                    style: TextStyle(fontSize: 20),
                  ),
                );
              } else {
                return ListView.builder(
                  itemCount: snapshot.data.length,
                  itemBuilder: (BuildContext context, int idx) {
                    var content = snapshot.data[idx];
                    return Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: <Widget>[
                        Container(
                          decoration: BoxDecoration(
                            border: (() {
                              bool flag = false;
                              String str = content.conTitle;
                              //log("제목 : $str");
                              for (String idx in kList) {
                                //log("키워드 $idx");
                                if (str.contains(idx)) {
                                  flag = true;
                                }
                              }

                              if (flag) {
                                return Border.all(
                                    color: Colors.purpleAccent,
                                    width: 2
                                );
                              } else {
                                return Border.all();
                              }
                            })(),
                          ),
                          child: Row(children: <Widget>[
                            Container(
                              alignment: Alignment.center,
                              margin: const EdgeInsets.all(2.0),
                              height: 42,
                              width: 42,
                              decoration: BoxDecoration(
                                  color: (() {
                                    switch (idx) {
                                      case 0:
                                        return Colors.red;
                                      case 1:
                                        return Colors.orange;
                                      case 2:
                                        return Colors.yellow;
                                      default:
                                        if (idx % 2 != 0) {
                                          return Colors.black26;
                                        } else {
                                          return Colors.black12;
                                        }
                                    }
                                  })(),
                                  border: Border(
                                    bottom: BorderSide(
                                        color: borderColor, width: borderWidth),
                                    right: BorderSide(
                                        color: borderColor, width: borderWidth),
                                  )),
                              child: (() {
                                switch (idx) {
                                  case 0:
                                    return Iconify(
                                      Emojione.i_1st_place_medal,
                                      size: 35,
                                    );
                                  case 1:
                                    return Iconify(
                                      Emojione.i_2nd_place_medal,
                                      size: 35,
                                    );
                                  case 2:
                                    return Iconify(
                                      Emojione.i_3rd_place_medal,
                                      size: 35,
                                    );
                                  default:
                                    return Text(
                                      "${idx + 1}",
                                      style: TextStyle(
                                          fontSize: 20,
                                          color: Colors.black,
                                          fontWeight: FontWeight.bold),
                                    );
                                }
                              })(),
                            ),
                            Expanded(
                              child: Container(
                                child: GestureDetector(
                                    onTap: () {
                                      setState(() {
                                        log("누름");
                                        contentsOpen(content.conAdr);
                                      });
                                    },
                                    child: Column(
                                        mainAxisAlignment:
                                        MainAxisAlignment.start,
                                        crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                        children: <Widget>[
                                          Row(
                                            mainAxisAlignment: MainAxisAlignment
                                                .spaceBetween,
                                            children: [
                                              Container(
                                                //color: Colors.purple,
                                                margin:
                                                EdgeInsets.fromLTRB(
                                                    0, 1, 0, 0.5),
                                                child: Text(
                                                  "${content.conTitle}",
                                                  style: TextStyle(
                                                      fontSize: 15,
                                                      overflow:
                                                      TextOverflow.ellipsis),
                                                ),
                                                width: 314,
                                              ),
                                              Container(
                                                //color: Colors.blue,
                                                child: (() {
                                                  bool flag = false;
                                                  String str = content.conTitle;

                                                  for (String idx in kList) {
                                                    if (str.contains(idx)) {
                                                      flag = true;
                                                    }
                                                  }

                                                  if (flag) {
                                                    return Iconify(Emojione
                                                        .key);
                                                  } else {
                                                    return Text("");
                                                  }
                                                })(),
                                              ),
                                            ],
                                          ),
                                          Container(
                                            //color: Colors.pink,
                                            margin:
                                            EdgeInsets.fromLTRB(0, 1, 0, 0.5),
                                            child: Row(
                                              mainAxisAlignment:
                                              MainAxisAlignment.spaceBetween,
                                              children: <Widget>[
                                                Container(
                                                  child: Text(
                                                      "${(content.conDate)
                                                          .toString()
                                                          .substring(
                                                          2, ((content.conDate)
                                                          .toString()
                                                          .length - 3))}"),
                                                ),
                                                Container(
                                                    child: Row(
                                                        children: <Widget>[
                                                          Text(
                                                              "${formatCurrency
                                                                  .format(
                                                                  content
                                                                      .conPrice)} | "),
                                                          Icon(
                                                            Icons
                                                                .thumb_up_alt_outlined,
                                                            size: 15,
                                                          ),
                                                          Text(" ${content
                                                              .conSuggest}")
                                                        ]))
                                              ],
                                            ),
                                          ),
                                        ])),
                                decoration: BoxDecoration(
                                    color: (() {
                                      switch (idx) {
                                        case 0:
                                          return Colors.red;
                                        case 1:
                                          return Colors.orange;
                                        case 2:
                                          return Colors.yellow;
                                        default:
                                          if (idx % 2 != 0) {
                                            return Colors.black26;
                                          } else {
                                            return Colors.black12;
                                          }
                                      }
                                    })(),
                                    border: Border(
                                        bottom: BorderSide(
                                            color: borderColor,
                                            width: borderWidth))),
                              ),
                            ),
                          ]),
                        ),
                      ],
                    );
                  },
                );
              }
            },
          ),
        ),
      ]),
    ),
    );
  }
}
