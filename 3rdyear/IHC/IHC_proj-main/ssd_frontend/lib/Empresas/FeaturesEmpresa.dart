import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:ssd_frontend/Empresas/homeScreentwo.dart';
import 'package:ssd_frontend/main.dart';
import 'package:ssd_frontend/noticias/feature_noticias.dart';
import 'package:ssd_frontend/servicos/servicos.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'dart:convert';
import 'package:ssd_frontend/Empresas/appbar_main_two.dart';
import 'package:ssd_frontend/Empresas/body_main_two.dart';
import 'package:http/http.dart' as http;


class UserInformation {
  final String companyName;
  final String companyPhone;
  final int numServices;

  UserInformation({
    required this.companyName,
    required this.companyPhone,
    required this.numServices,
  });

  int get services_count => numServices;
  String get company_phone => company_phone;
  String get company_name => company_name;
}
UserInformation? utilizador;

class FeaturesEmpresa extends StatefulWidget {
  const FeaturesEmpresa({Key? key}) : super(key: key);

  @override
  _FeaturesEmpresaState createState() => _FeaturesEmpresaState();
}

class _FeaturesEmpresaState extends State<FeaturesEmpresa> {
  FirebaseAuth auth = FirebaseAuth.instance;
  User? currentUser;

  @override
  void initState() {
    super.initState();
    currentUser = auth.currentUser;

    // call fetchUserInformation method to get user information
    fetchUserInformation(currentUser?.email ?? '').then((value) {
      setState(() {
        utilizador = value; // assign the returned value to utilizador variable
      });
    });
  }

  Future<UserInformation> fetchUserInformation(String email) async {
    var url = Uri.parse('http://127.0.0.1:8000/user_info/?email=$email');
    var response = await http.get(url);
    if (response.statusCode == 200) {
      var data = jsonDecode(response.body);
      return UserInformation(
        companyName: data['company_name'],
        companyPhone: data['company_phone'],
        numServices: data['services_count'],
      );
    } else {
      throw Exception('Failed to load user information');
    }
  }

  String userEmail = "";
  String userPhoneNumber = "";

  @override
  Widget build(BuildContext context) {
    if (currentUser != null) {
      userEmail = currentUser!.email ?? "";
      userPhoneNumber = currentUser!.phoneNumber ?? "";
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        shadowColor: Colors.transparent,
        title: const Text(
          "Área da Empresa",
          style: TextStyle(fontSize: 20, color: Colors.white),
        ),
      ),
      drawer: Drawer(
        child: Column(
          children: [
            Expanded(
              child: ListView(
                padding: EdgeInsets.zero,
                children: [
                  const DrawerHeader(
                    child: Image(
                      image: AssetImage(
                        "assets/icons/icon_app.png",
                      ),
                    ),
                  ),
                  ListTile(
                    leading: const Icon(Icons.home),
                    title: const Text(
                      "Voltar para a Página Principal",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    onTap: () {
                      Navigator.pushAndRemoveUntil(
                        context,
                        MaterialPageRoute(builder: (context) => homeScreentwo()),
                            (Route<dynamic> route) => false,
                      );
                    },
                  ),

                  ListTile(
                    leading: const Icon(Icons.dataset_rounded),
                    title: const Text(
                      "Criação de Anúncios", // CRIAR
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => ServicosDisponiveis()),
                      );
                    },
                  ),
                ],
              ),
            ),
            Align(
              alignment: FractionalOffset.bottomCenter,
              child: Container(
                child: Column(
                  children: const [
                    Divider(),
                    ListTile(
                      leading: Icon(
                        Icons.logout,
                      ),
                      title: Text(
                        "Terminar Sessão",
                        style: TextStyle(fontSize: 18),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
      body: Column(
        children: [
          const Expanded(flex: 2, child: _TopPortion()),
          Expanded(
            flex: 3,
            child: FutureBuilder<UserInformation>(
              future: fetchUserInformation(currentUser?.email ?? ''),
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      children: [
                        Text(
                          snapshot.data?.companyName ?? "N/A",
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 25,
                          ),
                        ),
                        SizedBox(height: 16,),
                        Text(
                          currentUser?.email ?? "",
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 18,
                          ),
                        ),
                        SizedBox(height: 10,),
                        Text(
                          snapshot.data?.companyPhone ?? "N/A",
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 18,
                          ),
                        ),
                        SizedBox(height: 16,),
                        _ProfileInfoRow()
                      ],
                    ),
                  );
                } else if (snapshot.hasError) {
                  return Center(child: Text("Error: ${snapshot.error}"));
                } else {
                  return Center(child: CircularProgressIndicator());
                }
              },
            ),
          ),
        ],
      ),
    );
  }
}

class _ProfileInfoRow extends StatelessWidget {

  _ProfileInfoRow({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final List<ProfileInfoItem> _items = [      ProfileInfoItem("Anúncios",  0),      ProfileInfoItem("Visitantes", 2),    ];

    return Container(
      height: 80,
      constraints: const BoxConstraints(maxWidth: 400),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: _items.map(
              (item) => Expanded(
            child: Row(
              children: [
                if (_items.indexOf(item) != 0) const VerticalDivider(),
                Text(item.label),
                const SizedBox(width: 8),
                Text(
                  item.value.toString(),
                  style: const TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ],
            ),
          ),
        ).toList(),
      ),
    );
  }
}


class ProfileInfoItem {
  final String title;
  final int value;

  const ProfileInfoItem(this.title, this.value);

  String get label => title;
}



class _TopPortion extends StatelessWidget {
  const _TopPortion({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Stack(
      fit: StackFit.expand,
      children: [
        Container(
          margin: const EdgeInsets.only(bottom: 50),
          decoration: const BoxDecoration(
              gradient: LinearGradient(
                  begin: Alignment.bottomCenter,
                  end: Alignment.topCenter,
                  colors: [Colors.blueAccent, Colors.blue]),
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(50),
                bottomRight: Radius.circular(50),
              )),
        ),
        Align(
          alignment: Alignment.bottomCenter,
          child: SizedBox(
            width: 150,
            height: 150,
            child: Stack(
              fit: StackFit.expand,
              children: [
                Container(
                  decoration: const BoxDecoration(
                    color: Colors.black,
                    shape: BoxShape.circle,
                    image: DecorationImage(
                        fit: BoxFit.cover,
                        image: AssetImage("assets/json_profiles/profile1.png")
                    ),
                  ),
                ),

              ],
            ),
          ),
        ),
      ],
    );
  }
}


