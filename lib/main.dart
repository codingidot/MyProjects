
import 'dart:async';
import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:wow/view/login.dart';
import 'package:wow/view/searchresult.dart';
import 'view/all.dart';
import 'view/home.dart';
import 'view/keyword.dart';
import 'view/rank.dart';
import 'view/setting.dart';
import 'model/variable.dart';

final _searchEditCon = TextEditingController();

Variable varIdx = Get.put(Variable());

String _searchIdx = "";

//RefreshController rfc = RefreshController(initialRefresh: false);

void main(){

  runApp(search());
}

void onRefresh() async {
  await Future.delayed(Duration(seconds: 1));
  //rfc.refreshCompleted();
}

class search extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'menu page',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.amber,
      ),
      home: MyMenuPage(),
    );
  }
}

class MyMenuPage extends StatefulWidget {
  const MyMenuPage({Key? key}) : super(key: key);

  @override
  State<MyMenuPage> createState() => _MyMenuPageState();
}

class _MyMenuPageState extends State<MyMenuPage> {

  late Timer _timer;

  Future<dynamic> _showdialog(BuildContext context){
    return showDialog(
        context: context,
        builder: (BuildContext context) {
          _timer = Timer(Duration(seconds: 2), () {
            Navigator.of(context).pop();
          });

          return AlertDialog(
            content: Text("검색어를 입력해주세요"),
          );
        }
    ).then((val){
      if (_timer.isActive){
        _timer.cancel();
      }
    });
  }

  int _selectedIndex=0;
  int _pageIdx =0;
  List<BottomNavigationBarItem> bottomItems=[];

  List pages=[
    Home(),
    All(),
    Rank(),
    KeyWord(),
    login2(),
    SearchResult(),
  ];

  @override
  Widget build(BuildContext context) {

    return GestureDetector(
      onTap: () {
        FocusManager.instance.primaryFocus?.unfocus();
      },
      onVerticalDragStart: (details) {
        log("드래그 시작");
        FocusManager.instance.primaryFocus?.unfocus();
      },
      onVerticalDragEnd: (details) {
        log("드래그 끝");
        FocusManager.instance.primaryFocus?.unfocus();
      },
      child: Scaffold(
          appBar: AppBar(
            title: Text('KING GOD DEAL'),
          ),

          bottomNavigationBar: BottomNavigationBar(
            type: BottomNavigationBarType.fixed,
            backgroundColor: Colors.grey,
            selectedItemColor: (_pageIdx != 5) ? Colors.white : Colors.white.withOpacity(.60),
            unselectedItemColor: Colors.white.withOpacity(.60),
            selectedFontSize: 14,
            unselectedFontSize: 14,
            currentIndex: _selectedIndex,
            onTap: (int index) {
              setState(() {
                _selectedIndex = index;
                _pageIdx = index;
              });
            },
            items:[
              BottomNavigationBarItem(
                icon: Icon(Icons.home),
                label: 'HOME',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.line_weight),
                label: 'ALL',
              ),
              BottomNavigationBarItem(

                icon: Icon(Icons.leaderboard),
                label: 'RANK',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.vpn_key),
                label: 'KEYWORD',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.settings),
                label: 'SETTING',
              ),
            ],
          ),
          body: SafeArea(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Container(
                  child: Column(
                      children: [
                        Container(
                          child: ((){
                            if(_selectedIndex != 099 && _selectedIndex != 4){
                              return Container(
                                color: Colors.deepOrange,
                                height: 50,
                                padding: EdgeInsets.fromLTRB(8, 8, 8, 8),
                                child:
                                Container(
                                  color: Colors.white,
                                  child: TextField(
                                    controller: _searchEditCon,
                                    style: TextStyle(fontSize: 14),
                                    decoration:
                                    InputDecoration(
                                      border: InputBorder.none,
                                      focusedBorder: InputBorder.none,
                                      hoverColor: Colors.red,
                                      suffixIcon: IconButton(
                                        icon : Icon(Icons.search,
                                          color: Colors.black,
                                        ),
                                        onPressed: (){
                                          FocusManager.instance.primaryFocus?.unfocus();
                                          setState(() {
                                            log("나와!!");
                                            _searchIdx = _searchEditCon.text.replaceAll(" ", "");
                                            _searchEditCon.clear();
                                            if(_searchIdx != ""){
                                              log(_searchIdx);
                                              varIdx.updateInfo(_searchIdx);
                                              _pageIdx = 5;
                                            }else{
                                              _showdialog(context);
                                            }
                                          });
                                        },
                                      ),
                                      contentPadding: EdgeInsets.only(left: 10, bottom: 0, top: 0, right: 0),
                                      hintText: '상품을 입력하세요',
                                    ),
                                    onSubmitted: (String text){
                                      FocusManager.instance.primaryFocus?.unfocus();
                                      setState(() {
                                        log("나와!!");
                                        _searchIdx = text.replaceAll(" ", "");
                                        _searchEditCon.clear();
                                        if(_searchIdx != ""){
                                          log(_searchIdx);
                                          varIdx.updateInfo(_searchIdx);
                                          _pageIdx = 5;
                                          varIdx.updateInfo(text);
                                        }else{
                                          _showdialog(context);
                                        }
                                      });
                                    },
                                  ),
                                ),
                              );
                            }
                          })(),
                        ),
                        SizedBox(
                          height: 10,
                        ),
                      ]
                  ),
                ),
                Expanded(child:pages[_pageIdx])
              ],
            ),
          )
      ),
    );
  }
}