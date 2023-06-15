#include "fcm.hpp"
#include<iostream>
#include<string>
#include <dirent.h>
#include <sys/types.h>
#include <ctime>
#include <limits>
#include <vector>
#include <fstream>
#include <algorithm> 
#include <numeric>


using namespace std;

struct Section {
    string modelname;
    int start;
    int end;
    double mean;
};

struct Section_f {
    string modelname;
    int start;
    int end;
};

// int countChars(ifstream &ifs);
vector<Section> findSections(string modelname, const vector<double>& v);
vector<Section> filterSections(vector<Section> sections);
vector<Section> orderSections(vector<Section> sections);
double calculateMean(const vector<double>& values, size_t start, size_t end);


int main(int argc, char** argv){
    // DIR *dr1;
    // struct dirent *en1;
    // dr1 = opendir("LANGS/"); //open all directory
    // fcm f1(4, 1); 
    // if (dr1) {
    //     while ((en1 = readdir(dr1)) != NULL) {
    //         cout << "... begin calculating" << endl;
    //         clock_t begin = clock();
    //         string filename = string(en1->d_name);
    //         if(filename != "." && filename != "..") {
    //             // cout<<""<< filename << endl;
    //             filename = "LANGS/" + filename;
    //             f1.createModel(filename.c_str());
    //                 clock_t end = clock();
    //             cout << "... done calculating" << endl;
    //             cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl<< endl;
    //         }
    //     }
    //     closedir(dr1); //close all directory
    // }    
    if(argc < 2){
        cerr << "Usage: " << argv[0] << " <text_analysis>"<< endl;
        return 0;
    }

    clock_t begin = clock();
    cout << "... begin loading the models.." << endl;
   
    DIR *dr;
    struct dirent *en;
    dr = opendir("MODELS/"); //open all directory
    vector<string> modelsList;
    vector<fcm> models;
    map<string, double> calcDist;
    if (dr) {
        while ((en = readdir(dr)) != NULL) {
            fcm f; 
            string filename = string(en->d_name);
            if(filename != "." && filename != "..") {
                // cout<<""<< filename << endl;
                filename = "MODELS/" + filename;
                modelsList.push_back(filename);
                f.loadModelFromFile(filename.c_str());
                models.push_back(f);
            }
        }
        closedir(dr); //close all directory
    }
    clock_t end = clock();
    cout << "... done loading" << endl;
    cout <<"Duration: " <<  (double(end - begin) / CLOCKS_PER_SEC) << " seconds" << endl<< endl;
    
    // string fileName = "example2.txt";

    // int totalChars = countChars(ifs);
    // cout << "TOTAL: " << totalChars << endl;
   
    for(long unsigned int i=0; i < models.size(); i++){
        // f = models[i];
        // f.loadModelFromFile(modelsList[i].c_str());
        // models[i].estimateLocate(fileName.c_str());
        // models[i] = f;
        models[i].estimateLocate(argv[1]);
    }

    vector<vector<double>> meansModels;
    fcm f;
    for(long unsigned int i=0; i < models.size(); i++){
        f = models[i];
        double v = 0;
        int size = f.entropy_char.size();
        vector<double> aux;
        double mean;
        for(int i=0; i < size; i++){
            v += f.entropy_char[i];
            if((i % 30 == 0) && (i > 0)){
                mean = v/30;
                aux.push_back(mean);
                v = 0;
            }
            if((i == size-1) && (size % 30 != 0)){
                mean = v / (size % 30);
                aux.push_back(mean);
                v = 0;
            }
        }
        meansModels.push_back(aux);
    }

    vector<Section> langSegments;
    for(long unsigned int i=0; i < meansModels.size(); i++){
        // langSegments.push_back(findSections(modelsList[i], meansModels[i]));
        vector<Section> aux = findSections(modelsList[i], meansModels[i]);
        langSegments.insert(langSegments.end(), aux.begin(), aux.end());
    }

    vector<Section> langSegmentsF = filterSections(langSegments);
    langSegmentsF = orderSections(langSegmentsF);
    // for (const Section& s : langSegmentsF) {
    //     cout << s.modelname << " segments: " << endl; 
    //     cout << s.start  << " -> " << s.end << endl;
    //     cout << "Mean: " << s.mean << endl << endl;
    // }

    vector<Section_f> langSegments_final;
    int aux2;
    for(long unsigned int i=0; i < langSegmentsF.size()-1; i++){
        const Section& s1 = langSegmentsF[i];
        const Section& s2 = langSegmentsF[i+1];
        auto it1 = std::find(modelsList.begin(), modelsList.end(), s1.modelname);
        std::size_t pos_model1 = std::distance(modelsList.begin(), it1);  //position of the model in the list
        auto it2 = std::find(modelsList.begin(), modelsList.end(), s2.modelname);
        std::size_t pos_model2 = std::distance(modelsList.begin(), it2);  //position of the model in the list
        int pos_char = s1.end * 30; // real position of the char
        vector<double> entropy_model1 = models[pos_model1].entropy_char;
        vector<double> entropy_model2 = models[pos_model2].entropy_char;
        int aux = 3;
        double mean1, mean2;
        bool flagUpper = true;

        // cleaning the modelname for better understanding of the output
        int pos = s1.modelname.find_first_of("/");
        string s1_modelname = s1.modelname.substr(pos+1);
        pos = s1_modelname.find_first_of("_");
        s1_modelname = s1_modelname.substr(0,pos);
        pos = s2.modelname.find_first_of("/");
        string s2_modelname = s2.modelname.substr(pos+1);
        pos = s2_modelname.find_first_of("_");
        s2_modelname = s2_modelname.substr(0,pos);

        mean1 = calculateMean(entropy_model1, pos_char, pos_char+aux);
        mean2 = calculateMean(entropy_model2, pos_char, pos_char+aux);
        if(mean1 < mean2){
            while(mean1 < mean2){
                aux += 3;
                mean1 = calculateMean(entropy_model1, pos_char+aux-3, pos_char+aux);
                mean2 = calculateMean(entropy_model2, pos_char+aux-3, pos_char+aux);
            }
        }
        else{
            flagUpper = false;
            mean1 = calculateMean(entropy_model1, pos_char-aux, pos_char);
            mean2 = calculateMean(entropy_model2, pos_char-aux, pos_char);
            while(mean1 > mean2){
                aux += 3;
                mean1 = calculateMean(entropy_model1, pos_char-aux, pos_char-aux+3);
                mean2 = calculateMean(entropy_model2, pos_char-aux, pos_char-aux+3);
            }
        }
        if(i == 0){
            if(flagUpper){
                langSegments_final.push_back({s1_modelname, s1.start, pos_char+aux-3});
                aux2 = pos_char+aux-3;
            }
            else{
                langSegments_final.push_back({s1_modelname, s1.start, pos_char-aux+3});
                aux2 = pos_char-aux+3;
            }
        }
        else if(i == langSegmentsF.size()-2){
            if(flagUpper){
                langSegments_final.push_back({s1_modelname, aux2, pos_char+aux-3});
                langSegments_final.push_back({s2_modelname, pos_char+aux-3, (int)entropy_model2.size()+3});
            }
            else{
                langSegments_final.push_back({s1_modelname, aux2, pos_char-aux+3});
                langSegments_final.push_back({s2_modelname, pos_char-aux+3, (int)entropy_model2.size()+3});
            }
        }
        else{
            if(flagUpper){
                langSegments_final.push_back({s1_modelname, aux2, pos_char+aux-3});
                aux2 = pos_char+aux-3;
            }
            else{
                langSegments_final.push_back({s1_modelname, aux2, pos_char-aux+3});
                aux2 = pos_char-aux+3;
            }
        }

    }

    // for (const Section_f& s : langSegments_final) {
    //     cout << s.modelname << " segments: " << endl; 
    //     cout << s.start  << " -> " << s.end << endl;
    // }

    ifstream ifs(argv[1], std::ios::in);
    ifs.seekg (0, ifs.end);
    int length = ifs.tellg();
    ifs.seekg (0, ifs.beg);
    char * text = new char [length];
    ifs.read (text,length);

    string text_str(text);
    string sec_text;
    for (const Section_f& s : langSegments_final) {
        int ini_pos = s.start;
        int final_pos = s.end;

        sec_text = text_str.substr(ini_pos, final_pos - ini_pos);
        cout << s.modelname << " (" << ini_pos << " - " << (final_pos-1) << ")" <<  " -> " << sec_text << endl << endl;
    }
    
    // vector<double> pt_model = meansModels[7];
    // vector<double> esp_model = meansModels[16];
    // vector<double> fr_model = meansModels[1];

    // for(int i=0; i < aux1.size(); i++){
    //     if(aux2[i] < aux1[i]) count++; 
    // }
    // cout << "TOTAL: " << aux1.size() << endl;
    // cout << "PT res: " << count << endl;

    // ofstream outfile;
	// outfile.open("resultsPT.dat", ios::trunc);    
    // for(int i=0; i < pt_model.size(); i++){
    //     outfile << i << '\t' << pt_model[i] << '\n';
    // }
    // outfile.close();

    // ofstream outfile2;
	// outfile2.open("resultsESP.dat", ios::trunc);    
    // for(int i=0; i < esp_model.size(); i++){
    //     outfile2 << i << '\t' << esp_model[i] << '\n';
    // }
    // outfile2.close();

    // ofstream outfile3;
	// outfile3.open("resultsFR.dat", ios::trunc);    
    // for(int i=0; i < fr_model.size(); i++){
    //     outfile3 << i << '\t' << fr_model[i] << '\n';
    // }
    // outfile3.close();
}

vector<Section> findSections(string modelname, const vector<double>& v) {
    vector<Section> result; // Initialize result vector
    int start = -1; // Initialize start index to an invalid value
    for (long unsigned int i = 0; i < v.size(); i++) {
        if (v[i] < 3.5) {
            if (start == -1) {
                // start of a new section
                start = i;
            }
        } else {
            if (start != -1) {
                // end of a section
                if (i - start > 1) {
                    // The section has 2 or more values, so it is valid
                    vector<double> section(v.begin() + start, v.begin() + i);
                    double mean = std::accumulate(section.begin(), section.end(), 0.0) / section.size();
                    result.push_back({modelname, start, (int)i - 1, mean});
                }
                start = -1; // Reset start index for the next section
            }
        }
    }
    // Check if the vector ended while a section was in progress
    if (start != -1) {
        if (v.size() - start > 1) {
            // The section has 2 or more values, so it is valid
            vector<double> section(v.begin() + start, v.end());
            double mean = std::accumulate(section.begin(), section.end(), 0.0) / section.size();
            result.push_back({modelname, start, (int)(v.size() - 1), mean});
        }
    }
    return result;
}

vector<Section> filterSections(vector<Section> sections) {
    std::vector<Section> result;
    for (long unsigned int i = 0; i < sections.size(); i++) {
        for (long unsigned int j = i + 1; j < sections.size(); j++) {
            const Section& s1 = sections[i];
            const Section& s2 = sections[j];

            if (s1.start >= s2.start && s1.end <= s2.end) {
                // s1 is contained within s2
                if (s1.mean < s2.mean) {
                    // s1 has a smaller mean, so it is the valid section
                    sections.push_back({s2.modelname, s2.start, s1.start - 1, s2.mean});
                    sections.push_back({s2.modelname, s1.end + 1, s2.end, s2.mean});
                    sections.erase(sections.begin() + j);
                    j--;
                } else {
                    sections.erase(sections.begin() + i);
                    i--;
                    break;
                }
                
                // break;
            } else if (s2.start >= s1.start && s2.end <= s1.end) {
                // s2 is contained within s1
                if (s2.mean < s1.mean) {
                    // s2 has a smaller mean, so it is the valid section
                    sections.push_back({s1.modelname, s1.start, s2.start - 1, s1.mean});
                    sections.push_back({s1.modelname ,s2.end + 1, s1.end, s1.mean});
                    sections.erase(sections.begin() + i);
                    i--;
                    break;
                } else {
                    sections.erase(sections.begin() + j);
                    j--;
                }
            }
        }
    }
    // Add any remaining sections to the result vector
    for (const Section& s : sections) {
        result.push_back(s);
    }
    return result;
}


vector<Section> orderSections(vector<Section> sections) {
    std::sort(sections.begin(), sections.end(), [](const Section& s1, const Section& s2) {
        return s1.start < s2.start;
    });
    return sections;
}

double calculateMean(const vector<double>& values, size_t start, size_t end) {
  // Check if the start position is valid
  if (start >= values.size()) {
    throw std::invalid_argument("Invalid start position");
  }

  // Check if the end position is valid
  if (end > values.size()) {
    throw std::invalid_argument("Invalid end position");
  }

  // Check if the start position comes before the end position
  if (start > end) {
    throw std::invalid_argument("Start position comes after end position");
  }

  // Calculate the sum of the values in the range [start, end)
  double sum = std::accumulate(values.begin() + start, values.begin() + end, 0.0);

  // Calculate the mean by dividing the sum by the number of values
  size_t numValues = end - start;
  double mean = sum / numValues;

  return mean;
}


// int countChars(ifstream &ifs){
//     string line;
//     int count  = 0;
//     while(getline(ifs, line )){
//         count += line.length();
//     }
//     return count;
// }