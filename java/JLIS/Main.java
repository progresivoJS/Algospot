import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
public class Main
{
	private static int[] sequenceA, sequenceB;
	private static int m, n;
	private static int[][] cache;
	public static int findJLIS(int[] seqA, int[] seqB)
	{
		sequenceA = seqA;
		sequenceB = seqB;
		m = sequenceA.length;
		n = sequenceB.length;
		cache = new int[m+1][n+1];
		for (int[] ints : cache)
			Arrays.fill(ints, -1);
		return findJLIS(-1, -1) - 2;
	}
	
	private static int findJLIS(int indexA, int indexB)
	{
		if (indexA == m-1 && indexB == n-1)
			return 2;
		int cacheIndexA = indexA + 1;
		int cacheIndexB = indexB + 1;
		if (cache[cacheIndexA][cacheIndexB] != -1)
			return cache[cacheIndexA][cacheIndexB];
		int result = 2;
		Long maxElement = Math.max(indexA == -1 ? Long.MIN_VALUE : sequenceA[indexA], indexB == -1 ? Long.MIN_VALUE : sequenceB[indexB]);
		for (int next = indexA + 1; next < m; next++)
			if (maxElement < sequenceA[next])
				result = Math.max(result, 1 + findJLIS(next, indexB));
		for (int next = indexB + 1; next < n; next++)
			if (maxElement < sequenceB[next])
				result = Math.max(result, 1 + findJLIS(indexA, next));
		return cache[cacheIndexA][cacheIndexB] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		for (int i = 0 ; i < test; i++)
		{
			int m = sc.nextInt();
			int n = sc.nextInt();
			int[] sequenceA = new int[m];
			int[] sequenceB = new int[n];
			for (int j = 0; j < m; j++)
				sequenceA[j] = sc.nextInt();
			for (int j = 0; j < n; j++)
				sequenceB[j] = sc.nextInt();
			System.out.println(findJLIS(sequenceA, sequenceB));
		}
	}
}