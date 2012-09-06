package iballs;

import java.util.LinkedList;
import java.util.List;

public class Main {

	
	
	public static List<Integer> makeMasks(int[][] idx, int[][] p) {
		LinkedList<Integer> masks = new LinkedList<Integer>();
		
		int nx = idx.length;
		int ny = idx[0].length;
		
		for (int y = 0; y < ny; ++y) {
			for (int x = 0; x < nx; ++x) {			
				int mask = 0;
				for (int[] xy : p) {
					int tx = x + xy[0];
				    int ty = y + xy[1];				    
				    if ((tx >= nx) || (ty >= ny) || (idx[tx][ty] == -1)) {   				    						    	
				    	mask = -1;
				    	break;
				    }				    
				    mask |= (1 << idx[tx][ty]);
				}								
				if (mask != -1) {
					masks.add(mask);
				}
			}
		}
		
		return masks;
	}
	
	public static void printMask(int mask) {
		for (int i = 0; i < 15; ++i) {
			if ((mask & 1) != 0) {
				System.out.print(i + " ");				
			}			
			mask >>= 1;
		}
		System.out.println();
	}
	
	public static boolean checkState(int state, List<Integer> masks) {
		//check solid ball at center
		if ((state & (1 << 4)) == 0) {
			return false;
		}
		
		//count number of solid balls, should be 8
		int cnt = 0;
		for (int i = 0; i < 15; ++i) {
			if ((state & (1 << i)) != 0) {
				++cnt;
			}
		}
		if (cnt != 8) {
			return false;
		}
		
		//check against mask set
		for (Integer m : masks) {
			if (((state & m) == m) || ((~state & m) == m)) {										
				return false;
			}
		}		
		
		return true;
	}
	
	public static void printState(int state) {		
		for (int r = 0; r < 5; ++r) {
			for (int n = 0; n < 4 - r; ++n) {
				System.out.print(" ");
			}
			for (int n = 0; n <= r; ++n) {
				System.out.print(((state & 1) != 0)?"X ":"O ");
				state >>= 1;
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		int[][] idx = new int[5][5];
		int num = 0;
		for (int y = 0; y < 5; ++y) {
			for (int x = 0; x < 5; ++x) {			
				idx[x][y] = (x <= y)?num++:-1;
			}
		}
		
		
		System.out.println("index matrix: ");
		for (int y = 0; y < 5; ++y) {
			for (int x = 0; x < 5; ++x) {
				System.out.print(idx[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		int[][][] patterns  = {
			{{0,0}, {0,1}, {1,1}}, //triangle
			{{0,0}, {0,1}, {0,2}}, //vertical row
			{{0,0}, {1,0}, {2,0}}, //horizontal row
			{{0,0}, {1,1}, {2,2}}, //diagonal			
		};
				
		List<Integer> masks = new LinkedList<Integer>();
		for (int[][] p : patterns) {			
			masks.addAll(makeMasks(idx, p));
		}
		
		System.out.println("check mask list: ");
		for (Integer mask : masks) {			
			printMask(mask);
		}
		System.out.println();
		
		System.out.println("valid states: ");
		int cnt = 0;
		for (int state = 0; state < 32768; ++state) {
			if (checkState(state, masks)) {
				++cnt;
				printState(state);
			}
		}		
		System.out.println();
		System.out.println("total count: " + cnt);
		
	}
}
