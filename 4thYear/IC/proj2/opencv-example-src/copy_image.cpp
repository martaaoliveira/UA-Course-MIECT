#include <iostream>
#include <opencv2/opencv.hpp>
#include <string.h>
//cntrl shift p abre json configurations
#include <opencv2/highgui/highgui.hpp> // import no include errors
#include <opencv2/imgproc/imgproc.hpp> // import no include errors 
#include <opencv2/core/core.hpp>       // import no include errors
#include<opencv2/core/mat.hpp>

using namespace std;
using namespace cv;

int main(int argc, char *argv[])
{


    if(argc <2) {
		cerr << "Usage: " << argv[0] << " <input file> <output_file>\n";
		return 1;
	}

    char *input_file = argv[1];
    char *output_file = argv[2];
    
    Mat image= imread(input_file);
    //Mat output;
    if(image.empty()){
        cerr << "Error: Could not open image\n" << endl;
        return 0;
    }

    Mat output(image.rows, image.cols, image.type());


    for(int i=0; i < image.rows; i++){
        for(int j=0 ; j < image.cols; j++)
            output.ptr<Vec3b>(i)[j] = Vec3b(image.ptr<Vec3b>(i)[j][0], image.ptr<Vec3b>(i)[j][1], image.ptr<Vec3b>(i)[j][2]); //https://stackoverflow.com/questions/32190494/what-is-the-vec3b-type
    }

    //Save output image
    imwrite(output_file ,output); 

    //Show image on window
    imshow("dst", output);         //displaying output image file
    waitKey(0);
    //destroyWindow("Output image");   

    
    
    return 0;

}




