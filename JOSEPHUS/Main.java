import java.util.Scanner;
import java.util.LinkedList;

public class Main
{
  private static int n, k;
  public static LinkedList<Integer> findSafeSeat(int n, int k)
  {
		Main.n = n;
		Main.k = k;
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		for (int i = 0; i < Main.n; i++)
			linkedList.add(i+1);
		linkedList.remove(0);
		return findSafeSeat(linkedList, 0);
  }
  
  private static LinkedList<Integer> findSafeSeat(LinkedList<Integer> linkedList, int current)
  {
		if (linkedList.size() == 2)
			return linkedList;
		int target = (current + k-1) % linkedList.size();
		linkedList.remove(target);
		return findSafeSeat(linkedList, target);
  }
  
  public static void main(String[] args)
  {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			int k = sc.nextInt();
			for (Integer seat : findSafeSeat(n, k))
				System.out.print(seat + " ");
			System.out.println();
		}
  }
}