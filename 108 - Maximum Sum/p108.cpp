// 108 - Maximum Sum
// author: Timo Weike

#include <stdio.h>
#include <cstdint> //for int8_t | requieres compiler flag -sdt=c++11
#include <climits> //for INT_MIN

void init(int n);
int getSumFor_internal(int c1, int r1, int c2, int r2);
int getSumFor(int c1, int r1, int c2, int r2);
int main();

const int max_N = 101;

int8_t square[max_N][max_N] = {};
int sums[max_N][max_N][max_N][max_N] = {};
bool is_summed[max_N][max_N][max_N][max_N] = {};

void init(int n) {
	for(int c1=0; c1 < n; c1++) {
		for(int r1=0; r1 < n; r1++) {
			for(int c2=0; c2 < n; c2++) {
				for(int r2=0; r2 < n; r2++) {
					sums[c1][r1][c2][r2] = INT_MIN; 
					is_summed[c1][r1][c2][r2] = false;
				}
			}
		}
	}
}

int getSumFor_internal(int c1, int r1, int c2, int r2) {
	int sum;
	if(c1 == c2 && r1 == r2) {
		is_summed[c1][r1][c2][r2] = true;
		sum = square[c1][r1];
		sums[c1][r1][c2][r2] = sum;
		return square[c1][r1];
	} else if(c2 < c1 || r2 < r1) {
		sum = 0;
	} else {
		int cH = c1 + ( (c2-c1)/2);
		int rH = r1 + ((r2-r1)/2);
		
		int tl = getSumFor(c1, r1, cH, rH);
		int tr = getSumFor(cH+1, r1, c2, rH);
		int bl = getSumFor(c1, rH+1, cH, r2);
		int br = getSumFor(cH+1, rH+1, c2, r2);
		
		is_summed[c1][r1][c2][r2] = true;
		sum = tl + tr + bl + br;
		sums[c1][r1][c2][r2] = sum;
	}
	
	return sum;
}

int getSumFor(int c1, int r1, int c2, int r2) {
	if(is_summed[c1][r1][c2][r2] == true) {
		return sums[c1][r1][c2][r2];
	} else if(c1 <= c2 && r1 <= r2) {
		return getSumFor_internal(c1, r1, c2, r2);
	} else {
		return 0;
	}
}

int main() {
	//~ init(max_N);
	int n = 0;
	
	while(scanf("%d", &n) == 1) {
		init(n);
		int8_t input = 0;
		
		for(int c = 0; c < n; c++) {
			for(int r = 0; r < n; r++) {
				scanf("%hhd", &input); // %hdd to read to an signed 8 bit integer
				square[c][r] = input;
			}
		}
		
		int maximum = INT_MIN;
		int currentSum = INT_MIN;
		
		for(int c1=0; c1 < n; c1++) {
			for(int r1=0; r1 < n; r1++) {
				for(int c2=c1; c2 < n; c2++) {
					for(int r2=r1; r2 < n; r2++) {
						currentSum = getSumFor(c1, r1, c2, r2);
						if(maximum < currentSum) {
							maximum = currentSum;
						}
					}
				}
			}
		}
		
		printf("%d\n", maximum);
	}
	
	return 0;
}
