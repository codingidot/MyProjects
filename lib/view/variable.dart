import 'package:get/get.dart';

class variable{
  String id;
  String logindate = "초초기값";
  variable({this.id="초기값"});
}


class Time {
  int second;
  int now;
  String day;
  String night;
  bool dayIsOn ;
  bool dayIsOff;
  bool nightIsOn;
  bool nightIsOff;
  int key;
  Time({this.second=5, this.now=0,this.day="on",this.night="off", this.dayIsOn=true, this.dayIsOff=false, this.nightIsOn=false, this.nightIsOff=true, this.key=0});






}
class Variable{
  final srcVO = SearchVO().obs;

  void updateInfo(String idx) {
    srcVO.update((val) {
      val?.searchIdx = idx;
    });
  }
}

class SearchVO{
  String searchIdx;
  String loginId;

  SearchVO({this.searchIdx = "", this.loginId = "test5"});
}