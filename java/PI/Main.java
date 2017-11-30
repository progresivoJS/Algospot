import java.util.Scanner;
public class Main
{
	private static String sequence;
	private static int[] cache;
	
	public static int memorize(String seq)
	{
		sequence = seq;
		cache = new int[sequence.length()];
		for (int i = 0; i < cache.length; i++)
			cache[i] = -1;
		return memorize(0);
	}
	
	private static int memorize(int index)
	{
		if (index == sequence.length()) return 0;
		
		if (cache[index] != -1) return cache[index];
		
		int result = 987654321;
		for (int chunk = 3; chunk <= 5; chunk++)
		{
			if (index + chunk <= sequence.length())
				result = Math.min(result, memorize(index + chunk) + classify(sequence.substring(index, index + chunk)));
		}
		
		return cache[index] = result;
	}
	
	private static int classify(String seq)
	{
		char[] characters = seq.toCharArray();
		int[] numbers = new int[characters.length];
		for (int i = 0; i < characters.length; i++)
			numbers[i] = characters[i] - '0';
		
		boolean areAllSame = true;
		for (int i = 0; i < numbers.length-1; i++)
			if (numbers[i] != numbers[i+1])
				areAllSame = false;
		if (areAllSame) return 1;
		
		boolean areProgressive = true;
		int dif = numbers[1] - numbers[0];
		for (int i = 1; i < numbers.length-1; i++)
		{
			if (Math.abs(dif) != 1)
			{
				areProgressive = false;
				break;
			}
			else if (numbers[i+1] - numbers[i] != dif)
			{
				areProgressive = false;
				break;
			}
		}		
		if (areProgressive) return 2;
		
		boolean areAlternating = true;
		for (int i = 0; i < numbers.length-2; i++)
			if (numbers[i] != numbers[i+2])
				areAlternating = false;
		if (areAlternating) return 4;
		
		boolean areEqualDifferent = true;
		dif = numbers[1] - numbers[0];
		for (int i = 0; i < numbers.length-1; i++)
			if (numbers[i+1] - numbers[i] != dif)
				areEqualDifferent = false;
		if (areEqualDifferent) return 5;
		
		return 10;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < test; i++)
		{
			String sequence = sc.nextLine();
			System.out.println(memorize(sequence));
		}
	}
}