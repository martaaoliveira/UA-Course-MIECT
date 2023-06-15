#ifndef GOLOMB_H
#define GOLOMB_H

#include <math.h>
#include <string>
#include "BitStream.hpp"

using namespace std;

class Golomb{
    private:
        BitStream file;
        int m;
        int b;

    public:
        // Golomb();

        Golomb(string filename, char mode, int m);

        int encode(int n);

        int decode();

        int fold(int n);

        int unfold(int n);

        void setM(int mValue);

        // Auxiliary functions for encoding/decoding audio files
        void encodeAudioHeader(int m, int nFrames, int sampleRate, int chs, int format, int nvalues, int lossy, int shQuant=0);

        std::vector<int> decodeAudioHeader();

        void writeBinValue(int value, int nbits);

        int readBinValue(int nbits);

        std::vector<int> convertToBin(int value, int nbits);

        int convertToInt(std::vector<int> binValue, int nbits);

        void close();
};

#endif