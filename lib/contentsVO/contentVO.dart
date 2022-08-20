class ContentsVO {
  final int conId;
  final String conTitle;
  final int conPrice;
  final String conDate;
  final int conSuggest;
  final String conAdr;
  final String conType;

  ContentsVO({
    required this.conId,
    required this.conTitle,
    required this.conPrice,
    required this.conDate,
    required this.conSuggest,
    required this.conAdr,
    required this.conType
  });

  factory ContentsVO.fromJson(Map<String, dynamic> json){
    return ContentsVO(
      conId: int.parse(json["conid"]),
      conTitle: (json["contitle"]),
      conPrice: int.parse(json["conprice"]),
      conDate: (json["condate"]),
      conSuggest: int.parse(json["consuggest"]),
      conAdr: (json["conadr"]),
      conType: (json["contype"]),
    );
  }

    Map<String, dynamic> toJson() => {
      "conId" : conId,
      "conTitle" : conTitle,
      "conPrice" : conPrice,
      "conDate" : conDate,
      "conSuggest" : conSuggest,
      "conAdr" : conAdr,
      "conType" : conType,
    };
}
