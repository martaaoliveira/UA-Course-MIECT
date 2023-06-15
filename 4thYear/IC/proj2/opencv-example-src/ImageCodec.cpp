#include "ImageCodec.hpp"
#include "Golomb.hpp"
#include <algorithm>

#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
// #include "opencv2/opencv.hpp"


void restore(uchar *data, vector<int>& res, int nRows, int nCols);
void reverse(uchar *in, uchar *out, int size);
vector<int> apply(Mat m);
int calculateM(Golomb& g, vector<int>& res_Y, vector<int>& res_U, vector<int>& res_V);
int predictorJLS(int a, int b, int c);

using namespace cv;
using namespace std;

ImageCodec::ImageCodec(const char *filename){
    Mat img = imread(filename, IMREAD_COLOR);

    if(img.empty()){
        throw new runtime_error("ERROR: Could not generate mat");
    }

    this->filename = (char*) filename;
    convertYUV420(img);
}

void ImageCodec::encode(const char *fileOut){
    vector<int> res_Y = apply(Y);
    vector<int> res_U = apply(U);
    vector<int> res_V = apply(V);
    
    int m = 0;
    Golomb g = Golomb(fileOut, 'e', m);
    m = calculateM(g, res_Y, res_U, res_V);
    g.setM(m);

    g.writeBinValue(m, 32);     // m (32bits)
    g.encode(Y.cols);
    g.encode(Y.rows);

    for(int i = 0; i < Y.cols * Y.rows; i++){
        g.encode(res_Y[i]);
    }
    for(int i = 0; i < U.cols * U.rows; i++){
        g.encode(res_U[i]);
    }
    for(int i = 0; i < V.cols * V.rows; i++){
        g.encode(res_V[i]);
    }
    g.close();
}


void ImageCodec::decode(const char *fileIn, const char *fileOut){
    Golomb g = Golomb(fileIn, 'd', 0);

    int m = g.readBinValue(32);
    g.setM(m);

    int nCols = g.decode();
    int nRows = g.decode();

    vector<int> res_Y; 
    vector<int> res_U; 
    vector<int> res_V;

    for(int i = 0; i < nCols*nRows; i++){
        res_Y.push_back(g.decode());
    }
    for(int i = 0; i < (nCols/2)*(nRows/2); i++){
        res_U.push_back(g.decode());
    }
    for(int i = 0; i < (nCols/2)*(nRows/2); i++){
        res_V.push_back(g.decode());
    }

    g.close();

    Y = Mat(nRows, nCols, CV_8UC1);
    Mat aux_U = Mat(nRows/2, nCols/2, CV_8UC1);
    Mat aux_V = Mat(nRows/2, nCols/2, CV_8UC1);

    uchar data_Y[Y.rows * Y.cols];
    uchar data_U[U.rows * U.cols]; 
    uchar dataV[V.rows * V.cols]; 

    int aux = Y.rows * Y.cols;

    restore(data_Y, res_Y, Y.rows, Y.cols);
    uchar outData_Y[Y.rows * Y.cols]; 
    reverse(data_Y, outData_Y, aux);

    restore(data_U, res_U, aux_U.rows, aux_U.cols);
    uchar outData_U[U.rows * U.cols];
    reverse(data_U, outData_U, aux/4);

    restore(dataV, res_V, aux_V.rows, aux_V.cols);
    uchar outData_V[V.rows * V.cols];
    reverse(dataV, outData_V, aux/4);

    Y.data = outData_Y;
    aux_U.data = outData_U;
    aux_V.data = outData_V;

    Mat m_aux(nRows, nCols, CV_8UC3);
    convertRGB(m_aux, aux_U, aux_V);
    imwrite(fileOut, m_aux);
}

void ImageCodec::convertYUV420(Mat m){
    // Y = 0.299R + 0.587G + 0.114B
    // Cb = 128 − 0.168736R − 0.331264G + 0.5B
    // Cr = 128 + 0.5R − 0.418688G − 0.081312B
    // aux[0] -> B // aux[1] -> G // aux[2] -> R
    Y = Mat(m.size(), CV_8UC1);
    Mat aux_U = Mat(m.size(), CV_8UC1);
    Mat aux_V = Mat(m.size(), CV_8UC1);
    Vec3b aux;

    for(int i = 0; i < m.rows ; i++){
        for(int j = 0; j < m.cols; j++){
            aux = m.at<Vec3b>(i, j);
            
            double y, u ,v;
            y = 0.299 * aux[2] + 0.587 * aux[1] + 0.114 * aux[0];
            u = 128 + (-0.169) * aux[2] - 0.331 * aux[1] + 0.5 * aux[0];
            v = 128 + 0.5 * aux[2] - 0.419 * aux[1] - 0.081 * aux[2];

            Y.at<uchar>(i, j) = y;
            aux_U.at<uchar>(i, j) = u;
            aux_V.at<uchar>(i, j) = v;
        }  
    }

    U = Mat(m.rows/2, m.cols/2, CV_8UC1);
    V = Mat(m.rows/2, m.cols/2, CV_8UC1);

    uchar bl, br, tr, tl;
    int sum, mean;

    for(int y = 0; y < aux_U.rows - 1; y += 2){
        for(int x = 0; x < aux_U.cols - 1; x += 2){
            bl = aux_U.at<uchar>(y, x);
            br = aux_U.at<uchar>(y + 1, x);
            tr = aux_U.at<uchar>(y, x + 1);
            tl = aux_U.at<uchar>(y + 1 , x + 1);

            sum = bl + br + tl + tr;

            mean = round((double) sum / 4);

            U.at<uchar>(y/2, x/2) = mean;

            bl = aux_V.at<uchar>(y, x);
            br = aux_V.at<uchar>(y + 1, x);
            tr = aux_V.at<uchar>(y, x + 1);
            tl = aux_V.at<uchar>(y + 1 , x + 1);

            sum = bl + br + tl + tr;

            mean = round((double) sum / 4);

            V.at<uchar>(y/2, x/2) = mean;
        }
    }
}

void ImageCodec::convertRGB(Mat &m, Mat &aux_U, Mat &aux_V){
    U = Mat(m.rows, m.cols, CV_8UC1);
    V = Mat(m.rows, m.cols, CV_8UC1);

    uchar Yp, u, v;
    int x2 = 0; 
    int y2 = 0;

    for(int y = 0; y < aux_U.rows; y++){
        for(int x = 0; x < aux_U.cols; x++){
            u = aux_U.at<uchar>(y, x);
            v = aux_V.at<uchar>(y, x);

            x2 = x*2;
            y2 = y*2;

            U.at<uchar>(y2, x2) = u;
            U.at<uchar>(y2 + 1, x2) = u;
            U.at<uchar>(y2, x2 + 1) = u;
            U.at<uchar>(y2 + 1, x2 + 1) = u;

            V.at<uchar>(y2, x2) = v;
            V.at<uchar>(y2 + 1, x2) = v;
            V.at<uchar>(y2, x2 + 1) = v;
            V.at<uchar>(y2 + 1, x2 + 1) = v;
        }
    }

    Vec3b BGR;
    for(int y = 0; y < Y.rows; y++){
        for(int x = 0; x < Y.cols; x++){
            Yp = Y.at<uchar>(y, x);
            u = U.at<uchar>(y, x);
            v = V.at<uchar>(y, x);
            
            BGR[2] = Yp + 1.400 * (v - 128);
            BGR[1] = Yp - 0.343 * (u - 128) - 0.711 * (v - 128);
            BGR[0] = Yp + 1.765 * (u - 128);

            m.at<Vec3b>(y, x) = BGR;
        }
    }
}

int calculateM(Golomb& g, vector<int>& res_Y, vector<int>& res_U, vector<int>& res_V){
    double mean;
    map<int, int> aux;
    for(int i : res_Y) aux[i]++;
    for(int i : res_U) aux[i]++;
    for(int i : res_V) aux[i]++;

    int s = 0;
    for(auto i : aux) s += i.second;
    for(auto i : aux) mean += g.fold(i.first) * ((double)i.second / s);

    return ceil(-1 / log2(mean / (mean + 1)));
}

int predictorJLS(int a, int b, int c){
    if (c >= max(a, b))
        return min(a, b);
    else if (c < min(a, b))
        return max(a, b);
    else
        return a + b - c;
}

void reverse(uchar *in, uchar *out, int size){
    for(int i=0; i<size; i++){
        out[i] = in[(size - 1) - i];
    }
}

void restore(uchar *data, vector<int>& res, int nRows, int nCols){
    for(int i = 0; i < nCols; i++){
        data[i] = res[i];
    }
    int a, b, c, rn, xCn;
    for(int i = nCols; i < nCols * nRows; i++){
        if (i % nCols == 0){
            data[i] = res[i];
            continue;
        }
        a = data[i - 1];
        b = data[i - nCols];
        c = data[i - nCols - 1];
        rn = res[i];
        xCn = predictorJLS(a, b, c);
        data[i] = (uchar) rn + xCn;
    }
}

vector<int> apply(Mat m){
    vector<int> out;
    int a, b, c;
    for(int x = m.cols - 1; x > - 1; x--){
        out.push_back(m.at<uchar>(m.rows - 1, x));
    }
    for(int y = m.rows - 2; y > -1 ; y--){
        for(int x = m.cols - 1; x > -1; x--){
            if(x == m.cols - 1){
                out.push_back(m.at<uchar>(y, x));
                continue;
            }
            a = m.at<uchar>(y, x + 1);
            b = m.at<uchar>(y + 1, x);
            c = m.at<uchar>(y + 1, x + 1);
            out.push_back(m.at<uchar>(y, x) - predictorJLS(a, b, c));
        }
    }
    return out;
}