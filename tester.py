#/usr/bin/python3

import sys

input_lines = sys.stdin.readlines();

argFile = sys.argv[1]

fi = open(argFile)

fl = fi.readlines()

for i in range(len(fl)):
	i_line = input_lines[i]
	o_line = fl[i]
	if(i_line != o_line):
		print("Not equal")
	else:
		print("Equal")
