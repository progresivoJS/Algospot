import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
public class Main
{
	private static int[][] triangle;
	private static int n;
	private static int[][] maxCache;
	private static int[][] pathCache;
	public static int findTriPathCount(int[][] tri)
	{
		triangle = tri;
		n = triangle.length;
		maxCache = new int[n][];
		pathCache = new int[n][];
		for (int i = 0; i < n; i++)
		{
			maxCache[i] = new int[i+1];
			Arrays.fill(maxCache[i], -1);
			
			pathCache[i] = new int[i+1];
			Arrays.fill(pathCache[i], -1);
		}
		
		return findTriPathCount(0, 0);
	}
	
	private static int findTriPathCount(int row, int col)
	{
		if (row == n-1)
			return 1;
		if (pathCache[row][col] != -1)
			return pathCache[row][col];
		int result = 0;
		if (findMaxPath(row+1, col) >= findMaxPath(row+1, col+1))
			result += findTriPathCount(row+1, col);
		if (findMaxPath(row+1, col) <= findMaxPath(row+1, col+1))
			result += findTriPathCount(row+1, col+1);
		return pathCache[row][col] = result;
	}
	
	private static int findMaxPath(int row, int col)
	{
		if (row == n-1)
			return triangle[row][col];
		if (maxCache[row][col] != -1)
			return maxCache[row][col];
		int result = -1;
		result = Math.max(result, triangle[row][col] + findMaxPath(row+1, col));
		result = Math.max(result, triangle[row][col] + findMaxPath(row+1, col+1));
		return maxCache[row][col] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			int[][] triangle = new int[n][];
			for (int j = 0; j < triangle.length; j++)
			{
				triangle[j] = new int[j+1];
				for (int k = 0; k < triangle[j].length; k++)
					triangle[j][k] = sc.nextInt();
			}
			System.out.println(findTriPathCount(triangle));
		}
	}
}