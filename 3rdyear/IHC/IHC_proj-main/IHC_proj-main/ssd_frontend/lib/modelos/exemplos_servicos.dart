import 'package:flutter/material.dart';

class Servico {
  final int id;
  final String title, image;
  final Color color;

  Servico(
      this.id,
      this.title,
      this.image,
      this.color
      );
}

// Demo list de serviços
List<Servico> servicos = [
  Servico(
       1,
      "Hotelaria",
      "assets/servicos_icons/hotel-png.png",
      Color(0xFFD9FFFC),
  ),

  Servico(
    2,
    "Restauração",
    "assets/servicos_icons/restaurante.png",
    Color(0xFFD9FFFC),
  ),

  Servico(
    1,
    "Bar de Praia",
    "assets/servicos_icons/beach-bar.png",
    Color(0xFFD9FFFC),
  ),
];



