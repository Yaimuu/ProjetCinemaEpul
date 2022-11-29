class Genre {
  final int id;
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
      label: json['label'],
      image: json['image'],
    );
  }

  Map toJson() => {
    'id': id,
    'label': label,
    'image': image,
  };

  @override
  String toString() {
    // TODO: implement toString
    return toJson().toString();
  }
}