import 'package:flutter/material.dart';
import 'package:ssd_frontend/appbar_main.dart';
import 'package:ssd_frontend/appbar_secondary.dart';

class Restaurant {
  final int id;
  final String name;
  final String district;
  final String city;
  final String cuisineType;
  final String image;
  final double distance;
  final String phoneNumber;

  Restaurant({
    required this.id,
    required this.name,
    required this.district,
    required this.city,
    required this.cuisineType,
    required this.image,
    required this.distance,
    required this.phoneNumber,
  });
}

class ResultSearch extends StatefulWidget {
  @override
  _ResultSearchState createState() => _ResultSearchState();
}

class _ResultSearchState extends State<ResultSearch> {
  final List<Restaurant> restaurants = [
    Restaurant(
      id: 1,
      name: 'Sal na Brasa',
      distance: 1.2,
      image: 'assets/images_restaurants/res3.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Example Cuisine',
      phoneNumber: "961245569"
    ),
    Restaurant(
      id: 2,
      name: 'O Forno da Mimi',
      distance: 1.5,
      image: 'assets/images_restaurants/res1.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Pizza',
      phoneNumber: "961245789"
    ),
    Restaurant(
      id: 3,
      name: 'Esplanada Santa Joana',
      distance: 1.7,
      image: 'assets/images_restaurants/res2.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Portuguese Cuisine',
      phoneNumber: "961245147"
    ),
    Restaurant(
      id: 4,
      name: 'Retiro da Avo Maria',
      distance: 2.0,
      image: 'assets/images_restaurants/res4.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Portuguese Cuisine',
      phoneNumber: "961241599"
    ),
    Restaurant(
      id: 5,
      name: 'O Mercantel',
      distance: 2.5,
      image: 'assets/images_restaurants/res5.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Seafood',
      phoneNumber: "961286189"
    ),
    Restaurant(
      id: 6,
      name: 'Un Poco Loco',
      distance: 1.0,
      image: 'assets/images_restaurants/res6.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Tacos',
      phoneNumber: "961987789"
    ),
    Restaurant(
  id: 7,
  name: 'O Recanto da Rosa',
  distance: 12.0,
  image: 'assets/images_restaurants/res7.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 8,
  name: 'Restaurante Chinês',
  distance: 8.0,
  image: 'assets/images_restaurants/res8.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 9,
  name: 'FAMA by Luís Lavrador',
  distance: 11.0,
  image: 'assets/images_restaurants/res9.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 10,
  name: 'Ceboleiros',
  distance: 11.0,
  image: 'assets/images_restaurants/res10.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 11,
  name: 'Maré Cheia',
  distance: 11.0,
  image: 'assets/images_restaurants/res11.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 12,
  name: 'Bacalhau & Afins',
  distance: 11.0,
  image: 'assets/images_restaurants/res12.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 13,
  name: 'Espeto do Sul - Restaurante Rodízio Brasileiro e Sushi Bar',
  distance: 11.0,
  image: 'assets/images_restaurants/res13.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 14,
  name: 'Churrascaria Madalena - Prato do dia',
  distance: 11.0,
  image: 'assets/images_restaurants/res14.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Portuguese Cuisine',
  phoneNumber: "962020202",
),

Restaurant(
  id: 15,
  name: 'Os Ossinhos Churrasqueira - Restaurante',
  distance: 11.0,
  image: 'assets/images_restaurants/res15.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Cuisine 20',
  phoneNumber: "962020202",
),

Restaurant(
  id: 16,
  name: 'Tasquinha do Leitão',
  distance: 11.0,
  image: 'assets/images_restaurants/res16.jpg',
  city: 'Aveiro',
  district: 'Aveiro',
  cuisineType: 'Cuisine 20',
  phoneNumber: "962020202",
),

  ];

bool isFavorite = false;

Set<int> favorites = Set<int>();
@override
Widget build(BuildContext context) {
  return Scaffold(
    appBar: CustomAppBar_2(favorites: favorites),
    body: Padding(
      padding: const EdgeInsets.all(16.0),
      child: GridView.count(
        crossAxisCount: 4,
        mainAxisSpacing: 16,
        crossAxisSpacing: 16,
        childAspectRatio: 1.5, // Adjust this value for desired aspect ratio
        children: restaurants.map((restaurant) {
          return GestureDetector(
            onTap: () {
              showDialog(
                context: context,
                builder: (_) => StatefulBuilder(
                  builder: (BuildContext context, StateSetter setState) {
                    return AlertDialog(
                      content: Column(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          Expanded(
                            flex: 2,
                            child: Image.asset(
                              restaurant.image,
                              fit: BoxFit.cover,
                            ),
                          ),
                          SizedBox(height: 16.0),
                          Expanded(
                            flex: 1,
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  restaurant.name,
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 20.0,
                                  ),
                                ),
                                SizedBox(height: 8.0),
                                Text(
                                  'Distrito: ${restaurant.district}',
                                  style: TextStyle(fontSize: 16),
                                ),
                                SizedBox(height: 8.0),
                                Text(
                                  'Concelho: ${restaurant.city}',
                                  style: TextStyle(fontSize: 16),
                                ),
                                SizedBox(height: 8.0),
                                Text(
                                  'Tipo: ${restaurant.cuisineType}',
                                  style: TextStyle(fontSize: 16),
                                ),
                                SizedBox(height: 8.0),
                                Text(
                                  'Contacto: ${restaurant.phoneNumber}',
                                  style: TextStyle(fontSize: 16),
                                ),
                              ],
                            ),
                          ),
                          SizedBox(height: 16.0),
                          ElevatedButton(
                            onPressed: () {
                              Navigator.of(context).pop();
                            },
                            child: Text('Close'),
                          ),
                          IconButton(
                            icon: Icon(
                              favorites.contains(restaurant.id)
                                  ? Icons.favorite
                                  : Icons.favorite_border,
                              color: Colors.red,
                            ),
                            onPressed: () {
                              setState(() {
                                if (favorites.contains(restaurant.id)) {
                                  favorites.remove(restaurant.id);
                                } else {
                                  favorites.add(restaurant.id);
                                }
                              });
                            },
                          ),
                        ],
                      ),
                    );
                  },
                ),
              );
            },
            child: Card(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Expanded(
                    flex: 2,
                    child: Stack(
                      children: [
                        Positioned.fill(
                          child: Image.asset(
                            restaurant.image,
                            fit: BoxFit.cover,
                          ),
                        ),
                        Positioned(
                          bottom: 0,
                          left: 0,
                          right: 0,
                          child: Container(
                            padding: const EdgeInsets.symmetric(
                              vertical: 8.0,
                              horizontal: 8.0,
                            ),
                            color: Colors.black54,
                            child: Row(
                              mainAxisAlignment:
                                  MainAxisAlignment.spaceBetween,
                              children: [
                                Expanded(
                                  child: Text(
                                    restaurant.name,
                                    style: TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold,
                                      fontSize: 16.0,
                                    ),
                                  ),
                                ),
                                IconButton(
                                  icon: Icon(
                                    favorites.contains(restaurant.id)
                                        ? Icons.favorite
                                        : Icons.favorite_border,
                                    color: Colors.red,
                                  ),
                                  onPressed: () {
                                    setState(() {
                                      if (favorites
                                          .contains(restaurant.id)) {
                                        favorites.remove(restaurant.id);
                                      } else {
                                        favorites.add(restaurant.id);
                                      }
                                    });
                                  },
                                ),
                              ],
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                  SizedBox(height: 8.0),
                  Expanded(
                    flex: 1,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          restaurant.name,
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 16.0,
                          ),
                        ),
                        // SizedBox(height: 4.0),
                        // Text(
                        //   'Distrito: ${restaurant.district}',
                        //   style: TextStyle(fontSize: 14),
                        // ),
                        // Text(
                        //   'Concelho: ${restaurant.city}',
                        //   style: TextStyle(fontSize: 14),
                        // ),
                        // Text(
                        //   'Tipo: ${restaurant.cuisineType}',
                        //   style: TextStyle(fontSize: 14),
                        // ),
                        // Text(
                        //   'Contacto: ${restaurant.phoneNumber}',
                        //   style: TextStyle(fontSize: 14),
                        // ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          );
        }).toList(),
      ),
    ),
  );
}
}