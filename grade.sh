# Create your grading script here

#set -e

totalScore=2
CPATH=".:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar:student-submission/"

rm -rf student-submission
git clone $1 student-submission

if [[ -e student-submission/ListExamples.java ]]
then
    echo $?
else
    echo "Can't find your file"
    echo "score: 0"
    exit 1
fi

touch student-submission/ListExamples.java
mv student-submission/ListExamples.java . 

javac -cp $CPATH TestListExamples.java 2>compError.txt

if [[ $? -eq 0 ]]
then
    echo "compile success"
else
    echo "compile failed"
    cat compError.txt
    echo "score: 0"
    exit 1
fi 

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples 1>codeError.txt
if [[ $(grep -c "testMerge(TestListExamples)" codeError.txt) -eq 1 ]]
then 
    ((totalScore-=1))
    echo "testMerge fails"
else
    echo "pass testMerge"
fi

if [[ $(grep -c "testFilter(TestListExamples)" codeError.txt) -eq 1 ]]
then 
    ((totalScore-=1))
    echo "testFilter fails"
else
    echo "pass testFilter"
fi

echo Score: $totalScore/2