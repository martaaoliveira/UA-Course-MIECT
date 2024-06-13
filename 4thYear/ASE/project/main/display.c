#include "display.h"
#include <stdio.h>

const int SEG_PINS[] = {2, 3, 8, 5, 4, 6, 7, 10};
static const uint8_t segmentos[][8] = {
    {1, 1, 1, 1, 1, 1, 0, 1},  // 0
    {0, 1, 1, 0, 0, 0, 0, 1},  // 1
    {1, 1, 0, 1, 1, 0, 1, 0},  // 2
    {1, 1, 1, 1, 0, 0, 1, 1},  // 3
    {0, 1, 1, 0, 0, 1, 1, 1},  // 4
    {1, 0, 1, 1, 0, 1, 1, 1},  // 5
    {1, 0, 1, 1, 1, 1, 1, 1},  // 6
    {1, 1, 1, 0, 0, 0, 0, 1},  // 7
    {1, 1, 1, 1, 1, 1, 1, 1},  // 8
    {1, 1, 1, 1, 0, 1, 1, 1},  // 9
};

extern volatile uint8_t current_temperature;

void configurar_gpio() {
    gpio_reset_pin(DISPLAY_PIN);
    gpio_set_direction(DISPLAY_PIN, GPIO_MODE_OUTPUT);
    for (int i = 0; i < 8; i++) {
        gpio_reset_pin(SEG_PINS[i]);
        gpio_set_direction(SEG_PINS[i], GPIO_MODE_OUTPUT);
    }
}

void display_decimal_digit(int value) {
    static char displayFlag = 0;
    int digit1 = value / 10; 
    int digit2 = value % 10; 

    gpio_set_level(DISPLAY_PIN, displayFlag); 

    for (int i = 0; i < 8; i++) {
        gpio_set_level(SEG_PINS[i], segmentos[displayFlag ? digit1 : digit2][i]);
    }
    displayFlag = !displayFlag; 
}

void display_task(void *pvParameters) {
    ESP_LOGI("Display Task", "Starting display task");
    while (1) {
        display_decimal_digit(current_temperature);
        vTaskDelay(10 / portTICK_PERIOD_MS); // 100 Hz refresh rate
    }
}
