import 'package:flutter/material.dart';

class AllNews extends StatefulWidget {
  @override
  _AllNewsState createState() => _AllNewsState();
}

class _AllNewsState extends State<AllNews> {
  TextEditingController searchController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(
            "All News",
          ),
          backgroundColor: Color(0xff2A75BC),
          elevation: 1,
        ),
        body: Container(
            padding: EdgeInsets.all(5),
            color: Color(0xff2A75BC),
          child: Column(
            children: [
              Container(
                  padding: EdgeInsets.symmetric(horizontal: 14),
                  decoration: BoxDecoration(
                      borderRadius:
                      BorderRadius.all(Radius.circular(8.0)),
                      color: Colors.white),
                  child: TextField(
                    controller: searchController,
                    style: const TextStyle(
                        fontFamily: 'WorkSansSemiBold',
                        fontSize: 18.0,
                        color: Colors.black),
                    decoration: const InputDecoration(
                      border: InputBorder.none,
                      icon: Icon(
                        Icons.search,
                        color: Colors.black,
                        size: 22.0,
                      ),
                      hintText: 'Search',
                      hintStyle: TextStyle(
                          fontFamily: 'WorkSansSemiBold',
                          fontSize: 18.0,
                          color: Colors.grey),
                    ),
                  )),
            ],
          ),
        )
    );
  }
}
