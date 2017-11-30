import java.util.Scanner;
public class Main
{
	private static int n;
	private static boolean[][] areFriends;
	private static boolean[] taken;
	
	public static int countPairs(int number, int[] pairs)
	{
	  	n = number;
		areFriends = new boolean[n][n];
	  	taken = new boolean[n];
		for (int i = 0; i < pairs.length-1; i += 2)
		{
			areFriends[pairs[i]][pairs[i+1]] = true;
			areFriends[pairs[i+1]][pairs[i]] = true;
		}
		return countPairs(taken);
	}
	
	private static int countPairs(boolean[] taken)
	{
		int firstFree = -1;
		for (int i = 0; i < taken.length; i++)
			if (!taken[i])
			{
				firstFree = i;
				break;
			}
		if (firstFree == -1) return 1;
		
		int result = 0;
		for (int pairWith = firstFree; pairWith < n; pairWith++)
		{
			if (areFriends[firstFree][pairWith] && !taken[pairWith])
			{
				taken[firstFree] = taken[pairWith] = true;
				result += countPairs(taken);
				taken[firstFree] = taken[pairWith] = false;
			}
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int caseNumber = sc.nextInt();
		for (int i = 0; i < caseNumber; i++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[] pairs = new int[m * 2];
			for (int j = 0; j < m * 2; j++)
				pairs[j] = sc.nextInt();
			System.out.println(countPairs(n, pairs));
		}
	}
}