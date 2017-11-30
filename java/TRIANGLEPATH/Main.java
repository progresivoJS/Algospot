import java.util.Scanner;
public class Main
{
	private static int[][] triangle;
	private static int n;
	private static int[][] cache;
	
	public static int computeMaxPath(int[][] tri)
	{
		triangle = tri;
		n = triangle.length;
		cache = new int[n+1][n+1];
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[i].length; j++)
				cache[i][j] = -1;
		return computeFastMaxPath(0, 0);
	}
	
	/**
	 *	do not use memoization.
	 */
	private static int computeSlowMaxPath(int row, int col, int sum)
	{
		if (row == n - 1)
			return sum;
		
		int result = -1;
		result = Math.max(computeMaxPath(row+1, col, sum + triangle[row+1][col]), computeMaxPath(row + 1, col + 1, sum + triangle[row+1][col+1]));
		
		return result;
	}
	
	/**
	 *	use memoization.
	 */
	private static int computeFastMaxPath(int row, int col)
	{
		if (row == n-1)
			return triangle[row][col];
		
		if (cache[row][col] != -1) return cache[row][col];
		
		int result = triangle[row][col] + Math.max(computeMaxPath(row+1, col), computeMaxPath(row+1, col+1));
		
		return cache[row][col] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			int[][] triangle = new int[n][];
			for (int row = 0; row < n; row++)
			{
				triangle[row] = new int[row+1];
				for (int col = 0; col <= row; col++)
					triangle[row][col] = sc.nextInt();
			}
		
			System.out.println(computeMaxPath(triangle));
		}
	}
}