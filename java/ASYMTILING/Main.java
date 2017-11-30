import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	private static int n;
	private static int[] totalCache;
	private static final int M = 1000000007;
	public static int countAsymTiling(int number)
	{
		n = number;
		totalCache = new int[n+1];
		Arrays.fill(totalCache, -1);
		return (countTotalTiling(n) - countSymTiling(n) + M) % M;
	}
	
	private static int countSymTiling(int width)
	{
		if (width % 2 == 1)
			return countTotalTiling(width/2) % M;
		int result = 0;
		result += countTotalTiling(width/2) % M;
		result += countTotalTiling(width/2 - 1) % M;
		return result % M;
	}
	
	private static int countTotalTiling(int width)
	{
		if (width <= 1)
			return 1;
		if (totalCache[width] != -1)
			return totalCache[width];
		return totalCache[width] = (countTotalTiling(width-1) + countTotalTiling(width-2)) % M;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			System.out.println(countAsymTiling(n));
		}
	}
}