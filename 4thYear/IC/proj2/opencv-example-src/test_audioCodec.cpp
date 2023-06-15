#include "AudioCodec.hpp"
#include <ctime>
#include <iostream>
#include "BitStream.hpp"
#include <fstream>
#include <stdio.h>
#include <string.h>

using namespace std;
int main(int argc, char* argv[]){
    if(argc < 2){
        cerr << "Usage: " << argv[0] << " <input filename>"<< endl;
        return 0;
    }

    char *filename = argv[1];
    AudioCodec codec(filename);
    
    int mode = -1;
    while(!(mode == 0 || mode == 1)){
        cout << "Choose codec mode (0 for lossless and 1 for lossy): ";
        cin >> mode;
    }

    int bitsQuant = -1;
    if(mode == 1){
        while((bitsQuant > 15) || (bitsQuant < 0)){
            cout << "Insert the number of the bits to quantize: ";
            cin >> bitsQuant;
        }
    }

    int predMode = -1;
    while(!(predMode == 1 || predMode == 2 || predMode == 3)){
        cout << "Choose the predictor mode(1, 2 or 3): ";
        cin >> predMode;
    }

    ifstream in_file(filename, ios::binary);
    in_file.seekg(0, ios::end);
    double file_size = in_file.tellg();
    cout << "... begin encoding" << endl;
    clock_t begin = clock();
    codec.encode("encoded_audio.bin", predMode, mode, bitsQuant);
    clock_t end = clock();
    cout << "... done encoding" << endl;
    cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl;
    ifstream in_file2("encoded_audio.bin", ios::binary);
    in_file2.seekg(0, ios::end);
    double fileOut_size = in_file2.tellg();
    double compRatio = (file_size / fileOut_size);
    double spaceSaving = (1 - (fileOut_size / file_size)) * 100;
    cout <<"Compression ratio: " << compRatio << endl;
    cout <<"Space saving: " << spaceSaving  << "\%" << endl;
    vector<double> entropy = codec.calculateEntropy();
    cout << "Entropy Original File: " << entropy[0] << endl;
    cout << "Entropy of the obtained residuals after prediction: " << entropy[1] << endl << endl;


    clock_t begin2 = clock();
    cout << "... begin decoding" << endl;
    codec.decode("encoded_audio.bin", "decoded_audio.wav");
    clock_t end2 = clock();
    cout << "... done decoding" << endl;
    cout <<"Duration: " <<  (double(end2 - begin2) / CLOCKS_PER_SEC) << " seconds" << endl;

}