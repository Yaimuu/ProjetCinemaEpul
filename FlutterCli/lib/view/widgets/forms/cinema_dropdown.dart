import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CinemaDropdown extends StatelessWidget {

  final String value;
  final List<String> items;
  final Function onChanged;


  const CinemaDropdown({super.key, required this.value, required this.items, required this.onChanged,});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return DropdownButton<String>(
      value: value,
      elevation: 16,
      underline: Container(
        height: 2,
        color: Colors.deepPurpleAccent,
      ),
      onChanged: (String? value) {
        // This is called when the user selects an item.
        onChanged.call(value);
      },
      items: items.map<DropdownMenuItem<String>>((String value) {
        return DropdownMenuItem<String>(
          value: value,
          child: Text(value),
        );
      }).toList(),

    );
  }

}