cd model
find -name "*.java" > ../sources.txt
cd ..
python compile.py
javac -cp contest.jar @sources.txt
javac -cp contest.jar player50.java