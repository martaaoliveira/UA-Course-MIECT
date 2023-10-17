import 'dart:math';
import 'package:flutter/material.dart';
import 'services.dart';
import 'details.dart';

class ServicesPackage extends StatelessWidget {
  final hotel = Servico.PeoplechoiceList();
  ServicesPackage({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 800,
      width: double.infinity,
      child: ListView.separated(
          separatorBuilder: (_,index) => const SizedBox(height: 10,),
          itemCount: hotel.length,
          itemBuilder: (context,index) {
            Servico servicoScreen = hotel[index];
            return Padding(
              padding: const EdgeInsets.all(10.0),
              child: GestureDetector(
                onTap: () {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (_) => DetailsScreen(servico: servicoScreen,),
                      )
                  );
                },
                child: Container(
                  height: 150,
                  width: double.infinity,
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(10),
                      color: Colors.white,
                      boxShadow: const [
                        BoxShadow(
                          color: Colors.black12,
                          offset: Offset(0.0, 4.0),
                          blurRadius: 8.0
                        )
                      ]
                  ),
                  child: Stack(
                    children: [
                        Positioned(
                            child: Hero(
                              tag: servicoScreen.imgurl,
                              child: Container(
                                height: 150,
                                width: 120,
                                decoration: BoxDecoration(
                                  borderRadius: const BorderRadius.only(
                                      topLeft: Radius.circular(10),
                                      bottomLeft: Radius.circular(10)
                                  ),
                                  image: DecorationImage(
                                      image: AssetImage(hotel[index].imgurl),
                                      fit: BoxFit.cover
                                  ),
                                ),
                              ),
                            )
                      ),
                      Positioned(
                          top: 15,
                          right: 110,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(hotel[index].title,
                                style: TextStyle(fontSize: 18,
                                    fontWeight: FontWeight.w500),),
                              Text(hotel[index].location, style: TextStyle(
                                  fontSize: 15, fontWeight: FontWeight.w400
                              ),),
                              Text('${hotel[index].price} € por noite',
                                style: TextStyle(
                                    fontSize: 15,
                                    fontWeight: FontWeight.w600,
                                    color: Colors.red
                                ),
                              ),

                              /*
                              Padding(
                                padding: EdgeInsets.only(top: 10),
                                child: Row(
                                  children: [
                                    Icon(Icons.directions_car,
                                      color: Colors.red,),
                                    Icon(Icons.hot_tub,
                                      color: Colors.red,),
                                    Icon(Icons.local_bar,
                                      color: Colors.red,),
                                    Icon(Icons.wifi,
                                      color: Colors.red,),
                                    Icon(Icons.park,
                                      color: Colors.red,),
                                  ],
                                ),
                              )
                              */

                            ],
                          )),
                      Positioned(
                          bottom: 40,
                          left: 300,
                          child: Center(
                            child: //Transform.rotate(
                              //angle: pi / -2,
                               Container(
                                height: 100,
                                width: 100,
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(10),
                                  color: Colors.red,
                                ),
                                child: Center(child: Text('VER MAIS')),
                              ),
                            //),
                          ))
                    ],
                  ),
                ),
              ),
            );
          }
      ),
    );
  }
}