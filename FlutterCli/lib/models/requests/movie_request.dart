import 'dart:ffi';

import 'package:flutter_cli/models/director.dart';
import 'package:flutter_cli/models/genre.dart';
import 'package:flutter_cli/models/movie.dart';

class MovieRequest {
  final String title;
  final int duration;
  final DateTime releaseDate;
  final int budget;
  final int income;
  final String idGenre;
  final int idDirector;

  const MovieRequest({
    required this.title,
    required this.duration,
    required this.releaseDate,
    required this.budget,
    required this.income,
    required this.idGenre,
    required this.idDirector,
  });

  factory MovieRequest.fromJson(Map<String, dynamic> json) {
    return MovieRequest(
      title: json['titre'],
      duration: json['duree'],
      releaseDate: DateTime.parse(json['dateSortie']),
      budget: json['budget'],
      income: json['montantRecette'],
      idDirector: json['noRea'],
      idGenre: json['codeCat'],

    );
  }

  factory MovieRequest.fromMovie(Movie movie) {
    return MovieRequest(
      title: movie.title,
      duration: movie.duration,
      releaseDate: movie.releaseDate,
      budget: movie.budget,
      income: movie.income,
      idDirector: movie.director.id,
      idGenre: movie.genre.id,

    );
  }

  Map toJson() => {
    'titre': title,
    'duree': duration,
    'dateSortie': releaseDate,
    'budget': budget,
    'montantRecette': income,
    'codeCat': idGenre,
    'noRea': idDirector,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}