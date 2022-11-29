class User {
  final String login;
  final String role;

  const User({
    required this.login,
    required this.role,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      login: json['sub'],
      role: json['role'],
    );
  }

  Map toJson() => {
    'login': login,
    'role': role,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}