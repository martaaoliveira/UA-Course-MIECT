class userEmpresa {
  int userID;
  String name;
  String address;
  String email;
  String phoneNumber;
  int nif;
  int cae;
  String website;
  String password;
  String confirmPassword;

  userEmpresa({ required this.userID,
    required this.name,
    required this.address,
    required this.email,
    required this.phoneNumber,
    required this.nif,
    required this.cae,
    required this.website,
    required this.password,
    required this.confirmPassword});

  factory userEmpresa.fromJson(Map<String, dynamic> responseData) {
    return userEmpresa(
        userID: responseData['id'],
        name: responseData['name'],
        address: responseData['address'],
        email: responseData['email'],
        phoneNumber: responseData['phoneNumber'],
        nif: responseData['nif'],
        cae: responseData['cae'],
        website: responseData['website'],
        password: responseData['password'],
        confirmPassword: responseData['confirmPassword']
    );
  }
}