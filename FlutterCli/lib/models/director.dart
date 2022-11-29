class Director {
  final int id;
  final String firstName;
  final String lastName;

  const Director({
    required this.id,
    required this.firstName,
    required this.lastName,
  });

  factory Director.fromJson(Map<String, dynamic> json) {
    return Director(
      id: json['id'],
      firstName: json['firstName'],
      lastName: json['lastName'],
    );
  }

  Map toJson() => {
    'id': id,
    'firstName': firstName,
    'lastName': lastName,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}