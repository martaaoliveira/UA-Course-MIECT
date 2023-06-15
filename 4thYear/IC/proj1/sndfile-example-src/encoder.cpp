#include <fstream>
#include <cstring> 
#include "BitStream.cpp"

int main(int argc, char** argv){

    if(argc != 3){
        cout << "Usage: " << argv[0] << " <input filename> <output filename>" << endl;
        return 0;
    }

    ifstream inputFile (argv [1], ios::in) ;
    if (!inputFile) {
        cerr << "Error: could not open input file " << argv [1] << endl ;
        return 1;
    }

    string outputFileName = argv[2];

    inputFile.seekg (0, inputFile.end);
    int length = inputFile.tellg();
    inputFile.seekg (0, inputFile.beg);
    char * buffer = new char [length];

    // read data as a block:
    inputFile.read (buffer,length);
    cout << "Length of the input file: " << length << endl;
    cout << "Content of the input file: '" << buffer << "'"<< endl;

    BitStream BSout (outputFileName, 'w') ;

    //write the bits to the output file
    vector<int> bits;
    for (int i = 0; i < length; i++){
        bits.push_back(buffer[i] - '0');  // Transform the Ascii code to an actual 0 or 1
    }
    BSout.writeNbits(bits);
    BSout.close();
    return 0;
}