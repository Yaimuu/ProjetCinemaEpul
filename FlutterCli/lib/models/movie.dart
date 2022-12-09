import 'dart:ffi';

import 'package:flutter_cli/models/director.dart';
import 'package:flutter_cli/models/genre.dart';

class Movie {
  final int id;
  final String title;
  final int duration;
  final DateTime releaseDate;
  final int budget;
  final int income;
  final Genre genre;
  final Director director;

  const Movie({
    required this.id,
    required this.title,
    required this.duration,
    required this.releaseDate,
    required this.budget,
    required this.income,
    required this.genre,
    required this.director,
  });

  factory Movie.fromJson(Map<String, dynamic> json) {
    return Movie(
      id: json['id'],
      title: json['titre'],
      duration: json['duree'],
      releaseDate: DateTime.parse(json['dateSortie']),
      budget: json['budget'],
      income: json['montantRecette'],
      director: Director.fromJson(json['noRea']),
      genre: Genre.fromJson(json['codeCat']),

    );
  }

  Map toJson() => {
    'id': id,
    'titre': title,
    'duree': duration,
    'dateSortie': releaseDate,
    'budget': budget,
    'montantRecette': income,
    'codeCat': genre,
    'noRea': director,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}