import 'package:flutter/material.dart';
import 'package:ssd_frontend/Favoritos.dart';
import 'package:ssd_frontend/Interesses.dart';
import 'package:ssd_frontend/AboutUS.dart';
//import 'package:ssd_frontend/SearchScreen.dart';
import 'package:ssd_frontend/registo_empresas/registo.dart';
import 'package:ssd_frontend/registo_empresas/signUp_pessoa.dart';
import 'result_search.dart';
import 'home_screen.dart';

import 'features_empresa/features_empresa.dart';
import 'login/login_turista.dart';

class CustomAppBar_2 extends StatelessWidget with PreferredSizeWidget {
  final double _preferredHeight = 100.0;
  final Set<int> favorites;

  @override
  Size get preferredSize => Size.fromHeight(_preferredHeight);

  const CustomAppBar_2({Key? key, required this.favorites}) : super(key: key);


  void openSearch(BuildContext context) {
    String? selectedCategory; // valor padrão para a primeira categoria
    String? selectedDistrict; // valor padrão para o primeiro distrito

    final List<String> categories = [      'Restauração',      'Eventos',      'Praias',      'Turismo Local',      'Museus'    ];
    final List<String> districts = [      'Aveiro',      'Beja',      'Braga',      'Bragança',      'Castelo Branco',      'Coimbra',      'Évora',      'Faro',      'Guarda',      'Leiria',      'Lisboa',      'Portalegre',      'Porto',      'Santarém',      'Setúbal',      'Viana do Castelo',      'Vila Real',      'Viseu'    ];

    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Pesquisar'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              DropdownButtonFormField<String>(
                decoration: InputDecoration(
                  labelText: 'Distrito',
                ),
                items: districts.map((String district) {
                  return DropdownMenuItem<String>(
                    value: district,
                    child: Text(district),
                  );
                }).toList(),
                onChanged: (String? value) {
                  selectedDistrict = value;
                },
                value: selectedDistrict,
              ),
              TextField(
                decoration: InputDecoration(
                  labelText: 'Concelho',
                ),
              ),
              DropdownButtonFormField<String>(
                decoration: InputDecoration(
                  labelText: 'Categoria',
                ),
                items: categories.map((String category) {
                  return DropdownMenuItem<String>(
                    value: category,
                    child: Text(category),
                  );
                }).toList(),
                onChanged: (String? value) {
                  selectedCategory = value;
                },
                value: selectedCategory,
              ),
            ],
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Cancelar'),
            ),
            ElevatedButton(
              onPressed: () {
                // Adicione aqui a lógica de pesquisa com base nos inputs do usuário
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (context) => ResultSearch(),
                  ),
                );
              },
              child: Text('Pesquisar'),
            ),
          ],
        );
      },
    );
  }

//  @override
//   Widget build(BuildContext context) {
//     //TextEditingController searchController = TextEditingController();
//     return Scaffold(
//       appBar: AppBar(
//         backgroundColor: Colors.blue[500],
//         leading: IconButton(
//           icon: Icon(Icons.menu),
//           onPressed: () {
//             // TODO: Add menu functionality
//           },
//         ),
//         title: Row(
//           children: [
//             Image.asset(
//               "assets/icons/icon_app.png",
//               height: 40,
//               alignment: Alignment.topLeft,
//             ),
//             SizedBox(width: 10),
//             Text(
//               'LusiTravel',
//               style: TextStyle(
//                 fontSize: 24,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//           ],
//         ),
//         actions: [

//   IconButton(
//     icon: Icon(Icons.search),
//     onPressed: () {
//       openSearch(context);
//     },
//   ),
//   IconButton(
//     icon: Stack(
//       children: [
//         Icon(Icons.favorite),
//         favorites.length > 0
//             ? Positioned(
//                 right: 0,
//                 child: Container(
//                   padding: EdgeInsets.all(1),
//                   decoration: BoxDecoration(
//                     color: Colors.red,
//                     borderRadius: BorderRadius.circular(6),
//                   ),
//                   constraints: BoxConstraints(
//                     minWidth: 12,
//                     minHeight: 12,
//                   ),
//                   child: Text(
//                     '${favorites.length}',
//                     style: TextStyle(
//                       color: Colors.white,
//                       fontSize: 8,
//                     ),
//                     textAlign: TextAlign.center,
//                   ),
//                 ),
//               )
//             : SizedBox(),
//       ],
//     ),
//     onPressed: () {
//       Navigator.of(context).push(
//         MaterialPageRoute(
//           builder: (context) => Favoritos(),
//         ),
//       );
//     },
//   ),
//   IconButton(
//     icon: Icon(Icons.bookmark),
//     onPressed: () {
//       // TODO: Add Interesses functionality
//     },
//   ),
//   IconButton(
//     icon: Icon(Icons.login),
//     onPressed: () {
//       // TODO: Add Login functionality
//     },
//   ),
// ],

//         bottom: PreferredSize(
//           preferredSize: Size.fromHeight(10),
//           child: Container(
//             height: 10,
//             color: Colors.blueGrey[000],
//           ),
//         ),
//       ),
//     //   body: Center(
//     //     child: Text('My App Home Page'),
//     //   ),
//     );
//   }
// }
@override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.only(top: 5),

      margin: const EdgeInsets.all(20),
      //padding: const EdgeInsets.all(20),
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
                  case 'Registro':
                  // Adicione a navegação para a página de registro aqui
                    break;
                }
              },

              itemBuilder: (BuildContext context) => <PopupMenuEntry<String>>[
                PopupMenuItem<String>(
                  value: 'favoritos',
                  child: Text('Favoritos'),
                ),
                PopupMenuItem<String>(
                  value: 'interesses',
                  child: Text('Interesses'),
                ),
                PopupMenuItem<String>(
                  value: 'Login',
                  child: Text('Login'),
                ),
                PopupMenuItem<String>(
                  value: 'Registro',
                  child: Text('Criar Conta'),
                ),
                PopupMenuItem<String>(
                  value: 'AboutUS',
                  child: Text('LusiTravel'),
                ),
              ],
              child: Icon(Icons.menu), // ícone do menu

            ),
          ),
          GestureDetector(
  onTap: () {
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(builder: (context) => homeScreen()),
    );
  },
  child: Padding(
    padding: const EdgeInsets.only(left: 0),
    child: Image.asset(
      "assets/icons/icon_app.png",
      height: 40,
      alignment: Alignment.topLeft,
    ),
  ),
),


          const SizedBox(width: 10),
          Expanded(
            child: GestureDetector(
              onTap: () {
                openSearch(context);
              },
              child: Container(
                margin: const EdgeInsets.only(left: 20),
                padding: const EdgeInsets.symmetric(horizontal: 16),
                decoration: BoxDecoration(
                  color: Colors.grey.withOpacity(0.2),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: Row(
                  children: [
                    Expanded(
                      child: const Text(
                        "Pesquisar",
                        style: TextStyle(fontSize: 18),
                      ),
                    ),
                    const Icon(Icons.search),
                  ],
                ),
              ),
            ),
          ),
          SizedBox(
            width: 10,
          ),



//           ElevatedButton(
//   onPressed: () {
//     Navigator.push(
//       context,
//       MaterialPageRoute(
//         builder: (context) => Favoritos(),
//       ),
//     );
//   },
//   child: Stack(
//     alignment: Alignment.center,
//     children: [
//       Text(
//         "Favoritos",
//         style: TextStyle(
//           fontSize: 18,
//         ),
//       ),
//       if (favorites.isNotEmpty)
//         Positioned(
//           top: 0,
//           right: 0,
//           child: Container(
//             padding: EdgeInsets.all(2),
//             decoration: BoxDecoration(
//               shape: BoxShape.circle,
//               color: Colors.red,
//             ),
//             child: Text(
//               "${favorites.length}",
//               style: TextStyle(
//                 color: Colors.white,
//                 fontSize: 12,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//           ),
//         ),
//     ],
//   ),
// ),
Tooltip(
  message: 'Favoritos',
  child: IconButton(
    icon: Stack(
      children: [
        Icon(Icons.favorite, color: Colors.blue[500]),
        favorites.length > 0
            ? Positioned(
                right: 0,
                child: Container(
                  padding: EdgeInsets.all(1),
                  decoration: BoxDecoration(
                    color: Colors.red,
                    borderRadius: BorderRadius.circular(6),
                  ),
                  constraints: BoxConstraints(
                    minWidth: 12,
                    minHeight: 12,
                  ),
                  child: Text(
                    '${favorites.length}',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 8,
                    ),
                    textAlign: TextAlign.center,
                  ),
                ),
              )
            : SizedBox(),
      ],
    ),
    onPressed: () {
      Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) => Favoritos(),
        ),
      );
    },
  ),
),
Tooltip(
  message: 'Interesses',
  child: IconButton(
    icon: Icon(Icons.bookmark, color: Colors.blue[500]),
    onPressed: () {
      // TODO: Add Interesses functionality
    },
  ),
),
Tooltip(
  message: 'Log-out',
  child: IconButton(
    icon: Icon(Icons.login, color: Colors.blue[500]),
    onPressed: () {
      // TODO: Add Login functionality
    },
  ),
),
          SizedBox(
            width: 5,
          ),

        ],
      ),
    );
  }
}
