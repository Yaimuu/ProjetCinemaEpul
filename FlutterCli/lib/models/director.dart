class Director {
  final int id;
  final String firstName;
  final String lastName;

  const Director({
    required this.id,
    required this.firstName,
    required this.lastName,
  });

  String getFullname() {
    return "$firstName $lastName";
  }

  factory Director.fromJson(Map<String, dynamic> json) {
    return Director(
      id: json['id'],
      firstName: json['prenRea'],
      lastName: json['nomRea'],
    );
  }

  Map toJson() => {
    'id': id,
    'prenRea': firstName,
    'nomRea': lastName,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}