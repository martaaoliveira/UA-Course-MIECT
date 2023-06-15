#include "Golomb.hpp"
#include "BitStream.hpp"
#include <math.h>
#include <string>
#include <cstdlib>
#include <vector>

using namespace std;

// Golomb::Golomb(){}

Golomb::Golomb(string filename, char mode, int m){
    if (mode == 'd'){
        file = BitStream(filename, 'r');
    }
    else if (mode == 'e'){
        file = BitStream(filename, 'w');
    }
    else {
        cerr << "Error: Invalid mode!" << endl;
    }

    this->m = m;
    b = ceil(log2(m));
}

int Golomb::encode(int n){
    n = fold(n);
    int q = floor((int)(n / m));
    int r = n - (q * m);

    int nbitsbin;  // stores the number os bits of the binary part
    int value;     // value to be coded in the binary part

    // Verify if m is a power of two
    if ((m & (m-1)) == 0 && m != 0) {
        nbitsbin = b;
        value = r;
    }
    else{
        int x = pow(2, b) - m;
        // Verify if r is smaller than x
        if (r < x){  
            nbitsbin = b - 1;
            value = r;
        }
        else{
            nbitsbin = b;
            value = r + x;
        }
    }

    vector<int> aux;
    int nbitsbinAux = nbitsbin;

    while (nbitsbinAux > 0){
        aux.push_back(value % 2 == 0 ? 0 : 1);
        value /= 2;
        nbitsbinAux--;
    }
    // while (nbitsbinAux > 0){
    //     aux.push_back(0);
    //     nbitsbinAux--;
    // }

    int nbitsTotal = nbitsbin;  // total size of the encoded word to be returned

    // cout << "Cod= ";
    // Calculation of the unary part
    nbitsTotal++;
    for(int i=0; i < q; i++){
        // cout << 0;
        file.writeBit(0);
        nbitsTotal++;
    }
    // cout << 1;
    file.writeBit(1);

    // Write binary part
    for (int i=nbitsbin-1; i>=0; i--){
        // cout << aux[i];
        file.writeBit(aux[i]);
    }
    // cout << endl;
    return nbitsTotal;
}

int Golomb::decode(){
    int U = 0;  // number of bits of the unary part
    int R = 0;  // number of bits of the rest
    int bit;    // current bit

    while(true){
        bit = file.readBit();
        if(bit == 1) break;
        U++;
    }
    // cout << "U= " << U << endl;
    // Verify if m is a power of two
    if ((m & (m-1)) == 0 && m != 0) {
        // vector<int> bin = file.readNbits(b); //reads the binary part
        int aux = b-1;
        for(int i = b-1; i >= 0; i--){
            bit = file.readBit();
            // cout << bit<< endl; 
            if (bit != 0){
                R += pow(2, aux);
            }
            aux--;
        }
        // cout << "R= " << R << endl;
        return unfold(m*U + R);
    }
    else {
        vector<int> bin = file.readNbits(b-1); //read b-1 bits from the binary part
        // cout << "b= " << b << endl;
        int aux = b-2;
        
        for(int i = 0; i < b-1; i++){
            if (bin[i] != 0){
                R += pow(2, aux);
            }
            aux--;
        }

        int x = (pow(2, b) - m);
        if(R < x){        
            // cout << "R= " << R << endl;
            return unfold(m*U + R); 
        }
        else{
            R = 2 * R; // "shift" of the bits that have been read 
            int Lsbit = file.readBit();
            if (Lsbit == 1){
                R++;
            }
            // cout << "x= " << x << endl;
            // cout << "R= " << R << endl;
            return unfold((m*U + R) - x); 
        }
    }
}

int Golomb::fold(int n){
    if (n < 0)
        return ((-1) * n) * 2 - 1;
    else
        return n * 2;
}

int Golomb::unfold(int n){
    if (n % 2 != 0)
        return ((-1) * ceil(n/2)) - 1;
    else
        return n / 2;
}

void Golomb::setM(int mValue){
    this->m = mValue;
    b = ceil(log2(m));
}

std::vector<int> Golomb::convertToBin(int value, int nbits){
    vector<int> binValue(nbits,0);
    int aux = nbits - 1;
    while(value != 0){
        binValue[aux] = (value % 2 == 0 ? 0 : 1);
        value /= 2;
        aux--;
    }
    return binValue;
}

int Golomb::convertToInt(std::vector<int> binValue, int nbits){
    int value = 0;
    for(int i = 0; i < nbits; i++){
        if(binValue[i] != 0){
            value += pow(2, (nbits-1) - i);
        }
    }
    return value;
}


int Golomb::readBinValue(int nbits){
    vector<int> binValue = file.readNbits(nbits);
    return convertToInt(binValue, nbits);
}

void Golomb::writeBinValue(int value, int nbits){
    vector<int> binValue = convertToBin(value, nbits);
    file.writeNbits(binValue);
}

void Golomb::encodeAudioHeader(int m, int nFrames, int sampleRate, int format, int chs, int nvalues, int lossy, int shQuant){
    // Header:
    // m(32bits) nSamples(32bits) sampleRate(32bits) format(32bits) channels(4bits) nvalues(2bits) lossy(1bit) <shQuant(5bits)>
    // cout << m << endl;
    // cout << nFrames << endl;
    // cout << sampleRate << endl;
    writeBinValue(m, 32);
    writeBinValue(nFrames, 32);
    writeBinValue(sampleRate, 32);
    writeBinValue(format, 32);
    writeBinValue(chs, 4);
    writeBinValue(nvalues, 2);
    if(lossy){
        file.writeBit(1);   // if lossy, mode = 1
        writeBinValue(shQuant, 5);
    }
    else{
        file.writeBit(0);   // if not lossy, mode = 0
    }
}

std::vector<int> Golomb::decodeAudioHeader(){
    vector<int> audio_header;
    audio_header.push_back(readBinValue(32)); // audio_header[0] = m value
    audio_header.push_back(readBinValue(32)); // audio_header[1] = frames value
    audio_header.push_back(readBinValue(32)); // audio_header[2] = sampleRate value
    audio_header.push_back(readBinValue(32)); // audio_header[3] = format value
    audio_header.push_back(readBinValue(4));  // audio_header[4] = channels value
    audio_header.push_back(readBinValue(2));  // audio_header[5] = number os values used in predictor
    int lossy = readBinValue(1);
    audio_header.push_back(lossy);            // audio_header[6] = mode (losse or lossless)
    if(lossy == 1){
        audio_header.push_back(readBinValue(5));  // audio_header[7] = shQuant value
    }
    return audio_header;
}
void Golomb::close(){
    file.close();
}

