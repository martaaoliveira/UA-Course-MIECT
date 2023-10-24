import 'dart:convert';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:ssd_frontend/componentes/constants.dart';
import 'package:ssd_frontend/features_empresa/empresa.dart';
import 'package:ssd_frontend/firstPage.dart';
import 'package:ssd_frontend/Favoritos.dart';
import 'package:ssd_frontend/login/login_turista.dart';
import 'package:ssd_frontend/registo_empresas/registo.dart';
import 'package:image_card/image_card.dart';
import 'result_search.dart';
import 'package:ssd_frontend/formularios/forms_alojamento.dart';
import 'package:ssd_frontend/formularios/forms_restauracao.dart';
import 'package:ssd_frontend/formularios/forms_monumentos.dart';

import 'result_search.dart';
import 'home_screen.dart';

import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';



Future<void> main() async {

  //runApp(const MyApp());

  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );

  /*
  SystemChrome.setPreferredOrientations(
      [DeviceOrientation.portraitUp, DeviceOrientation.portraitDown]);*/
  runApp(Destinos());

}

class Destinos extends StatelessWidget {
  /*
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
  return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'LusiTravel',
      initialRoute: '/',
      routes: {
        "/": (context) => MainPage(),
      },
      themeMode: ThemeMode.dark,
    );
  }*/

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'LusiTravel',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: kPrimaryColor,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),

       home: firstPage(),
       //home: ResultSearch(),
       //home: FeaturesEmpresa(),
    );


  }
}


/*
class MainPage extends StatefulWidget {
  MainPage({Key ? key}) : super (key: key);

  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  List<Map<String, dynamic>> profiles = <Map<String, dynamic>>[];
  List<Map<String, dynamic>> filtered = <Map<String, dynamic>>[];
  JsonDecoder decoder = JsonDecoder();

  Future<bool> _loadAsset() async {
    String value = await rootBundle.loadString('assets/files/profiles.json');
    for (Map<String, dynamic> x in decoder.convert(value)["contacts"]) {
      profiles.add(x);
      filtered.add(x);
    }
    return true;
  }

  @override
  void initState() {
    super.initState();
    _loadAsset().then((value) => setState(() {}));
  }

  @override
  Widget build(BuildContext context) {
    final double width = MediaQuery
        .of(context)
        .size
        .width;
    // final double height = MediaQuery.of(context).size.height;

    final List<String> images = [
      'assets/main_images/alojamento.jpeg',
      'assets/main_images/restaurante1.jpeg',
      'assets/main_images/cafe.jpeg',
    ];

    return MaterialApp(
      title: 'LusiTravel',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: kPrimaryColor,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: HomeScreen(),
    );


  }
}*/

      /*
      appBar: AppBar(
        actions: [

          ElevatedButton(
            onPressed: () {
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => RegistoEmpresaPage())
              );
            },
            child: Text("Registo de Empresas",
              style: TextStyle(
                color: Colors.white,
              ),
            ),
          ),

          SizedBox(
            width: 10,
          ),

          ElevatedButton(
            onPressed: () {
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => LoginTurista())
              );
            },
            child: Text("Login",
              style: TextStyle(
                color: Colors.white,
              ),
            ),
          ),

          SizedBox(
            width: 10,
          ),

          ElevatedButton(
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => FeaturesEmpresa())
                );
              },
              child: Text("Área da Empresa",
                style: TextStyle(
                  color: Colors.white,
                ),
              ),
          ),

          SizedBox(
            width: 10,
          ),

          Padding(
            padding: EdgeInsets.fromLTRB(0, 5, 10, 5),
            child: Image(image: AssetImage("assets/icons/icon_app.png"),
            ),
          ),



        ],

        backgroundColor: Colors.blue,
        shadowColor: Colors.transparent,

        title: const Text(
          "LusiTravel",
          style: TextStyle(
            fontSize: 20,
            color: Colors.white,
          ),
        ),


      ),

      // BODY -------------------------------------------------------------------
      body:
          ListView(
            children: [
              Stack(
                clipBehavior: Clip.none,
                children: [
                  Positioned(
                    left:0,
                    top:0,
                    child: FittedBox(
                      fit: BoxFit.fitWidth,
                      alignment: Alignment.topCenter,
                      child:

                      CarouselSlider(
                        items: [
                          ImageView('assets/main_images/alojamento.jpeg'),
                          ImageView('assets/main_images/restaurante1.jpeg'),
                          ImageView('assets/main_images/hotelaria.jpeg'),
                        ],
                        options: CarouselOptions(
                          height: 400,
                          autoPlay: true,
                          enlargeCenterPage: true,
                        ),
                      ),

                      ImageSlideshow(
                        width: width,
                        height: width * 0.43,
                        indicatorColor: Colors.redAccent,
                        indicatorBackgroundColor: Colors.grey,
                        //isLoop: true,
                        children: images.map((image) {
                          return Image.asset(image);
                        }).toList(),
                        autoPlayInterval: 5000,     // -> está em milisegundos
                        isLoop: true,
                      ),
                    ),
                  ),

                  Padding(
                      padding: EdgeInsetsDirectional.fromSTEB(0, width*0.4, 0, 0),
                      child: Container(
                        width: MediaQuery.of(context).size.width,
                        decoration: BoxDecoration(
                          color: Colors.white,
                          boxShadow: [
                            BoxShadow(
                              blurRadius: 5,
                              color: Color(0x5B000000),
                              offset: Offset(3, -5),
                            ),
                          ],
                          borderRadius: BorderRadius.only(
                            bottomLeft: Radius.circular(0),
                            bottomRight: Radius.circular(0),
                            topLeft: Radius.circular(20),
                            topRight: Radius.circular(20),
                          ),
                        ),

                        child: Column(
                          mainAxisSize: MainAxisSize.max,
                          children: [
                            Padding(
                                padding: const EdgeInsets.fromLTRB(8, 0, 8, 0),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  children: [
                                    Padding(
                                        padding: const EdgeInsets.fromLTRB(60, 0, 500, 16),
                                        child: TextField(
                                          style: TextStyle(
                                            color: Color.fromRGBO(44, 73, 108, 1.0)
                                          ),
                                          cursorColor: Color.fromRGBO(44, 73, 108, 1.0),
                                          decoration: InputDecoration(
                                            labelText: "Procurar",
                                            labelStyle:
                                            TextStyle(color: Color.fromRGBO(44, 73, 108, 1.0)),
                                            focusedBorder: UnderlineInputBorder(
                                              borderSide:
                                              BorderSide(color: Color.fromRGBO(44, 73, 108, 1.0)),
                                            ),
                                            suffixIcon: Icon(
                                              Icons.search,
                                              color: Color.fromRGBO(44, 73, 108, 1.0),
                                            )
                                        ),
                                          onChanged: (e) {
                                            print(profiles[0]["Localidade1"]);
                                            setState(() {
                                              filtered = profiles.where((element) =>
                                                element["Localidade1"].toLowerCase().contains(e.toLowerCase())).toList();
                                            });
                                          },
                                        ),
                                    ),

                                    ElevatedCardAnuncios(),
                                  ],
                                ),
                            ),
                          ],
                        ),
                    ),
                  ),

                ],
              ),
            ],
          ),

      Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          children: [
            Expanded(
                child: Align(
                  alignment: Alignment.topCenter,
                  child: Wrap(
                    runSpacing: (height+200)*0.02,
                    children: [

                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          SizedBox(
                            width: width*0.80,
                            height: height*0.1,
                            child: ElevatedButton(
                                onPressed: () {
                                  Navigator.push(context, MaterialPageRoute(
                                      builder: (context) => ServicosDisponiveis())
                                  );
                                },
                                style: ElevatedButton.styleFrom(
                                  shadowColor: Colors.black,
                                  elevation: 15,
                                  backgroundColor: const Color.fromARGB(230, 152, 0, 1),
                                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(13.0)),
                                ),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                  children: const [
                                    Text('Serviços Disponíveis',
                                      style: TextStyle(
                                          fontSize: Checkbox.width,
                                          color: Colors.white
                                      ),
                                    ),
                                  ],
                                )
                            ),
                          ),
                        ],
                      ),


                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          SizedBox(
                            width: width*0.80,
                            height: height*0.1,
                            child: ElevatedButton(
                                onPressed: () {
                                  Navigator.push(context, MaterialPageRoute(
                                      builder: (context) => RegistoEmpresaPage())
                                  );
                                },
                                style: ElevatedButton.styleFrom(
                                  shadowColor: Colors.black,
                                  elevation: 15,
                                  backgroundColor: const Color.fromARGB(230, 152, 0, 1),
                                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(13.0)),
                                ),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                  children: const [
                                    Text('Registar Empresa',
                                      style: TextStyle(
                                          fontSize: Checkbox.width,
                                          color: Colors.white
                                      ),
                                    ),
                                  ],
                                )
                            ),
                          ),
                        ],
                      ),

                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          SizedBox(
                            width: width*0.80,
                            height: height*0.1,
                            child: ElevatedButton(
                                onPressed: () {
                                  Navigator.push(context, MaterialPageRoute(
                                      builder: (context) => const LoginTurista())
                                  );
                                },
                                style: ElevatedButton.styleFrom(
                                  shadowColor: Colors.black,
                                  elevation: 15,
                                  backgroundColor: const Color.fromARGB(230, 152, 0, 1),
                                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(13.0)),
                                ),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                  children: const [
                                    Text('Login Turista',
                                      style: TextStyle(
                                          fontSize: Checkbox.width,
                                          color: Colors.white
                                      ),
                                    ),
                                  ],
                                )
                            ),
                          ),
                        ],
                      ),

                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          SizedBox(
                            width: width*0.80,
                            height: height*0.1,
                            child: ElevatedButton(
                                onPressed: () {
                                  Navigator.push(context, MaterialPageRoute(
                                      builder: (context) => const FeaturesEmpresa())
                                  );
                                },
                                style: ElevatedButton.styleFrom(
                                  shadowColor: Colors.black,
                                  elevation: 15,
                                  backgroundColor: const Color.fromARGB(230, 152, 0, 1),
                                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(13.0)),
                                ),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                  children: const [
                                    Text('Área da Empresa',
                                      style: TextStyle(
                                          fontSize: Checkbox.width,
                                          color: Colors.white
                                      ),
                                    ),
                                  ],
                                )
                            ),
                          ),
                        ],
                      ),


                    ],
                  ),
                ),
            ),
          ],
        ),
      ),

      // ------------------------ BOTTOM NAV BAR DA MAIN PAGE ------------------------------ //

      bottomNavigationBar: const BottomAppBar(
        child: Padding(
            padding: EdgeInsets.all(25),
            child: Text(
              "Este website foi criado no âmbito da Unidade Curricular de Projeto de Engenharia de Computadores e Informática"
                  "\nOrientador: Prof. Osvaldo Pacheco",
            ),
        ),
      ),

    );
  }
}


// ---------------- CARTOES PARA OS ANUNCIOS ---------------------- //
class ElevatedCardAnuncios extends StatelessWidget {
  const ElevatedCardAnuncios({super.key});

  @override
  Widget build (BuildContext context) {
    return Card(
      //semanticContainer: true,
      clipBehavior: Clip.antiAliasWithSaveLayer,
      child: Column(
        children: [

          FillImageCard(
            width: 200,
            heightImage: 140,
            imageProvider: AssetImage('assets/main_images/alojamento.jpeg'),
            //tags: [_tag('Category', () {}), _tag('Product', () {})],
            title: Text("Alojamento Local"),
            description: Text("Aveiro, Portugal"),
          ),

          //Image(image: AssetImage('assets/main_images/alojamento_local.jpeg')),
          /*Text(
            "Alojamento Casa Azul"
          ),*/
        ],
        ),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(10.0),
      ),
      elevation: 5,
      margin: EdgeInsets.all(10),
    );
  }
}


class ImageView extends StatelessWidget {
  String imgPath;
  ImageView(this.imgPath);
  @override
  Widget build (BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 5),
      child: FittedBox(
        fit: BoxFit.fill,
        child: Image.asset(imgPath),
      ),
    );
  }
}*/
