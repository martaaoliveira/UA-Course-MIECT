#ifndef FCM_H
#define FCM_H

#include <string>
#include <unordered_map>
#include <map>
#include <vector>
using namespace std;

class fcm
{
    private:
        int k;
        double alpha;
        unordered_map<string, unordered_map<char, int>> model;

    public:
        double dist;
        double modelEntropy;
        double estimEntropy;
        int nChars;
        vector<double> entropy_char;

        fcm();

        fcm(int k,double alfa);

        void setK(int k);

        void setAlpha(double alpha);

        void clearModel();

        void createModel(const char *filename);

        void loadModelFromFile(const char *filename);

        void calculateEntropy();

        void estimate(const char *filename);

        // int estimateLocate(const char *filename, int startRead, int nChars);
        void estimateLocate(const char *filename);
};

#endif
