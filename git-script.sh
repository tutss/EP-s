#!/bin/bash
git status
echo "Want to continue?"
read answer
if [ $answer = 'y' ]
then 
    git add -A

    echo "Add you git commit message! "

    read message
    echo \' $message \'
    git commit -m "$message"

    sleep .9
    git push
fi

