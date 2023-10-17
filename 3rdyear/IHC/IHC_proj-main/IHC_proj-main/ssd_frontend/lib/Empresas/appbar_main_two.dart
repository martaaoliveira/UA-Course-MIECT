import 'package:flutter/material.dart';
import 'package:ssd_frontend/Favoritos.dart';
import 'package:ssd_frontend/Interesses.dart';
import 'package:ssd_frontend/AboutUS.dart';
import 'package:ssd_frontend/Empresas/RegistoEmpresaPage.dart';
import 'package:ssd_frontend/features_empresa/features_empresa.dart';
import 'package:ssd_frontend/login/login_turista.dart';
import 'package:ssd_frontend/Empresas/ServicosDisponiveis.dart';
 
class CustomAppBar2 extends StatelessWidget {
  const CustomAppBar2 ({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(20),
      padding: const EdgeInsets.all(20),
      // padding: const EdgeInsets.fromLTRB(10, 20, 20, 20), // ajuste aqui
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(46),
        boxShadow: [
          BoxShadow(
            offset: const Offset(0, -2),
            blurRadius: 30,
            color: Colors.black.withOpacity(0.16),
          ),
        ],
      ),


      child: Row(

        children: [

          Container(
            width: 40,
            alignment: Alignment.centerLeft,
            child: PopupMenuButton<String>(

              onSelected: (value) {
                switch (value) {
                  case 'Favoritos':
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Favoritos()),
                    );
                    break;
                  case 'AboutUS':
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => AboutUS()),
                    );
                    break;
                  case 'interesses':
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Interesses()),
                    );
                    break;
                  case 'Login':
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => LoginTurista()),
                    );
                    break;
                  case 'Registo Empresa':
                  // Adicione a navegação para a página de registro aqui
                    break;
                  case  'Area Empresa':
                    //
                }
              },

              itemBuilder: (BuildContext context) => <PopupMenuEntry<String>>[
                PopupMenuItem<String>(
                  value: 'Area empresa',
                  child: Text('Área Empresa'),
                ),
                PopupMenuItem<String>(
                  value: 'Login',
                  child: Text('Login'),
                ),
                PopupMenuItem<String>(
                  value: 'Registo Empresa',
                  child: Text('Registo Empresa'),
                ),
                PopupMenuItem<String>(
                  value: 'AboutUS',
                  child: Text('LusiTravel'),
                ),
              ],
              child: Icon(Icons.menu), // ícone do menu

            ),

          ),
          Padding(
            padding: const EdgeInsets.only(left: 0),
            child: Image.asset(
              "assets/icons/icon_app.png",
              height: 40,
              alignment: Alignment.topLeft,
            ),
          ),





          const SizedBox(width: 5),
          Expanded(
            child: Container(
              margin: const EdgeInsets.only(left: 10),
              padding: const EdgeInsets.symmetric(horizontal: 16),
              decoration: BoxDecoration(
                color: Colors.grey.withOpacity(0.2),
                borderRadius: BorderRadius.circular(10),
              ),
              child: TextField(
                decoration: InputDecoration(
                  hintText: "Pesquisar",
                  border: InputBorder.none,
                  suffixIcon: Icon(Icons.search),
                ),
                style: TextStyle(fontSize: 18),
              ),
            ),
          ),
          SizedBox(
            width: 5,
          ),


          /*
          ElevatedButton(
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => RegistoEmpresaPage())
                );
              },
              child: Text(
                "Registo de Empresa",
                style: TextStyle(
                  fontSize: 18,
                ),
              )),
          */


          SizedBox(width: 10), // Adicionado aqui

          SizedBox(
            width: 5,
          ),

          SizedBox(
            width: 5,
          ),
          ElevatedButton(
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => ServicosDisponiveis())
                );
              },
              child: Text(
                "Criar Publicidade",
                style: TextStyle(
                  fontSize: 18,
                ),
              )),
          SizedBox(
            width: 5,
          ),

          ElevatedButton(
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => RegistoEmpresaPage(),
                ),
              );
            },
            child: Text(
              "Registo Empresa",
              style: TextStyle(
                fontSize: 18,
              ),
            ),
          ),

          SizedBox(
            width: 5,
          ),
          ElevatedButton(
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => FeaturesEmpresa())
                );
              },
              child: Text(
                "Área da Empresa",
                style: TextStyle(
                  fontSize: 18,
                ),
              )),

        ],
      ),
    );
  }
}
