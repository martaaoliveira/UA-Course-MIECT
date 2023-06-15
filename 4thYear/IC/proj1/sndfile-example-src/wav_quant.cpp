#include <iostream>
#include <vector>
#include <math.h>
#include <sndfile.hh>
#include "wav_quant.h"

using namespace std;
constexpr size_t FRAMES_BUFFER_SIZE = 65536; // Buffer for reading frames

int main(int argc, char* argv[]){


    if(argc < 4){
        cerr << "Usage: " << argv[0] << " <input filename> <output filename> <number of bits to discard>"<< endl;
        return 0;
    }

    SndfileHandle sndFileIn { argv[1] };
	if(sndFileIn.error()) {
		cerr << "Error: invalid input file\n";
		return 1;
    }

	if((sndFileIn.format() & SF_FORMAT_TYPEMASK) != SF_FORMAT_WAV) {
		cerr << "Error: file is not in WAV format\n";
		return 1;
	}

	if((sndFileIn.format() & SF_FORMAT_SUBMASK) != SF_FORMAT_PCM_16) {
		cerr << "Error: file is not in PCM_16 format\n";
		return 1;
	}

    SndfileHandle sndFileOut { argv[argc-2], SFM_WRITE, sndFileIn.format(), sndFileIn.channels(), sndFileIn.samplerate() };
	if(sndFileOut.error()) {
		cerr << "Error: invalid output file\n";
		return 1;
    }

    int bits_disc;
    try { 
        bits_disc = stoi(argv[3]);
        if (bits_disc > 16 || bits_disc < 1){
            cerr << "Error: Please enter a number of bits to discard between 1 and 16." << endl;
            return 1;
        }
    } catch (const std::exception& e) { 
        cerr << "Error: Please enter a valid integer number of bits." << endl;
        return 1;
    }

    vector<short> samples(FRAMES_BUFFER_SIZE * sndFileIn.channels());
    WAVQuant quant {  };
    
    size_t nFrames;
    while((nFrames = sndFileIn.readf(samples.data(), FRAMES_BUFFER_SIZE))) {
        samples.resize(nFrames * sndFileIn.channels());
        quant.quant_samples(samples, bits_disc);
    }
   
    quant.writeFile(sndFileOut);
    
    return 0;
}