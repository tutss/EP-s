# EP 1 - MAC0317
## Artur Magalhães Rodrigues dos Santos, 10297734

Neste EP, tinhamos que elaborar um programa que tocasse algumas notas, dado um tempo, um envelope e um *sampling rate*.

### Estrutura básica
O programa foi divido em 3 partes: parsers (1), player (2), note_map (3) e a main (4). 
- **parsers**: arquivo que contém as funções responsáveis pelo parsing das entradas. Contém duas funções: *receive_and_parse_adsr_file* e
*receive_and_parse_partiture_file*, sendo uma responsável pelo ADSR e a outra pela partitura (.part).
- **player**: arquivo que contém funções responsáveis por tocar as notas e gerar a música. Contém duas funções: *record_song* e *play_note*,
sendo uma responsável por passar as notas a serem tocadas - gerando a música - e a outra gerar uma nota individualmente.
- **note_map**: arquivo que contém um mapeamento entre notas e frequências. Optei por transcrevê-las ao invés de utilizar os múltiplos para 
cada oitava.
- **main**: função principal que chama as demais - dentro de parsers, player e note_map - e grava a música no arquivo de saída.

### Detalhamento das funções
#### Parsers
- *receive_and_parse_adsr_file*: recebe uma string correspondente ao caminho do arquivo ADSR. Lê as linhas e retorna uma matriz, na qual cada linha representa um momento
do envelope.
- *receive_and_parse_partiture_file*: Lê as linhas do arquivo recebido pela entrada padrão, monta uma matriz correspondente a partitura, retornando o número de notas e
a matriz com a partitura.
#### Player
- *play_note*: Para cada momento do envelope, gera a onda seno - com uso do *numpy* - na respectiva duração e amplitude, formando a nota. 
- *record_song*: Para cada uma das notas, gera sua respectiva onda, salva esses valores em um array, que posteriormente é retornado.

### Dificuldades
Ao longo do desenvolvimento, tive dificuldades em relação a forma correta de gerar a onda de cada nota. Na primeira tentativa, percebi que o número de *samples* estava errado - o tamanho excessivo e o uso incorreto das matrizes do *numpy* fez meu computador travar diversas vezes. Nas subsequentes, fui fazendo pequenos ajustes, até chegar a forma final, entregue no EP - felizmente, funcionando de maneira apropriada. Um ponto importante foi levar em conta os intervalos corretos para aplicar as amplitudes, vindos do envelope.

Outra parte interessante foi a do uso do *wave*. Tive alguns problemas em como salvar o resultado final em formato *wav* utilizando esta biblioteca.