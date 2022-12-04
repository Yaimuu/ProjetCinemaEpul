import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../models/genre.dart';
import 'cinema_title.dart';

class GenreCard extends StatelessWidget {

  final Genre genre;

  const GenreCard({super.key, required this.genre});


  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Flexible(
      child: Padding(
        padding: const EdgeInsets.all(50),
        child: Card(
          child: Container(
            height: 300,
            width: double.infinity,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(15.0),
                image: DecorationImage(
                  fit: BoxFit.cover,
                  image: Image.network(
                    genre.image,
                    fit: BoxFit.fill,
                  ).image,
                )),
            child: Padding(
              padding: const EdgeInsets.all(10.0),
              child: CinemaTitle(title: genre.label),
            ),
          ),
        ),
      ),
    );

  }

}

// Image.network(
// genre.image,
// fit: BoxFit.fill,
// ),