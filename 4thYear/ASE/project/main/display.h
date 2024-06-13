#ifndef DISPLAY_H
#define DISPLAY_H

#include "driver/gpio.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "esp_log.h"

#define DISPLAY_PIN GPIO_NUM_0

extern const int SEG_PINS[];

void configurar_gpio(void);
void display_decimal_digit(int value);
void display_task(void *pvParameters);

#endif // DISPLAY_H
