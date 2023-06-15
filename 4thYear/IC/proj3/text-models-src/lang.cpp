#include "fcm.hpp"
#include<iostream>
#include<string>

using namespace std;

int main(int argc, char** argv){
    
    if(argc < 3){
        cerr << "Usage: " << argv[0] << " <text_model> <text_analysis> <-l>"<< endl;
        return 0;
    }

    bool flagLoad = false;
    fcm f; // initialize fcm with no input parameters (they'll be defined by the loaded model or by the user)

    if(argc == 4){
        if(string(argv[3]) == "-l"){
            cout << "Load model from: " << argv[1] << endl;
            flagLoad = true;
            f.loadModelFromFile(argv[1]);
        }
    }
    
    if(!flagLoad){
        int k = -1;
        double alpha = -1;
        while(k <= 0){
            cout << "Insert context order (k): ";
            cin >> k;
        }
        while(alpha <= 0){
            cout << "Insert alpha (smoothing parameter): ";
            cin >> alpha;
        }

        f.setK(k);
        f.setAlpha(alpha);
        f.createModel(argv[1]);
    }

    
    f.estimate(argv[2]);  // estimate number of bits required to compress the text under analysis

    cout << "Estimated number of bits to compress the text file: " << f.dist << endl;
    cout << "Estimated number of bits per character: " << (f.dist / f.nChars) << endl;
}

