import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
public class Main
{
	private static int n;
	private static int[] cache;
	private static final int M = 1000000007;
	public static int findTiling(int number)
	{
		n = number;
		cache = new int[n+1];
		Arrays.fill(cache, -1);
		return computeTiling(n);
	}
	
	private static int computeTiling(int n)
	{
		if (n <= 1) return 1;
		if (cache[n] != -1) return cache[n];
		
		return cache[n] = (computeTiling(n-1) + computeTiling(n-2)) % M;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			System.out.println(findTiling(n));
		}
	}
}