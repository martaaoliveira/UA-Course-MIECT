import 'package:flutter/material.dart';
import 'package:ssd_frontend/Interesses.dart';
import 'package:ssd_frontend/AboutUS.dart';
import 'login/login_turista.dart';
import 'appbar_secondary.dart';

class Restaurant {
  final String name;
  final int id;
  final String district;
  final String city;
  final String cuisineType;
  final String image;
  final double distance;

  Restaurant({
    required this.id,
    required this.name,
    required this.district,
    required this.city,
    required this.cuisineType,
    required this.image,
    required this.distance,
  });
}

class Favoritos extends StatefulWidget {
  @override
  FavoritosState createState() => FavoritosState();
}

class FavoritosState extends State<Favoritos> {
  final List<Restaurant> restaurants = [
    Restaurant(
      id: 1,
      name: 'Un Poco Loco',
      distance: 1.2,
      image: 'assets/images_restaurants/res6.jpg',
      city: 'Aveiro',
      district: 'Aveiro',
      cuisineType: 'Mexican Cuisine',
    ),
  ];

  Set<int> favorites = Set<int>();
    @override
  void initState() {
    super.initState();
    favorites.add(1);
  }

  bool isFavorite = false;
  @override
  Widget build(BuildContext context) {
    //favorites.add(1);
    return Scaffold(
      appBar: CustomAppBar_2(favorites: favorites,),
      body: SingleChildScrollView(
        child: SizedBox(
          height: MediaQuery.of(context).size.height,
          child: Column(
            children: [
              Expanded(
                child: Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: GridView.count(
                      crossAxisCount: 4,
                      mainAxisSpacing: 16,
                      crossAxisSpacing: 16,
                      childAspectRatio: 1.75,
                      
                      children: restaurants.map((restaurant) {
                        //bool isFavorite = true;
                        //favorites.add(restaurant.id);
                        return GestureDetector(
                          onTap: () {
                            showDialog(
                              context: context,
                              builder: (_) => AlertDialog(
                                content: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    LimitedBox(
                                      maxHeight:
                                          MediaQuery.of(context).size.height *
                                              0.7,
                                      maxWidth:
                                          MediaQuery.of(context).size.width *
                                              0.7,
                                      child: Image.asset(
                                        restaurant.image,
                                        fit: BoxFit.cover,
                                      ),
                                    ),
                                    const SizedBox(height: 16.0),
                                    Text(
                                      restaurant.name,
                                      style: const TextStyle(
                                        fontWeight: FontWeight.bold,
                                        fontSize: 20.0,
                                      ),
                                    ),
                                    const SizedBox(height: 8.0),
                                    Text(
                                      'District: ${restaurant.district}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(height: 8.0),
                                    Text(
                                      'City: ${restaurant.city}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(height: 8.0),
                                    Text(
                                      'Cuisine Type: ${restaurant.cuisineType}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(height: 16.0),
                                    ElevatedButton(
                                      onPressed: () {
                                        Navigator.of(context).pop();
                                      },
                                      child: const Text('Close'),
                                    ),
                                  ],
                                ),
                              ),
                            );
                          },
                          child: Card(
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Expanded(
                                  child: Container(
                                    width: double.infinity,
                                    child: Image.asset(
                                      restaurant.image,
                                      fit: BoxFit.cover,
                                    ),
                                  ),
                                ),
                                Padding(
                                  padding: const EdgeInsets.all(8.0),
                                  child: Text(
                                    restaurant.name,
                                    style: const TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 16.0,
                                    ),
                                  ),
                                ),
                                Padding(
                                  padding: const EdgeInsets.symmetric(
                                    horizontal: 8.0,
                                    vertical: 4.0,
                                  ),
                                  child: Row(
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceBetween,
                                    children: [
                                      Text(
                                        '${restaurant.distance} km',
                                        style: const TextStyle(fontSize: 14),
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
                                ),
                              ],
                            ),
                          ),
                        );
                      }).toList(),
                    )),
              ),
            ],
          ),
        ),
      ),
      resizeToAvoidBottomInset: false,
    );
  }
}
