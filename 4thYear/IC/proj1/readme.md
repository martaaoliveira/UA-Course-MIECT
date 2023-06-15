# IC - PROJETO 1
## Requisitos
```bash
Livraria libsndfile e o programa gnuplot
```
## Exercício 2:
Como compilar:
```bash
make wav_hist
```
Executar o programa:
```bash
../sndfile-example-bin/wav_hist <input_file> <channel>
```
O channel poder ser 0 ou 1

## Exercício 3:
Como compilar:
```bash
make wav_quant
```
Executar o programa:
```bash
../sndfile-example-bin/wav_quant <input filename> <output filename> <number of bits to discard>
```

## Exercício 4:
Como compilar:
```bash
make wav_cmp
```
Executar o programa:
```bash
../sndfile-example-bin/wav_cmp <original file> <quantized file>
```

## Exercício 5:
Como compilar:
```bash
make wav_effects
```
Executar o programa:
```bash
../sndfile-example-bin/wav_effects <input file> <output_file> <effect>
```
effect:
's' -> eco único (single eco)
'm' -> eco múltiplo
'r' -> revertido
'a' -> modelação da amplitude

## Exercício 7:
Como compilar o encoder:
```bash
make encoder
```
Executar o programa:
```bash
../sndfile-example-bin/encoder <input filename> <output filename>
```

Como compilar o decoder:
```bash
make decoder
```
Executar o programa:
```bash
../sndfile-example-bin/decoder <input filename> <output filename>
```

