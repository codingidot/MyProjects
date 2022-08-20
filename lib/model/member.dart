class Member {
  final String? memid;
  final String? mempw;
  final String? memtype;

  Member({this.memid, this.mempw, this.memtype});

  factory Member.fromJson(Map<String,dynamic> json) {
    return Member(
      memid: json['memid'],
      mempw: json['mempw'],
      memtype: json['memtype']);
  }
  Map<String, dynamic> toJson() =>
      {'id': memid, 'pw':mempw, 'memtype':memtype};
}