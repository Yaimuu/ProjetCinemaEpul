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
    return Padding(
      padding: const EdgeInsets.all(20),
      child: Card(
        child: InkWell(
          onTap: () {
            // Function is executed on tap.
          },
          child: Container(
            height: 100,
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
              child: Center(
                child: Wrap(
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15.0),
                        color: Colors.black38,
                      ),
                      child: Center(child: CinemaTitle(title: genre.label, color: Colors.white,)),
                    ),
                  ],
                ),
              ),
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