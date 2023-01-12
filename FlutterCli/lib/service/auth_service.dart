import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class AuthService {
  final _storage = const FlutterSecureStorage();

  static final AuthService _instance = AuthService._internal();

  factory AuthService() {
    return _instance;
  }

  AuthService._internal();

  bool isAuthenticated() {
    bool authenticated = _storage.containsKey(key: "jwtToken") == true;
    return authenticated;
  }

  void logout() {
    _storage.deleteAll();
  }
}