import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:file_picker/file_picker.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:file_picker/file_picker.dart';
import 'package:http/http.dart' as http;
import 'package:firebase_auth/firebase_auth.dart';
import 'dart:typed_data';
import 'ConfirmationPage.dart';
import 'package:ssd_frontend/Empresas/homeScreentwo.dart';
import 'package:ssd_frontend/features_empresa/features_empresa.dart';




class AlojamentoForm extends StatefulWidget {
  @override
  _AlojamentoFormState createState() => _AlojamentoFormState();
}

class _AlojamentoFormState extends State<AlojamentoForm> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nameController = TextEditingController();
  TextEditingController _descriptionController = TextEditingController();
  TextEditingController _bedroomTypeController = TextEditingController();
  TextEditingController _bedroomPricesController = TextEditingController();
  TextEditingController _servicesController = TextEditingController();
  TextEditingController _imagesController = TextEditingController();
  TextEditingController _promosController = TextEditingController();
  // TextEditingController _locationController = TextEditingController();
  TextEditingController _latitudeController = TextEditingController();
  TextEditingController _longitudeController = TextEditingController();
 

  List<String> _imageDescriptionList = [];
  final _picker = ImagePicker();


  
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
        Text("Nome do alojamento"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _nameController,
          decoration: InputDecoration
            (
            border: OutlineInputBorder(),
            hintText: "Escreva o nome do alojamento",
          ),
          validator: (value) {
            if (value == "") {
              return "Escreva o nome do alojamento";
            }
            return null;
          },
        ),
      ],
    );
  }


  Widget _buildDescriptionInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Descrição do alojamento"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _descriptionController,
          decoration: InputDecoration
            (
            border: OutlineInputBorder(),
            hintText: "Escreva uma descrição do alojamento",
          ),
          validator: (value) {
            if (value == "") {
              return "Escreva uma descrição do alojamento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildBedroomTypeInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Tipo de quartos"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _bedroomTypeController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Indique o tipo de quartos do alojamento",
          ),
          validator: (value) {
            if (value == "") {
              return "Indique o tipo de quartos do alojamento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildBedroomPricesInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Preço dos quartos"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _bedroomPricesController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Indique o preço dos quartos do alojamento",
          ),
          validator: (value) {
            if (value == "") {
              return "Indique o preço dos quartos do alojamento";
            }
            return null;
          },
        ),
      ],
    );
  }

  Widget _buildServicesInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Serviços disponíveis"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _servicesController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Indique os serviços disponiveis no alojamento",
          ),
          validator: (value) {
            if (value == "") {
              return "Indique os serviços disponiveis no alojamento";
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
        child: Text("Inserir imagem do alojamento"),
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

  Widget _buildPromosInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Promoções"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _promosController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira as promoções disponiveis",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira as promoções disponiveis";
            }
            return null;
          },
        ),
      ],
    );
  }

  // void _saveAlojamento() async {
  //   //retriece user's email, this we can know to who the service belongs
  //   final user = FirebaseAuth.instance.currentUser;
  //   final email = user?.email ?? "";

  //   if (_formKey.currentState!.validate()) {
  //     // List<List<int>> imageBytesList = [];
  //     // for (File image in _imageList) {
  //     //   final bytes = await image.readAsBytes();
  //     //   imageBytesList.add(bytes);
  //     // }

  //     List<Map<String, dynamic>> imageDescriptionList = [];
  //     for (String description in _imageDescriptionList) {
  //       imageDescriptionList.add({'description': description});
  //     }

  //     Map<String, dynamic> alojamentoData = {
  //       'name': _nameController.text,
  //       'description': _descriptionController.text,
  //       'bedroom_type': _bedroomTypeController.text,
  //       'bedroom_prices': _bedroomPricesController.text,
  //       'services': _servicesController.text,
  //       // 'location': _locationController.text,
  //       'latitude': _latitudeController.text,
  //       'longitude': _longitudeController.text,
  //       'images': _imageStringList,
  //       'image_descriptions': imageDescriptionList,
  //       'user_email': email,
  //     };

  //     String jsonBody = json.encode(alojamentoData);

  //     Uri url = Uri.parse('http://127.0.0.1:8000/services/alojamento');
  //     http.Response response = await http.post(
  //       url,
  //       headers: {
  //         'Content-Type': 'application/json',
  //       },
  //       body: jsonBody,
  //     );

  //     if (response.statusCode == 200) {
  //       // Se a solicitação for bem-sucedida, exiba uma mensagem de sucesso
  //           Navigator.push(
  //           context,
  //           MaterialPageRoute(
  //             builder: (context) => ConfirmationPage(
  //               confirmationText: '',
  //             ),
  //           ),
  //         );

  //       // Limpe o formulário e a lista de imagens
  //       _formKey.currentState!.reset();
  //       setState(() {
  //         _imageList.clear();
  //         _imageDescriptionList.clear();
  //       });
  //     } else {
  //       // Se a solicitação falhar, exiba uma mensagem de erro
  //       ScaffoldMessenger.of(context).showSnackBar(SnackBar(
  //         content: Text('Ocorreu um erro ao criar o alojamento.'),
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
        content: Text('O serviço de alojamentos foi adicionado!'),
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
        title: Text("Formulário do alojamento"),
      ),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Form(
          key: _formKey,
          child: ListView(
            children: <Widget>[
              _buildNameInput(),
              SizedBox(height: 20.0),
              _buildDescriptionInput(),
              SizedBox(height: 20.0),
              _buildBedroomTypeInput(),
              SizedBox(height: 20.0),
              _buildBedroomPricesInput(),
              SizedBox(height: 20.0),
              _buildServicesInput(),
              SizedBox(height: 20.0),
              _buildImagesInput(),
              SizedBox(height: 20.0),
              _buildPromosInput(),
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