#include <iostream>
#include <string>
#include "BitStream.cpp"

using namespace std;

int main (int argc, char *argv[]) {	

    if(argc != 3){
        cout << "Usage: " << argv[0] << " <input filename> <output filename>" << endl;
        return 0;
    }

    string inputFileName = argv[1];
    BitStream BSin (inputFileName, 'r');
    
    ofstream outputFile (argv [2], ios::out) ;
    if (! outputFile) {
        cerr << "Error: could not open output file " << argv [2] << endl;
        return 1 ;
    }

    //read the bits from the input file
    vector<int> bits;
    bits = BSin.readNbits(BSin.getFileSize() * 8);
    BSin.close();

    //write the bits to the output file
    for (int i = 0; i < bits.size(); i++){
        outputFile << bits[i];
    }
    outputFile.close();

    return 0;
}