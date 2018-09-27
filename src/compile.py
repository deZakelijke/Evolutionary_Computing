
import os

cwd = os.getcwd()

# open file met .java names
f = open("sources.txt", "r")

# set right prefix
stringBuilder = ""
for line in f:
    stringBuilder = stringBuilder + line.replace("./", "./model/") + "\n"
f.close()

# save that
f = open("sources.txt", "w")
for line in stringBuilder:
    f.write(line)
f.close()

# build all classes
line1 = "javac -cp contest.jar @sources.txt"
print(line1)



os.system(line1)

# do some replacements
stringBuilder2 = stringBuilder.replace(".java", ".class").replace("\n", " ").replace("./model", "model")

# jar all
line2 = "jar cmf MainClass.txt submission.jar player50.class {}".format(stringBuilder2)

# run command for jarring
print(line2)
os.system(line2)

# remove sources.txt
os.remove("sources.txt")
