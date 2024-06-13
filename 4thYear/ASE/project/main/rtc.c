#include "rtc.h"
#include "esp_log.h"
#include "esp_sntp.h"
#include <time.h>
#include <sys/time.h>

static const char* TAG = "RTC";

void initialize_sntp(void) {
    ESP_LOGI(TAG, "Initializing SNTP");
    sntp_setoperatingmode(SNTP_OPMODE_POLL);
    sntp_setservername(0, "pool.ntp.org");
    sntp_init();
}

void obtain_time(void) {
    initialize_sntp();
    time_t now = 0;
    struct tm timeinfo = { 0 };
    int retry = 0;
    const int retry_count = 10;
    while (timeinfo.tm_year < (2016 - 1900) && ++retry < retry_count) {
        ESP_LOGI(TAG, "Waiting for system time to be set... (%d/%d)", retry, retry_count);
        vTaskDelay(2000 / portTICK_PERIOD_MS);
        time(&now);
        localtime_r(&now, &timeinfo);
    }

    if (retry == retry_count) {
        ESP_LOGE(TAG, "Failed to synchronize time with SNTP server");
    } 
    time(&now);
    localtime_r(&now, &timeinfo);

    // Set timezone 
    setenv("TZ", "CET-1CEST-2,M3.5.0/2,M10.5.0/3", 1);
    tzset();
    ESP_LOGI(TAG, "Timezone set");

    time(&now);
    localtime_r(&now, &timeinfo);
}

time_t adjust_time_for_cet(time_t utc_time) {
    return utc_time - 3600;
}


char* get_current_time(void) {
    time_t now;
    struct tm timeinfo;
    time(&now);
    now = adjust_time_for_cet(now);  

    localtime_r(&now, &timeinfo);

    static char time_str[64];
    strftime(time_str, sizeof(time_str), "%Y-%m-%d %H:%M:%S", &timeinfo);
    return time_str;
}
