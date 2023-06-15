#include "Golomb.hpp"
#include "BitStream.hpp"
#include <vector>
// #include "BitStream.cpp"

using namespace std;

int main(){
    Golomb g = Golomb("testEncode.bin", 'e', 5);
   
    g.encodeAudioHeader(5,10,7,2,5,2,1);
    g.close();
    Golomb g2 = Golomb("testEncode.bin", 'd', 5);
    vector<int> audio_header;
    audio_header = g2.decodeAudioHeader();
    g2.close();
    
    for(int i=0; i < audio_header.size(); i++){
        cout << audio_header[i] << endl;
    }

}

