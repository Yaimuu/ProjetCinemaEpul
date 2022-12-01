import 'package:flutter/cupertino.dart';

class CinemaTitle extends StatelessWidget {

  final int level;
  final String title;
  final double initialFontSize = 40.0;

  const CinemaTitle({super.key, this.level=1, required this.title});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 5, horizontal: 5),
      child: Text(
          title,
          style: TextStyle(
            color: const Color.fromARGB(255, 128, 128, 128),
            fontSize: initialFontSize / level,
            fontWeight: FontWeight.w500,
          )
      ),
    );
  }

}