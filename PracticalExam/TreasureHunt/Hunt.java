/**
 * Name 		: Wong Kang Fei 
 * Matric No. 	: A0138862W
 * PLab Acct. 	: plab1933
 */

import java.util.*;

public class Hunt {

	private int[][] map;
	
	// constructor
	// map of 2D int array col size must be 3
	public Hunt(int[][] map){
		this.map = map;
	}

	// pre: valid map
	// post: gives the maximum of each subpath taken
	public int solve(){
		return max(solve(0, 0, 0),
				   solve(0, 1, 0),
				   solve(0, 2, 0));
	}

	// pre: solve a particular path, given starting row, column, and value
	// inputs:  row: starting row
	//			col: choosing next col where col<3, col>=0
	//			value: accumulated value
	// post: return the maximum accumulated value from all the subpath
	public int solve(int row, int col, int value){
		if(row < map.length){
			// recursive case: depends on which col currently at, have 
different way of choosing next route
			switch(col){
				case 0: // at left col
					return Math.max(solve(row+1, 0, value + 
map[row][0]), // choose left col
							solve(row+1, 1, value + 
map[row][1])); // choose mid col
				case 1: // at mid col
					return max(solve(row+1, 0, value + 
map[row][0]), // choose left col
							solve(row+1, 1, value + 
map[row][1]), // choose mid col
							solve(row+1, 2, value + 
map[row][2])); // choose right col
				case 2: // ad right col
					return Math.max(solve(row+1, 1, value + 
map[row][1]), // choose mid col
							solve(row+1, 2, value + 
map[row][2])); // choose right col
				default:
					System.out.println("Something is wrong");
					return -1;
			}
		}else{
			return value; // base case: exceeded rowCount, return val 
instead
		}
	}

	// custom max function of 3 variables
	// pre: a, b, c are integers
	// post: return maximum out of 3 variables
	public int max(int a, int b, int c){
		if( a>b  && a>c){
			return a;
		}else{
			if(b>c){
				return b;
			}else{
				return c;
			}
		}
	}

	public static void main(String[] args) {
		// treat this as your "main" method
		Scanner sc = new Scanner(System.in);
		int rowCount = sc.nextInt();

		sc.nextLine(); // consume line feed;

		int[][] map = new int[rowCount][3];

		// init map
		for( int i=0; i< rowCount; i++){
			for(int j=0; j< 3; j++){
				map[i][j] = sc.nextInt();
			}
		}

		Hunt hunt = new Hunt(map);

		System.out.println(hunt.solve());
	}

/*
	public static void main(String[] args) {
		//Hunt treasureHunt = new Hunt();
		treasureHunt.run();
	}
	*/
}

