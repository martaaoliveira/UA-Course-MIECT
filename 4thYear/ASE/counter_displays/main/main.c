#include <stdio.h>
#include "esp_timer.h"
#include "esp_log.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "driver/gpio.h"

// Definição dos pinos para o display de 7 segmentos e MOSFET (inversor)
#define DISPLAY_PIN GPIO_NUM_0   // Pino único para controle do display
                     // a, b, c, d, e, f, g, dp
const int SEG_PINS[] = {2, 3, 8, 5, 4, 6, 7, 10};

// Tabela de conversão de binário para segmentos de 7 segmentos
const uint8_t segmentos[][8] = {
    {1, 1, 1, 1, 1, 1, 0, 1},  // 0
    {0, 1, 1, 0, 0, 0, 0, 1},  // 1
    {1, 1, 0, 1, 1, 0, 1, 1},  // 2
    {1, 1, 1, 1, 0, 0, 1, 1},  // 3
    {0, 1, 1, 0, 0, 1, 1, 1},  // 4
    {1, 0, 1, 1, 0, 1, 1, 1},  // 5
    {1, 0, 1, 1, 1, 1, 1, 1},  // 6
    {1, 1, 1, 0, 0, 0, 0, 1},  // 7
    {1, 1, 1, 1, 1, 1, 1, 1},  // 8
    {1, 1, 1, 1, 0, 1, 1, 1},  // 9
    {1, 1, 1, 0, 1, 1, 1, 1},  // A
    {0, 0, 1, 1, 1, 1, 1, 1},  // B
    {1, 0, 0, 1, 1, 1, 0, 1},  // C
    {0, 1, 1, 1, 1, 0, 1, 1},  // D
    {1, 0, 0, 1, 1, 1, 1, 1},  // E
    {1, 0, 0, 0, 1, 1, 1, 1}   // F
};


// Função para configurar os GPIOs
void configurar_gpio() {
    gpio_reset_pin(DISPLAY_PIN);
    gpio_set_direction(DISPLAY_PIN, GPIO_MODE_OUTPUT);

    // Configuração de cada um dos pinos dos segmentos
    for (int i = 0; i < 8; i++) {
        gpio_reset_pin(SEG_PINS[i]);
        gpio_set_direction(SEG_PINS[i], GPIO_MODE_OUTPUT);
    }
}

// Função para exibir um dígito hexadecimal nos displays de 7 segmentos
void display_hex_digit(int value) {
    static char displayFlag = 0;
    int digit1 = value / 16;
    int digit2 = value % 16;

    if (displayFlag == 0) { // display ativo baseado no estado do pino
        gpio_set_level(DISPLAY_PIN, 0); // Estado baixo ativa um display via inversor

        for (int i = 0; i < 8; i++) {
            gpio_set_level(SEG_PINS[i], segmentos[digit2][i]);
        }
        
    } 
    else { // alterna para o outro display
        gpio_set_level(DISPLAY_PIN, 1); // Estado alto ativa o outro display diretamente

        for (int i = 0; i < 8; i++) {
            gpio_set_level(SEG_PINS[i], segmentos[digit1][i]);
        }
        
    }
    displayFlag = !displayFlag; // Alterna entre 0 e 1
}




int counter = 0;

void display_refresh_callback(void* arg) {
    display_hex_digit(counter);
}

void counter_update_callback(void* arg) {
    counter = (counter + 1) % 256; // Increment counter
    printf("Counter value: 0x%X\n", counter); // Print counter value
}


void app_main(void) {
    configurar_gpio(); // Configure GPIOs

    // Create timer for counter update at 1Hz
    esp_timer_create_args_t counter_timer_args = {
        .callback = &counter_update_callback,
        .name = "counter_update_timer"
    };
    esp_timer_handle_t counter_timer;
    esp_timer_create(&counter_timer_args, &counter_timer);

    esp_timer_start_periodic(counter_timer, 1000000); // 1Hz

    // Create timer for display refresh at 100Hz
    esp_timer_create_args_t display_timer_args = {
        .callback = &display_refresh_callback,
        .arg = &counter_timer_args,
        .name = "display_refresh_timer"
    };
    esp_timer_handle_t display_timer;
    esp_timer_create(&display_timer_args, &display_timer);

    esp_timer_start_periodic(display_timer, 10000); // 100Hz
}

