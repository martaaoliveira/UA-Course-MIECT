#include "http.h"
#include "esp_log.h"
#include "esp_http_client.h"
#include "spiffs.h" // Ensure this includes your SPIFFS initialization and file handling functions
#include "rtc.h"    // Ensure this includes your get_current_time() function
#include <sys/stat.h>

static const char* TAG = "HTTP_POST";

#define TB_URL "http://44.194.165.190/api/v1/CwO212QFSXXU2IEelvpd/telemetry"
#define SPIFFS_PARTITION_LABEL NULL
#define SPIFFS_FILE_PATH "/spiffs/temperature.txt"

esp_err_t http_post_data(int temperature) {
    size_t total = 0, used = 0;
    esp_err_t ret = esp_spiffs_info(SPIFFS_PARTITION_LABEL, &total, &used);
    if (ret != ESP_OK) {
        ESP_LOGE("SPIFFS", "Failed to get SPIFFS partition information (%s)", esp_err_to_name(ret));
        return ret;
    }

    struct stat st;
    if (stat(SPIFFS_FILE_PATH, &st) != 0) {
        ESP_LOGE("SPIFFS", "Failed to get file size");
        return ESP_FAIL;
    }
    size_t file_size = st.st_size;

    char post_data[350];
    sprintf(post_data, "{\"temperature\":%d, \"spiffs_total\":%d, \"spiffs_used\":%d, \"file_size\":%d, \"timestamp\":\"%s\"}", 
            temperature, total, used, file_size, get_current_time());

    esp_http_client_config_t config = {
        .url = TB_URL,
        .method = HTTP_METHOD_POST,
    };
    esp_http_client_handle_t client = esp_http_client_init(&config);

    if (client == NULL) {
        ESP_LOGE(TAG, "Failed to initialise HTTP connection");
        return ESP_FAIL;
    }

    esp_http_client_set_post_field(client, post_data, strlen(post_data));
    esp_http_client_set_header(client, "Content-Type", "application/json");

    ESP_LOGI(TAG, "Sending data: %s", post_data);

    ret = esp_http_client_perform(client);
    if (ret == ESP_OK) {
        ESP_LOGI(TAG, "HTTP POST Status = %d, content_length = %lld",
                 esp_http_client_get_status_code(client),
                 esp_http_client_get_content_length(client));
    } else {
        ESP_LOGE(TAG, "HTTP POST request failed: %s", esp_err_to_name(ret));
    }

    esp_http_client_cleanup(client);
    return ret;
}
