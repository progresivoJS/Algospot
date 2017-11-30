import java.util.Scanner;

public class Main
{
	private static final int[][] buttons = {
		{0,1,2},
		{3,7,9,11},
		{4,10,14,15},
		{0,4,5,6,7},
		{6,7,8,10,12},
		{0,2,14,15},
		{3,14,15},
		{4,5,7,14,15},
		{1,2,3,4,5},
		{3,4,5,9,13}
	};
	
	private static int how = 0;
	
	public static int countPush(int[] clock)
	{
		return countPush(clock, 0);
	}
	
	private static boolean isAligned(int[] clock)
	{
		boolean isAligned = true;
		for (int i = 0; i < clock.length; i++)
			if (clock[i] % 12 != 0)
				isAligned= false;
		return isAligned;
	}
	
	private static int countPush(int[] clock, int toPush)
	{
		// base case.
		if (toPush == 10)
		{
			if (isAligned(clock))
				return 0;
			else
				return 987654321;
		}
		
		int result = 987654321;
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < i; j++)
				for (int k = 0; k < buttons[toPush].length; k++)
					clock[buttons[toPush][k]] += 3;
			
			result = Math.min(result, i + countPush(clock, toPush + 1));
			
			for (int j = 0; j < i; j++)
				for (int k = 0; k < buttons[toPush].length; k++)
					clock[buttons[toPush][k]] -= 3;
		}
		
		return result;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int[] clock = new int[16];
			for (int j = 0; j < 16; j++)
				clock[j] = sc.nextInt();
			int result = countPush(clock);
			if (result >= 987654321)
				System.out.println(-1);
			else
				System.out.println(result);
		}
	}
}