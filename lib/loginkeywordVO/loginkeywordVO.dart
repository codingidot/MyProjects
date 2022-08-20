class LoginKeywordVO {
  final String memId;
  final String keyWord;
  final String keyDate;
  final String keyUniq;

  LoginKeywordVO({
    required this.memId,
    required this.keyWord,
    required this.keyDate,
    required this.keyUniq,
  });

  factory LoginKeywordVO.fromJson(Map<String, dynamic> json){
    return LoginKeywordVO(
      memId: (json["memid"]),
      keyWord: (json["keyword"]),
      keyDate: (json["keydate"]),
      keyUniq: (json["keyuniq"]),
    );
  }

  Map<String, dynamic> toJson() => {
    "memid" : memId,
    "keyword" : keyWord,
    "keydate" : keyDate,
    "keyuniq" : keyUniq,
  };
}