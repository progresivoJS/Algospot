import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	private static int[][] cache;
	private static int M = 10000000;
	public static int computePoly(int n)
	{
		cache = new int[n+1][n+1];
		for (int[] ints : cache)
			Arrays.fill(ints, -1);
		int result = 0;
		for (int i = 1; i <= n; i++)
			result += computePoly(n, i) % M;
		return result % M;
	}
	
	private static int computePoly(int n, int first)
	{
		if (n == first)
			return 1;
		if (cache[n][first] != -1)
			return cache[n][first];
		int result = 0;
		for (int second = 1; second <= n-first; second++)
			result += (first + second - 1) * computePoly(n-first, second) % M;
		return cache[n][first] = result % M;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			System.out.println(computePoly(n));
		}
	}
}