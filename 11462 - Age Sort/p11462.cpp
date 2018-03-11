#include <stdio.h>
#include <iostream>
using namespace std;

const int max_age = 100;

int ages[max_age] = {};

int main();


int main() {
	
	int n;
	while(scanf("%d", &n) == 1) {
		if(n == 0) {
			break;
		}
		
		int input;
		for(int i=0; i < n; i++) {
			if(cin >> input) {
				ages[input-1] = ages[input-1] + 1;
			}
		}
		bool first = true;
		
		for(int i=0; i < max_age; i++) {
			int age_count = ages[i];
			
			if(0 < age_count) {
				for(int j=0; j < age_count; j++) {
					if (first) {
						cout << (i+1);
						first = false;
					} else {
						cout << ' ' << (i+1);
					}
				}
				ages[i] = 0;
			}
		}
		cout << '\n';
	}
	
	
	return 0;
}
