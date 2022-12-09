class Actor {
  final int id;
  final String firstName;
  final String lastName;
  final DateTime bornDate;
  final DateTime deathDate;

  const Actor({
    required this.id,
    required this.firstName,
    required this.lastName,
    required this.bornDate,
    required this.deathDate,
  });

  factory Actor.fromJson(Map<String, dynamic> json) {
    return Actor(
      id: json['id'],
      firstName: json['firstName'],
      lastName: json['lastName'],
      bornDate: json['bornDate'],
      deathDate: json['deathDate'],
    );
  }

  Map toJson() => {
    'id': id,
    'firstName': firstName,
    'lastName': lastName,
    'bornDate': bornDate,
    'deathDate': deathDate,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}