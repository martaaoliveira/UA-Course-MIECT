#include <iostream>
#include <math.h>
#include <vector>
#include <sndfile.hh>

using namespace std;
constexpr size_t FRAMES_BUFFER_SIZE = 65536; // Buffer for reading frames

int main(int argc, char* argv[])
{
    //Command line arguments processing
    if(argc < 3){
		cerr << "Usage: " << argv[0] << " <original file> <quantized file> " << endl;
        return 1; 
    }
    
    SndfileHandle sndFileO { argv[argc-2] };
	if(sndFileO.error()) {
		cerr << "Error: invalid input file1\n";
		return 1;
    }

    SndfileHandle sndFileQ { argv[argc-1] };
	if(sndFileQ.error()) {
		cerr << "Error: invalid input file2\n";
		return 1;
    }

    // Check original file format
	if((sndFileO.format() & SF_FORMAT_TYPEMASK) != SF_FORMAT_WAV) {
		cerr << "Error: original file is not in WAV format\n";
		return 1;
	}
	if((sndFileO.format() & SF_FORMAT_SUBMASK) != SF_FORMAT_PCM_16) {
		cerr << "Error: original file is not in PCM_16 format\n";
		return 1;
	}

    // Check quantized file format
    if((sndFileQ.format() & SF_FORMAT_TYPEMASK) != SF_FORMAT_WAV) {
		cerr << "Error: quantized file is not in WAV format\n";
		return 1;
	}

	if((sndFileQ.format() & SF_FORMAT_SUBMASK) != SF_FORMAT_PCM_16) {
		cerr << "Error: quantized file is not in PCM_16 format\n";
		return 1;
	}

    if(sndFileO.frames() != sndFileQ.frames()) {
        cerr << "Error: files must have the same number of frames\n";
        return 1;
    }

    std::vector<short> samples_original(FRAMES_BUFFER_SIZE * sndFileO.channels());
    std::vector<short> samples_quant(FRAMES_BUFFER_SIZE * sndFileQ.channels());

    size_t nFrames;
    double signal_energy = 0, noise_energy = 0;
    double SNR;
    double maxError = 0, tmpError;

    while((nFrames = sndFileO.readf(samples_original.data(), FRAMES_BUFFER_SIZE))) {
        sndFileQ.readf(samples_quant.data(), FRAMES_BUFFER_SIZE);

        samples_original.resize(nFrames * sndFileO.channels());
        samples_quant.resize(nFrames * sndFileQ.channels());

        for (long int i = 0; i < (int)samples_original.size(); i++) {
            signal_energy += pow(samples_original[i], 2);
            tmpError = abs(samples_original[i] - samples_quant[i]);
            noise_energy += pow(tmpError, 2);
            
            if(tmpError > maxError){
                maxError = tmpError;
            }
        }    
    }

    SNR = 10 * log10(signal_energy / noise_energy);
    cout << "SNR: " << SNR << " dB\nMaximum per sample absolute error: " << maxError << endl;

    return 0;
}