import 'package:expandable/expandable.dart';
import 'package:flutter/material.dart';
import 'package:flutter_cli/models/user.dart';
import 'package:flutter_cli/service/auth_service.dart';
import 'package:flutter_cli/service/movie_service.dart';
import 'package:flutter_cli/view/widgets/cinema_title.dart';
import 'package:intl/intl.dart';
import 'package:rflutter_alert/rflutter_alert.dart';

import 'dart:developer' as developer;

import '../../models/movie.dart';
import 'forms/movie_form.dart';

class MovieCard extends StatelessWidget {
  final Movie movie;
  final Function? notifyParent;

  const MovieCard({super.key, required this.movie, this.notifyParent});

  @override
  Widget build(BuildContext context) {

    final DateFormat dateFormat = DateFormat("EEEE dd MMMM yyyy");

    final List<Widget> adminButtons = [
      Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: ElevatedButton(
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.lightBlueAccent,
            padding: const EdgeInsets.all(3),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(24),
            ),
          ),

          onPressed: () async {
            String alertTitle = "Add Movie";
            if(movie != null)
            {
              alertTitle = "Update ${movie.title}";
            }

            Widget movieForm = MovieForm(movie, notifyParent);

            // alert.content = Column(
            //   children: <Widget>[
            //     movieForm,
            //   ],
            // );
            Alert alert = Alert(
              context: context,
              title: alertTitle,
              content: Column(
                children: <Widget>[
                  movieForm,
                ],
              ),
            );

            // Function callBack = () async {
            //   alert.dismiss();
            //   notifyParent!.call();
            // };

            alert.show();

          },
          child: const Icon(Icons.edit),
        ),
      ),
      Padding(
        padding: const EdgeInsets.symmetric(vertical: 5, horizontal: 5),
        child: ElevatedButton(
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.redAccent,
            padding: const EdgeInsets.all(3),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(24),
            ),
          ),

          onPressed: () async {
            //Navigator.of(context).pushNamed(HomePage.tag);
            MovieService().deleteMovie(movie.id);
            notifyParent?.call();
          },
          child: const Icon(Icons.delete),
        ),
      ),
    ];

    return FutureBuilder<User>(
      future: AuthService().getAuthenticatedUser(),
      builder: (context, AsyncSnapshot<User> snapshot) {
        bool isAdmin = false;
        if(snapshot.hasData)
          {
            isAdmin = snapshot.data!.role.contains("admin");
          }

        final List<Widget> collapsedContent = [];

        final List<Widget> expandedContent = collapsedContent + [
          Text("Released date : ${dateFormat.format(movie.releaseDate)}"),
          Text("Duration : ${MovieService().durationToString(movie.duration)}"),
          Text("Budget : ${movie.budget}"),
          Text("Income : ${movie.income}"),

        ];

        return Padding(
          padding: const EdgeInsets.all(10),
          child: Card(

            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: <Widget> [
                // Image.network(
                //   movie.genre.image,
                //   fit: BoxFit.fill,
                // ),
                ListTile(
                  leading: const Icon(Icons.movie),
                  title: ExpandablePanel(
                    header: CinemaTitle(
                      title: movie.title,
                    ),
                    collapsed: Column(
                      mainAxisSize: MainAxisSize.min,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: collapsedContent,
                    ),
                    expanded: Column(
                      mainAxisSize: MainAxisSize.min,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: expandedContent,
                    ),
                  ),
                  // title: Text("Released date : ${movie.releaseDate}"),
                  subtitle: Text('Directed by ${movie.director.firstName} ${movie.director.lastName}'),

                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: isAdmin ? adminButtons : [],
                ),
              ],
            ),



          )
        );
      },
    );

  }

}