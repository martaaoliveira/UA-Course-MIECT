#ifndef AUDIOCODED_H
#define AUDIOCODED_H

#include <stdio.h>
#include <sndfile.hh>
#include <vector>

class AudioCodec{
    private:
        char* filename;
        SF_INFO sfinfo;
        SndfileHandle sndFileIn;
        std::vector<short> samples = {};    // samples of each channel
        std::vector<short> res_n = {};      // residuals values
        int nvalues;

    public:
        AudioCodec(const char *filename);

        void encode(const char *fileOut, int nValues, int lossy, int shQuant=0);

        void decode(const char *fileIn, const char *fileOut);

        void predictorLossless();

        void predictorLossy(int shQuant);

        std::vector<double> calculateEntropy();
};
#endif