
#include <stdio.h>

//reads a Mineswaper of the given size into the given array
char* read_field(int row, int col, char field[]) {
	for (int i=0; i < row*col; i++) {
		char c;
		if (scanf("%c", &c) == 1) {
			if (c == '\n') {
				i--;
				continue;
			} else {
				field[i] = c;
			}
		}
	}
	
	return field;
}

//gets the a char from the given Minesweeper field from the given coordinate
char get_from_2D_arr(char arr[], int row, int col, int row_num, int col_num) {
	if (row < 0 || col < 0 || row_num <= row || col_num <= col) {
		//printf("a\n");
		return 'a';
	} 
	else {
		int x = col + (row * col_num);
		return arr[x];
	}
}

//counts how many bombs a around a given position in a given field
int count_stars_in_neigborhood(char arr[], int row, int col, int row_num, int col_num) {
	int res = 0;
	res += get_from_2D_arr(arr, row-1, col-1, row_num, col_num) == '*' ? 1 : 0; 		//left up
	res += get_from_2D_arr(arr, row-1,   col, row_num, col_num) == '*' ? 1 : 0; 		//middle up
	res += get_from_2D_arr(arr, row-1, col+1, row_num, col_num) == '*' ? 1 : 0;		//right up
	res += get_from_2D_arr(arr, row, col-1, row_num, col_num) == '*' ? 1 : 0;		//left middle
	res += get_from_2D_arr(arr, row, col+1, row_num, col_num) == '*' ? 1 : 0;		//right middle
	res += get_from_2D_arr(arr, row+1, col-1, row_num, col_num) == '*' ? 1 : 0;		//left bottom
	res += get_from_2D_arr(arr, row+1, col, row_num, col_num) == '*' ? 1 : 0;		//middle bottmo
	res += get_from_2D_arr(arr, row+1, col+1, row_num, col_num) == '*' ? 1 : 0;		//right bottmo
	return res;
}

int main()
{
	int row, col; //dim of filed
	int fieldN = 1;
  
	while (scanf("%d %d\n", &row, &col) == 2) {
		char field[row*col];
		
		if (row == 0 && col == 0) {
			break;
		}
		
		read_field(row, col, field);
		
		if (fieldN != 1) {
			printf("\n");
		}
		
		printf("Field #%d:\n", fieldN);
		
		for(int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++){
				char ch = get_from_2D_arr(field, r, c, row, col);
				
				if (ch == '.') {
					printf("%d", count_stars_in_neigborhood(field, r, c, row, col));
				} else {
					printf("%c", ch);
				}
				
			}
			printf("\n");
		}
		
		fieldN++;
	}
	
	return(0);
}
