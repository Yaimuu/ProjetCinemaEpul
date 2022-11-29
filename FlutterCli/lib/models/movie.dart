import 'dart:ffi';

import 'package:flutter_cli/models/director.dart';
import 'package:flutter_cli/models/genre.dart';

class Movie {
  final String title;
  final Duration duration;
  final DateTime releaseDate;
  final Float budget;
  final Director director;
  final Genre genre;

  const Movie({
    required this.title,
    required this.duration,
    required this.releaseDate,
    required this.budget,
    required this.genre,
    required this.director,
  });

  factory Movie.fromJson(Map<String, dynamic> json) {
    return Movie(
      title: json['title'],
      duration: json['duration'],
      releaseDate: json['releaseDate'],
      budget: json['budget'],
      genre: json['genre'],
      director: json['director'],
    );
  }

  Map toJson() => {
    'title': title,
    'duration': duration,
    'releaseDate': releaseDate,
    'budget': budget,
    'genre': genre,
    'director': director,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}