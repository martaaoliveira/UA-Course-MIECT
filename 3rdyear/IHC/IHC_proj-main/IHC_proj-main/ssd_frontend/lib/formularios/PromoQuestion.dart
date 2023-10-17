import 'package:flutter/material.dart';
import 'package:ssd_frontend/features_empresa/features_empresa.dart';
import 'package:audioplayers/audioplayers.dart';

class PromoQuestion extends StatefulWidget {
  @override
  _PromoQuestionState createState() => _PromoQuestionState();
}

class _PromoQuestionState extends State<PromoQuestion> {
  List<Widget> promoFields = []; // List to hold the promotion text fields
  int promoCount = 1; // Initial number of promotions
  AudioPlayer audioPlayer = AudioPlayer();

  @override
  void initState() {
    super.initState();
    promoFields.add(buildPromoTextField(1)); // Add the initial promotion text field
  }

  Widget buildPromoTextField(int promoNumber) {
    return Column(
      children: [
        TextField(
          decoration: InputDecoration(
            labelText: 'Promoção $promoNumber',
            border: OutlineInputBorder(),
          ),
        ),
        SizedBox(height: 16.0),
      ],
    );
  }

  void addPromoField() {
    setState(() {
      promoCount++; // Increment the promotion count
      promoFields.add(buildPromoTextField(promoCount)); // Add a new promotion text field
    });
  }

void playSound() async {
  int result = await audioPlayer.play('assets/ads/1093926272423428137.mp3', volume: 0.2);
  if (result == 1) {
    // Success
    print('Sound played successfully.');
  } else {
    // Failure
    print('Error playing sound.');
  }
}


  @override
  void dispose() {
    audioPlayer.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Promoções'),
      ),
      body: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Text(
              'Adicione promoções',
              style: TextStyle(
                fontSize: 24.0,
                fontWeight: FontWeight.bold,
              ),
            ),
            SizedBox(height: 16.0),
            Column(
              children: promoFields,
            ),
            ElevatedButton(
              onPressed: () {
                // Perform any actions you need when the button is pressed
                //playSound(); // Play the sound when the button is pressed
                Navigator.of(context).push(
                  MaterialPageRoute(builder: (context) => FeaturesEmpresa()),
                );
              },    
              child: Text('Submeter'),
            ),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: addPromoField, // Call the addPromoField method when the button is pressed
              child: Text('Adicionar promoção'),
            ),
          ],
        ),
      ),
    );
  }
}
