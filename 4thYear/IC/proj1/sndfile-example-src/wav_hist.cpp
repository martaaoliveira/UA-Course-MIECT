#include <iostream>
#include <vector>
#include <sndfile.hh>
#include "wav_hist.h"

using namespace std;

constexpr size_t FRAMES_BUFFER_SIZE = 65536; // Buffer for reading frames

int main(int argc, char *argv[]) {

	if(argc < 3) {
		cerr << "Usage: " << argv[0] << " <input file> <channel> (0 or 1 for left or right, s for side and m for mid)\n";
		return 1;
	}

	SndfileHandle sndFile { argv[argc-2] };
	if(sndFile.error()) {
		cerr << "Error: invalid input file\n";
		return 1;
    }

	if((sndFile.format() & SF_FORMAT_TYPEMASK) != SF_FORMAT_WAV) {
		cerr << "Error: file is not in WAV format\n";
		return 1;
	}

	if((sndFile.format() & SF_FORMAT_SUBMASK) != SF_FORMAT_PCM_16) {
		cerr << "Error: file is not in PCM_16 format\n";
		return 1;
	}
	
	int channel { };
	static int hist_mode;
	std :: string str(argv[argc-1]);
	
	if(str.compare("0") == 0 || str.compare("1") == 0){
		int channel { stoi(argv[argc-1]) };
		hist_mode = 0;
	}
	else if (str.compare("m") == 0) {
		hist_mode = 1;
	}
	else if(str.compare("s") == 0){
		hist_mode = 2;
	}
	else{
		cerr << "Error: invalid channel requested\n";
		return 1;
	}
	size_t nFrames;
	vector<short> samples(FRAMES_BUFFER_SIZE * sndFile.channels());
	WAVHist hist { sndFile };
	while((nFrames = sndFile.readf(samples.data(), FRAMES_BUFFER_SIZE))) {
		samples.resize(nFrames * sndFile.channels());
		hist.update(samples);
	}
	
	if (hist_mode == 0){
		hist.dump(channel);
		cout << "Histogram saved on \'hist.dat\'" << endl;
		cout << "Plot using gnuplot -> plot \"hist.dat\"" << endl;
	}
	else if(hist_mode == 1){
		hist.dumpM();
		cout << "Histogram saved on \'histM.dat\'" << endl;
		cout << "Plot using gnuplot -> plot \"histM.dat\"" << endl;
	}
	else {
		hist.dumpS();
		cout << "Histogram saved on \'histS.dat\'" << endl;
		cout << "Plot using gnuplot -> plot \"histS.dat\"" << endl;
	}

		
	return 0;
}

