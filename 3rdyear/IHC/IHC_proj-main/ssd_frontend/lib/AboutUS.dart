import 'package:flutter/material.dart';
import 'package:ssd_frontend/Favoritos.dart';
import 'package:ssd_frontend/Interesses.dart';
import 'login/login_turista.dart';
class AboutUS extends StatelessWidget {
  const AboutUS({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBody: true,
      body:SafeArea(
        top:true,
        child: Material(
          child:Container(
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
                        case 'Interesses':
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
                        child: Text('Registro'),
                      ),
                      PopupMenuItem<String>(
                        value: 'AboutUS',
                        child: Text('Acerca de nós'),
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

                const SizedBox(width: 5),

                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => Favoritos(),
                      ),
                    );
                  },
                  child: Text(
                    "Favoritos",
                    style: TextStyle(
                      fontSize: 18,
                    ),
                  ),
                ),
                SizedBox(width: 10), // Adicionado aqui


                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => Interesses(),
                      ),
                    );
                  },
                  child: Text(
                    "Interesses",
                    style: TextStyle(
                      fontSize: 18,
                    ),
                  ),
                ),

                SizedBox(
                  width: 5,
                ),
                SizedBox(
                  width: 5,
                ),
                ElevatedButton(
                    onPressed: () {
                      Navigator.push(context, MaterialPageRoute(
                          builder: (context) => LoginTurista())
                      );
                    },
                    child: Text(
                      "Login",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    )),
                SizedBox(
                  width: 5,
                ),
                Text(
                  "Descubra aqui os serviços apropriados\npara o seu gosto!",
                  style: TextStyle(
                      fontSize: 21,
                      color: Colors.black
                  ),
                ),


              ],
            ),
          ),
        ),
      ),
    );
  }
}
