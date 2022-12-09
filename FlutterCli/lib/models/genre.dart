class Genre {
  final String id;
  final String label;
  final String image;

  const Genre({
    required this.id,
    required this.label,
    required this.image,
  });

  factory Genre.fromJson(Map<String, dynamic> json) {
    return Genre(
      id: json['id'],
      label: json['libelleCat'],
      image: json['image'],
    );
  }

  Map toJson() => {
    'id': id,
    'libelleCat': label,
    'image': image,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}