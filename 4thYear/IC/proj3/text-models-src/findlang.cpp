#include "fcm.hpp"
#include<iostream>
#include<string>
#include <dirent.h>
#include <sys/types.h>
#include <ctime>
#include <limits>

using namespace std;

int main(int argc, char** argv){
    
    DIR *dr1;
    struct dirent *en1;
    dr1 = opendir("LANGS/"); //open all directory
    fcm f1(3, 0.2); 
    if (dr1) {
        while ((en1 = readdir(dr1)) != NULL) {
            string filename = string(en1->d_name);
            if(filename != "." && filename != "..") {
                // cout<<""<< filename << endl;
                cout << "... begin calculating" << endl;
                clock_t begin = clock();
                filename = "LANGS/" + filename;
                f1.createModel(filename.c_str());
                clock_t end = clock();
                cout << "... done calculating" << endl;
                cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl<< endl;
            }
        }
        closedir(dr1); //close all directory
    }    

    if(argc < 2){
        cerr << "Usage: " << argv[0] << " <text_analysis>"<< endl;
        return 0;
    }

    clock_t begin = clock();
    cout << "... begin calculating" << endl;
   
    DIR *dr;
    struct dirent *en;
    dr = opendir("MODELS/"); //open all directory
    fcm f;
    map<string, double> calcDist;
    if (dr) {
        while ((en = readdir(dr)) != NULL) {
            string filename = string(en->d_name);
            if(filename != "." && filename != "..") {
                // cout<<""<< filename << endl;
                string fileName = "MODELS/" + filename;
                f.loadModelFromFile(fileName.c_str());
                f.estimate(argv[1]);
                int pos = filename.find_first_of("_");
                filename = filename.substr(0, pos);
                calcDist[filename] = f.dist;
            }
        }
        closedir(dr); //close all directory
    }
    clock_t end = clock();
    cout << "... done calculating" << endl;
    cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl<< endl;
    
    string model;
    double aux = std::numeric_limits<double>::max(); // aux = inf
    for(const auto& [m, v] : calcDist){
        if(v < aux){
            model = m;
            aux = v;
        }
    }

    cout << "The text \'" << argv[1] << "\' is written in " << model << endl; 
}