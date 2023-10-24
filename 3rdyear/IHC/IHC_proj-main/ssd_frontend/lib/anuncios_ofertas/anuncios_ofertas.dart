import 'package:flutter/material.dart';

class VerAnuncios extends StatefulWidget {
  const VerAnuncios({Key? key}) : super(key: key);

  @override
  _VerAnunciosState createState() => _VerAnunciosState();
}

class _VerAnunciosState extends State<VerAnuncios> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        shadowColor: Colors.transparent,
        title: const Text(
          "Anúncios das Ofertas",
          style: TextStyle(
              fontSize: 20,
              color: Colors.white
          ),
        ),
      ),
    );
  }

}

