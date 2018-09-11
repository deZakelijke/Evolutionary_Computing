f = open("sources.txt", "r")

heap = ""

for line in f:
    heap = heap + line.replace("./", "./model/") + "\n"

f.close()

f = open("sources.txt", "w")

for line in heap:
    f.write(line)

f.close()