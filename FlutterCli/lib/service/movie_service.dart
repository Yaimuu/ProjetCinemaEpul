import 'dart:convert';

import 'package:flutter_cli/models/requests/movie_request.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import 'dart:developer' as developer;

import 'package:flutter_cli/models/movie.dart';

import '../constants.dart';
import 'auth_service.dart';

class MovieService {
  final _storage = const FlutterSecureStorage();

  static final MovieService _instance = MovieService._internal();

  factory MovieService() {
    return _instance;
  }

  MovieService._internal();

  String durationToString(int minutes) {
    var d = Duration(minutes:minutes);
    List<String> parts = d.toString().split(':');
    return '${parts[0].padLeft(2, '0')}:${parts[1].padLeft(2, '0')}';
  }

  Duration parseDuration(String s) {
    int hours = 0;
    int minutes = 0;
    int micros;
    List<String> parts = s.split(':');
    if (parts.length > 2) {
      hours = int.parse(parts[parts.length - 3]);
    }
    if (parts.length > 1) {
      minutes = int.parse(parts[parts.length - 2]);
    }
    micros = (double.parse(parts[parts.length - 1]) * 1000000).round();
    return Duration(hours: hours, minutes: minutes, microseconds: micros);
  }

  Future<List<Movie>> getMovies() async {
    developer.log("Get movies...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.get(
      Uri.parse('${Constants.BASE_URL}films'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'Authorization': 'Bearer $token',
      },
    );

    // developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

    if (response.statusCode == 200) {
      developer.log("Get all movies response processing...");
      Iterable jsonResponse = jsonDecode(utf8.decode(response.bodyBytes));

      List<Movie> movies = List<Movie>.from( jsonResponse.map((model)=> Movie.fromJson(model)) );

      return Future.value(movies);
    } else if(response.statusCode == 401) {
      AuthService().refreshToken();
      throw Exception('Failed to connect. Refresh connection...');
    }
    else
    {
      throw Exception('Failed to connect.');
    }
  }

  Future<Movie> getMovie(int id) async {
    developer.log("Get movie $id...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.get(
      Uri.parse('${Constants.BASE_URL}films/$id'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=utf-8',
        'Authorization': 'Bearer $token',
      },
    );

    // developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

    if (response.statusCode == 200) {
      developer.log("Get movie $id response processing...");
      Movie movieResponse = Movie.fromJson(jsonDecode(utf8.decode(response.bodyBytes)));

      return Future.value(movieResponse);
    } else {
      throw Exception('Failed to connect.');
    }
  }

  Future<int> addMovie(MovieRequest movieRequest) async {
    developer.log("Add movie ...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.post(
      Uri.parse('${Constants.BASE_URL}films/create'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=utf-8',
        'Authorization': 'Bearer $token',
      },
      body: jsonEncode(movieRequest.toJson()),
    );
    developer.log(jsonEncode(movieRequest.toJson()));
    developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

    return Future.value(response.statusCode);
  }

  Future<int> updateMovie(MovieRequest movieRequest) async {
    developer.log("Update movie ...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);
    // MovieRequest movieRequest = MovieRequest.fromMovie(movie);

    final response = await http.post(
      Uri.parse('${Constants.BASE_URL}films/update/${movieRequest.id}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=utf-8',
        'Authorization': 'Bearer $token',
      },
      body: jsonEncode(movieRequest.toJson()),
    );

    developer.log(jsonEncode(movieRequest.toJson()));
    developer.log(response.statusCode.toString());

    return Future.value(response.statusCode);
  }

  Future<int> deleteMovie(int id) async {
    developer.log("Delete movie $id...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.delete(
      Uri.parse('${Constants.BASE_URL}films/$id'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'Authorization': 'Bearer $token',
      },
    );

    return Future.value(response.statusCode);
  }
}