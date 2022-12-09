import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CinemaTextInput extends StatelessWidget {

  final TextEditingController controller;
  final String hintText;
  final TextInputType textType;

  const CinemaTextInput({super.key, required this.hintText, required this.controller, this.textType=TextInputType.text});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return TextFormField(
      keyboardType: textType,
      autofocus: false,
      controller: controller,
      decoration: InputDecoration(
        hintText: hintText,
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );
  }

}