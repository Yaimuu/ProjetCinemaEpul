import 'dart:convert';

import 'package:http/http.dart' as http;
import 'dart:developer' as developer;

import 'package:flutter_cli/models/user.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

import '../constants.dart';
import '../models/requests/login_request.dart';
import '../models/requests/login_response.dart';

class AuthService {
  final _storage = const FlutterSecureStorage();

  static final AuthService _instance = AuthService._internal();

  factory AuthService() {
    return _instance;
  }

  AuthService._internal();

  Future<bool> isAuthenticated() async {
    return _storage.containsKey(key: "jwtToken");
  }

  Future<String?> getRefreshToken() async {
    String? token = "";
    token = await _storage.read(key: "refreshToken");

    return token;
  }
  
  Future<String?> getToken() async {
    String? token = "";
    token = await _storage.read(key: "jwtToken");

    return token;
  }

  Future<User> getAuthenticatedUser() async {
    final token = await getToken();
    // developer.log(token!);
    Map<String, dynamic> decodedToken = JwtDecoder.decode(token!);
    developer.log(decodedToken.toString());

    developer.log(DateTime.fromMicrosecondsSinceEpoch(decodedToken["exp"]).toString());
    User user = User.fromJson(decodedToken);
    // developer.log(user.role);

    return Future.value(user);
  }

  void refreshToken() async {
    var refresh_token = await getRefreshToken();

    final response = await http.post(Uri.https(Constants.BASE_URL, '/auth/refreshtoken'),
        headers: {'grant_type': 'refresh_token', 'refresh_token': '$refresh_token'});

    // developer.log(response.body);

    var token = jsonDecode(response.body)['jwtToken'];
    // refresh_token = jsonDecode(response.body)['refreshToken'];

    await _storage.write(key: 'jwtToken', value: token);
  }

  void logout() {
    _storage.deleteAll();
  }

  Future<LoginResponse> loginUser(LoginRequest loginRequest) async {
    developer.log(loginRequest.toJson().toString());
    developer.log('${Constants.BASE_URL}auth/login');

    final response = await http.post(
      Uri.parse('${Constants.BASE_URL}auth/login'),
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: json.encode(loginRequest.toJson()),
    );

    developer.log(response.statusCode.toString());
    developer.log(response.body.toString());

    if (response.statusCode == 200) {
      LoginResponse jsonResponse = LoginResponse.fromJson(jsonDecode(response.body));

      await _storage.write(key: 'jwtToken', value: jsonResponse.jwtToken);
      await _storage.write(key: 'refreshToken', value: jsonResponse.refreshToken);

      // If the server did return a 201 CREATED response,
      // then parse the JSON.
      return jsonResponse;
    } else {
      // If the server did not return a 201 CREATED response,
      // then throw an exception.
      throw Exception('Failed to connect.');
    }
  }
}