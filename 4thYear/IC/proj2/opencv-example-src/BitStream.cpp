#include <iostream>
#include <fstream>
#include "BitStream.hpp"
using namespace std;


// write mode = 0
// read mode = 1
BitStream::BitStream(){}

BitStream::BitStream(string filename, char mode){

   if(mode == 'w'){ 
        file = fstream(filename, ios::binary | ios::out); //https://courses.cs.vt.edu/cs2604/fall02/binio.html - a forma de abrir ficheiros binarios é diferente
        this->mode=0;
        this->filename = filename;
        currentPos = 0;
        
    }else if(mode == 'r'){ 
        file = fstream(filename, ios::binary | ios::in);
        this->mode = 1;
        this->filename = filename;
        file.seekg(0, ios::end);
        fileSize = file.tellg();
        file.seekg(0, ios::beg);
        currentPos = 0;

    }else{
        cerr<<"Error: Invalid mode selected"<<endl;
        exit(1);
    }
    
    if(!file.is_open()){
        throw runtime_error("Could not open file");
    }
    
}

int BitStream::readBit() {
    if (mode ==0) {
        cerr << "Cannot read file in write mode" << endl;
        exit(1);
    }
    
    if (currentPos == 0) {
        char byte;
        file.read(&byte, 1);
        buffer = byteToBuffer(byte);
    }
    int bit = buffer[currentPos];
    currentPos = (currentPos + 1) % 8;
    return bit;
}

std::vector<int> BitStream::readNbits(int n){
    if(mode == 0) {
        cerr<<"Cannot read in write mode"<<endl;
        exit(1);
    }

    std::vector<int> bits;
    for(int i = 0; i < n; i++){
        bits.push_back(readBit());
    }

    return bits;
}

//https://stackoverflow.com/questions/1856514/writing-files-in-bit-form-to-a-file-in-c?rq=1
//You can't write a single bit directly to a file. The I/O unit of reading/writing is a byte (8-bits). So you need to pack your bools into chunks of 8 bits and then write the bytes.
//Collect bits until you have enough bits to fill a byte and then write it
//

void BitStream::writeBit(int bit)
{
    if(mode == 1) {
        cerr <<"Cannot write bit in read mode"<< endl;
        exit(1);
    }
    
    // if the current bit position is 8, the byte will be written and the current position will be set to 0
    if (currentPos == 8){
        char byte = bufferToByte(buffer);
        file.write(&byte, 1);
        currentPos = 0;
    }

    // if the current pos is 0, the buffer needs to be initialized
    if (currentPos == 0) buffer = std::vector<int>(8);

    buffer[currentPos] = bit; // put the current bit on the buffer
    currentPos++;
}

void BitStream::writeNbits(std::vector<int> bits){
    if(mode == 1) {   
        cerr<<"Cannot write in read mode"<<endl;
        exit(1);
    }

    int size = bits.size();

    for(int i = 0; i < size; i++){
        writeBit(bits[i]);
    }
}


std::vector<int> BitStream::byteToBuffer(char b) {
    //read the byte and insert into the buffer 
    std::vector<int> buffer;
    for (int i = 0; i < 8; i++){
        buffer.push_back((b >> i) & 1);
    }
    
    if (mode == 1){
        //reverse the buffer
        std::vector<int> reversedBuffer;
        for (int i = 7; i >= 0; i--){
            reversedBuffer.push_back(buffer[i]);
        }
        return reversedBuffer;
    }

    return buffer;
}   

char BitStream::bufferToByte(std::vector<int> buffer) {
    char byte = 0;

    std::vector<int> reversedBuffer;
    for (int i = 7; i >= 0; i--){
        reversedBuffer.push_back(buffer[i]);
    }

    for (int i = 0; i < 8; i++){
        byte |= reversedBuffer[i] << i;
    }

    return byte;
}

 int BitStream::getFileSize() {
        std::fstream file2;
        file2.open(filename, std::ios::in | std::ios::binary);
        file2.seekg(0, std::ios::end);
        int size = file2.tellg();
        file2.seekg(0, std::ios::beg);
        file2.close();
        return size;
}

void BitStream::close(){ 
    if(mode == 0){
        char byte = bufferToByte(buffer);
        file.write(&byte, 1);   
    }
    file.close();
}



