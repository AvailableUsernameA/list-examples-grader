# Lab Report 5
## grade.sh
~~~
{
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
}
~~~
## Screenshots
![image](correct.png)
This is the screenshot for http://localhost:3456/grade?repo=https://github.com/ucsd-cse15l-f22/list-methods-corrected. It says that the files are compiled successfully and it passes alll the tests and get the full score.
![image](error.png)
This is the screenshot for http://localhost:3456/grade?repo=https://github.com/ucsd-cse15l-f22/list-methods-compile-error. It says that there is a compiled error and so it gets 0.
![image](failure.png)
This is the screenshot for http://localhost:3456/grade?repo=https://github.com/ucsd-cse15l-f22/list-methods-lab3. It says that there are two test failures leading it to get 0.

## trace
For
~~~
totalScore=2
CPATH=".:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar:student-submission/"
~~~
two variables are created. They both have return codes as zero.

For
~~~
rm -rf student-submission
git clone $1 student-submission
~~~
things stored in https://github.com/ucsd-cse15l-f22/list-methods-lab3 is clone to the local computer and it the standard output is Cloning into 'student-submission'.... The return code is zero here.

For
~~~
if [[ -e student-submission/ListExamples.java ]]
then
    echo $?
else
    echo "Can't find your file"
    echo "score: 0"
    exit 1
fi
~~~
Since there exists a ListExamples.java stores in student-submission, the condition is true. Thus, it has standard output, return code, which is 0 and it won't run the else part.

For 
~~~
touch student-submission/ListExamples.java
mv student-submission/ListExamples.java .
~~~
ListExamples.java is moved from student-submission to the current directory, which is list-examples-grader where all java files exist. They both have return code of 0.

For 
~~~
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
~~~
Then the TestListExamples.java is compiled. The standard error is redirected to compError.txt (for this one, standard error doesn't give anything and compError.txt is empty). Since this compiles success, the return code is 0. Thus, the condition for if is true and it runs the command in then part and won't run the commands in else part. Thus, it will give standard output "coompile success". The return code for echo command is 0.

For 
~~~
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
~~~
Then runs TestListExamples and stores the standard output, which reports the failures in running two tests, in codeError.txt. Thus, it has a a zero return code. Then check if "testMerge(TestListExample)" appears one time in codeError.txt. In this case, since the test fails on testMerge, the string is in codeError.txt and so the condition for this if is true. It has nonzero return code. Thus, totalScore munis one and gives standard output "testMerge fails". They both have the zero return codes. The else part won't run. The second if checks "testFilter(TestListExamples)" appears one time in codeError.txt. It has a zero return code. In this case, since the test fails on testFilter, the string appears in codeError.txt as a part of report of failure. The condition is true and so then part runs and else part won't run. Thus, totalScore minus one and gives the standard output "testFilter fails". They both have the zero return codes.

For
~~~
echo Score: $totalScore/2
~~~
Since now totalScore is 0, it has standard output "Score: 0/2". It has return code of 0.