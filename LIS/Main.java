import java.util.Scanner;
import java.io.File;
public class Main
{
	private static int[] sequence;
	private static int[] cache;
	private static int n;
	public static int findLIS(int[] seq)
	{
		sequence = seq;
		n = sequence.length;
		cache = new int[n];
		for (int i = 0; i < n; i++)
			cache[i] = -1;
		int max = -1;
		for (int i = 0; i < n; i++)
			max = Math.max(max, findLIS(i));
		
		return max;
	}
	
	private static int findLIS(int start)
	{
		if (start == n-1) return 1;
		if (cache[start] != -1) return cache[start];
		
		int result = 1;
		for (int next = start + 1; next < n; next++)
			if (sequence[next] > sequence[start])
				result = Math.max(result, 1 + findLIS(next));
		return cache[start] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			int[] sequence = new int[n];
			for (int m = 0; m < sequence.length; m++)
				sequence[m] = sc.nextInt();
			System.out.println(findLIS(sequence));
		}
	}
}
