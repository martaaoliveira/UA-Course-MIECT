#include "temp_sensor_tc74.h"
#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
void app_main() {
    esp_err_t result;
    i2c_master_bus_handle_t busHandle;
    i2c_master_dev_handle_t sensorHandle;
    uint8_t temp;

    // Inicialização do sensor
    result = tc74_init(&busHandle, &sensorHandle, 0x49, 1, 8, 50000); 
    if (result != ESP_OK) {
        printf("Falha na inicialização do sensor\n");
        return;
    }
    else{
        printf("Sensor inicializado\n");

    }

    // Acorda o sensor
    result = tc74_wakeup(sensorHandle);
    if (result != ESP_OK) {
        printf("Falha ao acordar o sensor\n");
        return;
    }
    else {
        printf("Sensor acordado\n");
        
    }

while(1){
    // Verifica se a temperatura está pronta
   bool is_ready= tc74_is_temperature_ready(sensorHandle);
    if(is_ready)printf("Bool is ready:"+ is_ready);

        // Lê a temperatura
        result = tc74_read_temp_after_cfg(sensorHandle, &temp);
        if (result == ESP_OK) {
            printf("Temperatura: %d°C\r", temp);
            //printf("Temperatura: %d°C\n", (int8_t)temp);  // Cast temp to int8_t when printing

        } else {
            printf("Falha na leitura da temperatura\n");
        }
        vTaskDelay(50);
    
    
    
}

}
