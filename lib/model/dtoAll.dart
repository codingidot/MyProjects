class DTOAll {
  final String contitle;
  final String condate;
  final String conprice;
  final String consuggest;
  final String conadr;

  DTOAll({
    required this.contitle,
    required this.condate,
    required this.conprice,
    required this.consuggest,
    required this.conadr,
  });

  factory DTOAll.fromJson(Map<String, dynamic> json) {
    return DTOAll(
      contitle: json['contitle'],
      condate: json['condate'],
      conprice: json['conprice'],
      consuggest: json['consuggest'],
      conadr: json['conadr'],
    );
  }

  Map<String, dynamic> toJson() =>
      {
        'contitle': contitle,
        'condate': condate,
        'conprice': conprice,
        'consuggest': consuggest,
        'conadr': conadr,
      };
}