import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

// ...


class RegistoEmpresaPage extends StatefulWidget {
  const RegistoEmpresaPage({Key? key}) : super(key: key);

  @override
  _RegistoEmpresaPageState createState() => _RegistoEmpresaPageState();
}

class _RegistoEmpresaPageState extends State<RegistoEmpresaPage> {
  final _formKey = GlobalKey<FormState>();


  final List<String> _distritos = [
    'Aveiro', 'Beja', 'Braga', 'Bragança', 'Castelo Branco', 'Coimbra', 'Évora', 'Faro',
    'Guarda', 'Leiria', 'Lisboa', 'Portalegre', 'Porto', 'Santarém', 'Setúbal', 'Viana do Castelo',
    'Vila Real', 'Viseu', 'Açores', 'Madeira'];

  final List<String> _servicos = ['Alojamento', 'Hotelaria', 'Restauração', 'Edifícios Culturais'];


  final Empresa _empresa = Empresa();

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
                Text("Dados da Empresa",
                    style: TextStyle(fontSize: 24) ),
                TextFormField(
                  decoration: InputDecoration(labelText: 'Nome da Empresa'),
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return 'Por favor, insira o nome da empresa.';
                    }
                    return null;
                  },
                  onSaved: (String? value) {
                    _empresa.nome = value!;
                  },
                ),

                TextFormField(
                  decoration: InputDecoration(labelText: 'Morada'),
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return 'Por favor, insira a morada da empresa.';
                    }
                    return null;
                  },
                  onSaved: (value) {
                    _empresa.morada = value!;
                  },
                ),

                //NIF
                TextFormField(
                  decoration: InputDecoration(labelText: 'NIF'),
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return 'Por favor, insira o NIF da empresa.';
                    }
                    else if (!RegExp(r'^[0-9]+$').hasMatch(value)) {
                      return 'O NIF deve conter apenas números.';
                    }
                    return null;
                  },
                  onSaved: (value) {
                    _empresa.nif = value!;
                  },
                ),

                TextFormField(
                  decoration: InputDecoration(labelText: 'CAE'),
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return 'Por favor, insira o CAE da empresa.';
                    }
                    else if (!RegExp(r'^[0-9]+$').hasMatch(value)) {
                      return 'O CAE deve conter apenas números.';
                    }
                    return null;
                  },
                  onSaved: (value) {
                    _empresa.cae = value!;
                  },
                ),

                TextFormField(
                  decoration:
                  InputDecoration(labelText: 'Contacto telefónico'),
                  validator: (String? value) {
                    if (value!.isEmpty) {
                      return 'Por favor, insira o contacto telefónico da empresa.';
                  }
                    else if (!RegExp(r'^[0-9]+$').hasMatch(value)) {
                      return 'O contacto telefónico deve conter apenas números.';
                  }
                    return null;
                  },
                  onSaved: (value) {
                    _empresa.contacto = value!;
                  }
                ),

                  // EMAIL
                TextFormField(
                  decoration: InputDecoration(labelText: 'E-mail'),
                  // The validator receives the text that the user has entered.
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter Gmail account';
                    } else if (!value.endsWith('@gmail.com')) {
                      return 'Please enter valid Gmail account';
                    }
                    return null;
                  },
                  onSaved: (value) {
                    _empresa.email = value!;
                  },
                ),


                TextFormField(
                  decoration: InputDecoration(labelText: 'Website da empresa'),
                  onSaved: (value) {
                    _empresa.website = value!;
                  },

                ),

                SizedBox(height: 30),


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
                        _empresa.distritos.add(distrito);
                      } else {
                        selectedDistritos.remove(distrito);
                        _empresa.distritos.remove(distrito);
                      }
                  });
                  },
                  ),
                  ))
                      .toList(),

                  ),

                  SizedBox(height: 30),

                  Text("Concelhos de"),
                  Wrap(
                    children: selectedDistritos
                        .map((distrito) => FutureBuilder(
                    future: getConcelhos(distrito),
                    builder: (context, AsyncSnapshot<List<String>> snapshot) {
                    if (snapshot.hasData) {
                      return Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(distrito),
                  Wrap(
                    children: snapshot.data!
                        .map((concelho) => Padding(
                    padding: EdgeInsets.all(4.0),
                    child: FilterChip(
                    label: Text(concelho),
                    selected: _empresa.concelhos.contains(concelho),
                    onSelected: (bool selected) {
                    setState(() {
                    if (selected) {
                      _empresa.concelhos.add(concelho);
                    } else {
                      _empresa.concelhos.remove(concelho);
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
                              value: _empresa.servicos.contains(servico),
                              onChanged: (value) {
                                setState(() {
                                  if (value!) {
                                    _empresa.servicos.add(servico);
                                  } else {
                                    _empresa.servicos.remove(servico);
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

                  ElevatedButton(onPressed: _submitForm, child: Text("Registar empresa"))
                  ],
                  ),
                  ),
    ]),
    ),
    ),
    ),
    );
}

  void _submitForm() async {
  if (_formKey.currentState!.validate()) {
    _formKey.currentState!.save();


    print(_empresa);

    // Convert the _empresa object to a JSON string
    String empresaJson = jsonEncode(_empresa);
    
    // Send the JSON string to the Django back-end
    final response = await http.post(Uri.parse('http://127.0.0.1:8000/empresa'),
      body: jsonEncode(_empresa.toDict())
    );
    //headers: {'Content-Type': 'application/json'});
    
    // Handle the response from the Django back-end
    if (response.statusCode == 200) {
      // Show a confirmation message
      ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Empresa registada com sucesso!')));

      print('Empresa object sent successfully');
    } else {
      print('Failed to send Empresa object');
    }
  }
}
}

class Empresa {
  late String nome;
  late String morada;
  late String nif;
  late String cae;
  late String contacto;
  late String email;
  List<String> distritos = [];
  List<String> concelhos = [];
  late String website;
  List<String> servicos = [];

  Map<String, dynamic> toDict() {
    return {
      'nome': nome,
      'morada': morada,
      'nif': nif,
      'cae': cae,
      'contacto': contacto,
      'email': email,
      'distritos': distritos,
      'concelhos': concelhos,
      'website': website,
      'servicos': servicos,
    };
  }

  String toJson() => jsonEncode(toDict());

  static Empresa fromJson(String source) => fromMap(json.decode(source));

  static Empresa fromMap(Map<String, dynamic> map) {
    return Empresa()
      ..nome = map['nome']
      ..morada = map['morada']
      ..nif = map['nif']
      ..cae = map['cae']
      ..contacto = map['contacto']
      ..email = map['email']
      ..distritos = List<String>.from(map['distrito'])
      ..concelhos = List<String>.from(map['concelhos'])
      ..website = map['website']
      ..servicos = List<String>.from(map['servicos']);

  }

  Map<String, dynamic> get empresa {
    return {
      'nome': nome,
      'morada': morada,
      'nif': nif,
      'cae': cae,
      'contacto': contacto,
      'email': email,
      'distritos': distritos,
      'concelhos': concelhos,
      'website': website,
      'servicos': servicos,

    };
  }

  @override
  String toString() {
    return 'Empresa(nome: $nome, morada: $morada, nif: $nif, cae: $cae, '
        'contacto: $contacto, email: $email, distritos: $distritos, '
        'concelhos: $concelhos, website: $website servicos: $servicos, ';
  }
}


