#include "spiffs.h"
#include <string.h>  
#include "rtc.h"

extern volatile uint8_t current_temperature;
const int max_lines = 100; 
static int write_count = 0;

void init_spiffs(void) {
    esp_vfs_spiffs_conf_t conf = {
        .base_path = "/spiffs",
        .partition_label = NULL,
        .max_files = 5,
        .format_if_mount_failed = true
    };
    esp_err_t ret = esp_vfs_spiffs_register(&conf);

    if (ret != ESP_OK) {
        if (ret == ESP_FAIL) {
            ESP_LOGE("SPIFFS", "Failed to mount or format filesystem");
        } else if (ret == ESP_ERR_NOT_FOUND) {
            ESP_LOGE("SPIFFS", "Failed to find SPIFFS partition");
        } else {
            ESP_LOGE("SPIFFS", "Failed to initialize SPIFFS (%s)", esp_err_to_name(ret));
        }
        return;
    }

#ifdef CONFIG_EXAMPLE_SPIFFS_CHECK_ON_START
    ESP_LOGI("SPIFFS", "Performing SPIFFS_check().");
    ret = esp_spiffs_check(conf.partition_label);
    if (ret != ESP_OK) {
        ESP_LOGE("SPIFFS", "SPIFFS_check() failed (%s)", esp_err_to_name(ret));
        return;
    } else {
        ESP_LOGI("SPIFFS", "SPIFFS_check() successful");
    }
#endif

    size_t total = 0, used = 0;
    ret = esp_spiffs_info(conf.partition_label, &total, &used);
    if (ret != ESP_OK) {
        ESP_LOGE("SPIFFS", "Failed to get SPIFFS partition information (%s)", esp_err_to_name(ret));
    } else {
        ESP_LOGI("SPIFFS", "Partition size: total: %d, used: %d", total, used);
    }

    if (used > total) {
        ESP_LOGW("SPIFFS", "Number of used bytes cannot be larger than total. Performing SPIFFS_check().");
        ret = esp_spiffs_check(conf.partition_label);
        if (ret != ESP_OK) {
            ESP_LOGE("SPIFFS", "SPIFFS_check() failed (%s)", esp_err_to_name(ret));
            return;
        } else {
            ESP_LOGI("SPIFFS", "SPIFFS_check() successful");
        }
    }
}

void delete_file_on_start(const char* file_path) {
    if (remove(file_path) == 0) {
        ESP_LOGI("SPIFFS", "Successfully deleted the file: %s", file_path);
    } else {
        ESP_LOGI("SPIFFS", "Failed to delete the file: %s", file_path);
    }
}

#define SPIFFS_FILE_PATH "/spiffs/temperature.txt"

void manage_file_size(const char* file_path) {
    FILE* f = fopen(file_path, "r");
    if (f == NULL) {
        ESP_LOGE("SPIFFS", "Failed to open file for reading");
        return;
    }

    char line[64];
    int line_count = 0;
    while (fgets(line, sizeof(line), f) != NULL) {
        line_count++;
    }
    fclose(f);

    if (line_count > max_lines) {
        ESP_LOGI("SPIFFS", "Max lines reached. Clearing file to start over.");

        // Clear the file
        f = fopen(file_path, "w");
        if (f == NULL) {
            ESP_LOGE("SPIFFS", "Failed to open file for writing");
            return;
        }
        fclose(f);
    }
}

void write_temperature_to_file(int temperature) {
    FILE* f = fopen(SPIFFS_FILE_PATH, "a");
    if (f == NULL) {
        ESP_LOGE("SPIFFS", "Failed to open file for writing");
        return;
    }
    char* timestamp = get_current_time();
    fprintf(f, "%s - %d°C\n", timestamp, temperature);
    fclose(f);
    ESP_LOGI("SPIFFS", "Temperature saved to file");
    
    write_count++;
    if (write_count >= 5) {
        print_from_file();
        write_count = 0; 
    }
    manage_file_size(SPIFFS_FILE_PATH);
}


void print_from_file(void) {
    FILE* f = fopen("/spiffs/temperature.txt", "r");
    if (f == NULL) {
        ESP_LOGW("SPIFFS", "File does not exist yet: %s", "/spiffs/temperature.txt");
        return;
    }

    // Allocate memory for a line buffer
    char line[64];

    ESP_LOGI("SPIFFS", "Reading file");
    // Read each line and print it
    while (fgets(line, sizeof(line), f) != NULL) {
        char* pos = strchr(line, '\n');
        if (pos) {
            *pos = '\0';
        }
        ESP_LOGI("SPIFFS", "Temperature: %s", line);
    }

    ESP_LOGI("SPIFFS", "End of file");

    fclose(f);
}
