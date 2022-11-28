import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class AuthService {
  final _storage = const FlutterSecureStorage();

  static final AuthService _instance = AuthService._internal();

  factory AuthService() {
    return _instance;
  }

  AuthService._internal();

  Future<bool> isAuthenticated() async {
    return await _storage.containsKey(key: "jwtToken");

  }

  void logout() {
    _storage.deleteAll();
  }
}