import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	private static double[][] cache;
	private static int wellDepth;
	private static int deadline;
	private static double climeProb(int n, int m)
	{
		wellDepth = n;
		deadline = m;
		cache = new double[n+3][n+3]; // cache row is n+3, but col is not exatly n+3, but to scope with the case such as n is very large, but m is small.
		for (double[] doubles : cache)
			Arrays.fill(doubles, -1);
		return compute(0, 0);
	}
	
	private static double compute(int climed, int days)
	{
		if (climed >= wellDepth)
			if (days <= deadline)
				return 1;
			else
				return 0;
		if (cache[climed][days] != -1)
			return cache[climed][days];
		return cache[climed][days] = compute(climed + 2, days + 1) * 0.75 + compute(climed + 1, days + 1) * 0.25;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			System.out.printf("%.10f\n", climeProb(n, m));
		}
	}
}