import 'package:flutter/material.dart';
import 'package:ssd_frontend/servicos/servicos_disponiveis.dart';

import '../features_empresa/features_empresa.dart';
import '../main.dart';

class FeatureNoticias extends StatefulWidget {
  const FeatureNoticias({Key? key}) : super(key: key);

  @override
  _FeatureNoticiasState createState() => _FeatureNoticiasState();
}

class _FeatureNoticiasState extends State<FeatureNoticias> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.deepPurpleAccent,
        foregroundColor: Colors.white,
        shadowColor: Colors.transparent,
        title: const Text(
          "Alertas / Notícias",
          style: TextStyle(
              fontSize: 20,
              color: Colors.white
          ),
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
                    child: Image(image: AssetImage("assets/icons/icon_app.png"),
                    ),
                  ),

                  ListTile(
                    leading: const Icon(
                        Icons.home
                    ),
                    title: const Text(
                      "Voltar para a Página Principal",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    onTap: () {
                      Navigator.push(context, MaterialPageRoute(
                          builder: (context) => Destinos())
                      );
                    },
                  ),

                  ListTile(
                    leading: const Icon(
                        Icons.home
                    ),
                    title: const Text(
                      "Área da Empresa",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    onTap: () {
                      Navigator.push(context, MaterialPageRoute(
                          builder: (context) => FeaturesEmpresa())
                      );
                    },
                  ),

                  ListTile(
                    leading: const Icon(
                        Icons.dataset_rounded
                    ),
                    title: const Text(
                      "Serviços Disponíveis",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                    onTap: () {
                      Navigator.push(context, MaterialPageRoute(
                          builder: (context) => ServicosDisponiveis())
                      );
                    },
                  ),



                ],
              ),
            ),

          ],
        ),
      ),
    );
  }

}