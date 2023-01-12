import 'package:flutter/material.dart';
import 'package:flutter_cli/service/auth_service.dart';
import 'package:flutter_cli/view/login_page.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class NavDrawer extends StatelessWidget {
  final _storage = const FlutterSecureStorage();

  final String title;

  const NavDrawer({super.key, this.title=''});

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: AuthService().isAuthenticated() ? <Widget>[
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

          ListTile(
            leading: const Icon(Icons.verified_user),
            title: const Text('Profile'),
            onTap: () => {Navigator.of(context).pop()},
          ),
          ListTile(
            leading: const Icon(Icons.settings),
            title: const Text('Settings'),
            onTap: () => {Navigator.of(context).pop()},
          ),
          ListTile(
            leading: const Icon(Icons.border_color),
            title: const Text('Feedback'),
            onTap: () => {Navigator.of(context).pop()},
          ),
          ListTile(
            leading: const Icon(Icons.exit_to_app),
            title: const Text('Logout'),
            onTap: () => {Navigator.of(context).pop()},
          ),
        ]
        :
        [
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
          ListTile(
            leading: const Icon(Icons.people_alt_rounded),
            title: const Text('Login'),
            onTap: () => {
              Navigator.of(context).push(MaterialPageRoute(builder: (context) => LoginPage(title: title))),
            },
          ),
        ],
      ),
    );
  }
}