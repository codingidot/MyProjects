
class Contents {
  final int? conid;
  final String? contitle;
  final int? conprice;
  final String? condate;
  final int? consuggest;
  final String? conadr;
  final String? contype;

  Contents
      (
      { this.conid,  this.contitle,  this.conprice,  this.condate,  this.consuggest,  this.conadr,  this.contype});

  factory Contents.fromJson(Map<String, dynamic>json){
    return Contents(
        conid: int.parse(json['conid']),
        contitle: json['contitle'],
        conprice: int.parse(json['conprice']),
        condate: json['condate'],
        consuggest: int.parse(json['consuggest']),
        conadr: json['conadr'],
        contype: json['contype']
    );
  }

  Map<String, dynamic> toJson() =>
      {
        'conid': conid,
        'contitle': contitle,
        'conprice': conprice,
        'condate': condate,
        'consuggest': consuggest,
        'conadr': conadr,
        'contype': contype
      };

}