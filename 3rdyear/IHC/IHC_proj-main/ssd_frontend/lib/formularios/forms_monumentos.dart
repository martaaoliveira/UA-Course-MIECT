import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:ssd_frontend/features_empresa/features_empresa.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:firebase_auth/firebase_auth.dart';
import 'ConfirmationPage.dart';
import 'dart:io';
import 'package:image_picker/image_picker.dart';
import 'package:file_picker/file_picker.dart';
import 'dart:typed_data';
import 'package:ssd_frontend/Empresas/homeScreentwo.dart';


class MonumentoForm extends StatefulWidget {
  @override
  _MonumentoFormState createState() => _MonumentoFormState();
}

class _MonumentoFormState extends State<MonumentoForm> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nameController = TextEditingController();
  TextEditingController _storyController = TextEditingController();
  TextEditingController _styleController = TextEditingController();
  TextEditingController _accessabilityController = TextEditingController();
  TextEditingController _scheduleController = TextEditingController();
  TextEditingController _priceController = TextEditingController();
  TextEditingController _activityController = TextEditingController();
  TextEditingController _guideVisitController = TextEditingController();
  // TextEditingController _locationController = TextEditingController();
  TextEditingController _latitudeController = TextEditingController();
  TextEditingController _longitudeController = TextEditingController();
  List<String> _imageDescriptionList = [];
  List<Uint8List> _imageBytesList = [];
  List<String> _imageStringList = [];
  List<File> _imageList = [];


  Future<void> _getImage(ImageSource source) async {
    final pickedFile = await ImagePicker().getImage(source: source);
    if (pickedFile != null) {
      final bytes = await pickedFile.readAsBytes();
      final encodedImage = base64Encode(bytes); // Convert bytes to base64 encoded string
      setState(() {
        _imageList.add(File(pickedFile.path));
        _imageStringList.add(encodedImage); // Add encoded image string to the list
        _imageDescriptionList.add(''); // Add an empty string to the list
      });
    }
  }

  Widget _buildNameInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Nome do monumento"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _nameController,
          decoration: InputDecoration
            (
            border: OutlineInputBorder(),
            hintText: "Escreva o nome do monumento",
          ),
          validator: (value) {
            if (value == "") {
              return "Escreva o nome do monumento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildStoryInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("História"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _storyController,
          decoration: InputDecoration
            (
            border: OutlineInputBorder(),
            hintText: "Escreva um texto sobre a história do monumento",
          ),
          validator: (value) {
            if (value == "") {
              return "Escreva um texto sobre a história do monumento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildStyleInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Estilo arquitetónico"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _styleController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira o estilo arquitetónico do monumento",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira o estilo arquitetónico do monumento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildAccessabilityInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Acessibilidade"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _accessabilityController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Indique se há restrições de acessibilidade",
          ),
          validator: (value) {
            if (value == "") {
              return "Indique se há restrições de acessibilidade";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildScheduleInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Horário"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _scheduleController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira o horário de funcionamento",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira o horário de funcionamento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildPriceInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Preço"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _priceController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira o preçário do estabelecimento",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira o preçário do estabelecimento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildActivityInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Atividades"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _activityController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira as atividades disponiveis",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira as atividades disponiveis";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildGuideVisitInput() {
  return Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: <Widget>[
      Text("É possível marcar visitas guiadas?"),
      SizedBox(height: 10.0),
      DropdownButtonFormField(
        value: _guideVisitController.text.isNotEmpty ? _guideVisitController.text : null,
        decoration: InputDecoration(
          border: OutlineInputBorder(),
          hintText: "É possível marcar visitas guiadas?",
        ),
        items: [
          DropdownMenuItem(
            child: Text("Sim"),
            value: "Sim",
          ),
          DropdownMenuItem(
            child: Text("Não"),
            value: "Não",
          ),
        ],
        onChanged: (value) {
          setState(() {
            _guideVisitController.text = value!;
          });
        },
        validator: (value) {
          if (value == null) {
            return "Selecione uma opção";
          }
          return null;
        },
      ),
    ],
  );
}

  Widget _buildImagesInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Imagens"),
        SizedBox(height: 10.0),
        ElevatedButton(
          onPressed: () {
            _getImage(ImageSource.gallery);
          },
          child: Text("Inserir imagem do monumento"),
        ),
        SizedBox(height: 10.0),
        if (_imageList.isNotEmpty)
          Column(
            children: List.generate(_imageList.length, (index) {
              return Column(
                children: [
                  SizedBox(height: 10.0),
                  Row(
                    children: [
                      Image.network(
                        _imageList[index].path,
                        height: 100.0,
                        width: 100.0,
                      ),
                      SizedBox(width: 10.0),
                      Expanded(
                        child: TextFormField(
                          onChanged: (value) {
                            setState(() {
                              _imageDescriptionList[index] = value;
                            });
                          },
                          decoration: InputDecoration(
                            hintText: "Insira uma descrição para esta imagem",
                            border: OutlineInputBorder(),
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              );
            }),
          ),
      ],
    );
  }




  Widget _buildLocationInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Morada"),
        SizedBox(height: 10.0),
        Row(
          children: [
            Expanded(
              child: TextFormField(
                controller: _latitudeController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  hintText: "Insira a morada",
                ),
                validator: (value) {
                  if (value?.isEmpty ?? true) {
                    return "Insira a morada";
                  }
                  // if (double.tryParse(value!) == null) {
                  //   return "A latitude deve ser um número válido(por exemplo: 12.345)";
                  // }
                  return null;
                },
              ),
            ),
            SizedBox(width: 10.0),
            // Expanded(
            //   child: TextFormField(
            //     controller: _longitudeController,
            //     decoration: InputDecoration(
            //       border: OutlineInputBorder(),
            //       hintText: "Insira a longitude",
            //     ),
            //     validator: (value) {
            //       if (value?.isEmpty ?? true) {
            //         return "Insira a longitude";
            //       }
            //       if (double.tryParse(value!) == null) {
            //         return "A longitude deve ser um número válido(por exemplo: 12.345)";
            //       }
            //       return null;
            //     },
            //   ),
            // ),
          ],
        ),
      ],
    );
  }




  // void _saveMonumento() async {
  //     final user = FirebaseAuth.instance.currentUser;
  //   final email = user?.email ?? "";
  //   if (_formKey.currentState!.validate()) {
  //     Map<String, dynamic> monumentoData = {
  //       'name': _nameController.text,
  //       'story': _storyController.text,
  //       'style': _styleController.text,
  //       'accessability': _accessabilityController.text,
  //       'schedule': _scheduleController.text,
  //       'price': _priceController.text,
  //       'activity': _activityController.text,
  //       'guide_visit': _guideVisitController.text,
  //       'images': _imageStringList,
  //       'imageDescriptions': _imageDescriptionList,
  //       // 'location': _locationController.text,
  //       'latitude': _latitudeController.text,
  //       'longitude': _longitudeController.text,
  //       'user_email': email,
  //     };

  //     String jsonBody = json.encode(monumentoData);

  //     Uri url = Uri.parse('http://127.0.0.1:8000/services/monumentos');
  //     http.Response response = await http.post(
  //       url,
  //       headers: {
  //         'Content-Type': 'application/json',
  //       },
  //       body: jsonBody,
  //     );

  //     if (response.statusCode == 200) {
  //       // Se a solicitação for bem-sucedida, exiba uma mensagem de sucesso
  //         Navigator.push(
  //         context,
  //         MaterialPageRoute(
  //           builder: (context) => ConfirmationPage(
  //             confirmationText: '',
  //           ),
  //         ),
  //       );

  //       // Limpe o formulário
  //       _formKey.currentState!.reset();
  //     } else {
  //       // Se a solicitação falhar, exiba uma mensagem de erro
  //       ScaffoldMessenger.of(context).showSnackBar(SnackBar(
  //         content: Text('Ocorreu um erro ao criar o monumento.'),
  //       ));
  //     }
  //   }
  // }

  void _submitForm() {
  // Submit the form logic goes here

  // Show a pop-up message
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text('Formulário submetido!'),
        content: Text('O serviço de monumentos foi adicionado!'),
        actions: [
          TextButton(
            onPressed: () {
              // Navigate to another page
              Navigator.of(context).push(MaterialPageRoute(builder: (context) => FeaturesEmpresa()));
            },
            child: Text('OK'),
          ),
        ],
      );
    },
  );
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Formulário de monumento"),
      ),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Form(
          key: _formKey,
          child: ListView(
            children: <Widget>[
              _buildNameInput(),
              SizedBox(height: 20.0),
              _buildStoryInput(),
              SizedBox(height: 20.0),
              _buildStyleInput(),
              SizedBox(height: 20.0),
              _buildAccessabilityInput(),
              SizedBox(height: 20.0),
              _buildScheduleInput(),
              SizedBox(height: 20.0),
              _buildPriceInput(),
              SizedBox(height: 20.0),
              _buildActivityInput(),
              SizedBox(height: 20.0),
              _buildGuideVisitInput(),
              SizedBox(height: 20.0),
               _buildImagesInput(),
              SizedBox(height: 20.0),
              _buildLocationInput(),
              SizedBox(height: 20.0),
              ElevatedButton(
                onPressed: _submitForm,
                child: Text("Enviar"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}





