import 'dart:convert';

import 'package:flutter_cli/models/director.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import 'package:http/http.dart' as http;
import 'dart:developer' as developer;

import '../constants.dart';
import 'auth_service.dart';

class DirectorService {
  final _storage = const FlutterSecureStorage();

  static final DirectorService _instance = DirectorService._internal();

  factory DirectorService() {
    return _instance;
  }

  DirectorService._internal();

  Future<List<Director>> getDirectors() async {
    developer.log("Get categories...");

    String? token = await _storage.read(key: 'refreshToken');
    // developer.log(token!);

    final response = await http.get(
      Uri.parse('${Constants.BASE_URL}realisateurs'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'Authorization': 'Bearer $token',
      },
    );

    // developer.log(response.statusCode.toString());
    // developer.log((response.statusCode == 200).toString());

    if (response.statusCode == 200) {
      developer.log("Get all directors response processing...");
      Iterable jsonResponse = jsonDecode(utf8.decode(response.bodyBytes));

      List<Director> movies = List<Director>.from( jsonResponse.map((model)=> Director.fromJson(model)) );

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