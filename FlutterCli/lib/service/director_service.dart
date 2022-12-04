import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class DirectorService {
  final _storage = const FlutterSecureStorage();

  static final DirectorService _instance = DirectorService._internal();

  factory DirectorService() {
    return _instance;
  }

  DirectorService._internal();


}