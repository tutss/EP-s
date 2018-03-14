#!/bin/bash

echo 'Check if the file exists in the directory.'
echo -n 'Nome do programa: '
read programa
javac -cp .:stdlib.jar $programa.java
echo 'Done compiling...'
echo 'Check if the file exists in the directory.'
printf '\n'
