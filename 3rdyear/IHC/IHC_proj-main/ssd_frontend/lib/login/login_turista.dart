import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:lottie/lottie.dart';
import 'package:ssd_frontend/home_screen.dart';
import 'package:ssd_frontend/registo_empresas/signUp_pessoa.dart';
import '../componentes/constants.dart';
import '../componentes/simple_ui_controller.dart';
import '../features_empresa/features_empresa.dart';

/*
class LoginTurista extends StatefulWidget {
  const LoginTurista({Key? key}) : super(key: key);

  @override
  State<LoginTurista> createState() => _LoginTuristaState();
}

class LoginTuristaState extends State<LoginTurista> {
  //const LoginTurista({Key? key}) : super(key: key);
  //static const String _title = 'Login';

  final _formKey = GlobalKey<FormState>();


  //This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    var size = MediaQuery.of(context).size;
    var theme = Theme.of(context);


    return MaterialApp(
      title: _title,
      home: Scaffold(
        appBar: AppBar(
          automaticallyImplyLeading: false,
          actions: const [
            Padding(
              padding: EdgeInsets.fromLTRB(0, 5, 10, 5),
              child: Image(image: AssetImage("assets/icons/icon_app.png"),
              ),
            ),
          ],
          leading: const BackButton(
            color: Colors.white,
          ),
          title: const Text(_title),
            /*leading: const BackButton(
              color: Colors.white,
            ),*/
        ),
        body: const LoginTuristaState(),
      ),
    );
  }
}*/

class LoginTurista extends StatefulWidget {
  const LoginTurista({Key? key}) : super(key: key);

  @override
  State<LoginTurista> createState() => _LoginTuristaState();
}



class _LoginTuristaState extends State<LoginTurista> {

  //TextEditingController nameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  final _formKey = GlobalKey<FormState>();

  // Login function
  static Future<User?> loginUsingEmailPassword({required String email, required String password, required BuildContext context}) async {
    FirebaseAuth auth = FirebaseAuth.instance;
    User? user;

    try {
      UserCredential userCredential = await auth.signInWithEmailAndPassword(email: email, password: password);
      user = userCredential.user;
      print('Login done');
      print("Login successful: ${user?.email}"); // Print a message with the logged-in user's email
    } on FirebaseAuthException catch (e) {
      if (e.code == "user-not-found")
        print ("Não há nenhum utilizador para este endereço de email");
    }
    return user;
  }

  // Initialize Firebase App
  Future<FirebaseApp> _initializeFirebase () async {
    FirebaseApp firebaseapp = await Firebase.initializeApp();
    return firebaseapp;

  }

  SimpleUIController simpleUIController = Get.put(SimpleUIController());

  /*
  @override
  Widget build(BuildContext context) {

    /*
    TextEditingController emailController = TextEditingController();
    TextEditingController passwordController = TextEditingController();
    */

    var size = MediaQuery.of(context).size;
    var theme = Theme.of(context);

    return GestureDetector(
      onTap: () => FocusManager.instance.primaryFocus?.unfocus(),
      child: Scaffold(
          backgroundColor: Colors.white,
          resizeToAvoidBottomInset: false,
          body: LayoutBuilder(
            builder: (context, constraints) {
              if (constraints.maxWidth > 600) {
                return _buildLargeScreen(size, simpleUIController, theme);
              } else {
                return _buildSmallScreen(size, simpleUIController, theme);
              }
            },
          )
      ),
    );

  }
  */
  @override
  Widget build(BuildContext context) {
    // Wrap the page with a Scaffold
    var size = MediaQuery.of(context).size;
    var theme = Theme.of(context);
    return Scaffold(
      appBar: AppBar(
        title: Text(''),
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      body: GestureDetector(
        onTap: () => FocusManager.instance.primaryFocus?.unfocus(),
        child: LayoutBuilder(
          builder: (context, constraints) {
            if (constraints.maxWidth > 600) {
              return _buildLargeScreen(size, simpleUIController, theme);
            } else {
              return _buildSmallScreen(size, simpleUIController, theme);
            }
          },
        ),
      ),
    );
  }



  /// For large screens
    Widget _buildLargeScreen(
        Size size, SimpleUIController simpleUIController, ThemeData theme) {
      return Row(
        children: [

          Expanded(
            flex: 4,
            child: RotatedBox(
              quarterTurns: 3,
              child:
              /*Lottie.asset(
              'assets/images_servicos/restaurante1.jpg',
              height: size.height * 0.3,
              width: double.infinity,
              fit: BoxFit.fill,
            ),*/
              Image(
                image: AssetImage('assets/images_servicos/apartamentos.jpg'),
                height: size.height * 0.3,
                width: double.infinity,
                fit: BoxFit.fill,
              ),
            ),
          ),
          SizedBox(width: size.width * 0.06),
          Expanded(
            flex: 5,
            child: _buildMainBody(size, simpleUIController, theme),
          ),
        ],
      );
    }

  /// For Small screens
  Widget _buildSmallScreen(
      Size size, SimpleUIController simpleUIController, ThemeData theme) {
    return Center(
      child: _buildMainBody(size, simpleUIController, theme),
    );
  }

  /// Main Body
  Widget _buildMainBody(
      Size size, SimpleUIController simpleUIController, ThemeData theme) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisAlignment:
      size.width > 600 ? MainAxisAlignment.center : MainAxisAlignment.start,
      children: [
        size.width > 600
            ? Container()
            : Lottie.asset(
          'assets/icons/icon_app.png',
          height: size.height * 0.2,
          width: size.width,
          fit: BoxFit.fill,
        ),
        SizedBox(
          height: size.height * 0.03,
        ),
        Padding(
          padding: const EdgeInsets.only(left: 20.0),
          child: Text(
            'Login',
            style: kLoginTitleStyle(size),
          ),
        ),
        const SizedBox(
          height: 10,
        ),
        /*
        Padding(
          padding: const EdgeInsets.only(left: 20.0),
          child: Text(
            'Create Account',
            style: kLoginSubtitleStyle(size),
          ),
        ),*/

        SizedBox(
          height: size.height * 0.03,
        ),
        Padding(
          padding: const EdgeInsets.only(left: 20.0, right: 20),
          child: Form(
            key: _formKey,
            child: Column(
              children: [
                /*
                /// username
                TextFormField(
                  style: kTextFormFieldStyle(),
                  decoration: const InputDecoration(
                    prefixIcon: Icon(Icons.person),
                    hintText: 'Username',
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(15)),
                    ),
                  ),

                  controller: emailController,
                  // The validator receives the text that the user has entered.
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter username';
                    } else if (value.length < 4) {
                      return 'at least enter 4 characters';
                    } else if (value.length > 30) {
                      return 'maximum character is 13';
                    }
                    return null;
                  },
                ),
                */
                 

                SizedBox(
                  height: size.height * 0.02,
                ),


                /// Email
                TextFormField(
                  style: kTextFormFieldStyle(),
                  controller: emailController,
                  decoration: const InputDecoration(
                    prefixIcon: Icon(Icons.email_rounded),
                    hintText: 'e-mail',
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(15)),
                    ),
                  ),
                  // The validator receives the text that the user has entered.
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                    return 'Please enter your e-mail';
                    } //else if ((!value.endsWith('@gmail.com')) || (!value.endsWith('@outlook.pt'))) {
                    //return 'please enter valid e-mail';
                    //}
                    return null;
                  },
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),

                /// password
                Obx(
                      () => TextFormField(
                      style: kTextFormFieldStyle(),
                      controller: passwordController,
                      obscureText: simpleUIController.isObscure.value,
                      decoration: InputDecoration(
                        prefixIcon: const Icon(Icons.lock_open),
                        suffixIcon: IconButton(
                          icon: Icon(
                            simpleUIController.isObscure.value
                                ? Icons.visibility
                                : Icons.visibility_off,
                          ),
                          onPressed: () {
                            simpleUIController.isObscureActive();
                          },
                        ),
                        hintText: 'Password',
                        border: const OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(15)),
                        ),
                      ),
                      // The validator receives the text that the user has entered.
                      validator: (value) {
                        if (value == null || value.isEmpty) {
                          return 'Please enter some text';
                        } else if (value.length < 7) {
                          return 'at least enter 6 characters';
                        } else if (value.length > 13) {
                          return 'maximum character is 13';
                        }
                        return null;
                      },
                  ),
                ),
                SizedBox(
                  height: size.height * 0.01,
                ),
                /*
                Text(
                  'Creating an account means you\'re okay with our Terms of Services and our Privacy Policy',
                  style: kLoginTermsAndPrivacyStyle(size),
                  textAlign: TextAlign.center,
                ),*/

                SizedBox(
                  height: size.height * 0.02,
                ),

                /// SignUp Button
                signUpButton(theme),
                SizedBox(
                  height: size.height * 0.03,
                ),

                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text('Ainda não tem conta?'),
                    TextButton(
                      child: const Text(
                        'Registe-se aqui',
                        style: TextStyle(fontSize: 20),
                      ),
                      onPressed: () {
                        //signup screen
                        Navigator.push(context, MaterialPageRoute(
                            builder: (context) => const SignUpView())
                        );
                      },
                    )
                  ],
                ),

                /*
                /// Navigate To Login Screen
                GestureDetector(
                  onTap: () {
                    Navigator.push(
                        context,
                        CupertinoPageRoute(
                            builder: (ctx) => const LoginTurista()));
                    //nameController.clear();
                    emailController.clear();
                    passwordController.clear();
                    _formKey.currentState?.reset();

                    simpleUIController.isObscure.value = true;
                  },

                  child: RichText(
                    text: TextSpan(
                      text: 'Already have an account?',
                      style: kHaveAnAccountStyle(size),
                      children: [
                        TextSpan(
                            text: " Login",
                            style: kLoginOrSignUpTextStyle(size)),
                      ],
                    ),
                  ),

                ),*/

              ],
            ),
          ),
        ),
      ],
    );
  }

  // SignUp Button
  Widget signUpButton(ThemeData theme) {
    return SizedBox(
      width: double.infinity,
      height: 55,
      child: ElevatedButton(
        style: ButtonStyle(
          backgroundColor: MaterialStateProperty.all(Colors.deepPurpleAccent),
          shape: MaterialStateProperty.all(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
            ),
          ),
        ),
        onPressed: () async {
          User? user = await loginUsingEmailPassword(email: emailController.text, password: passwordController.text, context: context);
          print(user);
          print(emailController.text);
          print(passwordController.text);
          if (user != null) {
            Navigator.push(context, MaterialPageRoute(
                builder: (context) => homeScreen())
            );
          }
        },
        child: const Text('Login'),
      ),
    );
  }
}

/*
    // -------------------------------------------------------------------------

    TextEditingController emailController = TextEditingController();
    TextEditingController passwordController = TextEditingController();

    return Padding(
        padding: const EdgeInsets.all(10),
        child: ListView(
          children: <Widget>[
            Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.all(10),
                child: const Text(
                  'Login',
                  style: TextStyle(
                      color: Colors.blue,
                      fontWeight: FontWeight.w500,
                      fontSize: 30),
                )),
            Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.all(10),
                child: const Text(
                  'Sign in',
                  style: TextStyle(fontSize: 20),
                )),
            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: emailController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'User Name',
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
              child: TextField(
                obscureText: true,
                controller: passwordController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Password',
                ),
              ),
            ),

            /*
            TextButton(
              onPressed: () {
                //forgot password screen
              },
              child: const Text('Forgot Password',),
            ),*/

            SizedBox(
              height: 10,
            ),

            Container(
                height: 50,
                padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
                child: ElevatedButton(
                  child: Text('LOGIN'),
                  onPressed: () async {
                    User? user = await loginUsingEmailPassword(email: emailController.text, password: passwordController.text, context: context);
                    print(user);
                    print(emailController.text);
                    print(passwordController.text);
                    if (user != null) {
                      Navigator.push(context, MaterialPageRoute(
                          builder: (context) => FeaturesEmpresa())
                      );
                    }
                  },
                )
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text('Does not have account?'),
                TextButton(
                  child: const Text(
                    'Sign up',
                    style: TextStyle(fontSize: 20),
                  ),
                  onPressed: () {
                    //signup screen
                    Navigator.push(context, MaterialPageRoute(
                        builder: (context) => SignUpView())
                    );
                  },
                )
              ],
            ),
          ],
        ));
  }
}
*/
