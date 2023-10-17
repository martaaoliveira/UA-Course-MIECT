import 'package:flutter/material.dart';
import 'services.dart';

class DetailsScreen extends StatefulWidget {
  final Servico servico;

  DetailsScreen({required this.servico});

  @override
  _DetailsScreenState createState() => _DetailsScreenState();
}

class _DetailsScreenState extends State<DetailsScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
          children:[
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [

              Stack(
                children: [

                  Container(
                    height: MediaQuery.of(context).size.width * 0.8,
                    decoration: BoxDecoration(boxShadow: [
                      BoxShadow(
                          color: Colors.black26,
                          offset: Offset(0.0, 2.0),
                          blurRadius: 6.0)
                    ]),
                    child: Hero(
                      tag: widget.servico.imgurl,
                      child: Image(
                        image: AssetImage(widget.servico.imgurl),
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),

                  Positioned(
                      top: 50,
                      left: 30,
                      child: Container(
                        height: 30,
                        width: 30,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10),
                            color: Colors.white),
                        child: Center(
                          child: IconButton(
                            onPressed: () {
                              Navigator.pop(context);
                            },
                            icon: Icon(
                              Icons.arrow_back_ios,
                              size: 15,
                            ),
                          ),
                        ),
                      )
                  )

                ],
              ),
              SizedBox(
                height: 10,
              ),

              Padding(
                padding: const EdgeInsets.only(left: 20,top: 20),
                child: Text(widget.servico.title,
                  style: TextStyle(
                      fontSize: 25.0,
                      fontWeight: FontWeight.w400
                  ),),
              ),

              Padding(
                padding: const EdgeInsets.only(left: 20,top: 5),
                child: Text(widget.servico.location,
                  style: TextStyle(
                      fontSize: 16,
                      color: Colors.grey
                  ),
                ),
              ),

              SizedBox(height: 20,),
              Padding(
                padding: const EdgeInsets.all(10.0),
                child: Text(widget.servico.description),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 20.0,top: 10),
                child: Row(
                  children: [
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [

                          Text('Price',style: TextStyle(color: Colors.grey),),
                          SizedBox(height: 10,),
                          Text('${widget.servico.price} € por noite '),

                        ],
                      ),
                    ),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text('Reviews',style: TextStyle(color: Colors.grey,fontWeight: FontWeight.w500),),
                          SizedBox(height: 10,),
                          Row(
                            children: [
                              Text(widget.servico.rating.toString()),
                              Icon(Icons.star,size: 12,color: Colors.red,),
                              Icon(Icons.star,size: 12,color: Colors.red),
                              Icon(Icons.star,size: 12,color: Colors.red),
                              Icon(Icons.star_half,size: 12,color: Colors.red),
                            ],
                          ),
                        ],
                      ),
                    ),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text('Recently booked',style: TextStyle(color: Colors.grey),),
                          SizedBox(height: 10,),
                          Stack(
                            children: [
                              Container(
                                height: 20,
                                width: 70,
                              ),
                              Positioned(
                                  left: 20,
                                  child: Container(
                                    height: 20,
                                    width: 20,
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(5),
                                        color: Colors.red
                                    ),
                                  )
                              ),
                              Positioned(
                                  left: 30,
                                  child: Container(
                                    height: 20,
                                    width: 20,
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(5),
                                        color: Colors.blueAccent
                                    ),
                                  )
                              ),
                              Positioned(
                                  left: 40,
                                  child: Container(
                                    height: 20,
                                    width: 20,
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(5),
                                        color: Colors.yellow
                                    ),
                                  )
                              ),
                              Positioned(
                                  left: 50,
                                  child: Container(
                                    height: 20,
                                    width: 20,
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(5),
                                        color: Colors.green
                                    ),
                                    child: Center(
                                      child: Text(
                                        '+3',style: TextStyle(
                                          color: Colors.white,
                                          fontSize: 14
                                      ),
                                      ),
                                    ),
                                  )
                              ),
                            ],
                          )
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 10,left: 20),
                child: Text('Amenity',
                  style: TextStyle(fontSize: 20,fontWeight: FontWeight.w700),),
              ),
              SizedBox(height: 10,),
              Row(
                children: [
                  Expanded(
                    child: Column(
                      children: [
                        Container(
                          height: 40,
                          width: 40,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Colors.white,
                              boxShadow: [BoxShadow(
                                  color: Colors.black12,
                                  offset: Offset(0.0,2.0),
                                  blurRadius: 10.0,
                                  spreadRadius: 6
                              )]
                          ),
                          child: Center(
                            child: Icon(
                              Icons.directions_car,
                              color: Colors.red,
                            ),
                          ),
                        ),
                        SizedBox(height: 5,),
                        Text('Parking',
                          style: TextStyle(
                              color: Colors.red
                          ),)
                      ],
                    ),
                  ),
                  Expanded(
                    child: Column(
                      children: [
                        Container(
                          height: 40,
                          width: 40,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Colors.white,
                              boxShadow: [BoxShadow(
                                  color: Colors.black12,
                                  offset: Offset(0.0,2.0),
                                  blurRadius: 10.0,
                                  spreadRadius: 6
                              )]
                          ),
                          child: Center(
                            child: Icon(
                              Icons.hot_tub,
                              color: Colors.red,
                            ),
                          ),
                        ),
                        SizedBox(height: 5,),
                        Text('Bath',
                          style: TextStyle(
                              color: Colors.red
                          ),)
                      ],
                    ),
                  ),
                  Expanded(
                    child: Column(
                      children: [
                        Container(
                          height: 40,
                          width: 40,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Colors.white,
                              boxShadow: [BoxShadow(
                                  color: Colors.black12,
                                  offset: Offset(0.0,2.0),
                                  blurRadius: 10.0,
                                  spreadRadius: 6
                              )]
                          ),
                          child: Center(
                            child: Icon(
                              Icons.pool,
                              color: Colors.red,
                            ),
                          ),
                        ),
                        SizedBox(height: 5,),
                        Text('Pool',
                          style: TextStyle(
                              color: Colors.red
                          ),)
                      ],
                    ),
                  ),
                  Expanded(
                    child: Column(
                      children: [
                        Container(
                          height: 40,
                          width: 40,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Colors.white,
                              boxShadow: [BoxShadow(
                                  color: Colors.black12,
                                  offset: Offset(0.0,2.0),
                                  blurRadius: 10.0,
                                  spreadRadius: 6
                              )]
                          ),
                          child: Center(
                            child: Icon(
                              Icons.wifi,
                              color: Colors.red,
                            ),
                          ),
                        ),
                        SizedBox(height: 5,),
                        Text('wifi',
                          style: TextStyle(
                              color: Colors.red
                          ),)
                      ],
                    ),
                  ),
                  Expanded(
                    child: Column(
                      children: [
                        Container(
                          height: 40,
                          width: 40,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Colors.white,
                              boxShadow: [
                                BoxShadow(
                                  color: Colors.black12,
                                  offset: Offset(0.0,2.0),
                                  blurRadius: 10.0,
                                  spreadRadius: 6
                                )
                              ]
                          ),
                          child: const Center(
                            child:
                            Icon(
                              Icons.park,
                              color: Colors.red,
                            ),
                          ),
                        ),
                        const SizedBox(
                          height: 5,
                        ),

                        const Text('Park',
                          style: TextStyle(
                              color: Colors.red
                          ),
                        )
                      ],
                    ),
                  ),
                ],
              ),

              /*
              Padding(
                padding:EdgeInsets.only(right: 10,top: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Container(
                      height: 40,
                      width: 150,
                      decoration: BoxDecoration(
                          color: Colors.red,
                          borderRadius: BorderRadius.circular(10),
                          boxShadow: [BoxShadow(
                            color: Colors.black12,
                            offset: Offset(0.0, 2.0),
                          )]
                      ),
                      child: Center(
                        child:GestureDetector(
                          onTap: ()=>showDialog<String>(context: context,
                              builder: (BuildContext context) =>
                                  AlertDialog(
                                    title: Text("Confirmation"),
                                    content: Text("Hotel booked at ${widget.hotel.title}"),
                                    actions: [
                                      TextButton(onPressed:()=> Navigator.pop(context,'ok') , child: Text('ok'))
                                    ],
                                  )),
                          child: Text('VER MAIS',
                            style: TextStyle(fontWeight: FontWeight.bold,
                                fontSize: 20,color: Colors.white),),
                        ),
                      ),

                    )
                  ],
                ),

              )
              */

            ],
          ),
          ]
      ),
    );
  }
}