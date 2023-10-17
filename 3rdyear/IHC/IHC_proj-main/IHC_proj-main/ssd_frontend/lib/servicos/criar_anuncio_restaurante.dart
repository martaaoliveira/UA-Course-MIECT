import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';

class CriarAnuncioRestaurante extends StatefulWidget {
  const CriarAnuncioRestaurante({Key? key}) : super(key: key);

  @override
  _CriarAnuncioRestauranteState createState() => _CriarAnuncioRestauranteState();
}

class _CriarAnuncioRestauranteState extends State<CriarAnuncioRestaurante> {

  XFile? image;

  final ImagePicker picker = ImagePicker();

  //we can upload image from camera or from gallery based on parameter
  Future getImage(ImageSource media) async {
    var img = await picker.pickImage(source: media);

    setState(() {
      image = img;
    });
  }

  //show popup dialog
  void myAlert() {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            shape:
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
            title: Text('Selecione onde deseja procurar as imagens'),
            content: Container(
              height: MediaQuery.of(context).size.height / 6,
              child: Column(
                children: [
                  ElevatedButton(
                    //if user click this button, user can upload image from gallery
                    onPressed: () {
                      Navigator.pop(context);
                      getImage(ImageSource.gallery);
                    },
                    child: Row(
                      children: [
                        Icon(Icons.image),
                        Text('Da Galeria'),
                      ],
                    ),
                  ),
                  ElevatedButton(
                    //if user click this button. user can upload image from camera
                    onPressed: () {
                      Navigator.pop(context);
                      getImage(ImageSource.camera);
                    },
                    child: Row(
                      children: [
                        Icon(Icons.camera),
                        Text('Através de Câmara'),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          );
        });
  }

  bool valuefirst = false;
  bool valuesecond = false;
  bool valuethree = false;
  bool valuefour = false;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.blue,
            foregroundColor: Colors.white,
            shadowColor: Colors.transparent,
            title: const Text(
              "Criar Anúncio - Restauração",
              style: TextStyle(
                  fontSize: 20,
                  color: Colors.white
              ),
            ),
          ),

          // -------------------------------------------- body -----------------------------------------------

          body:  SingleChildScrollView(
            child: Padding(
              padding: EdgeInsets.all(16),
              child: Form(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [

                    Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        ElevatedButton(
                          onPressed: () {
                            myAlert();
                          },
                          child: Text('Upload Photo'),
                        ),

                        SizedBox(height: 10,),

                        image != null
                            ? Padding(
                              padding: const EdgeInsets.symmetric(horizontal: 20),
                                child: ClipRRect(
                                  borderRadius: BorderRadius.circular(8),
                                  child: Image.file(
                                    File(image!.path),
                                    fit: BoxFit.cover,
                                    width: MediaQuery.of(context).size.width,
                                    height: 300,
                                  ),
                                ),
                              )
                              : Text(
                                "Nenhuma imagem foi selecionada",
                                style: TextStyle(fontSize: 18),
                              ),
                      ],
                    ),

                    TextFormField(
                      decoration: InputDecoration(labelText: 'Introduzir título'),
                      validator: (String? value) {
                        if (value!.isEmpty) {
                          return 'Por favor, insira o título do anúncio';
                        }
                        return null;
                      },
                      onSaved: (String? value) {},
                    ),

                    TextFormField(
                      decoration: InputDecoration(labelText: 'Contacto'),
                      validator: (String? value) {
                        if (value!.isEmpty) {
                          return 'Por favor, insira o contacto';
                        }
                        return null;
                      },
                      onSaved: (String? value) {},
                    ),

                    TextFormField(
                      decoration: InputDecoration(labelText: 'Descrição do anúncio'),
                      validator: (String? value) {
                        if (value!.isEmpty) {
                          return 'Por favor, insira a descrição';
                        }
                        return null;
                      },
                      onSaved: (String? value) {},
                    ),

                    const SizedBox(
                      height: 20,
                    ),

                    Column(
                      children: [

                        const Text(
                          "Tipos de serviços disponíveis",
                          style: TextStyle(
                            fontSize: 20,
                          ),
                        ),

                        CheckboxListTile(
                          title: const Text("Takeaway"),
                            value: valuefirst,
                            onChanged: (bool? value) {
                              setState(() {
                                valuefirst = value!;
                              });
                            },
                        ),

                        CheckboxListTile(
                          title: const Text("Self-service"),
                          value: valuesecond,
                          onChanged: (bool? value) {
                            setState(() {
                              valuesecond = value!;
                            });
                          },
                        ),

                        CheckboxListTile(
                          title: const Text("Serviço à mesa"),
                          value: valuethree,
                          onChanged: (bool? value) {
                            setState(() {
                              valuethree = value!;
                            });
                          },
                        ),

                        CheckboxListTile(
                          title: const Text("Bar aberto"),
                          value: valuefour,
                          onChanged: (bool? value) {
                            setState(() {
                              valuefour = value!;
                            });
                          },
                        ),

                      ],
                    ),

                  ],
                ),
              ),
            ),
          ),


        ),
    );

  }

}