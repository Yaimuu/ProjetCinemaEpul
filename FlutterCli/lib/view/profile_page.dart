import 'package:flutter/material.dart';
import 'package:flutter_cli/service/auth_service.dart';
import 'package:flutter_cli/view/widgets/cinema_title.dart';
import 'package:flutter_cli/view/widgets/menu.dart';
import 'package:flutter_cli/view/widgets/forms/movie_form.dart';
import 'dart:developer' as developer;

import '../constants.dart';
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
              return Column(
                children: [
                  Container(
                    decoration: const BoxDecoration(
                      gradient: LinearGradient(
                        begin: Alignment.topRight,
                        end: Alignment.bottomLeft,
                        colors: [
                          Colors.blue,
                          Colors.yellow,
                          Colors.white,
                        ],
                      ),
                    ),
                    padding: const EdgeInsets.all(12),
                    child: const Center(
                      child: logo,
                    ),
                  ),

                  Center(
                    child: CinemaTitle(
                      title: "Profil de ${snapshot.data!.login}",
                    ),
                  ),
                  const Padding(
                    padding: EdgeInsets.all(12),
                    child: Center(
                      child: Text(
                        Constants.LOREM_IPSUM,
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

