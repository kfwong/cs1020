/**
 * Name 		: Wong Kang Fei 
 * Matric No. 	: A0138862W
 * PLab Acct. 	: plab1933
 */

import java.util.*;

public class Garden {

	private char[][] map;
	private int rowCount;
	private int colCount;

	public Garden(char[][] map){
		this.map = map;
		this.rowCount = map.length;
		this.colCount = map[0].length;
	}

	public void run() {
		// treat this as your "main" method

		// init value subplot count
		int subPlotCount = 0;

		// O(n^2): to count the single dots -- trival cases
		for(int i = 0; i< rowCount; i++){
			for(int j = 0; j< colCount; j++){
				if(map[i][j] == '.'){
					subPlotCount++;
				}else{
					continue;
				}
			}
		}

		// O(n^3): to count subplot given a square size > 1
		if(rowCount > 1 && colCount > 1){
			for(int i=0; i< rowCount; i++){
				for(int j = 0; j< colCount; j++){
					for(int k = 1; k < Math.min(rowCount, 
colCount); k++){ // k is distance between  corners of square, and size cannot exceed 
the min of either row or col count
						try{
							// to check if it's indeed a 
square plot
							// check for four corners
							// if a smaller square is 
not a subplot 
							//		-> bigger 
square containing it cannot be a subplot
							//			-> 
break loop and continue next j col
							boolean isValidSmallerSquare 
= true;
							if(map[i][j] == '.' && // 
top left
									map[i+k][j] 
== '.' && // bottom left
									map[i][j+k] 
== '.' && // top right
									
map[i+k][j+k] == '.'){ // bottom right

								// special case: 
need to check last row and last col given a sub square
								for(int m = 0 ; m < 
k; m++){
									
if(map[i+k][j+m] == '.' && map[i+m][j+k] == '.'){
										
continue;
									}else{
										
isValidSmallerSquare = false;
										
break;
									}
								}
								
								
if(isValidSmallerSquare){
									
subPlotCount++;
								}else{
									break;
								}
							}else{
								// current square 
got BOOOOOMZZZZ, must skip!
								break;
							}
						
}catch(ArrayIndexOutOfBoundsException ex){
							// square size exceeded 
rowCount or colCount
							// ignore the case
							continue;
						}
					}
				}
			}
		}

		// time complexity: n^2+n^4 = O(n^4)

		System.out.println(subPlotCount);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rowCount = sc.nextInt();
		int colCount = sc.nextInt();

		sc.nextLine(); // consume line feed;

		// init map size
		char[][] map = new char[rowCount][colCount];

		// read map
		for(int i=0; i<rowCount; i++){
			map[i] = sc.nextLine().trim().toCharArray();
		}

		Garden myBeautifulGarden = new Garden(map);
		myBeautifulGarden.run();
	}
}

