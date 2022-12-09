import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:flutter_cli/service/movie_service.dart';
import 'package:flutter_cli/view/widgets/genre_card.dart';
import 'package:flutter_cli/view/widgets/forms/login_form.dart';
import 'package:flutter_cli/view/widgets/menu.dart';
import 'package:flutter_cli/view/widgets/movie_card.dart';

import '../models/genre.dart';
import '../models/movie.dart';
import '../models/user.dart';
import '../service/auth_service.dart';
import '../service/genre_service.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key, required this.title});

  final String title;

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  Future<List<dynamic>> getGenresAndMovies() async {
    return Future.value([
      await GenreService().getGenres(),
      await MovieService().getMovies()
    ]);
  }

  @override
  Widget build(BuildContext context) {

    return FutureBuilder<User>(
      future: AuthService().getAuthenticatedUser(),
      builder: (context, snapshot) {
        User authenticatedUser;
        if(snapshot.hasData)
        {
          if(snapshot.data!.role.contains("admin"))
            {
              authenticatedUser = snapshot.data!;
              return FutureBuilder<List<dynamic>>(
                future: getGenresAndMovies(),
                builder: (context, snapshot) {

                  List<GenreCard> genreCards = [];
                  List<MovieCard> currentMovieCards = [];

                  List<Genre> genres = [];
                  List<Movie> currentMovies = [];

                  if(snapshot.hasData && snapshot.connectionState == ConnectionState.done)
                  {
                    genreCards.clear();

                    genres = snapshot.data![0];
                    currentMovies = snapshot.data![1];

                    for (var element in genres) {
                      genreCards.add(GenreCard(genre: element));
                    }

                    currentMovieCards.clear();
                    for (var movie in currentMovies) {
                      if(movie.genre.id == genres[0].id) {
                        // print(movie);
                        currentMovieCards.add(MovieCard(movie: movie, notifyParent: () async {setState(() { });}, authenticatedUser: authenticatedUser,));
                      }
                    }
                  }

                  Widget updateContent() {
                    return ListView.builder(
                      itemCount: currentMovieCards.length,
                      itemBuilder: (BuildContext context, int index) => currentMovieCards[index],
                    );
                  }

                  ValueNotifier contentNotifier = ValueNotifier<Widget>(updateContent());

                  final contentListener = ValueListenableBuilder(
                      valueListenable: contentNotifier,
                      builder: (ctx, value, child) {
                        return value;
                      }
                  );

                  final genreCarousel = CarouselSlider(
                    options: CarouselOptions(
                      height: 200.0,
                      enlargeCenterPage: false,
                      onPageChanged: (position,reason) {
                        // print(reason);
                        // print(CarouselPageChangedReason.controller);
                        print(position);
                        // print(currentMovies);
                        // setState(() {
                        //
                        // });
                        currentMovieCards.clear();
                        for (var movie in currentMovies) {
                          if(movie.genre.id == genres[position].id) {
                            // print(movie);
                            currentMovieCards.add(
                                MovieCard(
                                    movie: movie,
                                    notifyParent: () async {
                                      contentNotifier.value = updateContent();
                                      setState(() {

                                      });
                                    },
                                    authenticatedUser: authenticatedUser,
                                )
                            );
                          }
                        }

                        contentNotifier.value = updateContent();
                      },
                      enableInfiniteScroll: false,
                    ),
                    items: genreCards,
                  );

                  return Scaffold(
                    appBar: AppBar(
                      title: Text(widget.title),
                    ),
                    drawer: NavDrawer(title: widget.title),
                    body: Column(
                      children: [
                        genreCarousel,
                        Expanded(
                          child: contentListener,
                        ),
                      ],
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