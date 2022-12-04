import 'package:flutter/material.dart';
import 'package:flutter_cli/models/movie.dart';
import 'package:flutter_cli/service/movie_service.dart';
import 'package:intl/intl.dart';
import 'dart:developer' as developer;

class MovieForm extends StatelessWidget {

  final Movie? movie;

  const MovieForm(this.movie, {super.key,});

  @override
  Widget build(BuildContext context) {

    TextEditingController? title = TextEditingController();
    TextEditingController? duration = TextEditingController();
    TextEditingController? dateInput = TextEditingController();
    TextEditingController? budget = TextEditingController();
    TextEditingController? income = TextEditingController();
    // ---
    TextEditingController? idDirector = TextEditingController();
    TextEditingController? idGenre = TextEditingController();

    if(movie != null)
    {
      // developer.log(movie.toString());

      title.text = movie!.title.toString();
      duration.text = movie!.duration.toString();
      dateInput.text = movie!.releaseDate.toString();
      budget.text = movie!.budget.toString();
      income.text = movie!.income.toString();
      // idDirector.text = movie!.director.id.toString();
      // idGenre.text = movie!.releaseDate.toString();

    }

    final titleField = TextFormField(
      keyboardType: TextInputType.text,
      autofocus: false,
      controller: title,
      decoration: InputDecoration(
        hintText: 'Title',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final durationField = TextFormField(
      keyboardType: TextInputType.number,
      autofocus: false,
      controller: duration,
      decoration: InputDecoration(
        hintText: 'Duration',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
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

    final budgetField = TextFormField(
      keyboardType: TextInputType.number,
      autofocus: false,
      controller: budget,
      decoration: InputDecoration(
        hintText: r'Budget (in $)',
        contentPadding: const EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final incomeField = TextFormField(
      keyboardType: TextInputType.number,
      autofocus: false,
      controller: income,
      decoration: InputDecoration(
        hintText: r'Income (in $)',
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

    return Column(
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