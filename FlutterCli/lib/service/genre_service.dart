import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import '../constants.dart';
import '../models/genre.dart';

import 'package:http/http.dart' as http;
import 'dart:developer' as developer;

import 'auth_service.dart';

class GenreService {
  final _storage = const FlutterSecureStorage();

  static final GenreService _instance = GenreService._internal();

  factory GenreService() {
    return _instance;
  }

  GenreService._internal();

  Future<List<Genre>> getGenres() async {
    developer.log("Get categories...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.get(
      Uri.parse('${Constants.BASE_URL}categories'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'Authorization': 'Bearer $token',
      },
    );

    // developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

    if (response.statusCode == 200) {
      developer.log("Get all categories response processing...");
      Iterable jsonResponse = jsonDecode(response.body);

      List<Genre> movies = List<Genre>.from( jsonResponse.map((model)=> Genre.fromJson(model)) );

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
}