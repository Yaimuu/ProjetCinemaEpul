import 'dart:developer' as developer;

import 'package:flutter/material.dart';
import 'package:flutter_cli/service/movie_service.dart';
import 'package:flutter_cli/view/widgets/menu.dart';
import 'package:flutter_cli/view/widgets/movie_card.dart';
import 'package:flutter_cli/view/widgets/forms/movie_form.dart';
import 'package:rflutter_alert/rflutter_alert.dart';

import '../models/movie.dart';



class MoviesPage extends StatefulWidget {
  final String title;

  const MoviesPage({super.key, required this.title});

  @override
  State<MoviesPage> createState() => _MoviesPageState();
}

class _MoviesPageState extends State<MoviesPage> {

  List<Movie> movies = [];

  late List<MovieCard> movieCards = [];

  void _showAddMovieForm(Movie? movie) {
    setState(() {
      Widget movieForm = MovieForm(movie, () async {setState(() { });} );
      String alertTitle = "Add Movie";
      if(movie != null)
      {
        alertTitle = "Update ${movie.title}";
      }
      Alert(
        context: context,
        title: alertTitle,
        content: Column(
          children: <Widget>[
            movieForm,
          ],
        ),
      ).show();

    });
  }

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
            setState(() { });
          },
          child: FutureBuilder<List<Movie>>(
            future: MovieService().getMovies(),
            builder: (BuildContext context, AsyncSnapshot<List<Movie>> snapshot) {
              developer.log("Has data : ${snapshot.hasData.toString()} - Connection state : ${snapshot.connectionState}");

              if(snapshot.hasData && snapshot.connectionState == ConnectionState.done)
              {
                // developer.log(snapshot.data.toString());
                movies.clear();
                snapshot.data?.forEach((element) {
                  movies.add(element);
                });

                movieCards.clear();
                for (var element in movies) {
                  movieCards.add(MovieCard(movie: element, notifyParent: () async {setState(() { });} ));
                }

                return ListView(
                  children: movieCards,
                );
              }
              else {
                return const Center(child: CircularProgressIndicator(),);
              }

            },

          ),
      ),

      floatingActionButton: FloatingActionButton(
        onPressed: () => _showAddMovieForm(null),
        tooltip: 'Add movie',
        child: const Icon(Icons.add),
      ),

    );
  }

}

