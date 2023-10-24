import 'dart:io';
import 'package:ssd_frontend/Empresas/homeScreentwo.dart';
import 'package:ssd_frontend/features_empresa/features_empresa.dart';
import 'package:ssd_frontend/formularios/PromoQuestion.dart';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:file_picker/file_picker.dart';
//import 'package:font_awesome_flutter/font_awesome_flutter.dart';
//import 'package:geoflutterfire/geoflutterfire.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:firebase_auth/firebase_auth.dart';
import 'dart:typed_data';


import 'ConfirmationPage.dart';

class RestaurantForm extends StatefulWidget {
  @override
  _RestaurantFormState createState() => _RestaurantFormState();
}

class _RestaurantFormState extends State<RestaurantForm> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nameController = TextEditingController();
  TextEditingController _tipoEstabelecimentoController = TextEditingController();
  TextEditingController _imagesController = TextEditingController();
  TextEditingController _hoursController = TextEditingController();
  TextEditingController _descriptionController = TextEditingController();
  // TextEditingController _locationController = TextEditingController();
  TextEditingController _latitudeController = TextEditingController();
  TextEditingController _longitudeController = TextEditingController();
  TextEditingController _promoController = TextEditingController();
  // List<File> _imageList = [];
  List<String> _images_ementas_string_list = []; 
  List<File> _imageEmentaList = [];
  List<File> _imageList = [];

  List<String> _imageDescriptionList = [];
  List<String> _imageStringList = [];
  final _picker = ImagePicker();

  final String url = 'http://127.0.0.1:8000/services/restaurants';
  final List<String> _restaurantTypes = ["Restaurante", "Café", "Bar", "Snack-Bar", "Salão de chá", "Food Truck", "Self-service"];

  List<String> _selectedTypes = [];

   @override
  void initState() {
    super.initState();
    _nameController.text = 'Sal na brasa';
    _selectedTypes.add('Restaurante');
    _latitudeController.text = 'Avenida Lourenço Peixinho';
  }

  void _toggleType(String type) {
    setState(() {
      if (_selectedTypes.contains(type)) {
        _selectedTypes.remove(type);
      } else {
        _selectedTypes.add(type);
      }
    });
  }

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


  Future<void> _getImage_ementa(ImageSource source) async {
    final pickedFile = await ImagePicker().getImage(source: source);
    if (pickedFile != null) {
      final bytes = await pickedFile.readAsBytes();
      final encodedImage = base64Encode(bytes); // Convert bytes to base64 encoded string
      setState(() {
        _imageEmentaList.add(File(pickedFile.path));
        _images_ementas_string_list.add(encodedImage); // Add encoded image string to the list
        //_imageDescriptionList.add(''); // Add an empty string to the list
      });
    }
  }
  /*Future _getPDF() async {
    final result = await FilePicker.platform.pickFiles(
      type: FileType.custom,
      allowedExtensions: ['pdf'],
    );
    setState(() {
      if (result != null) {
        _imageList.add(File(result.files.single.path!));
        _imageDescriptionList.add("");
        print('PDF done');
      }
    });
  }*/

  Widget _buildNameInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Nome do restaurante"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _nameController,
          decoration: InputDecoration
            (
            border: OutlineInputBorder(),
            hintText: "Escreva o nome do restaurante",
          ),
          validator: (value) {
            if (value == "") {
              return "Escreva o nome do restaurante";
            }
            return null;
          },
        ),
      ],
    );
  }

  bool _isSelectedTypesValid = true;

Widget _buildRestaurantTypeInput() {
  return Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: <Widget>[
      Text("Tipo de estabelecimento", style: TextStyle(color: _isSelectedTypesValid ? null : Colors.red)),
      SizedBox(height: 10.0),
      Column(
        children: _restaurantTypes
            .map(
              (type) => Row(
                children: <Widget> [
                  Checkbox(
                    value: _selectedTypes.contains(type),
                    onChanged: (value){
                      setState(() {
                        if(value!){
                          _selectedTypes.add(type);
                        }
                        else{
                          _selectedTypes.remove(type);
                        }
                        _isSelectedTypesValid = true; // Reset the validation state
                      });
                    }
                ),
                Text(type),
              ],
            ),
          )
          .toList(),
      ),
      if (!_isSelectedTypesValid) Text("Selecione pelo menos uma opção", style: TextStyle(color: Colors.red)),
    ],
  );
}


  Widget _buildImagesEmentaInput() {
  return Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: <Widget>[
      Text("Ementa"),
      SizedBox(height: 10.0),
      ElevatedButton(
        onPressed: () {
          _getImage_ementa(ImageSource.gallery);
        },
        child: Text("Inserir imagem da ementa"),
      ),
      SizedBox(height: 10.0),
      if (_imageEmentaList.isNotEmpty)
        Column(
          children: List.generate(_imageEmentaList.length, (index) {
            return Column(
              children: [
                SizedBox(height: 10.0),
                Row(
                  children: [
                    Image.network(
                      _imageEmentaList[index].path,
                      height: 100.0,
                      width: 100.0,
                    ),
                    // SizedBox(width: 10.0),
                    // Expanded(
                    //   child: TextFormField(
                    //     onChanged: (value) {
                    //       setState(() {
                    //         _imageDescriptionList[index] = value;
                    //       });
                    //     },
                    //     decoration: InputDecoration(
                    //       hintText: "Insira uma descrição para esta imagem",
                    //       border: OutlineInputBorder(),
                    //     ),
                    //   ),
                    // ),
                  ],
                ),
              ],
            );
          }),
        ),
    ],
  );
}





  Widget _buildHoursInput() {
    return Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text("Horário de funcionamento"),
          SizedBox(height: 10.0),
          TextFormField(
            controller: _hoursController,
            decoration: InputDecoration
            (
              border: OutlineInputBorder(),
              hintText: "Insira o horário de funcionamento: <hh:mn -- hh:mn>",
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

  Widget _buildDescriptionInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Descrição do ambiente"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _descriptionController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira uma descrição do ambiente",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira uma descrição do ambiente";
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
        child: Text("Inserir imagem do local"),
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

  Widget _buildPromoInput() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Promoções / ofertas especiais"),
        SizedBox(height: 10.0),
        TextFormField(
          controller: _promoController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Insira as promoções / ofertas especiais",
          ),
          validator: (value) {
            if (value == "") {
              return "Insira as promoções / ofertas especiais";
            }
            return null;
          },
        ),
      ],
    );
  }

@override
Widget build(BuildContext context) {
  return Scaffold(
    appBar: AppBar(
      title: Text("Formulário de restaurante"),
    ),
    body: Padding(
      padding: EdgeInsets.all(20.0),
      child: Form(
        key: _formKey,
        child: ListView(
          children: <Widget>[
            _buildNameInput(),
            SizedBox(height: 20.0),
            _buildRestaurantTypeInput(),
            SizedBox(height: 16.0),
            _buildImagesEmentaInput(),
            SizedBox(height: 20.0),
            _buildHoursInput(),
            SizedBox(height: 20.0),
            _buildDescriptionInput(),
            SizedBox(height: 20.0),
            _buildImagesInput(),
            SizedBox(height: 20.0),
            _buildLocationInput(),
            // SizedBox(height: 20.0),
            // _buildPromoInput(),
            SizedBox(height: 20.0),
            ElevatedButton(
              onPressed: _submitForm, // Updated here
              child: Text("Enviar"),
            ),
          ],
        ),
      ),
    ),
  );
}

// void _submitForm() async {
//   if (_formKey.currentState!.validate()) {
//     Get the currently logged-in user's email
//     final user = FirebaseAuth.instance.currentUser;
//     final email = user?.email ?? "";
//     print(_imageDescriptionList);

//     Encode the form data and user email as a JSON object
//     final data = json.encode({
//       'name': _nameController.text,
//       'tipoEstabelecimento': _selectedTypes,
//       'ementa': _images_ementas_string_list,
//       'hours': _hoursController.text,
//       'description': _descriptionController.text,
//       'images': _imageStringList,
//       'imageDescriptions': _imageDescriptionList,
//       'location': _locationController.text,
//       'latitude': _latitudeController.text,
//       'longitude': _longitudeController.text,
//       'promo': _promoController.text,
//       'email': email,
//     });

//     Send the form data to the server
//     final response = await http.post(
//       Uri.parse(url),
//       body: data,
//     );

//     if (response.statusCode == 200) {
//       Success: navigate to the confirmation page
//       Navigator.push(
//         context,
//         MaterialPageRoute(
//           builder: (context) => ConfirmationPage(
//             confirmationText: '',
//           ),
//         ),
//       );
//     } else {
//       Error: display an error dialog
//       showDialog(
//         context: context,
//         builder: (BuildContext context) {
//           return AlertDialog(
//             title: Text('Erro'),
//             content: Text('Não foi possível enviar os dados do formulário.'),
//             actions: <Widget>[
//               TextButton(
//                 child: Text('Fechar'),
//                 onPressed: () {
//                   Navigator.of(context).pop();
//                 },
//               ),
//             ],
//           );
//         },
//       );
//     }
//   }
// }

void _submitForm() {
  // Submit the form logic goes here

  // Show a dialog to ask the user if they want to add promotions
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text('Formulário submetido!'),

        content: Text('Deseja adicionar promoções?'),

        // content: Text('A publicidade foi adicionada com sucesso!'),

        actions: [
          TextButton(
            onPressed: () {
              // Navigate to the PromoQuestion page
              Navigator.of(context).pop(); // Close the dialog
              Navigator.of(context).push(
                MaterialPageRoute(builder: (context) => PromoQuestion()),
              );
            },
            child: Text('Sim'),
          ),
          TextButton(
            onPressed: () {
              // Navigate to the Profile page
              Navigator.of(context).pop(); // Close the dialog
              Navigator.of(context).push(
                MaterialPageRoute(builder: (context) => FeaturesEmpresa()),
              );
            },
            child: Text('Não'),
          ),
        ],
      );
    },
  );
}



  Widget _buildSubmitButton() {
    return ElevatedButton(
  onPressed: _submitForm,
  child: Text("Enviar"),
);

  }
}
