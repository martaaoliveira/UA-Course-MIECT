#include "ImageCodec.hpp"

using namespace std;
int main(int argc, char* argv[]){
    if(argc < 2){
        cerr << "Usage: " << argv[0] << " <input filename>"<< endl;
        return 0;
    }

    char *filename = argv[1];
    ImageCodec codec(filename);
 
    clock_t begin = clock();
    cout << "... begin encoding" << endl;
    codec.encode("encoded_img.bin");
    clock_t end = clock();
    cout << "... done encoding" << endl;
    cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl << endl;

    clock_t begin2 = clock();
    cout << "... begin decoding" << endl;
    codec.decode("encoded_img.bin", "restored_img.ppm");
    clock_t end2 = clock();
    cout << "... done decoding" << endl;
    cout <<"Duration: " <<  (double(end2 - begin2) / CLOCKS_PER_SEC) << " seconds" << endl;
}