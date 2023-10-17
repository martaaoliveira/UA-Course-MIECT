import 'package:flutter/material.dart';
import 'package:ssd_frontend/componentes/constants.dart';
import 'package:ssd_frontend/total_services/total_services.dart';
import 'home_screen.dart';

class Body extends StatelessWidget {
  const Body({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            "LusiTravel".toUpperCase(),
            style: Theme.of(context).textTheme.headline1?.copyWith(
              color: Colors.white,
              fontWeight: FontWeight.bold,
            ),
          ),
          Text(
            "Venha conhecer Portugal com a Lusitravel\!",
            style: TextStyle(
              fontSize: 21,
              color: Colors.white
            ),
          ),

          SizedBox(
            height: 20,
          ),

          FittedBox(
            // Now it just take the required spaces
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  shadowColor: Colors.black,
                  backgroundColor: Colors.white,
                ),
                  onPressed: () {
                    Navigator.push(context, MaterialPageRoute(
                        builder: (context) => TodosServicos())
                    );
                  },
                  child: Text(
                    "Descubra mais sobre nós!",
                    style: TextStyle(
                      fontSize: 25,
                      color: Colors.blue,
                    ),
                  ),
              ),
              /*Row(
                children: <Widget>[
                  Container(
                    padding: EdgeInsets.all(10),
                    height: 38,
                    width: 38,
                    decoration: const BoxDecoration(
                      color: kPrimaryColor,
                      shape: BoxShape.circle,
                    ),
                    child: Container(
                      decoration: const BoxDecoration(
                        color: Color(0xFF372930),
                        shape: BoxShape.circle,
                      ),
                    ),
                  ),

                  const SizedBox(width: 15),

                  Text(
                    "Começar".toUpperCase(),
                    style: const TextStyle(
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                      fontSize: 19,
                    ),
                  ),
                  const SizedBox(width: 15),
                ],
              ),*/
            ),

        ],
      ),
    );
  }
}

