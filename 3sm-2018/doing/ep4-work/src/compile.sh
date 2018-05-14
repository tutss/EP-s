# echo 'Name of the file: '
# read answer

javac-algs4 KdTreeST.java
echo 'File compiled!'
echo 'Want to clean?'
read ans
if [ $ans = 'y' ]
then 
    rm *.class
fi

java-algs4 KdTreeST

