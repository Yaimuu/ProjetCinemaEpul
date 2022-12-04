import 'package:flutter/material.dart';

import '../../models/requests/login_request.dart';
import '../../models/requests/login_response.dart';
import '../../service/auth_service.dart';
import 'menu.dart';

class LoginForm extends StatelessWidget {

  const LoginForm({super.key,});

  @override
  Widget build(BuildContext context) {
    final loginController = TextEditingController(text: "Merlot");
    final passwordController = TextEditingController(text: "secret");

    late LoginResponse _futureLoginResponse;

    // @override
    // void dispose() {
    //   // Clean up the controller when the widget is disposed.
    //   loginController.dispose();
    //   passwordController.dispose();
    //   super.dispose();
    // }

    const logo = Hero(
      tag: 'PolyMovie',
      child: CircleAvatar(
        backgroundColor: Colors.red,

        radius: 100,
        backgroundImage: AssetImage(
          'lib/assets/images/logo.jpg',
        ),
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

    return Container(
      decoration: const BoxDecoration(
        // gradient: LinearGradient(
        //   begin: Alignment.topRight,
        //   end: Alignment.bottomLeft,
        //   colors: [
        //     Colors.blue,
        //     Colors.black38,
        //   ],
        // ),
      ),
      child: ListView(
        // shrinkWrap: true,
        padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 70),
        children: <Widget>[
          logo,
          const SizedBox(height: 48.0),
          login,
          const SizedBox(height: 8.0),
          password,
          const SizedBox(height: 24.0),
          loginButton,
          forgotLabel
        ],
      ),
    );
  }

}