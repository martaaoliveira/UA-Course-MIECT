#ifndef WAVQUANT_H
#define WAVQUANT_H

#include <iostream>
#include <vector>
#include <map>
#include <sndfile.hh>
#include<bits/stdc++.h>

using namespace std;

class WAVQuant {
  private:
	std::vector<short> samples_quant { };

  public:
	WAVQuant() {
		samples_quant.resize(0);
	}

	void quant_samples(const std::vector<short>& samples, size_t bits_disc) {
    for(auto sample : samples){
      sample = (sample >> bits_disc) << bits_disc;
      samples_quant.push_back(sample);
		}
	}

	void writeFile(SndfileHandle sndFileOut) const {
      sndFileOut.write(samples_quant.data(), samples_quant.size());
    }

};

#endif