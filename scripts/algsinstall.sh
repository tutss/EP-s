#!/usr/bin/env bash

# For the shell script to work you need to allow execution.
# chmod +x <shellscriptname>.sh

#installing jdk
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo apt-get install oracle-java8-set-default

#checking versions
echo 'Checking versions...'
echo 'Compiler: ' | javac -version
echo 'Java: ' | java -version
sleep 0.8

#installing algs4
cd /usr/local
sudo mkdir algs4
sudo chmod 755 algs4
cd algs4

sleep 0.8

if [ ${PWD} = '/usr/local/algs4' ]
then 
	sudo curl -O "https://algs4.cs.princeton.edu/code/algs4.jar"
	sudo curl -O "https://algs4.cs.princeton.edu/linux/{javac,java}-{algs4,cos226,coursera}"
	sudo chmod 755 {javac,java}-{algs4,cos226,coursera}
	sudo mv {javac,java}-{algs4,cos226,coursera} /usr/local/bin	
fi

sleep 3
echo \n
echo \n
echo "Done installing algs4..."
sleep 3

#installing findbugs
if [ ${PWD} = '/usr/local/algs4' ]
then 
	echo -n "Do you want to install FindBugs (y/n)?"
	read answer
	if echo "$answer" | grep -iq "^y"
	then 
		sudo curl -O "https://algs4.cs.princeton.edu/linux/findbugs.{zip,xml}"
		sudo curl -O "https://algs4.cs.princeton.edu/linux/findbugs-{algs4,cos226,coursera}"
		sudo unzip findbugs.zip
		sudo chmod 755 findbugs-{algs4,cos226,coursera}
		sudo mv findbugs-{algs4,cos226,coursera} /usr/local/bin
	fi
fi