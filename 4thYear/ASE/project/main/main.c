#include "temp_sensor_tc74.h"
#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "esp_log.h"
#include "esp_sleep.h"
#include "esp_timer.h"
#include "display.h"
#include "spiffs.h"
#include "rtc.h"
#include "wifi.h"
#include "http.h"
#include "nvs_flash.h"
#include "esp_wifi.h"  // Include esp_wifi.h here

#define TIMER_WAKEUP_TIME_US (5 * 1000 * 1000) // 5 seconds

uint8_t current_temperature = 0; 

void temperature_task(void *pvParameters) {
    ESP_LOGI("Temperature Task", "Starting temperature task");
    i2c_master_bus_handle_t busHandle;
    i2c_master_dev_handle_t sensorHandle;
    esp_err_t result;

    result = tc74_init(&busHandle, &sensorHandle, 0x49, 1, 8, 50000);
    if (result != ESP_OK) {
        ESP_LOGE("Temperature Task", "Sensor initialization failed");
        return;
    }

    result = tc74_wakeup(sensorHandle);
    if (result != ESP_OK) {
        ESP_LOGE("Temperature Task", "Sensor wakeup failed");
        return;
    }

    while (1) {
        esp_err_t wifi_status = esp_wifi_connect();

        if (wifi_status == ESP_OK) {
            ESP_LOGI("Temperature Task", "WiFi connected successfully");
        } else {
            ESP_LOGE("Temperature Task", "WiFi connection failed: %s", esp_err_to_name(wifi_status));
        }

        vTaskDelay(5000 / portTICK_PERIOD_MS);

        result = tc74_read_temp_after_cfg(sensorHandle, &current_temperature);
        if (result == ESP_OK) {
            ESP_LOGI("Temperature Task", "Temperature: %d°C", current_temperature);
            write_temperature_to_file(current_temperature);
            http_post_data(current_temperature);
        } else {
            ESP_LOGE("Temperature Task", "Temperature read failed");
        }


        //light sleep enabled just for energy tests


        // // Get the timestamp before entering light sleep
        // int64_t before_sleep = esp_timer_get_time();
        // ESP_LOGI("Temperature Task", "Entering light sleep for 5 seconds");

        // // Enter light sleep
        // esp_sleep_enable_timer_wakeup(TIMER_WAKEUP_TIME_US);
        // esp_light_sleep_start();

        // // Get the timestamp after waking up
        // int64_t after_sleep = esp_timer_get_time();
        // int64_t wakeup_time = after_sleep - before_sleep;

        // ESP_LOGI("Temperature Task", "Woke up from light sleep, wakeup time: %lld microseconds", wakeup_time);
        
        vTaskDelay(5000 / portTICK_PERIOD_MS);
    }
}

void app_main(void) {
    esp_err_t ret = nvs_flash_init();
    if (ret == ESP_ERR_NVS_NO_FREE_PAGES || ret == ESP_ERR_NVS_NEW_VERSION_FOUND) {
        ESP_ERROR_CHECK(nvs_flash_erase());
        ESP_ERROR_CHECK(nvs_flash_init());
    }

    init_spiffs();
    wifi_init_sta();
    configurar_gpio();

    xTaskCreate(&temperature_task, "Temperature Task", 4096, NULL, 10, NULL);
    xTaskCreate(&display_task, "Display Task", 4096, NULL, 10, NULL);
}
