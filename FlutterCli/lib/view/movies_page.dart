import 'dart:developer' as developer;

import 'package:flutter/material.dart';
import 'package:flutter_cli/service/movie_service.dart';
import 'package:flutter_cli/view/widgets/menu.dart';
import 'package:flutter_cli/view/widgets/movie_card.dart';

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

  void _showAddMovieForm() {
    setState(() {


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
      body: FutureBuilder<List<Movie>>(
        future: MovieService().getMovies(),
        builder: (BuildContext context, AsyncSnapshot<List<Movie>> snapshot) {
          developer.log("Has data : ${snapshot.hasData.toString()} - Connection state : ${snapshot.connectionState}");

          if(snapshot.hasData && snapshot.connectionState == ConnectionState.done)
            {
              // developer.log(snapshot.data.toString());
              if(movies.isEmpty || snapshot.data?.length != movies.length)
                {
                  snapshot.data?.forEach((element) {
                    movies.add(element);
                  });
                }
              if(movieCards.length != movies.length)
                {
                  movieCards.clear();
                  for (var element in movies) {
                    movieCards.add(MovieCard(movie: element));
                  }
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
      floatingActionButton: FloatingActionButton(
        onPressed: _showAddMovieForm,
        tooltip: 'Add movie',
        child: const Icon(Icons.add),
      ),
    );
  }

}

