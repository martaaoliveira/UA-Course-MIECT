#ifndef IMAGECODEC_H
#define IMAGECODEC_H

#include <opencv2/opencv.hpp>
#include <opencv2/core.hpp>
// #include "opencv2/opencv.hpp"

using namespace cv;

class ImageCodec{
    private:
        char* filename;
        Mat Y, U, V;

    public:
        ImageCodec(const char *filename);

        void encode(const char *fileOut);

        // void encodeLossy(const char *fileOut, int yQ, int uQ, int vQ); //number of bits to be quantized in each channels

        void decode(const char *fileIn, const char *fileOut);

        void convertYUV420(Mat m);

        void convertRGB(Mat &m, Mat &auxU, Mat &auxV);

        
};

#endif