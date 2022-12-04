import 'package:flutter/material.dart';
import 'package:flutter_cli/service/auth_service.dart';
import 'package:flutter_cli/view/widgets/menu.dart';
import 'package:flutter_cli/view/widgets/movie_form.dart';
import 'dart:developer' as developer;

import '../models/user.dart';

class ProfilePage extends StatefulWidget {
  final String title;

  const ProfilePage({super.key, required this.title});

  @override
  State<ProfilePage> createState() => _ProfilePageState();

}

class _ProfilePageState extends State<ProfilePage> {

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      drawer: NavDrawer(title:widget.title),
      body: RefreshIndicator(
        onRefresh: () async {
          setState(() {

          });
        },
        child: FutureBuilder(
          future: AuthService().getAuthenticatedUser(),
          builder: (context, AsyncSnapshot<User> snapshot) {
            if(snapshot.hasData)
            {
              developer.log(snapshot.data.toString());
              return ListView(
                children: [
                  Center(
                    child: Text(
                      "Bienvenue ${snapshot.data!.login}",
                      style: const TextStyle(
                        fontSize: 30,
                      ),
                    ),
                  ),
                  // MovieForm(null),
                ],
              );
            }
            return const Text("Not authenticated");
          },
        ),
      ),

    );
  }

}

