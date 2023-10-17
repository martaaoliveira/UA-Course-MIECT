import 'package:flutter/material.dart';
import 'package:ssd_frontend/servicos/criar_anuncio_alojamento.dart';
import 'package:ssd_frontend/servicos/criar_anuncio_hotelaria.dart';
import 'package:ssd_frontend/servicos/criar_anuncio_restaurante.dart';
import '../features_empresa/features_empresa.dart';
import '../formularios/forms_restauracao.dart';
import '../main.dart';
import '../noticias/feature_noticias.dart';

class ServicosDisponiveis extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          foregroundColor: Colors.white,
          shadowColor: Colors.transparent,
          title: const Text(
            "Serviços Disponíveis / Criar Anúncios",
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
                            Icons.home
                        ),
                        title: const Text(
                          "Notícias",
                          style: TextStyle(
                            fontSize: 18,
                          ),
                        ),
                        onTap: () {
                          Navigator.push(context, MaterialPageRoute(
                              builder: (context) => FeatureNoticias())
                          );
                        },
                      ),

                    ],
                  ),
              ),
            ],
          ),

        ),


        // ----------------------------------------- body ---------------------------------------------------

        body: Center(
          child: Column(
            children: [

              const SizedBox(
                height: 50,
              ),

              ElevatedButton(
                onPressed: () {
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => CriarAnuncioHotelaria())
                  );
                },
                // style: ButtonStyle(elevation: MaterialStateProperty(12.0 )),
                style: ElevatedButton.styleFrom(
                    elevation: 12.0,
                    textStyle: const TextStyle(
                        color: Colors.white,
                        fontSize: 25,
                    )
                ),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: const [
                    Icon(
                        Icons.hotel,
                        size: 45,
                        color: Colors.white
                    ),

                    SizedBox(
                      height: 5,
                    ),

                    Text('CRIAR ANÚNCIO - HOTELARIA',
                      style: TextStyle(
                          fontSize: 20,
                          fontFamily: 'Montserrat',
                          color: Colors.white
                      ),
                    ),
                  ],
                )
              ),

              SizedBox(
                height: 15,
              ),

              ElevatedButton(
                  onPressed: () {
                    Navigator.push(context, MaterialPageRoute(
                        builder: (context) => CriarAnuncioAlojamento())
                    );
                  },
                  // style: ButtonStyle(elevation: MaterialStateProperty(12.0 )),
                  style: ElevatedButton.styleFrom(
                      elevation: 12.0,
                      textStyle: const TextStyle(
                        color: Colors.white,
                        fontSize: 25,
                      )
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: const [
                      Icon(
                          Icons.house,
                          size: 45,
                          color: Colors.white
                      ),

                      SizedBox(
                        height: 5,
                      ),

                      Text('CRIAR ANÚNCIO - ALOJAMENTO LOCAL',
                        style: TextStyle(
                            fontSize: 20,
                            fontFamily: 'Montserrat',
                            color: Colors.white
                        ),
                      ),
                    ],
                  )
              ),

              SizedBox(
                height: 15,
              ),

              ElevatedButton(
                  onPressed: () {
                    // Navigator.push(context, MaterialPageRoute(
                    //     builder: (context) => FormRestaurante(title: 'Formulário - Criação de Serviço de Restauração',))
                    // );
                  },
                  // style: ButtonStyle(elevation: MaterialStateProperty(12.0 )),
                  style: ElevatedButton.styleFrom(
                      elevation: 12.0,
                      textStyle: const TextStyle(
                        color: Colors.white,
                        fontSize: 25,
                      )
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: const [
                      Icon(
                          Icons.local_cafe,
                          size: 45,
                          color: Colors.white
                      ),

                      SizedBox(
                        height: 5,
                      ),

                      Text('CRIAR ANÚNCIO - RESTAURAÇÃO',
                        style: TextStyle(
                            fontSize: 20,
                            fontFamily: 'Montserrat',
                            color: Colors.white
                        ),
                      ),
                    ],
                  )
              ),

            ],
          ),
        ),

        /*Container(
          constraints: BoxConstraints(maxWidth: 2000),
            child: Column(
              children: [
                Expanded(
                  child: ListView.builder(
                    itemCount: servicos.length,
                    itemBuilder: (context, index) {
                      return Expanded(
                        child: CartaoServicos(
                          index: index,
                          key: ValueKey(index),
                        ),
                      );
                    },
                  ),
                ),
              ],
            ),
        ),*/
      ),
    );
  }
}