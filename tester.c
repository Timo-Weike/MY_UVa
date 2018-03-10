
#include <stdio.h>

void print_diff(char ic, char oc) {
	printf("diff: in='");
	if(ic == '\n') {
		printf("\\n");
	} else {
		printf("%c", ic);
	}
	printf("'  out='");
	if(oc == '\n') {
		printf("\\n");
	} else {
		printf("%c", oc);
	}
	printf("'\n");
}

int main(int argc, char** args)
{
	FILE *fr;
	
	fr = fopen(args[1], "rt");
	
	char ic, oc;
	while(scanf("%c", &ic) == 1) {
		if (fscanf(fr, "%c", &oc) == 1) {
			print_diff(ic, oc);
			if (ic != oc) {
				printf("Diffrent");
				return 1;
			}
		} else {
			printf("To many output\nMistake");
			return 1;
		}
	}
	return 0;
}
