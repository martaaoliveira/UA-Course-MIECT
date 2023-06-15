#include <iostream>
#include <opencv2/opencv.hpp>
#include <string.h>
#include "opencv2/opencv.hpp"
//cntrl shift p abre json configurations
using namespace std;
using namespace cv;

int main(int argc, char *argv[])
{

     if(argc <3) {
		cerr << "Usage: " << argv[0] << " <input file> <effect>\n<effect> is 'r' for rotate, 'l' for brightness,'n' to create the negative version and 'm' for a mirrored version of an image\n";
		return 1;
	}

    char *input_file = argv[1];
    //char *output_file = argv[2];
    string effect=argv[2];
    string r;
    
    
    if(effect=="r"){
        
    do{
        cout<<"choose angle of rotation: 90,-90 or 180\n";
        cin>>r;
    }while(r!="90"&& r!="-90"&& r!="180");
    }
    
    
    Mat image= imread(input_file);
    Mat dst(image.rows, image.cols, image.type());
    if(image.empty()){
        cerr << "Error: Could not open image\n" << endl;
        return 0;
    }

    if(effect=="r"){

        if(r=="90"){
            rotate(image,dst,ROTATE_90_CLOCKWISE); 
        }
        else if(r=="-90"){
            rotate(image,dst,ROTATE_90_COUNTERCLOCKWISE);
        }
        else if(r=="180"){
            rotate(image,dst,ROTATE_180);
        }
    
    imshow("dst", dst);  //displaying output image file
    waitKey(0);          //to exit press escape
    
    }

    if(effect=="n"){
    Mat img2=255 - image; //Image negative is produced by subtracting each pixel from the maximum intensity value. e.g. for an 8-bit image, the max intensity value is 28– 1 = 255, 
    //thus each pixel is subtracted from 255 to produce the output image
    imshow("img2",img2); 
    waitKey(0);
    return 0; 
    }  

    if(effect=="m"){ //https://stackoverflow.com/questions/14907964/mirror-image-in-opencv
        Mat dst2;   
        cout<<"To flip image horizontally insert a '0'. To flip vertically insert an '1'\n";
        int value;
        cout<<"choose value: "<<endl;
        cin>>value;
        //flip code: A flag to specify how to flip the array; 0 means flipping around the x-axis and positive value (for example, 1) means flipping around y-axis. 
        // //Negative value (for example, -1) means flipping around both axes. Return Value: It returns an image.
        if(value==0){
            Mat dst2;
            flip(image,dst2,0);
            imshow("flipped_horizontally",dst2);
            waitKey(0);
        }
        if(value==1){
            Mat dst2;
            flip(image,dst,1);
            imshow("flipped_vertically",dst);
            waitKey(0);
        }             
    }

    if(effect=="l"){ //https://www.opencv-srf.com/2018/02/change-brightness-of-images-and-videos.html
        cout<<"To increase light insert a positive <value> to decrease insert a negative <value>\n";
        int value;
        cout<<"choose value: "<<endl;
        cin>>value;
        Mat imageBrighnessHigh;
        if(value>0){
            image.convertTo(imageBrighnessHigh, -1, 1, value); //increase the brightness
            imshow("light_increase",imageBrighnessHigh);
            waitKey(0);
        }
        
        Mat imageBrighnessLow;
        if (value<0){
            image.convertTo(imageBrighnessLow, -1, 1, value); //decrease the brightness
            imshow("light_decrease",imageBrighnessLow);
            waitKey(0);
        }
    
        
        return 0;
    }

    return 0;


}