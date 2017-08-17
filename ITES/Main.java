import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main
{
  public int computeSection(int k, int n)
  {
		RNG rng = new RNG();
		Queue<Integer> queue = new LinkedList<>();

		int result = 1983;
		queue.add(result);

		int count = 0;
		int i = 0;
		while (i <= n)
		{
			if (result > k)
				result -= queue.remove();
			else if (result < k)
			{
				int target = rng.next();
				result += target;
				queue.add(target);
				i++;
			}
			else
			{
				count++;
				result -= queue.remove();
			}
		}
	
		return count;
  }
	
	public int slightMoreFasterComputeSection(int k, int n)
	{
		RNG rng = new RNG();
		Queue<Integer> range = new LinkedList<>();

		int count = 0;
		int rangeSum = 1983;
		range.add(1983);

		for (int i = 0; i < n; i++)
		{
			int newSignal = rng.next();
			rangeSum += newSignal;
			range.add(newSignal);

			while (rangeSum > k)
				rangeSum -= range.remove();
			if (rangeSum == k)
				count++;
		}
		return count;
	}
  
  private class RNG
  {
		private long seed;
		private final long M = 0x100000000L;
	
		public RNG()
		{
			seed = 1983;
		}

		public int next()
		{
			long result = seed;
			result = (result * 214013 + 2531011) % M;
			seed = result;
			return (int)(result % 10000 + 1);
		}
  }
  
  public static void main(String[] args)
  {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int k = sc.nextInt();
			int n = sc.nextInt();
			Main ites = new Main();
			System.out.println(ites.computeSection(k, n));
		}
  }
}