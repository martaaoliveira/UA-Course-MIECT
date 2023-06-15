#include "AudioCodec.hpp"
#include <ctime>
#include <iostream>
#include "BitStream.hpp"
#include <fstream>
#include <stdio.h>
#include <string.h>
#include <iomanip>

using namespace std;
int main(int argc, char* argv[]){
    if(argc < 2){
        cerr << "Usage: " << argv[0] << " <input filename>"<< endl;
        return 0;
    }

    char *filename = argv[1];
    
    
    // Calculate original file size
    ifstream in_file(filename, ios::binary);
    in_file.seekg(0, ios::end);
    double file_size = in_file.tellg();

    cout << "FILE: " << filename << endl;
    cout << "---------LOSSLESS--------" << endl;
    // cout << "|  PredMode  |    compRatio     |  Duration    |   EntropyOrig  |   EntropyAfterPred    |" << endl;
    cout << "|  PredMode  |   compRatio   |   SpaceSaving(%)   |   Duration(s)   |" << endl;
    for(int predMode = 1; predMode < 4; predMode++){
        AudioCodec codec(filename);
        double compRatio = 0, spaceSaving = 0, fileOut_size = 0, duration = 0;
        string fileOutName = "RESULTS/encoded_audio" + (to_string(predMode)) + ".bin";
        const char * fileout = fileOutName.c_str();
        clock_t begin = clock();
        codec.encode(fileout, predMode, 0);
        clock_t end = clock();
        duration = (double(end - begin) / CLOCKS_PER_SEC);

        // cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl;
        // Calculate encoded file size
        ifstream in_file2(fileout, ios::binary);
        in_file2.seekg(0, ios::end);
        fileOut_size = in_file2.tellg();

        compRatio = (file_size / fileOut_size);
        spaceSaving = (1 - (fileOut_size / file_size)) * 100;
        printf("|      %d     |   %4f    |      %4f     |     %4f    |\n", predMode, compRatio, spaceSaving, duration);
    }
    
    cout << "\n---------LOSSY--------" << endl;
    cout << "|  bitsQuant  |   compRatio   |   SpaceSaving(%)   |   Duration(s)   |" << endl;
    for(int bitsQuant = 1; bitsQuant < 15;){
        AudioCodec codec(filename);
        double compRatio = 0, spaceSaving = 0, fileOut_size = 0, duration = 0;
        
        clock_t begin = clock();
        string fileOutName = "RESULTS/encoded_audioL" + (to_string(bitsQuant)) + ".bin";
        const char * fileout = fileOutName.c_str();
        codec.encode(fileout, 2, 1, bitsQuant);
        clock_t end = clock();
        duration = (double(end - begin) / CLOCKS_PER_SEC);

        // Calculate encoded file size
        ifstream in_file2(fileout, ios::binary);
        in_file2.seekg(0, ios::end);
        fileOut_size = in_file2.tellg();

        compRatio = (file_size / fileOut_size);
        spaceSaving = (1 - (fileOut_size / file_size)) * 100;
       
        printf("|      %2d     |   %2f    |       %4f    |     %4f    |\n", bitsQuant, compRatio, spaceSaving, duration);

        string fileOutName2 = "RESULTS/decoded_audioL" + (to_string(bitsQuant)) + ".wav";
        const char * filedec = fileOutName2.c_str();
        codec.decode(fileout, filedec);
        
        if(bitsQuant < 5) bitsQuant++;
        else bitsQuant += 2;
    }

}