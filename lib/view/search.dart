import 'package:flutter/material.dart';

class Search extends StatefulWidget {

  @override
  State<Search> createState() => _SearchState();
}

class _SearchState extends State<Search> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          Container(
            color: Colors.deepOrange,
            height: 50,
            padding: EdgeInsets.fromLTRB(8, 8, 8, 8),
            child: Container(
              color: Colors.white,
              child: TextField(
                style: TextStyle(fontSize: 14),

                decoration:
                InputDecoration(
                  border: InputBorder.none,
                  focusedBorder: InputBorder.none,
                  hoverColor: Colors.red,
                  suffixIcon: Icon(
                    Icons.search,
                    color: Colors.deepOrange,

                  ), //검색 아이콘 추가
                  contentPadding: EdgeInsets.only(left: 10, bottom: 0, top: 0, right: 0),
                  hintText: '상품을 입력하세요',
                ),
              ),
            )
          ),
          SizedBox(
            height: 10,
          ),
        ]
      ),
    );
  }
}