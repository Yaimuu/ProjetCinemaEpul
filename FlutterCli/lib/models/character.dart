class Character {
  final int numActor;
  final int numFilm;
  final String name;

  const Character({
    required this.numActor,
    required this.numFilm,
    required this.name,
  });

  factory Character.fromJson(Map<String, dynamic> json) {
    return Character(
      numActor: json['numActor'],
      numFilm: json['numFilm'],
      name: json['name'],
    );
  }

  Map toJson() => {
    'numActor': numActor,
    'numFilm': numFilm,
    'name': name,
  };

  @override
  String toString() {
    return toJson().toString();
  }
}