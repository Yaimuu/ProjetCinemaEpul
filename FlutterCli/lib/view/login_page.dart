import 'dart:developer' as developer;

import 'package:flutter/material.dart';
import 'package:flutter_cli/models/requests/login_request.dart';
import 'package:flutter_cli/service/auth_service.dart';
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

    final loginController = TextEditingController(text: "Merlot");
    final passwordController = TextEditingController(text: "secret");

    late LoginResponse _futureLoginResponse;

    @override
    void dispose() {
      // Clean up the controller when the widget is disposed.
      loginController.dispose();
      passwordController.dispose();
      super.dispose();
    }

    final logo = Hero(
      tag: 'hero',
      child: CircleAvatar(
        backgroundColor: Colors.transparent,
        radius: 48.0,
        child: Image.asset('assets/logo.png'),
      ),
    );

    final login = TextFormField(
      keyboardType: TextInputType.text,
      autofocus: false,
      controller: loginController,
      decoration: InputDecoration(
        hintText: 'Login',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final password = TextFormField(
      autofocus: false,
      obscureText: true,
      controller: passwordController,
      decoration: InputDecoration(
        hintText: 'Password',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final loginButton = Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          backgroundColor: Colors.lightBlueAccent,
          padding: const EdgeInsets.all(12),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(24),
          ),
        ),

        onPressed: () async {
          //Navigator.of(context).pushNamed(HomePage.tag);



          _futureLoginResponse = await AuthService().loginUser(LoginRequest(login: loginController.text, password: passwordController.text));

          if(_futureLoginResponse.jwtToken.isNotEmpty)
          {
            Navigator.of(context).pushNamedAndRemoveUntil('/', (Route<dynamic> route) => false);
          }

        },
        child: const Text('Log In', style: TextStyle(color: Colors.white)),
      ),
    );

    final forgotLabel = TextButton(
      child: const Text(
        'Forgot password?',
        style: TextStyle(color: Colors.black54),
      ),
      onPressed: () {},
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      drawer: NavDrawer(title:widget.title),
      body: Center(
        child: ListView(
          shrinkWrap: true,
          padding: const EdgeInsets.only(left: 24.0, right: 24.0),
          children: <Widget>[
            // logo,
            const SizedBox(height: 48.0),
            login,
            const SizedBox(height: 8.0),
            password,
            const SizedBox(height: 24.0),
            loginButton,
            forgotLabel
          ],
        ),
      ),
    );
  }

}