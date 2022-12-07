import 'package:flutter/material.dart';
import 'package:flutter_cli/models/genre.dart';
import 'package:flutter_cli/models/movie.dart';
import 'package:flutter_cli/models/requests/movie_request.dart';
import 'package:flutter_cli/service/director_service.dart';
import 'package:flutter_cli/service/genre_service.dart';
import 'package:flutter_cli/service/movie_service.dart';
import 'package:intl/intl.dart';
import 'dart:developer' as developer;

import '../../../models/director.dart';
import 'cinema_dropdown.dart';
import 'cinema_text_input.dart';

class MovieForm extends StatelessWidget {

  final Movie? movie;
  final Function? notifyParent;

  const MovieForm(this.movie, this.notifyParent, {super.key,});

  Future<List<dynamic>> getDirectorsAndGenres() async {
    return Future.value([
      await GenreService().getGenres(),
      await DirectorService().getDirectors()
    ]);
  }

  @override
  Widget build(BuildContext context) {

    TextEditingController title = TextEditingController();
    TextEditingController duration = TextEditingController();
    TextEditingController dateInput = TextEditingController();
    TextEditingController budget = TextEditingController();
    TextEditingController income = TextEditingController();
    // ---
    int? idDirector;
    String? idGenre;

    if(movie != null)
    {
      // developer.log(movie.toString());

      title.text = movie!.title.toString();
      duration.text = movie!.duration.toString();
      dateInput.text = DateFormat('yyyy-MM-dd').format(movie!.releaseDate);
      budget.text = movie!.budget.toString();
      income.text = movie!.income.toString();
      // ---
      idDirector = movie!.director.id;
      idGenre = movie!.genre.id;

    }

    final titleField = CinemaTextInput(
      controller: title,
      hintText: r'Title',
    );

    final durationField = CinemaTextInput(
      textType: TextInputType.number,
      controller: duration,
      hintText: r'Duration',
    );

    final releasedDateField = TextFormField(
        keyboardType: TextInputType.datetime,
        autofocus: false,
        controller: dateInput,
        decoration: InputDecoration(
          hintText: 'Released Date',
          icon: const Icon(Icons.calendar_today),
          contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
        ),
      readOnly: true,

      onTap: () async {
        DateTime? pickedDate = await showDatePicker(
            context: context,
            initialDate: DateTime.now(),
            firstDate: DateTime(1900),
            //DateTime.now() - not to allow to choose before today.
            lastDate: DateTime(2100),
        );

        if (pickedDate != null) {
          print(pickedDate); //pickedDate output format => 2021-03-10 00:00:00.000
          String formattedDate = DateFormat('yyyy-MM-dd').format(pickedDate);
          print(formattedDate); //formatted date output using intl package =>  2021-03-16
          dateInput.text = formattedDate; //set output date to TextField value.

        }
      },
    );

    final budgetField = CinemaTextInput(
      textType: TextInputType.number,
      controller: budget,
      hintText: r'Budget (in $)',
    );

    final incomeField = CinemaTextInput(
      textType: TextInputType.number,
      controller: income,
      hintText: r'Income (in $)',
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

          MovieRequest newMovie = MovieRequest(
            title: title.text,
            duration: MovieService().parseDuration(duration.text).inSeconds,
            releaseDate: DateTime.parse(dateInput.text),
            budget: int.parse(budget.text),
            income: int.parse(income.text),
            idGenre: idGenre!,
            idDirector: idDirector!,

          );

          if(movie != null) {
            newMovie.id = movie!.id;
            MovieService().updateMovie(newMovie);
          }
          else {

            MovieService().addMovie(newMovie);
          }

          notifyParent?.call();

        },
        child: const Text('Save Movie', style: TextStyle(color: Colors.white)),
      ),
    );

    return FutureBuilder<List<dynamic>>(
      future: getDirectorsAndGenres(),
        builder: (context, snapshot) {
        List<Widget> inputs = [
            // logo,
            const SizedBox(height: 48.0),
            titleField,
            const SizedBox(height: 8.0),
            durationField,
            const SizedBox(height: 24.0),
            releasedDateField,
            const SizedBox(height: 24.0),
            budgetField,
            const SizedBox(height: 8.0),
            incomeField,


          ];
          if(snapshot.hasData)
          {
            List<Director> directors = snapshot.data![1] as List<Director>;
            List<Genre> genres = snapshot.data![0] as List<Genre>;

            String directorValue = movie != null ?
            movie!.director.getFullname() : directors[0].getFullname();

            String? genreValue = movie != null ?
            movie?.genre.label : genres[0].label;

            idDirector = directors.where((director) =>
            director.getFullname() == directorValue).first.id;

            idGenre = genres.where((genre) => genre.label
                == genreValue).first.id;

            inputs.add(
              StatefulBuilder(
                  builder: (BuildContext context, StateSetter setState) {
                    return Row(
                      children: [
                        CinemaDropdown(
                          value: directorValue,
                          items: directors.map((director) => "${director.firstName} ${director.lastName}").toList(),
                          onChanged: (String? value) {
                            setState(() {
                              directorValue = value!;
                              idDirector = directors.where((director) =>
                              director.getFullname() == directorValue).first.id;

                              developer.log(idDirector.toString());
                            });
                          },
                        ),
                        CinemaDropdown(
                          value: genreValue!,
                          items: genres.map((genre) => genre.label).toList(),
                          onChanged: (String? value) {
                            setState(() {
                              genreValue = value!;

                              idGenre = genres.where((genre) => genre.label
                                  == genreValue).first.id;
                            });

                          },
                        ),
                      ],
                    );
                  }
              )

            );
          }

        inputs.add(saveButton);

          return Column(
            // shrinkWrap: true,
            // padding: const EdgeInsets.only(left: 24.0, right: 24.0),
            children: inputs,
          );
        },
    );

      Column(
      // shrinkWrap: true,
      // padding: const EdgeInsets.only(left: 24.0, right: 24.0),
      children: <Widget>[
        // logo,
        const SizedBox(height: 48.0),
        titleField,
        const SizedBox(height: 8.0),
        durationField,
        const SizedBox(height: 24.0),
        releasedDateField,
        const SizedBox(height: 24.0),
        budgetField,
        const SizedBox(height: 8.0),
        incomeField,

        saveButton
      ],
    );
  }

}