import 'dart:convert';

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
      Iterable jsonResponse = jsonDecode(response.body);

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
      Movie movieResponse = Movie.fromJson(jsonDecode(response.body));

      return Future.value(movieResponse);
    } else {
      throw Exception('Failed to connect.');
    }
  }

  Future<int> addMovie(Movie movie) async {
    developer.log("Add movie ...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.post(
      Uri.parse('${Constants.BASE_URL}films/create'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=utf-8',
        'Authorization': 'Bearer $token',
      },
      body: movie.toJson(),
    );

    // developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

    return Future.value(response.statusCode);
  }

  Future<int> updateMovie(Movie movie) async {
    developer.log("Update movie ...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.post(
      Uri.parse('${Constants.BASE_URL}films/update/${movie.id}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=utf-8',
        'Authorization': 'Bearer $token',
      },
      body: movie.toJson(),
    );

    // developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

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