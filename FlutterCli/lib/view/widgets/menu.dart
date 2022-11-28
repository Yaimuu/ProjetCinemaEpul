import 'package:flutter/material.dart';
import 'package:flutter_cli/service/auth_service.dart';
import 'package:flutter_cli/view/login_page.dart';
import 'package:flutter_cli/view/profile_page.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import '../movies_page.dart';

class NavDrawer extends StatelessWidget {
  final _storage = const FlutterSecureStorage();

  final String title;

  const NavDrawer({super.key, this.title=''});

  @override
  FutureBuilder<bool> build(BuildContext context) {
    return FutureBuilder(
      future: AuthService().isAuthenticated(),
      builder: (context, AsyncSnapshot<bool> snapshot) {

        // Default menu widgets
        List<Widget> userMenu = [
          DrawerHeader(
            decoration: const BoxDecoration(
              color: Colors.blue,
              /*image: DecorationImage(
                    fit: BoxFit.fill,
                    image: AssetImage('assets/images/cover.jpg')
                )*/
            ),
            child: Text(
              title,
              style: const TextStyle(color: Colors.white, fontSize: 25),
            ),
          ),
          ListTile(
            leading: const Icon(Icons.input),
            title: const Text('Welcome'),
            onTap: () => {Navigator.of(context).pushNamedAndRemoveUntil('/', (Route<dynamic> route) => false)},
          ),
        ];

        if(snapshot.hasData)
        {

          // User is authenticated
          if(snapshot.data!)
          {
            userMenu.addAll(<Widget>[
              ListTile(
                leading: const Icon(Icons.verified_user),
                title: const Text('Profile'),
                onTap: () => {Navigator.of(context).push(MaterialPageRoute(builder: (context) => ProfilePage(title: title))),},
              ),
              ListTile(
                leading: const Icon(Icons.movie),
                title: const Text('Films'),
                onTap: () => {Navigator.of(context).push(MaterialPageRoute(builder: (context) => MoviesPage(title: title))),},
              ),
              ListTile(
                leading: const Icon(Icons.exit_to_app),
                title: const Text('Logout'),
                onTap: () => {
                  AuthService().logout(),
                  Navigator.of(context).pushNamedAndRemoveUntil('/', (Route<dynamic> route) => false),
                },
              ),
            ]);
          }
          // User is not authenticated
          else {
            userMenu.addAll(<Widget>[
              ListTile(
                leading: const Icon(Icons.people_alt_rounded),
                title: const Text('Login'),
                onTap: () => {
                  Navigator.of(context).push(MaterialPageRoute(builder: (context) => LoginPage(title: title))),
                },
              ),
            ]);
          }
        }

        // Final menu
        return Drawer(
          child: ListView(
            padding: EdgeInsets.zero,
            children: userMenu,
          ),
        );
      },

    );

  }
}