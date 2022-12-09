class LoginRequest {
  final String login;
  final String password;

  const LoginRequest({
    required this.login,
    required this.password,
  });

  factory LoginRequest.fromJson(Map<String, dynamic> json) {
    return LoginRequest(
      login: json['login'],
      password: json['password'],
    );
  }

  Map toJson() => {
    'login': login,
    'password': password,
  };
}