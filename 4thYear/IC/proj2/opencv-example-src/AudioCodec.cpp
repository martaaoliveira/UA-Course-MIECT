#include "AudioCodec.hpp"
#include "Golomb.hpp"

#include <sndfile.hh>
#include <iostream>
#include <stdio.h>
#include <math.h>
#include <vector>
#include <map>

using namespace std;


AudioCodec::AudioCodec(const char *filename){
    sndFileIn = SndfileHandle{filename};
    // SNDFILE *fileIn;
    // int cnt_r;
    // short ch[2];

    if(sndFileIn.error()) {
        cerr << "Error: invalid input file\n";
        exit(EXIT_FAILURE);
    }

    if((sndFileIn.format() & SF_FORMAT_TYPEMASK) != SF_FORMAT_WAV) {
        cerr << "Error: file is not in WAV format\n";
        exit(EXIT_FAILURE);
    }

    if((sndFileIn.format() & SF_FORMAT_SUBMASK) != SF_FORMAT_PCM_16) {
        cerr << "Error: file is not in PCM_16 format\n";
        exit(EXIT_FAILURE);
    }
   
    size_t nChannels { static_cast<size_t>(sndFileIn.channels()) };
	size_t nFrames { static_cast<size_t>(sndFileIn.frames()) };
    vector<short> samp(nChannels * nFrames);
	sndFileIn.readf(samp.data(), nFrames);
    samples = samp;

}

void AudioCodec::encode(const char *fileOut, int nValues, int lossy, int shQuant){
    nvalues = nValues;

    if(lossy == 1) predictorLossy(shQuant);
    else predictorLossless();
    
    Golomb g(fileOut, 'e', 0);

    // Calculate the ideal value of m
    double mean = 0;
    for(short r : res_n){
        mean += g.fold(r);
    }
    mean = mean / res_n.size();
    int m = (int) ceil(-1 / (log2(mean/(mean+1))));
    g.setM(m);  // define the value of m

    
    if(lossy == 1){
        g.encodeAudioHeader(m, sndFileIn.frames(), sndFileIn.samplerate(), sndFileIn.format(), sndFileIn.channels(), nvalues, lossy, shQuant);
    }
    else{
        g.encodeAudioHeader(m, sndFileIn.frames(), sndFileIn.samplerate(), sndFileIn.format(), sndFileIn.channels(), nvalues, lossy);
    }

    for(short r : res_n){
        g.encode(r);
    }
    g.close();
}

void AudioCodec::decode(const char *fileIn, const char *fileOut){
    Golomb g(fileIn, 'd', 0);
    // vector<int> audio_header(8, 0);
    vector<int> audio_header = g.decodeAudioHeader(); // audio_header -> m, frames, sampleRate, format, channels, nvalues, lossy, <shQuant> 
    // for(int i = 0; i < audio_header.size(); i++){
    //     cout << audio_header[i] << endl;
    // }
    int m = audio_header[0];
    int frames = audio_header[1];
    int sampleRate = audio_header[2];
    int format = audio_header[3];
    int channels = audio_header[4];
    int nValues = audio_header[5];
    int lossy = audio_header[6];

    vector<short> res_samples, res_l, res_r, res_xl, res_xr;

    g.setM(m);    // define the value of m
    if(lossy == 1){
        int shQuant = audio_header[7];
        for(int i=0; i < frames * channels; i++){
            res_samples.push_back(g.decode() << shQuant);
        }
    }
    else{
        for(int i=0; i < frames * channels; i++){
            res_samples.push_back(g.decode());
        }
    }

    g.close();

    for(long unsigned i=0; i < res_samples.size()-1; i+=2){
        res_l.push_back(res_samples[i]);
        res_r.push_back(res_samples[i+1]);
    }

    vector<short> res_xn, resHat_xl, resHat_xr;

    if(nValues == 1){
        res_xl.push_back(res_l[0]);
        res_xr.push_back(res_r[0]);
        res_xn.push_back(res_l[0]);
        res_xn.push_back(res_r[0]);
        for(long unsigned i = 1; i < res_l.size(); i++) {
            res_xl.push_back((short) res_l[i] + res_xl[i-1]);
            res_xr.push_back((short) res_r[i] + res_xr[i-1]);
            res_xn.push_back(res_xl[i]);
            res_xn.push_back(res_xr[i]);
        }
    }
    else{
        for(int i=0; i < nValues; i++){
            resHat_xl.push_back(0);
            resHat_xr.push_back(0);
            res_xl.push_back(res_l[i]);
            res_xr.push_back(res_r[i]);
            res_xn.push_back(res_l[i]);
            res_xn.push_back(res_r[i]);
        }
        
        if(nValues ==2){
            for(long unsigned i=nValues; i < res_l.size(); i++){
                resHat_xl.push_back((int) (2*res_xl[i-1] - res_xl[i-2]));
                resHat_xr.push_back((int) (2*res_xr[i-1] - res_xr[i-2]));
                res_xl.push_back((short) res_l[i] + resHat_xl[i]);
                res_xr.push_back((short) res_r[i] + resHat_xr[i]);
                res_xn.push_back(res_xl[i]);
                res_xn.push_back(res_xr[i]);
            }
        }
        else{  // nValues == 3
            for(long unsigned i=nValues; i < res_l.size(); i++){
                resHat_xl.push_back((int) (3*res_xl[i-1] - 3*res_xl[i-2] + res_xl[i-3]));
                resHat_xr.push_back((int) (3*res_xr[i-1] - 3*res_xr[i-2] + res_xr[i-3]));
                res_xl.push_back((short) res_l[i] + resHat_xl[i]);
                res_xr.push_back((short) res_r[i] + resHat_xr[i]);
                res_xn.push_back(res_xl[i]);
                res_xn.push_back(res_xr[i]);    
            }
        }
    }

    SF_INFO sfinfoOut ;
    sfinfoOut.channels = channels;
    sfinfoOut.samplerate = sampleRate;
    sfinfoOut.format = format;
    sfinfoOut.frames = frames;

    SNDFILE *outfile = sf_open(fileOut, SFM_WRITE, &sfinfoOut);
    // sf_count_t count = 
    sf_write_short(outfile, &res_xn[0], res_xn.size());
    sf_write_sync(outfile);
    sf_close(outfile);
}

void AudioCodec::predictorLossless(){
    vector<short> l, r;     // vector's for storing left and right channels values
    for(long unsigned i=0; i < samples.size()-1; i+=2) {
        l.push_back(samples[i]);
        r.push_back(samples[i+1]);
    }

    vector<short> xn_l, xn_r;
    
    for(int i=0; i < nvalues; i++){
        xn_l.push_back(0);
        xn_r.push_back(0);
        res_n.push_back(l[i]-xn_l[i]);
        res_n.push_back(r[i]-xn_r[i]);
    }

    if(nvalues == 1){
        for(long unsigned i=nvalues; i < l.size(); i++){
            xn_l.push_back(l[i-1]);
            xn_r.push_back(r[i-1]);
            res_n.push_back(l[i]-xn_l[i]);
            res_n.push_back(r[i]-xn_r[i]);
        }
    }
    else if(nvalues == 2){
        for(long unsigned i=nvalues; i < l.size(); i++){
            xn_l.push_back(2*l[i-1] - l[i-2]);
            xn_r.push_back(2*r[i-1] - r[i-2]);
            res_n.push_back(l[i]-xn_l[i]);
            res_n.push_back(r[i]-xn_r[i]);
        }
    }
    else{
        for(long unsigned i=nvalues; i < l.size(); i++){
            xn_l.push_back(3*l[i-1] - 3*l[i-2] + l[i-3]);
            xn_r.push_back(3*r[i-1] - 3*r[i-2] + r[i-3]);
            res_n.push_back(l[i]-xn_l[i]);
            res_n.push_back(r[i]-xn_r[i]);
        }
    }
}

void AudioCodec::predictorLossy(int shQuant){
    vector<short> l, r;     // vector's for storing left and right channels values
    for(long unsigned i=0; i < samples.size()-1; i+=2) {
        l.push_back(samples[i]);
        r.push_back(samples[i+1]);
    }

    vector<short> xn_l, xn_r;
    
    for(int i=0; i < nvalues; i++){
        xn_l.push_back(0);
        xn_r.push_back(0);
        res_n.push_back(((l[i]-xn_l[i]) >> shQuant));
        res_n.push_back(((r[i]-xn_r[i]) >> shQuant));
        l[i] = (res_n[2*i] << shQuant) + xn_l[i];
        r[i] = (res_n[2*i+1] << shQuant) + xn_r[i];
    }

    if(nvalues == 1){
        for(long unsigned i=nvalues; i < l.size(); i++){
            xn_l.push_back(l[i-1]);
            xn_r.push_back(r[i-1]);
            res_n.push_back(((l[i]-xn_l[i]) >> shQuant));
            res_n.push_back(((r[i]-xn_r[i]) >> shQuant));
            l[i] = (res_n[2*i] << shQuant) + xn_l[i];
            r[i] = (res_n[2*i+1] << shQuant) + xn_r[i];
       }
    }
    else if(nvalues == 2){
        for(long unsigned i=nvalues; i < l.size(); i++){
            xn_l.push_back(2*l[i-1] - l[i-2]);
            xn_r.push_back(2*r[i-1] - r[i-2]);
            res_n.push_back(((l[i]-xn_l[i]) >> shQuant));
            res_n.push_back(((r[i]-xn_r[i]) >> shQuant));
            l[i] = (res_n[2*i] << shQuant) + xn_l[i];
            r[i] = (res_n[2*i+1] << shQuant) + xn_r[i];
        }
    }
    else{
        for(long unsigned i=nvalues; i < l.size(); i++){
            xn_l.push_back(3*l[i-1] - 3*l[i-2] + l[i-3]);
            xn_r.push_back(3*r[i-1] - 3*r[i-2] + r[i-3]);
            res_n.push_back(((l[i]-xn_l[i]) >> shQuant));
            res_n.push_back(((r[i]-xn_r[i]) >> shQuant));
            l[i] = (res_n[2*i] << shQuant) + xn_l[i];
            r[i] = (res_n[2*i+1] << shQuant) + xn_r[i];
        }
    }
}

vector<double> AudioCodec::calculateEntropy(){
    map<short, int> original;
    map<short, int> pred;
    vector<double> out_entropy(2,0);

    for(int i = 0; i < (int) samples.size(); i++) {
        original[samples[i]]++;
        pred[res_n[i]]++;
    }

    double entropy = 0;
    double p;
    for(auto i : original) {
        p = (double) i.second / (double) samples.size();
        entropy += p * (-log2(p));
    }
    out_entropy[0] = entropy;

    double entropy_pred = 0;
    for(auto i : pred) {
        p = (double) i.second / (double) res_n.size();
        entropy_pred += p * (-log2(p));
    }
    out_entropy[1] = entropy_pred;

    return out_entropy;
}