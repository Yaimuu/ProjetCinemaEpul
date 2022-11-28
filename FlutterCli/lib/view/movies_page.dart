import 'package:flutter/material.dart';
import 'package:flutter_cli/view/widgets/menu.dart';

class MoviesPage extends StatefulWidget {
  final String title;

  const MoviesPage({super.key, required this.title});

  @override
  State<MoviesPage> createState() => _MoviesPageState();

}

class _MoviesPageState extends State<MoviesPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      drawer: NavDrawer(title:widget.title),

    );
  }

}

