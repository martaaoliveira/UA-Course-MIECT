#include "fcm.hpp"
#include<iostream>

// void printModel(map<string, map<char, int>> model){
//     // int i = 0;
//     for (auto it : model) {
//         cout << "{\"" << it.first << "\" :";
//         map<char, int> &internal_map = it.second;
//         for (auto it2: internal_map) {
//             cout << " {'" << it2.first << "' : " << it2.second << "}";
//         }
//         cout << "}" << endl;
//         // if(i > n){
//         //     break;
//         // }
//         // i++;
//     }
// }

int main(int argc, char** argv){
    if(argc < 3){
        cerr << "Usage: " << argv[0] << " <k> <alpha> "<< endl;
        return 0;
    }
    int k = stoi(argv[1]);
    double alpha = stod(argv[2]);
    fcm f(k, alpha);
    fcm f2(k, alpha);
    f.createModel("LANGS/portuguese.utf8");
    f2.createModel("LANGS/english.utf8");
    f.calculateEntropy();
    f2.calculateEntropy();
    cout << "PT Model Entropy: " << f.modelEntropy << endl;
    cout << "ENG Model Entropy: " << f2.modelEntropy << endl << endl;

    f.estimate("BOOKS/lusiadas.txt");
    cout << "PT Model: " << endl;
    cout << "Distance of the text: " << f.dist << endl;
    cout << "Estimated entropy of each character: " << f.estimEntropy << endl;
    cout << "Number of characters of the text: " << f.nChars << endl << endl;

    f2.estimate("BOOKS/lusiadas.txt");
    cout << "ENG Model: " << endl;
    cout << "Distance of the text: " << f2.dist << endl;
    cout << "Estimated entropy of each character: " << f2.estimEntropy << endl;
    cout << "Number of characters of the text: " << f2.nChars << endl;


    // fcm f2(5, 0.5);
    // f2.loadModelFromFile("example_model.txt");
    // f2.calculateEntropy();
    // cout << f2.modelEntropy << endl;
}

