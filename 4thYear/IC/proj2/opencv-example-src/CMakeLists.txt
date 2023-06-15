cmake_minimum_required(VERSION 3.16)
SET (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -std=c++17")
SET (CMAKE_CXX_FLAGS_RELEASE "-O3")
SET (CMAKE_CXX_FLAGS_DEBUG "-g3 -fsanitize=address")

SET (BASE_DIR ${CMAKE_SOURCE_DIR} )
SET (CMAKE_RUNTIME_OUTPUT_DIRECTORY ${BASE_DIR}/../opencv-example-bin)


set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)


# set the project name
project(IC_PROJECT2 VERSION 1.0)

add_library(bitstream BitStream.cpp)
add_library(golomb Golomb.cpp)
add_library(audiocodec AudioCodec.cpp)
add_library(imagecodec ImageCodec.cpp)

find_package(Python3 COMPONENTS Interpreter Development NumPy REQUIRED)
find_package(PythonLibs 3.0 REQUIRED)
find_package( OpenCV 4 REQUIRED )
include_directories(${PYTHON3_INCLUDE_DIRS} ${NumPy_INCLUDE_DIRS})

target_link_libraries(golomb bitstream)
target_link_libraries(audiocodec golomb sndfile)
target_link_libraries(imagecodec golomb ${OpenCV_LIBS})

include_directories( ${OpenCV_INCLUDE_DIRS} )
add_executable (copy_image copy_image.cpp)
target_link_libraries (copy_image ${OpenCV_LIBS})
add_executable (ex_2 ex_2.cpp)
target_link_libraries(ex_2 ${OpenCV_LIBS})
#target_link_libraries(wav_hist ${OpenCV_LIBS} )

add_executable (test_golomb test_golomb.cpp)
target_link_libraries(test_golomb bitstream golomb)

add_executable (test_audiocodec test_audioCodec.cpp)
target_link_libraries(test_audiocodec audiocodec bitstream)

add_executable (test_audiocodec2 test_audioCodec2.cpp)
target_link_libraries(test_audiocodec2 audiocodec)

add_executable (test_golomb2 test_golomb2.cpp)
target_link_libraries(test_golomb2 bitstream golomb)

add_executable (test_imagecodec test_imageCodec.cpp)
target_link_libraries(test_imagecodec imagecodec)