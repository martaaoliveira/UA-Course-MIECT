#include <iostream>
#include <vector>
#include <sndfile.hh>
#include <math.h>

using namespace std;

constexpr size_t FRAMES_BUFFER_SIZE = 65536; // Buffer for reading frames

int main(int argc, char *argv[]) {

    if(argc <3) {
		cerr << "Usage: " << argv[0] << " <input file> <output_file> <effect>\n<effect> is 's' for single echo or 'm' for multiple echo or 'r' for reverse or 'a' for amplitude modulation\n";
		return 1;
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

    SndfileHandle sndFileOut { argv[2], SFM_WRITE, sndFileIn.format(),sndFileIn.channels(), sndFileIn.samplerate() };
	if(sndFileOut.error()) {
		cerr << "Error: invalid output file\n";
		return 1;
    }

    string effect = argv[3];    
    float alpha;
    int delay;
    double f;
    if(effect=="s"){
        cout<<"Insert: <gain> <delay>: " ;
    try {
        cin>>alpha;
        cin>>delay;
    }catch(exception &e){
        cerr << "Error: invalid gain or delay\n";
        exit(1);
    }
    }

    else if(effect=="a"){
        cout<<"Usage: <frequency>: ";
    try {
        cin>>f;
    }catch(exception &e){
        cerr << "Error: invalid frequency\n";
        exit(1);
    }
    }


    if(effect=="m"){
        cout<<"Usage: <gain>: ";
    try {
        cin>>alpha;
    }catch(exception &e){
        cerr << "Error: invalid gain\n";
        exit(1);
    }
    }


    
    vector<short> samples_original(FRAMES_BUFFER_SIZE * sndFileIn.channels());
    vector<short> samples_out;
    size_t size;
    double fa=sndFileIn.samplerate(); //frequencia de amostragem
    samples_out.resize(0);
    short single_sample_out; 

// y(n) = (x(n) + alpha * x(n-delay))/(1+alpha) -> single echo
// y(n) = (x(n) + alpha * y(n-1))/(1+alpha) -> multiple eco
// y(n) = x(n) * cos(2*pi*(f/fa)*n) -> amplitude modulation
 
    if (effect == "s" || effect == "m") {

        while((size = sndFileIn.readf(samples_original.data(), FRAMES_BUFFER_SIZE))) {
            samples_original.resize(size * sndFileIn.channels());
            for (int i = 0; i < samples_original.size(); i++) {
                if (effect == "s") {
                    if (i >= delay) single_sample_out = (samples_original[i] + alpha * samples_original.at(i - delay))/(1 + alpha);
     
                    else single_sample_out = samples_original[i];
                    
                } else if (effect == "m") { 
                    if(i==0)single_sample_out = samples_original[i];
                    else single_sample_out = (samples_original[i] + alpha * samples_out.at(i - 1))/(1 + alpha);
                }               

                samples_out.insert(samples_out.end(), single_sample_out);
            }
        }
    }

    else if(effect=="r"){
        while((size = sndFileIn.readf(samples_original.data(), FRAMES_BUFFER_SIZE))) {
            samples_original.resize(size * sndFileIn.channels());
            for (int i = (int)samples_original.size() - 1; i >= 0; i--) samples_out.insert(samples_out.end(), samples_original.at(i));
            }
    }


    else if(effect=="a"){
         while((size = sndFileIn.readf(samples_original.data(), FRAMES_BUFFER_SIZE))) {
            samples_original.resize(size * sndFileIn.channels());
            for (int i = 0; i < samples_original.size(); i++) {
                single_sample_out = samples_original.at(i) * cos((2 * M_PI * (0.1/fa) * i));
                samples_out.insert(samples_out.end(), single_sample_out);
            }
        }
    }

    else{
        cerr << "Error: invalid affect\n<effect> is 's' for single echo or 'm' for multiple echo or 'r' for reverse or 'a' for amplitude modulation\n";
        exit(1);
    }

    sndFileOut.writef(samples_out.data(), samples_out.size() / sndFileIn.channels());
    
}


