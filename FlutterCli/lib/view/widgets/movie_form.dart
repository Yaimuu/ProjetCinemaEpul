import 'package:flutter/material.dart';
import 'package:flutter_cli/models/movie.dart';
import 'package:flutter_cli/service/movie_service.dart';

import 'menu.dart';

class MovieForm extends StatelessWidget {

  final Movie movie;

  const MovieForm(this.movie, {super.key,});

  @override
  Widget build(BuildContext context) {

    if(movie != null) {

    }

    final titleField = TextFormField(
      keyboardType: TextInputType.text,
      autofocus: false,
      // controller: loginController,
      decoration: InputDecoration(
        hintText: 'Title',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final durationField = TextFormField(
      keyboardType: TextInputType.number,
      autofocus: false,
      // controller: loginController,
      decoration: InputDecoration(
        hintText: 'Duration',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final releasedDateField = TextFormField(
      keyboardType: TextInputType.datetime,
      autofocus: false,
      // controller: loginController,
      decoration: InputDecoration(
        hintText: 'Released Date',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final saveButton = Padding(
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



          // _futureMovieResponse = await MovieService().ad);
          //
          // if(_futureLoginResponse.jwtToken.isNotEmpty)
          // {
          //   Navigator.of(context).pushNamedAndRemoveUntil('/', (Route<dynamic> route) => false);
          // }

        },
        child: const Text('Save Movie', style: TextStyle(color: Colors.white)),
      ),
    );

    return ListView(
      shrinkWrap: true,
      padding: const EdgeInsets.only(left: 24.0, right: 24.0),
      children: <Widget>[
        // logo,
        const SizedBox(height: 48.0),
        titleField,
        const SizedBox(height: 8.0),
        durationField,
        const SizedBox(height: 24.0),
        releasedDateField,

        saveButton
      ],
    );
  }

}