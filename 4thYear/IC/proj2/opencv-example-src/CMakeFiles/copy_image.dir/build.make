# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.22

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/bruno/Desktop/IC/proj2/opencv-example-src

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/bruno/Desktop/IC/proj2/opencv-example-src

# Include any dependencies generated for this target.
include CMakeFiles/copy_image.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/copy_image.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/copy_image.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/copy_image.dir/flags.make

CMakeFiles/copy_image.dir/copy_image.cpp.o: CMakeFiles/copy_image.dir/flags.make
CMakeFiles/copy_image.dir/copy_image.cpp.o: copy_image.cpp
CMakeFiles/copy_image.dir/copy_image.cpp.o: CMakeFiles/copy_image.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/bruno/Desktop/IC/proj2/opencv-example-src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/copy_image.dir/copy_image.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/copy_image.dir/copy_image.cpp.o -MF CMakeFiles/copy_image.dir/copy_image.cpp.o.d -o CMakeFiles/copy_image.dir/copy_image.cpp.o -c /home/bruno/Desktop/IC/proj2/opencv-example-src/copy_image.cpp

CMakeFiles/copy_image.dir/copy_image.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/copy_image.dir/copy_image.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/bruno/Desktop/IC/proj2/opencv-example-src/copy_image.cpp > CMakeFiles/copy_image.dir/copy_image.cpp.i

CMakeFiles/copy_image.dir/copy_image.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/copy_image.dir/copy_image.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/bruno/Desktop/IC/proj2/opencv-example-src/copy_image.cpp -o CMakeFiles/copy_image.dir/copy_image.cpp.s

# Object files for target copy_image
copy_image_OBJECTS = \
"CMakeFiles/copy_image.dir/copy_image.cpp.o"

# External object files for target copy_image
copy_image_EXTERNAL_OBJECTS =

/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: CMakeFiles/copy_image.dir/copy_image.cpp.o
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: CMakeFiles/copy_image.dir/build.make
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_gapi.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_highgui.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_ml.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_objdetect.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_photo.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_stitching.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_video.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_videoio.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_imgcodecs.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_dnn.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_calib3d.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_features2d.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_flann.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_imgproc.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: /usr/local/lib/libopencv_core.so.4.6.0
/home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image: CMakeFiles/copy_image.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/bruno/Desktop/IC/proj2/opencv-example-src/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable /home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/copy_image.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/copy_image.dir/build: /home/bruno/Desktop/IC/proj2/opencv-example-bin/copy_image
.PHONY : CMakeFiles/copy_image.dir/build

CMakeFiles/copy_image.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/copy_image.dir/cmake_clean.cmake
.PHONY : CMakeFiles/copy_image.dir/clean

CMakeFiles/copy_image.dir/depend:
	cd /home/bruno/Desktop/IC/proj2/opencv-example-src && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/bruno/Desktop/IC/proj2/opencv-example-src /home/bruno/Desktop/IC/proj2/opencv-example-src /home/bruno/Desktop/IC/proj2/opencv-example-src /home/bruno/Desktop/IC/proj2/opencv-example-src /home/bruno/Desktop/IC/proj2/opencv-example-src/CMakeFiles/copy_image.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/copy_image.dir/depend

