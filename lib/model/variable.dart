import 'package:get/get.dart';

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