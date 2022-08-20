import 'package:flutter/material.dart';

class Header extends StatefulWidget {
  const Header({Key? key}) : super(key: key);

  @override
  State<Header> createState() => _HeaderState();
}

class _HeaderState extends State<Header> {
  @override
  Widget build(BuildContext context) {
    return Container(

      child: Column(
          children: <Widget>[
            TextField(
              decoration: InputDecoration(
                labelText: '상품을 입력하세요',
              ),
            ),
            Container(
              child: Text('홈페이지'),
            )
          ]
      ),
    );
  }
}
