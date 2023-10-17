import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:ssd_frontend/Empresas/ServicosDisponiveis.dart';
import 'package:ssd_frontend/Empresas/homeScreentwo.dart';
// ...


class RegistoRegiao extends StatefulWidget {
  const RegistoRegiao({Key? key}) : super(key: key);

  @override
  _RegistoRegiaoPageState createState() => _RegistoRegiaoPageState();
}


class _RegistoRegiaoPageState extends State<RegistoRegiao> {
  final _formKey = GlobalKey<FormState>();


  final List<String> _distritos = [
    'Aveiro', 'Beja', 'Braga', 'Bragança', 'Castelo Branco', 'Coimbra', 'Évora', 'Faro',
    'Guarda', 'Leiria', 'Lisboa', 'Portalegre', 'Porto', 'Santarém', 'Setúbal', 'Viana do Castelo',
    'Vila Real', 'Viseu', 'Açores', 'Madeira'];

  final List<String> _servicos = ['Alojamento', 'Hotelaria', 'Restauração', 'Edifícios Culturais'];


  final Regiao _regioes = Regiao();

  List<String> selectedDistritos= [];

  List<String> selectedServico =[];


  // FUNCTION TO GET THE CONCELHOS OF A DISTRICT
  Future<List<String>> getConcelhos(String distrito) async {
    final apiUrl = "https://json.geoapi.pt/distritos/" + distrito + "/municipios";
    final response = await http.get(Uri.parse(apiUrl));
    // print(response.statusCode);

    if (response.statusCode == 200) {
      final jsonData = jsonDecode(response.body);
      // print(jsonData);
      final municipios = List<String>.from((jsonData['municipios'] as List).map((municipio) => municipio['nome']));

      // print(municipios);
      return municipios;
    } else {
      throw Exception('Failed to load data from API');
    }
  }


  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text('Registo de Empresa'),
      ),
      body: SingleChildScrollView(

        child: Padding(
          padding: EdgeInsets.all(16),
          child: Form(
            key: _formKey,
            child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Text("Região da atividade",
                      style: TextStyle(fontSize: 24)),

                  SizedBox(height: 30),

                  Container(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text("Distritos"),
                        Wrap(
                          children: _distritos
                              .map((distrito) => Padding(
                            padding: EdgeInsets.all(4.0),
                            child: FilterChip(
                              label: Text(distrito),
                              selected: selectedDistritos.contains(distrito),
                              onSelected: (bool selected) {
                                setState(() {
                                  if (selected) {
                                    selectedDistritos.add(distrito);
                                    _regioes.distritos.add(distrito);
                                  } else {
                                    selectedDistritos.remove(distrito);
                                    _regioes.distritos.remove(distrito);
                                  }
                                });
                              },
                            ),
                          ))
                              .toList(),

                        ),

                        SizedBox(height: 30),


                        Wrap(
                          children: selectedDistritos
                              .map((distrito) => FutureBuilder(
                            future: getConcelhos(distrito),
                            builder: (context, AsyncSnapshot<List<String>> snapshot) {
                              if (snapshot.hasData) {
                                return Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text("Concelhos de " + distrito),
                                    Wrap(
                                      children: snapshot.data!
                                          .map((concelho) => Padding(
                                        padding: EdgeInsets.all(4.0),
                                        child: FilterChip(
                                          label: Text(concelho),
                                          selected: _regioes.concelhos.contains(concelho),
                                          onSelected: (bool selected) {
                                            setState(() {
                                              if (selected) {
                                                _regioes.concelhos.add(concelho);
                                              } else {
                                                _regioes.concelhos.remove(concelho);
                                              }
                                            });
                                          },
                                        ),
                                      ))
                                          .toList(),
                                    ),
                                  ],
                                );
                              } else if (snapshot.hasError) {
                                return Text('Ocorreu um erro a carregar os concelhos.');
                              } else {
                                return Center(
                                  child: CircularProgressIndicator(),
                                );
                              }
                            },
                          ))
                              .toList(),
                        ),

                        SizedBox(height: 30),

                        Text("Serviços",
                            style: TextStyle(fontSize: 24) ),
                        Column(
                          children: _servicos
                              .map(
                                (servico) => Row(
                              children: <Widget>[
                                Checkbox(
                                  value: _regioes.servicos.contains(servico),
                                  onChanged: (value) {
                                    setState(() {
                                      if (value!) {
                                        _regioes.servicos.add(servico);
                                      } else {
                                        _regioes.servicos.remove(servico);
                                      }
                                    });
                                  },
                                ),
                                Text(servico),
                              ],
                            ),
                          )
                              .toList(),
                        ),

                        SizedBox(height: 16),

                        ElevatedButton(
                          onPressed: () async {
                            await showDialog(
                              context: context,
                              builder: (BuildContext context) {
                                return AlertDialog(
                                  title: Text('Formulário submetido!'),
                                  content: Text('A empresa foi registada com sucesso!'),
                                  actions: [
                                    TextButton(
                                      onPressed: () {
                                        // Fecha o AlertDialog e retorna true
                                        Navigator.of(context).pop(true);
                                      },
                                      child: Text('OK'),
                                    ),
                                  ],
                                );
                              },
                            ).then((value) {
                              if (value == true) {
                                // Navegar para a próxima tela
                                Navigator.push(
                                  context,
                                  MaterialPageRoute(builder: (context) => homeScreentwo()),
                                );
                              } else {
                                // Tratar o caso onde o diálogo foi fechado sem pressionar "OK"
                              }
                            });
                          },
                          child: Text("Continuar registo"),
                        ),


                      ],
                    ),
                  ),
                ]),
          ),
        ),
      ),
    );
  }

  Future<bool> _submitForm() async {
    if (_formKey.currentState!.validate()) {
      _formKey.currentState!.save();

      // Get the currently authenticated user's email
      String email = FirebaseAuth.instance.currentUser?.email ?? "";

      // Add the email to the _empresa object
      _regioes.user_email = email;

      print(_regioes);

      // Convert the _empresa object to a JSON string
      String regioesJson = jsonEncode(_regioes);

      // Send the JSON string to the Django back-end
      final response = await http.post(Uri.parse('http://127.0.0.1:8000/regions'),
          body: jsonEncode(_regioes.toDict())
      );
      //headers: {'Content-Type': 'application/json'});

      // Handle the response from the Django back-end
      if (response.statusCode == 200) {
        // Show a confirmation message
        ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(content: Text('Empresa registada com sucesso!')));

        print('Empresa object sent successfully');
        return true;
      } else {
        print('Failed to send Empresa object');
        return false;
      }
    }
    return false;
  }
}

class Regiao {

  List<String> distritos = [];
  List<String> concelhos = [];
  List<String> servicos = [];
  late String user_email;

  Map<String, dynamic> toDict() {
    return {
      'distritos': distritos,
      'concelhos': concelhos,
      'servicos': servicos,
      'user_email': user_email,
    };
  }

  String toJson() => jsonEncode(toDict());

  static Regiao fromJson(String source) => fromMap(json.decode(source));

  static Regiao fromMap(Map<String, dynamic> map) {
    return Regiao()

      ..distritos = List<String>.from(map['distrito'])
      ..concelhos = List<String>.from(map['concelhos'])
      ..servicos = List<String>.from(map['servicos'])
      ..user_email = map['user_email'];

  }

  Map<String, dynamic> get empresa {
    return {
      'distritos': distritos,
      'concelhos': concelhos,
      'user_email': user_email,
      'servicos': servicos,

    };
  }

  @override
  String toString() {
    return 'Regiao(distritos: $distritos, '
        'concelhos: $concelhos, user_email: $user_email, servicos: $servicos, ';
  }
}


