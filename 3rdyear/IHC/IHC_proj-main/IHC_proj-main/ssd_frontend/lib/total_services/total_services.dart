import 'package:flutter/material.dart';
import 'package:ssd_frontend/main.dart';
import 'package:ssd_frontend/noticias/feature_noticias.dart';
import 'package:ssd_frontend/servicos/servicos.dart';
import 'package:ssd_frontend/total_services/services_package.dart';

class TodosServicos extends StatefulWidget {
  const TodosServicos({Key? key}) : super(key: key);

  @override
  _TodosServicosState createState() => _TodosServicosState();
}

class _TodosServicosState extends State<TodosServicos> {

  @override
  Widget build(BuildContext context) {

    Size size = MediaQuery.of(context).size;

    return Scaffold(

      appBar: AppBar(
        backgroundColor: Colors.white,
        foregroundColor: const Color.fromRGBO(44, 73, 108, 1.0),
        shadowColor: Colors.transparent,
        title: const Text(
          "Todos os serviços",
          style: TextStyle(
            fontSize: 24,
          ),
        ),
      ),

      body: Padding(
        padding: const EdgeInsets.fromLTRB(8, 0, 8, 0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(11.0),
              child: Container(
                height: 50,
                width: double.infinity,
                decoration: BoxDecoration(
                    color: const Color(0xFFEFEDEE),
                    borderRadius: BorderRadius.circular(10.0),
                    boxShadow: const [
                      BoxShadow(
                          color: Colors.black12,
                          offset: Offset(0.0, 10.0),
                          blurRadius: 10.0)
                    ]),
                child: Row(
                  children: [
                    const Padding(
                      padding: EdgeInsets.symmetric(horizontal: 10.0),
                      child: Icon(
                        Icons.search,
                        color: Colors.grey,
                        size: 30.0,
                      ),
                    ),
                    SizedBox(
                      height: 50.0,
                      width: MediaQuery.of(context).size.width * 0.79,
                      child: const TextField(
                        decoration: InputDecoration(
                            border: InputBorder.none,
                            hintText: 'Faça aqui a sua pesquisa'),
                      ),
                    )
                  ],
                ),
              ),
            ),

            /*
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 10),
              child: Row(
                children: [
                  Text('Best Deals',
                    style: TextStyle(
                        fontSize: 20.0,fontWeight: FontWeight.bold
                    ),
                  )
                ],
              ),
            ),
            */

            SizedBox(
              height: 20,
            ),

            ServicesPackage(),

          ],
        ),
      ),


    );
  }
}
