#ifndef WAVHIST_H
#define WAVHIST_H

#include <iostream>
#include <vector>
#include <map>
#include <sndfile.hh>
#include <fstream>

using namespace std;
class WAVHist {
  private:
	std::vector<std::map<short, size_t>> counts;
	std::map<short, size_t> countsMono;
	std::map<short, size_t> countsSide;

  public:
	WAVHist(const SndfileHandle& sfh) {
		counts.resize(sfh.channels());
	}

	void update(const std::vector<short>& samples) {
		size_t n { };
		
		for(int i=0; i < (int) samples.size()/2; i+=2){
			counts[0][samples[i]]++;
			counts[1][samples[i+1]]++;
			countsMono[(samples[i] + samples[i+1]) / 2]++;
			countsSide[(samples[i] - samples[i+1]) / 2]++;
		}
	}

	void dump(const size_t channel) const {
		ofstream outfile;
		outfile.open("hist.dat", ios::trunc);
		for(auto [value, counter] : counts[channel]){
			outfile << value << '\t' << counter << '\n';
		}
		outfile.close();
	}

	void dumpM(void){
		ofstream outfile;
		outfile.open("histM.dat", ios::trunc);
		for(auto [value, counter] : countsMono){
			outfile << value << '\t' << counter << '\n';
		}
		outfile.close();
	}

	void dumpS(void){
		ofstream outfile;
		outfile.open("histS.dat", ios::trunc);
		for(auto [value, counter] : countsSide){
			outfile << value << '\t' << counter << '\n';
		}
		outfile.close();
	}

};

#endif

