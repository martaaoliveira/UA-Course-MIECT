#ifndef SPIFFS_H
#define SPIFFS_H

#include "esp_spiffs.h"
#include "esp_log.h"
#include <stdio.h>

extern const int max_lines;


void init_spiffs(void);
void delete_file_on_start(const char* file_path);
void manage_file_size(const char* file_path);
void write_temperature_to_file(int temperature);
void print_from_file(void);

#endif 
