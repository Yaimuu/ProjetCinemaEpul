class LoginResponse {
  final String jwtToken;
  final String refreshToken;

  const LoginResponse({
    required this.jwtToken,
    required this.refreshToken,
  });

  factory LoginResponse.fromJson(Map<String, dynamic> json) {
    return LoginResponse(
      jwtToken: json['accessToken'],
      refreshToken: json['refreshToken'],
    );
  }
}