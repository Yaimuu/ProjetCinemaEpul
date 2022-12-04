import 'package:flutter/material.dart';
import 'package:flutter_cli/view/widgets/genre_card.dart';
import 'package:flutter_cli/view/widgets/login_form.dart';
import 'package:flutter_cli/view/widgets/menu.dart';

import '../models/genre.dart';
import '../service/auth_service.dart';
import '../service/genre_service.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  @override
  Widget build(BuildContext context) {

    return FutureBuilder<bool>(
      future: AuthService().isAuthenticated(),
      builder: (context, snapshot) {

        if(snapshot.hasData)
        {
          if(snapshot.data!)
            {
              return FutureBuilder<List<Genre>>(
                future: GenreService().getGenres(),
                builder: (context, snapshot) {


                  List<GenreCard> genreCards = [];

                  if(snapshot.hasData && snapshot.connectionState == ConnectionState.done)
                  {
                    List<Genre>? genres = snapshot.data;
                    for (var element in genres!) {
                      genreCards.add(GenreCard(genre: element));
                    }
                  }

                  return Scaffold(
                    appBar: AppBar(
                      title: Text(widget.title),
                    ),
                    drawer: NavDrawer(title: widget.title),
                    body: Center(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: genreCards,
                      ),
                    ),
                  );

                },
              );
            }
        }

        return Scaffold(
          appBar: AppBar(
            title: Text(widget.title),
          ),
          drawer: NavDrawer(title: widget.title),
          body: const Center(
            child: LoginForm(),
          ),
        );
      },
    );





  }
}