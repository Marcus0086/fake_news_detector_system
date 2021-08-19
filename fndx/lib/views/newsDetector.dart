import 'package:flutter/material.dart';

class NewsDetector extends StatefulWidget {
  @override
  _NewsDetectorState createState() => _NewsDetectorState();
}

class _NewsDetectorState extends State<NewsDetector> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(
            "News Detector",
          ),
          backgroundColor: Colors.redAccent,
        ),
        body: Container(
          padding: EdgeInsets.all(5),
          color: Colors.redAccent,
        )
    );
  }
}
