import 'package:flutter/material.dart';
import 'package:fndx/views/home.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  // This widget is the root of your application.
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ChatX',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: Colors.redAccent,
        scaffoldBackgroundColor: Colors.black,
        accentColor: Color(0xff2A75BC),
      ),
      home: Container(
        child: Center(
          child: Home(),
        ),
      ),
    );
  }
}
