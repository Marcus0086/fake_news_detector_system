import 'package:flutter/material.dart';
import 'package:fndx/views/allNews.dart';
import 'package:fndx/views/newsDetector.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int _selectedIndex = 0;

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  static List<Widget> _pages = [AllNews(), NewsDetector()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: _pages.elementAt(_selectedIndex), //New
      ),
      bottomNavigationBar: BottomNavigationBar(
        iconSize: 25,
        showUnselectedLabels: false,
        unselectedIconTheme: IconThemeData(
          color: Colors.white,
        ),
        unselectedItemColor: Colors.white,
        selectedIconTheme: IconThemeData(color: Colors.redAccent, size: 30),
        selectedItemColor: Colors.redAccent,
        selectedLabelStyle: TextStyle(fontWeight: FontWeight.bold),
        backgroundColor: Colors.black,
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.apps),
            label: 'News',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.fact_check_outlined),
            label: 'Detector',
          ),
        ],
      ),
    );
  }
}
