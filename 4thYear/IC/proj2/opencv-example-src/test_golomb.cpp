#include "Golomb.hpp"
#include <vector>
// #include "BitStream.cpp"

using namespace std;

int main(){

    int m=8;
    Golomb g = Golomb("teste.bin", 'e', m);

    for (int i = -1000; i < 1000; i++){
        int bits = g.encode(i);
    }
    g.close();

    Golomb g2 = Golomb("teste.bin", 'd', m);
    for (int i = -1000; i < 1000; i++){
        int res = g2.decode();
        if (i != res){
            cout << "ERROR: value: " << res << " != " << i << endl;
        }
    }
    g2.close();

    Golomb gt = Golomb("teste2.bin", 'e', m);
    for (int v = 1; v < 10; v++){
        vector<int> bin = gt.convertToBin(v, 5);
        for(int j = 0; j < bin.size(); j++){
            cout << bin[j];
        }
        int conv_value = gt.convertToInt(bin, 5);
        cout << " -> " << conv_value;
        cout << endl;
    }
    
    cout << endl;
    for(int i=0; i < 10; i++){
        gt.writeBinValue(i,6);
    }
    gt.close();
    Golomb gt2 = Golomb("teste2.bin", 'd', m);
    for(int i=0; i < 10; i++){
        int res = gt2.readBinValue(6);
        cout << res << endl;
    }
    gt2.close();
}

