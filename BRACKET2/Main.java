import java.util.Scanner;
import java.util.Stack;

public class Main
{
  public static boolean isLegal(String s)
  {
		char[] characters = s.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < characters.length; i++)
		{
			if (characters[i] =='(' || characters[i] == '{' || characters[i] == '[')
				stack.push(characters[i]);
			else
			{
				if (stack.isEmpty()) return false;
				char top = stack.pop();
				if (characters[i] == ')' && top != '(')
					return false;
				else if (characters[i] == '}' && top != '{')
					return false;
				else if (characters[i] == ']' && top != '[')
					return false;
			}
		}
		if (!stack.isEmpty()) return false;
		return true;
  }
  
  public static void main(String[] args)
  {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < test; i++)
		{
			String s = sc.nextLine();
			if (isLegal(s))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
  }
}