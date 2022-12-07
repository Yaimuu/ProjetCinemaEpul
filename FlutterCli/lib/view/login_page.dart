import 'dart:developer' as developer;

import 'package:flutter/material.dart';
import 'package:flutter_cli/models/requests/login_request.dart';
import 'package:flutter_cli/service/auth_service.dart';
import 'package:flutter_cli/view/widgets/forms/login_form.dart';
import 'package:flutter_cli/view/widgets/menu.dart';
import '../models/requests/login_response.dart';

class LoginPage extends StatefulWidget {
  final String title;

  const LoginPage({super.key, required this.title});

  @override
  State<LoginPage> createState() => _LoginPageState();

}

class _LoginPageState extends State<LoginPage> {


  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        drawer: NavDrawer(title:widget.title),

        body: const LoginForm(),
    );


  }

}