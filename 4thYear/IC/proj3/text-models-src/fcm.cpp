#include "fcm.hpp"

#include<fstream>
#include<string>
#include<iostream>
#include<cmath>
#include<sstream>

#define ALPHABETH_SIZE 27
void readChar(ifstream &ifs, char *c);

using namespace std;

fcm::fcm(){}

fcm::fcm(int k, double alpha){
    this->k = k;
    this->alpha = alpha; 
}

void fcm::setK(int k){
    this->k = k;
}

void fcm::setAlpha(double alpha){
    this->alpha = alpha;
}

void fcm::clearModel(){
    unordered_map<string, unordered_map<char, int>> model;
    vector<double> entropy;
    this->model = model;
    this->entropy_char = entropy;
    modelEntropy = 0;
}


// Creates a model with the text file received
void fcm::createModel(const char *filename){
    ifstream ifs(filename, std::ios::in);
    if(!ifs.is_open()){
        throw runtime_error("ERROR: Could not open file!");
    }

    clearModel();

    string context;
    char c;
    for(int i=0; i<k; i++){
        readChar(ifs, &c);
        context.push_back(c);
    }

    while(!ifs.eof()){
        readChar(ifs, &c);

        if(model.count(context) == 0){
            unordered_map<char, int> occ;
            occ[c]++;
            model[context] = occ;
        }
        else{
            model[context][c]++;
        }

        context.erase(0,1);
        context.push_back(c); // The first char is removed and the next char is added to the context
    }

    string fn = string(filename);
    size_t pos_slash = fn.find_last_of("/");
    string filenameModel = "MODELS" + (fn.substr(pos_slash));
    size_t pos_dot = filenameModel.find_last_of(".");
    filenameModel = (filenameModel.substr(0, pos_dot)) + "_model.txt";
    cout << "Model saved at: \"" << filenameModel << "\"" << endl;

    ofstream fileModel(filenameModel, ios::out);
    fileModel << k << "\n" << alpha << endl;
    
    for(const auto& [ctx, occ] : model){
        fileModel << ctx;
        for(const auto& [c, v] : occ){
            fileModel << "\t" << c << "\t" << v;
        }
        fileModel << endl;
    }
    fileModel.close();
}

void fcm::loadModelFromFile(const char *filename){
    ifstream ifs(filename, std::ios::in);
    if(!ifs.is_open()){
        throw runtime_error("ERROR: Could not open file!");
    }

    clearModel();

    string readLine;
    getline(ifs, readLine);
    k = stoi(readLine);
    getline(ifs, readLine);
    alpha = stod(readLine);

    // cout << "k = " << k << "\t" << "alpha = " << alpha << endl;
    while(getline(ifs, readLine)){
        string context = readLine.substr(0, (size_t)k);
        readLine = readLine.substr(k+1);
        // cout << context  << endl;
        istringstream auxLine(readLine);
    
        bool flagC = true;
        char c;
        int v;
        while(getline(auxLine, readLine, '\t')){
            if(flagC){
                c = readLine[0];
                // cout << '\t' << c;
                flagC = !flagC;
            }
            else{
                v = stoi(readLine);
                // cout << '\t' << v;
                model[context][c] = v;
                flagC = !flagC;
            }
        }
        // cout << endl;
    }
    cout << "Model \"" << filename << "\" loaded." << endl;
}


//Uma estrutura unordered_map em que as keys são string que representam o contexto de ordem k.
//Cada chave unordered_mapeia para outra estrutura unordered_map que por sua vez tem como key o char seguinte ao contexto e este unordered_mapeia o número de occurências deste no modelo.
void fcm::calculateEntropy(){
    if(model.empty()){
        throw runtime_error("ERROR: Create or load a model first!");
    }
    
    int nrTotalEntrys=0;
    unordered_map<string, int> TotalOccurCtx; //nr de ocorrencias no contexto
    int aux;
    
    //percorrer modelo 
    for(const auto& [ctx, occ] : model){
        aux=0;

        for(const auto& [c, v] : occ){
            aux += v;  
        }
        nrTotalEntrys += aux;
        TotalOccurCtx[ctx]=aux;
    }
    
    double probCtx, prob, ctxEntropy, H;
    int ctxTotal;
    
    //a probabilidade do contexto a probabilidade associado à frequência relativa que se pode deduzir pela razão entre o somatório do número de ocurrências de caracteres nesse
    //contexto, Totalctx, pelo total do número de ocurrência entre todos os contextos
    for(const auto& [ctx, occ] : model){
        ctxTotal = TotalOccurCtx[ctx];
        probCtx = (double)ctxTotal / nrTotalEntrys;
        ctxEntropy = 0;
        for(const auto& [c, v] : occ){
            prob = (double) v / ctxTotal;
            ctxEntropy -= prob * log2(prob);   ////Entropia = soma da entropia da probabilidade da frequência relativa para cada ocurrência de carcter, Pi
        }
        H += ctxEntropy * probCtx;
    }
    modelEntropy = H;
}

void fcm::estimate(const char *filename){
    if(model.empty()){
        throw runtime_error("ERROR: Create or load a model first!");
    }

    ifstream ifs(filename, std::ios::in);
    if(!ifs.is_open()){
        throw runtime_error("Error: Could not open file!");
    }

    string context;
    char c;
    for(int i=0; i<k; i++){
        readChar(ifs, &c);
        context.push_back(c);
    }

    int nOcc, totalOcc;
    double sum_H = 0;
    int cnt = 0;

    while(!ifs.eof()){
        cnt++;
        readChar(ifs, &c);
        totalOcc = 0;

        if(model.count(context) == 0){  //contexto não existe no modelo
            nOcc = 0;
            totalOcc = 0;
        }
        else{
            unordered_map<char, int> &occ = model[context];
             // verifica se o char existe no contexto
            if(occ.count(c) == 0) nOcc = 0;
            else nOcc = occ[c];

            // Obter o numero total de ocurrências
            for(const auto& [c, v] : occ) totalOcc += v;
        }

        sum_H += -log2((nOcc + alpha) / (totalOcc + (alpha * ALPHABETH_SIZE)));
        context.erase(0,1);
        context.push_back(c); // The first char is removed and the next char is added to the context
    }

    dist = sum_H;
    estimEntropy = sum_H / cnt;
    nChars = cnt;
}

// int fcm::estimateLocate(const char *filename, int startRead, int charsRead){
void fcm::estimateLocate(const char *filename){

    if(model.empty()){
        throw runtime_error("ERROR: Create or load a model first!");
    }

    ifstream ifs(filename, std::ios::in);
    if(!ifs.is_open()){
        throw runtime_error("Error: Could not open file!");
    }

    // ifs.seekg(startRead);

    string context;
    char c;
    for(int i=0; i<k; i++){
        readChar(ifs, &c);
        context.push_back(c);
    }

    int nOcc, totalOcc;
    double sum_H = 0;
    int cnt = 0;
    // int contextZero = 0;

    // while((cnt < charsRead) && (!ifs.eof())){
    while(!ifs.eof()){
        readChar(ifs, &c);
        totalOcc = 0;

        if(model.count(context) == 0){  //contexto não existe no modelo
            // contextZero++;
            nOcc = 0;
            totalOcc = 0;
            // break;
        }
        else{
            // contextZero = 0;
            unordered_map<char, int> &occ = model[context];
             // verifica se o char existe no contexto
            if(occ.count(c) == 0) nOcc = 0;
            else nOcc = occ[c];

            // Obter o numero total de ocurrências
            for(const auto& [c, v] : occ) totalOcc += v;
        }

        cnt++;
        // sum_H += -log2((nOcc + alpha) / (totalOcc + (alpha * ALPHABETH_SIZE)));
        entropy_char.push_back(-log2((nOcc + alpha) / (totalOcc + (alpha * ALPHABETH_SIZE))));
        context.erase(0,1);
        context.push_back(c); // The first char is removed and the next char is added to the context
        // if(contextZero == 5) break;
    }

    dist = sum_H;
    estimEntropy = sum_H / cnt;
    nChars = cnt;
    // return cnt;
}


void readChar(ifstream &ifs, char *c){
    char s;
    do{
        ifs.get(s);
        if(!((s == '\n') | (s == '\t'))) {
            if(isalpha(s) && isupper(s)){
                *c = s + 32; // converte para letra minuscula
            }
            else{
                *c = s;
            }
        }
    }while(((*c == '\n') | (*c == '\t')) && !ifs.eof());
}