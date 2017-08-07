import java.util.Scanner;
public class Main
{
	private static int[] h;
	public static int computeArea(int[] heights)
	{
		h = heights;
		return computeArea(0, h.length-1);
	}
	
	private static int computeArea(int left, int right)
	{
		if (left == right) return h[left];
		
		int mid = left + (right - left) / 2;
		int area = -1;
		area = Math.max(computeArea(left, mid), computeArea(mid+1, right));
		
		int lo = mid;
		int hi = mid + 1;
		int minHeight = Math.min(h[lo], h[hi]);
		area = Math.max(area, minHeight * (hi - lo + 1));
		
		while (lo > left || hi < right)
		{
			if (lo > left && ((hi == right || h[lo-1] > h[hi+1])))
			{
				lo--;
				minHeight = Math.min(minHeight, h[lo]);
			}
			else
			{
				hi++;
				minHeight = Math.min(minHeight, h[hi]);
			}
			area = Math.max(area, minHeight * (hi - lo + 1));
		}
		
		return area;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			int[] heights = new int[n];
			for (int j = 0; j < n; j++)
				heights[j] = sc.nextInt();
			System.out.println(computeArea(heights));
		}
	}
}