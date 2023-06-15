#ifndef BITSTREAM_H
#define BITSTREAM_H

#include <iostream>
#include <fstream>
#include <string>
#include <stdlib.h>
#include <map>
#include <vector>

using namespace std;

class BitStream {
    private:
        fstream file;
        string filename;
        int mode; 
        int fileSize;  
        std::vector<int> buffer; // stores the bits of the current byte
        int currentPos; // stores the current bit position of reading/writting

    public:
        BitStream();

        BitStream(string filename, char mode);

        int readBit();
        
        std::vector<int> readNbits(int n);

        void writeBit(int bit);

        void writeNbits(std::vector<int>);

        std::vector<int> byteToBuffer(char c);

        char bufferToByte(std::vector<int>);

        int getFileSize();

        void close();
};

#endif