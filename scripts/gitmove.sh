#!/bin/bash
echo -n 'Mensagem: '
read mensagem
git add *
git commit -m "$mensagem"
sleep 5
git push
sleep 7
git pull
sleep 5
printf '\n'
echo 'gitgitgit'
