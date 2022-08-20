class DTOKeyword {
  final String memid;
  final String keyword;

  DTOKeyword({
    required this.memid,
    required this.keyword,
  });

  factory DTOKeyword.fromJson(Map<String, dynamic> json) {
    return DTOKeyword(
        memid: json['memid'],
      keyword: json['keyword'],
    );
  }

  Map<String, dynamic> toJson() =>
      {'memid': memid, 'key': keyword,};
}