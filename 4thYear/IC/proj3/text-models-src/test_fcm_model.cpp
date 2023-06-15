#include "fcm.hpp"
#include <iostream>
#include <vector>
#include <fstream>

int main(){
    fcm f(1, 0.1);
    ofstream outfile;
	outfile.open("results.dat", ios::trunc);    
    // vector<double> entropy; 
    for(int i=1; i < 21; i++){
        f.setK(i);
        f.createModel("LANGS/portuguese.utf8");
        f.calculateEntropy();
        // cout << "PT Model Entropy: " << f.modelEntropy << endl;
        // entropy.push_back(f.modelEntropy);
        cout << "k = " << i << endl;
        outfile << i << '\t' << f.modelEntropy << '\n';
        f.clearModel();
    }
    outfile.close();
    cout << "Done" << endl;
}